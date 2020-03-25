package cn.wan.owl.dto;

import cn.wan.owl.model.Cart;
import cn.wan.owl.model.NProduct;
import cn.wan.owl.util.UserUtil;
import lombok.Data;
import lombok.experimental.var;

import java.util.List;

@Data
public  class CartDto
{

public Cart setCart(NProduct nProduct){
    Cart cart= new Cart();
    cart.setPrice(nProduct.getPrice());
    cart.setQuantity(1);
    cart.setProductid(nProduct.getProductid());
    cart.setProductname(nProduct.getProductname());
    cart.setUserid(UserUtil.getCurrentUser().getUserid());
    return cart;
}

}
