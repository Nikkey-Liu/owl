package cn.wan.owl.mapper;

import cn.wan.owl.model.Cart;

import java.util.List;

public interface CartMapper {
    int deleteByPrimaryKey(Integer cartid);

    int insert(Cart record);
    List<Cart> selectByUserId(Integer userid);
    int insertSelective(Cart record);

    Cart selectByPrimaryKey(Integer cartid);

    int updateByPrimaryKeySelective(Cart record);

    int updateByPrimaryKey(Cart record);
}