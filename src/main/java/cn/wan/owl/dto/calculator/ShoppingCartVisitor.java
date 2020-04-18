package cn.wan.owl.dto.calculator;


import cn.wan.owl.model.NProduct;
import cn.wan.owl.model.Product;

import java.awt.print.Book;

public interface ShoppingCartVisitor {
    int visit(Product product);
    int visit(NProduct nProduct);
}
