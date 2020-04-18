package cn.wan.owl.dto.calculator;

public interface ItemElement {
    public int accept(ShoppingCartVisitor visitor);
}
