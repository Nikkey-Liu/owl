package cn.wan.owl.dto;

import cn.wan.owl.model.Purchase;
import lombok.Data;

import java.util.Date;


@Data
public class PurchaseCreateDto {

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
