package cn.wan.owl.dto;

import cn.wan.owl.model.Cart;
import cn.wan.owl.model.NProduct;
import cn.wan.owl.util.Constantvalue;
import cn.wan.owl.model.MyOrder;
import cn.wan.owl.util.UserUtil;
import lombok.Data;
import org.springframework.core.annotation.Order;


@Data
public class OrderDto {
    public MyOrder getOrderByCart(Cart cart){
        MyOrder  order= new MyOrder();
        order.setPrice(cart.getPrice());
        order.setPaymentstate(Constantvalue.DONE);
        order.setQuantity(1);
        order.setUserid(cart.getUserid());
        order.setProductname(cart.getProductname());
        order.setProductid(cart.getProductid());
        return order;
    }
    public MyOrder getOrderByPublic(NProduct nProduct){
        MyOrder order= new MyOrder();
        order.setProductid(nProduct.getProductid());
        order.setProductname(nProduct.getProductname());
        order.setUserid(UserUtil.getCurrentUser().getUserid());
        order.setPaymentstate(Constantvalue.DONE);
        order.setQuantity(1);
        order.setPrice(nProduct.getPrice());
        return order;
    }
}
