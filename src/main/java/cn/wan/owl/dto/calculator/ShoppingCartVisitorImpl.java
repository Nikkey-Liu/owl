package cn.wan.owl.dto.calculator;

import cn.wan.owl.model.NProduct;
import cn.wan.owl.model.Product;

public class ShoppingCartVisitorImpl implements ShoppingCartVisitor  {
    @Override
    public int visit(Product product) {
        int cost=0;
        cost = product.getPrice();
        System.out.println("active name:" +product.getName() + " cost ="+ cost);
        return cost;
    }

    @Override
    public int visit(NProduct nProduct) {
        int cost=0;
        cost = nProduct.getPrice();
        System.out.println("Procduct name:" +nProduct.getProductname() + " cost ="+ cost);
        return cost;
    }
}
