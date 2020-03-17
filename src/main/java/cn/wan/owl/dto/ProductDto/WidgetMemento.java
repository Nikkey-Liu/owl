package cn.wan.owl.dto.ProductDto;

public class WidgetMemento implements Memento {
    private String state;
    @Override
    public String getState() {
        return state;
    }

    @Override
    public void setState(String state) {
        this.state=state;
    }
}
