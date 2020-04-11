package cn.wan.owl.dto;

import cn.wan.owl.model.Cart;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
public class PayhelperDto {
    private int flag;
    private int id ;
    private List<Cart> carts =new ArrayList<Cart>();
    private Cart cart =new Cart();
    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
        setFlag(1);
    }

    public List<Cart> getCarts() {
        return carts;

    }

    public void setCarts(List<Cart> carts) {
        this.carts = carts;
        setFlag(2);
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(Cart cart) {
        this.cart = cart;
        setFlag(3);
    }


}