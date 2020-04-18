package cn.wan.owl.dto.calculator;

import java.util.List;

public class ShoppingCartHelper {
    public   int calculatePrice(List<ItemElement> items)
    {
        ShoppingCartVisitor visitor = new ShoppingCartVisitorImpl();
        int sum=0;
        for(ItemElement item : items)
        {
            sum = sum + item.accept(visitor);
        }
        return sum;
    }
}
