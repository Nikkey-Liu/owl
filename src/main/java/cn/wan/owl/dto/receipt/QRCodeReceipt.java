package cn.wan.owl.dto.receipt;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class QRCodeReceipt  extends Receipt {
    public QRCodeReceipt(Printing printing, Map<String,String> data)
    {
        super(printing);
        this.data = data;

    }

    @Override
    public void print()
    {
        printReceiptToQRCode(data);// calling from the implementor methods
    }

}
