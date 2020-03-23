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
     * 获取最后的支付价格
     * @param amount
     * @return
     */
    BigDecimal getLastPrice(BigDecimal amount){
        return goodsActivity.getPayPrive(amount);
    }

}
