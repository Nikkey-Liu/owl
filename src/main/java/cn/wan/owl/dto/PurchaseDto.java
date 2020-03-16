package cn.wan.owl.dto;

import cn.wan.owl.model.Product;
import cn.wan.owl.model.Purchase;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PurchaseDto {

    private Integer userId;

    private Integer productId;

    /**
     * 买入总额
     */
    private Double rental;

    /**
     * 单位净值
     */
    private Double nav;

    /**
     * 购入份数
     */
    private Double quantity;

    /**
     * 确认总额
     */
    private Double confirmRental;

    /**
     * 手续费
     */
    private Double poundage;

    private Date buyTime;

    private Date confirmTime;

    private String flags;

    private String productName;

    public static PurchaseDto fromPo(Purchase po) {
        return PurchaseDto.builder()
                .userId(po.getUserId())
                .productId(po.getProductId())
                .rental(po.getRental())
                .nav(po.getNav())
                .quantity(po.getQuantity())
                .confirmRental(po.getConfirmRental())
                .poundage(po.getPoundage())
                .buyTime(po.getBuyTime())
                .confirmTime(po.getConfirmTime())
                .flags(po.getFlags())
                .build();
    }

    public PurchaseDto fromProduct(Product product) {
        this.setProductName(product.getName());
        return this;
    }

}
