package cn.wan.owl.dto.receipt;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class ISOInvoicePrinter {
    public static void printISOReceiptWithQRCode(Map<String,String> data)
    {
    System.out.println("ISO Printer With QRCode :"+data);    // Part to implement when ISO Printing is considered for QR code
    }

    public static void printISOReceiptWithBarcode(Map<String,String> data)
    {
        System.out.println("ISO Printer With Barcode :"+data);  // Part to implement when ISO Printing is considered for Bar code
    }
}
