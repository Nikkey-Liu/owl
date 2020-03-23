package cn.wan.owl.dto.price.activity;

import java.math.BigDecimal;

public interface GoodActivity {
    BigDecimal getPayPrive(BigDecimal amount);
}
