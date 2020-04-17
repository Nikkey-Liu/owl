package cn.wan.owl.dto.price.activity;

import java.math.BigDecimal;

public class GoodsActivityContext {
    private GoodActivity goodsActivity;

    public GoodsActivityContext(GoodActivity goodsActivity){
        this.goodsActivity=goodsActivity;
    }

    /**
     * Get product price
     * @param amount
     * @return
     */
    public BigDecimal getPrice(BigDecimal amount){
        return goodsActivity.getPayPrive(amount);
    }

}
