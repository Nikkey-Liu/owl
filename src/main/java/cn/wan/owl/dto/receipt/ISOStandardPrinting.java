package cn.wan.owl.dto.receipt;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ISOStandardPrinting extends Printing
{

    @Override
    public void printReceiptToQRCode(Map<String,String> data )
    {
        ISOInvoicePrinter.printISOReceiptWithQRCode( data);
    }

    @Override
    public void printReceiptWithBarcode( Map<String,String> data )
    {
        ISOInvoicePrinter.printISOReceiptWithBarcode( data);
    }

}
