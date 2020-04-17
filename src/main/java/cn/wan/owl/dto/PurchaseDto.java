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
     * Total purchase
     */
    private Double rental;

    /**
     * Net unit
     */
    private Double nav;

    /**
     * Purchases
     */
    private Double quantity;

    /**
     * Confirm the total
     */
    private Double confirmRental;

    /**
     * Handling fee
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
