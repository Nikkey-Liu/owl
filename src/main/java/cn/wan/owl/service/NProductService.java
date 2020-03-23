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
    public NProduct selectProductByMaxId(){
        return nProductMapper.selectProductByMaxId();
    }
    public int removeProductByid(int productid)
    {
        return nProductMapper.deleteByPrimaryKey(productid);
    }
    public int editProduct(NProduct nProduct)
    {
    return nProductMapper.updateByPrimaryKeySelective(nProduct);
    }
    public List<NProduct> viewOnsaleProductByProductState(String productState){
        return nProductMapper.selectAllProductOnsale(productState);
    }
    public NProduct selectProductbyid( int id)
    {
        return nProductMapper.selectByPrimaryKey(id);
    }
    public int approveProductByTeacher(NProduct nProduct){
        return nProductMapper.updateByPrimaryKeySelective(nProduct);
    }

}
