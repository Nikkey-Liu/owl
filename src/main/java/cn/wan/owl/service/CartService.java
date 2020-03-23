package cn.wan.owl.service;

import cn.wan.owl.mapper.CartMapper;
import cn.wan.owl.model.Cart;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;
import java.util.List;

@Slf4j
@Service
public class CartService {
    @Autowired
    CartMapper cartMapper;
    public List <Cart> selectCartbyUserID(int userid){
        return cartMapper.selectByUserId(userid);
    }
    public int addCart(Cart cart){
        return cartMapper.insertSelective(cart);
    }
    public int deletCartbyid(int id)
    {
        return cartMapper.deleteByPrimaryKey(id);
    }
    public void deletCartbyuserid(int id)
    {
        List<Cart> carts= cartMapper.selectByUserId(id);
        for (Cart cart:carts)
        {
            cartMapper.deleteByPrimaryKey(cart.getCartid());
        }

    }

}
