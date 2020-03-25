package cn.wan.owl.controller;

import cn.wan.owl.dto.CartDto;
import cn.wan.owl.dto.IdDto;
import cn.wan.owl.mapper.CartMapper;
import cn.wan.owl.model.Cart;
import cn.wan.owl.model.CommonResponse;
import cn.wan.owl.model.NProduct;
import cn.wan.owl.service.CartService;
import cn.wan.owl.service.NProductService;
import cn.wan.owl.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    CartService cartService;
    @Autowired
    NProductService nProductService;
    @GetMapping("/list/cart")
    public Object list(){
       int userid= UserUtil.getCurrentUser().getUserid();
        return CommonResponse.success(cartService.selectCartbyUserID(userid));
    }
    @PostMapping("/add")
    public Object add(@RequestBody IdDto idDto){
        NProduct nProduct=nProductService.selectProductbyid(idDto.getid());
        nProduct.setQuantity(nProduct.getQuantity()-1);
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
