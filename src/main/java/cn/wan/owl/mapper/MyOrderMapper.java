package cn.wan.owl.mapper;

import cn.wan.owl.model.MyOrder;

import java.util.List;

public interface MyOrderMapper {
    int deleteByPrimaryKey(Integer orderid);

    int insert(MyOrder record);
   List<MyOrder> selectByUserid (Integer userid);
    int insertSelective(MyOrder record);

    MyOrder selectByPrimaryKey(Integer orderid);

    int updateByPrimaryKeySelective(MyOrder record);

    int updateByPrimaryKey(MyOrder record);
}