package cn.wan.owl.mapper;

import cn.wan.owl.model.NProduct;

import java.util.List;

public interface NProductMapper {
    int deleteByPrimaryKey(Integer productid);

    int insert(NProduct record);

    int insertSelective(NProduct record);
    List<NProduct> selectProductByUserId(Integer userid);
    NProduct selectByPrimaryKey(Integer productid);
    NProduct  selectProductByMaxId();
    int updateByPrimaryKeySelective(NProduct record);
    List<NProduct> selectAllProductOnsale(String productstate);
    int updateByPrimaryKey(NProduct record);
}