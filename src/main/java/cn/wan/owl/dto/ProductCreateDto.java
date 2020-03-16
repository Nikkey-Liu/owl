package cn.wan.owl.dto;

import cn.wan.owl.model.Product;
import lombok.Data;

import java.util.Date;


@Data
public class ProductCreateDto {

    private String kind;

    private String code;

    private String name;

    private Double poundage;

    private String remark;

    public Product toPo() {
        return new Product(
                0,
                kind, code, name, poundage, remark, new Date(), new Date(), 0
        );
    }
}
