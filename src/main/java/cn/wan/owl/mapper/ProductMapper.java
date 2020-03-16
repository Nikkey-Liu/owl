package cn.wan.owl.mapper;

import cn.wan.owl.model.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    List<Product> listByKeyword(String keyword);

    List<Product> listByIds(@Param("idList") List<Integer> idList);
}