package cn.wan.owl.dto.price.activity;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

public class GoodsActivityStrategyFactory {
    @Autowired
    private Map<String,GoodActivity> goodsActivityMap;
    /**
     * 根据活动类型 获取所对应的策略
     * @param activityType
     */
    public GoodsActivityContext getGoodsActivityStrategy(String activityType){

        GoodsActivityContext goodsActivityContext;

        switch (activityType){
            case "discount":
                goodsActivityContext = new GoodsActivityContext(goodsActivityMap.get("discountActivity"));
                break;
            case "fullReduce":
                goodsActivityContext = new GoodsActivityContext(goodsActivityMap.get("fullReduceActivity"));
                break;
            case "integral":
                goodsActivityContext = new GoodsActivityContext(goodsActivityMap.get("integralActivity"));
                break;
            default:
                goodsActivityContext = null;
        }

        return goodsActivityContext;
    }
}
