package cn.wan.owl.dto.price.activity;

import java.math.BigDecimal;

public class DiscountActivity implements GoodActivity{ //折扣
    private double discount ;

    @Override
    public BigDecimal getPayPrive(BigDecimal amount) {
        //get
        discount = 8.0;

        return new BigDecimal(discount).multiply(amount).divide(new BigDecimal(10));
    }
}
