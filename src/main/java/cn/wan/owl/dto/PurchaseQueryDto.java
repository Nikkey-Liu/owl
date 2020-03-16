package cn.wan.owl.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;

@Data
@NoArgsConstructor
public class PurchaseQueryDto {

    private int userId;

    private String orderBy = "c_buy_time";

    private String orderType = "desc";

    public PurchaseQueryDto(int userId, String orderBy, String orderType) {
        this.userId = userId;
        if (StringUtils.isNotBlank(orderBy)) {
            this.orderBy = orderBy;
        }
        if (StringUtils.isNotBlank(orderType)) {
            this.orderType = orderType;
        }
    }
}
