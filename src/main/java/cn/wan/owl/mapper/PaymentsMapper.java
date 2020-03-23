package cn.wan.owl.mapper;

import cn.wan.owl.model.Payments;

public interface PaymentsMapper {
    Payments selectBycardnumber(String cardnumber);
    int deleteByPrimaryKey(Integer cardid);

    int insert(Payments record);

    int insertSelective(Payments record);

    Payments selectByPrimaryKey(Integer cardid);

    int updateByPrimaryKeySelective(Payments record);

    int updateByPrimaryKey(Payments record);
}