package cn.wan.owl.dto.price.activity;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@Service
public class GoodsActivityStrategyFactory {

    private Map<String,GoodActivity> goodsActivityMap = new HashMap<String,GoodActivity>();
    private GoodActivity goodActivity1=new DiscountActivity();
    private GoodActivity goodActivity2=new FullReduceActivity();
    private GoodActivity goodActivity3=new IntegralActivity();
    /**
     * 根据活动类型 获取所对应的策略
     * @param activityType
     */
    public GoodsActivityContext getGoodsActivityStrategy(String activityType){

        goodsActivityMap.put("discountActivity",goodActivity1);
        goodsActivityMap.put("fullReduceActivity",goodActivity2);
        goodsActivityMap.put("integralActivity",goodActivity3);
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
