package cn.wan.owl.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ProductHandler {
    private Stack mementoStack, originatorStack;
    private OriginatorWidget ow;
    List<String> products;

    public ProductHandler() {
        mementoStack = new Stack();
        originatorStack = new Stack();
    }

    public void setWidget(OriginatorWidget ow) {
        this.ow = ow;
    }

    public void undoOperation() {

        if(originatorStack.size() != 0)
        {
            ProductOriginator ow = (ProductOriginator) originatorStack.pop();
            Memento poppingItem = (Memento) mementoStack.pop();
            ow.restore(poppingItem);
        }

    }

    public List<String> getProductList() {
        products =new ArrayList<String>();
        for (Object item: mementoStack) {
            String [] arrOfStr = ((WidgetMemento)item).getState().split("#");
            products.add(arrOfStr[0]);

        }

        System.out.println( "Current Products List" + products );

        return products;
    }

    @SuppressWarnings("unchecked")
    public void setWidgetValue(String value) {
        ow.setValue(value);
        originatorStack.push(ow);
        mementoStack.push(ow.createMemento());
    }

    public String getWidgetValue() {
        return ow.getValue();
    }
}
