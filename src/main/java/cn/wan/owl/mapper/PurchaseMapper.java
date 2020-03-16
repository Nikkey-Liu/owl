package cn.wan.owl.mapper;

import cn.wan.owl.dto.PurchaseQueryDto;
import cn.wan.owl.model.Purchase;

import java.util.List;

public interface PurchaseMapper {
    int deleteByPrimaryKey(Integer id);

    int insertSelective(Purchase record);

    int updateByPrimaryKeySelective(Purchase record);

    List<Purchase> listByQueryDto(PurchaseQueryDto queryDto);
}