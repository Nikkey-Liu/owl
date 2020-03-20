package cn.wan.owl.dto;

import cn.wan.owl.model.NProduct;
import cn.wan.owl.model.Product;
import cn.wan.owl.util.Constantvalue;
import cn.wan.owl.util.UserUtil;
import lombok.Data;

import java.util.Date;


@Data
public class NProductCreateDto {

    private Integer productid;

    private String productname;

    private String productdescription;

    private Integer quantity;

    private Integer price;

    private Integer userid;

//    private String productstate;


    public NProduct toPo() {
        return new NProduct( productid , productname, productdescription, quantity, price, UserUtil.getCurrentUser().getUserid(), Constantvalue.UNAPPROVE
        );
    }
}
