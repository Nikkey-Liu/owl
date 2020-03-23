package cn.wan.owl.dto.price.activity;

import java.math.BigDecimal;

public class FullReduceActivity implements GoodActivity{
    //满多少
    private BigDecimal fullAmount;

    //减多少
    private BigDecimal reductionAmount ;
    @Override
    public BigDecimal getPayPrive(BigDecimal amount) {
        fullAmount = new BigDecimal(300);

        reductionAmount  = new BigDecimal(100);

        return amount.compareTo(fullAmount)>=0?amount.subtract(reductionAmount):amount;
    }
}
