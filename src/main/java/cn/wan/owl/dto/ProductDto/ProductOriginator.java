package cn.wan.owl.dto.ProductDto;

public interface ProductOriginator {
    public void restore(Memento m);

    public Memento createMemento();
}
