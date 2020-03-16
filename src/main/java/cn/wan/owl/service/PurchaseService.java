package cn.wan.owl.service;

import cn.wan.owl.dto.PurchaseCreateDto;
import cn.wan.owl.dto.PurchaseDto;
import cn.wan.owl.dto.PurchaseQueryDto;
import cn.wan.owl.mapper.PurchaseMapper;
import cn.wan.owl.model.Product;
import cn.wan.owl.model.Purchase;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @Author: wanhongji
 * @Date: 2020/2/8
 */
@Slf4j
@Service
public class PurchaseService {

    @Autowired
    private PurchaseMapper purchaseMapper;

    @Autowired
    private ProductService productService;

    public List<PurchaseDto> list(PurchaseQueryDto queryDto) {
        List<Purchase> purchases = purchaseMapper.listByQueryDto(queryDto);
        if (CollectionUtils.isEmpty(purchases)) {
            return Collections.emptyList();
        }
        List<Integer> productIdList = purchases.stream().map(Purchase::getProductId).collect(Collectors.toList());
        Map<Integer, Product> productMap =
                productService.list(productIdList).stream().collect(Collectors.toMap(Product::getId, Function.identity()));

        List<PurchaseDto> dtoList = purchases.stream().map(PurchaseDto::fromPo).collect(Collectors.toList());
        dtoList.forEach(dto -> dto.fromProduct(productMap.get(dto.getProductId())));
        return dtoList;
    }

    public void add(PurchaseCreateDto dto) {
        if (dto != null) {
            insert(dto.toPo());
        }
    }

    private void insert(Purchase record) {
        if (record == null) {
            return;
        }
        purchaseMapper.insertSelective(record);
    }

    public void update(Purchase record) {
        if (record == null) {
            return;
        }
        purchaseMapper.updateByPrimaryKeySelective(record);
    }

    public void delete(int id) {
        purchaseMapper.deleteByPrimaryKey(id);
    }
}
