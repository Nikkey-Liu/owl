package cn.wan.owl.dto.price.activity;

import java.math.BigDecimal;

public class GoodsActivityContext2 {
    private GoodActivity goodsActivity;

    public GoodActivity getGoodsActivity() {
        return goodsActivity;
    }

    public void setGoodsActivity(GoodActivity goodsActivity) {
        this.goodsActivity = goodsActivity;
    }

    /**
     * Get the final payment price
     * @param amount
     * @return
     */
    BigDecimal getLastPrice(BigDecimal amount){
        return goodsActivity.getPayPrive(amount);
    }

}
