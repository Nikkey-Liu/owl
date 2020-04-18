package cn.wan.owl.controller;

import cn.wan.owl.dto.CartDto;
import cn.wan.owl.dto.IdDto;
import cn.wan.owl.dto.calculator.ItemElement;
import cn.wan.owl.dto.calculator.ShoppingCartHelper;
import cn.wan.owl.mapper.CartMapper;
import cn.wan.owl.model.Cart;
import cn.wan.owl.model.CommonResponse;
import cn.wan.owl.model.NProduct;
import cn.wan.owl.model.Product;
import cn.wan.owl.service.CartService;
import cn.wan.owl.service.NProductService;
import cn.wan.owl.util.Constantvalue;
import cn.wan.owl.util.UserUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    ShoppingCartHelper shoppingCartHelper=new ShoppingCartHelper();
    @Autowired
    CartService cartService;
    @Autowired
    NProductService nProductService;
    @GetMapping("/list/cart")
    public Object list(HttpSession session){
        List<ItemElement> items =new ArrayList<ItemElement>();
        int userid= UserUtil.getCurrentUser().getUserid();
        List<Cart> carts=cartService.selectCartbyUserID(userid);
        String msg="";
        if (carts==null)
        {
            msg="Deliver price: 20"+ "  Total cost : 0\n" +"Cart is empty";
        }
        Product deliver= new Product();
        deliver.setName("deliver goods");
        deliver.setPrice(20);
        items.add(deliver);
        for(Cart cart: carts)
        {
            NProduct n= new NProduct();
            n.setProductname(cart.getProductname());
            n.setPrice(cart.getPrice());
            items.add(n);
        }
        int totalCost=shoppingCartHelper.calculatePrice(items);


        msg="Deliver price: 20"+ "Total cost: "+ totalCost ;
        session.setAttribute("total",msg);
        return CommonResponse.success(carts);
    }
    @PostMapping("/add")
    public Object add(@RequestBody IdDto idDto){
        NProduct nProduct=nProductService.selectProductbyid(idDto.getid());
        nProduct.setQuantity(nProduct.getQuantity()-1);
        if (nProduct.getQuantity()==0)
        {
            nProduct.setProductstate(Constantvalue.SALEOUT);
        }

        nProductService.editProduct(nProduct);
        CartDto cartDto=new CartDto();
        cartService.addCart(cartDto.setCart(nProduct));
        return CommonResponse.success();
    }
    @PostMapping("/delete")
    public Object delete(@RequestBody IdDto id) {

        NProduct nProduct=nProductService.selectProductbyid(cartService.selectCartByID(id.getid()).getProductid());
        nProduct.setQuantity(nProduct.getQuantity()+1);
        nProductService.editProduct(nProduct);
        cartService.deletCartbyid(id.getid());
        return CommonResponse.success();
    }


}
