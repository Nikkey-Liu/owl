package cn.wan.owl.service;

import cn.wan.owl.mapper.NProductMapper;
import cn.wan.owl.model.NProduct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class NProductService {
    @Autowired
    NProductMapper nProductMapper;
    public List<NProduct> viewProductsbyUserId(int userId){
        return nProductMapper.selectProductByUserId(userId);
    }
    public int addProduct(NProduct nProduct)
    {
      return   nProductMapper.insertSelective(nProduct);
    }

    public int removeProductByid(int productid)
    {
        return nProductMapper.deleteByPrimaryKey(productid);
    }
    public int editProduct(NProduct nProduct)
    {
    return nProductMapper.updateByPrimaryKeySelective(nProduct);
    }
}