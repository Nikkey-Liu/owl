package cn.wan.owl.dto.receipt;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class LetterHeadPrinting extends Printing
{
    @Override
    public void printReceiptToQRCode(Map<String,String> data )
    {
        LetterHeadPrinter.printDocumentWithQRCode( data );
    }

    @Override
    public void printReceiptWithBarcode( Map<String,String> data)
    {
        LetterHeadPrinter.printDocumentWithBarcode( data     );
    }
}

