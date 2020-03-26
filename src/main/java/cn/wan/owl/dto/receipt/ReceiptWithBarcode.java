package cn.wan.owl.dto.receipt;

import java.util.Map;

public class ReceiptWithBarcode extends Receipt{
    public ReceiptWithBarcode( Printing printing, Map<String,String> data )
    {
        super(printing);
        this.data = data;
    }

    @Override
    public void print()
    {
        printReceiptWithBarcode( data );
    }
}
