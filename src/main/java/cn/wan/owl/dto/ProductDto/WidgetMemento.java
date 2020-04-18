package cn.wan.owl.dto.ProductDto;

public class WidgetMemento implements Memento {
    private String state;

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state=state;
    }
}
