package cn.wan.owl.service;


import cn.wan.owl.dto.NProductCreateDto;
import cn.wan.owl.mapper.ProductMapper;
import cn.wan.owl.model.Product;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class ProductService {

    @Autowired
    private ProductMapper productMapper;

    public List<Product> list(String keyword) {
        return productMapper.listByKeyword(keyword);
    }

    public List<Product> list(List<Integer> idList) {
        return productMapper.listByIds(idList);
    }

    public void insert(Product record) {
        if (record == null) {
            return;
        }
        productMapper.insertSelective(record);
    }

    public void update(Product record) {
        if (record == null) {
            return;
        }
        productMapper.updateByPrimaryKeySelective(record);
    }

    public void delete(int id) {
        productMapper.deleteByPrimaryKey(id);
    }

    public void add(NProductCreateDto dto) {
//        insert(dto.toPo());
    }
}