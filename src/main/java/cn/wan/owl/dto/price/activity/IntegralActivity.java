package cn.wan.owl.dto.price.activity;

import java.math.BigDecimal;

public class IntegralActivity implements GoodActivity{
    private int integral = 1000;
    @Override
    public BigDecimal getPayPrive(BigDecimal amount) {
        return amount.subtract(new BigDecimal(integral/10));
    }
}
