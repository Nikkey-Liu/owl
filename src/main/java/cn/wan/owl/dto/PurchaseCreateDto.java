package cn.wan.owl.dto;

import cn.wan.owl.model.Purchase;
import lombok.Data;

import java.util.Date;


@Data
public class PurchaseCreateDto {

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

    public Purchase toPo() {
        return new Purchase(
                0,
                productId,
                userId,
                rental,
                nav,
                quantity,
                confirmRental,
                poundage,
                buyTime,
                confirmTime,
                flags,
                new Date(),
                new Date(),
                0
        );
    }

}
