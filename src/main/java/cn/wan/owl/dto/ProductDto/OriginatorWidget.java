package cn.wan.owl.dto.ProductDto;

public class OriginatorWidget implements ProductOriginator {
    private String value = "";

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        String [] arrOfStr = (value.split("#"));
        System.out.println("Originator: Setting state to " + arrOfStr[0]);
        this.value = value;
    }

    @Override
    public Memento createMemento() {
        WidgetMemento m= new WidgetMemento();
        m.setState(new String(value));
        System.out.println("Originator: Creating Memento.");
        return m;
    }

    @Override
    public void restore(Memento m) {
        WidgetMemento wm =(WidgetMemento) m;
        this.value=(wm.getState());
    }
}
