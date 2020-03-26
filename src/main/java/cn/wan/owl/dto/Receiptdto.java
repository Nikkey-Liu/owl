package cn.wan.owl.dto;

import cn.wan.owl.dto.pdf.PDFStructure;
import cn.wan.owl.dto.receipt.Printing;
import cn.wan.owl.dto.receipt.QRCodeReceipt;
import cn.wan.owl.dto.receipt.Receipt;
import cn.wan.owl.dto.receipt.ReceiptTypes;
import cn.wan.owl.model.MyOrder;
import cn.wan.owl.util.UserUtil;
import org.springframework.core.annotation.Order;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class Receiptdto {
    private static Receiptdto instance;

    private Receiptdto()
    {

    }

    public static Receiptdto getInstance()
    {
        if(instance == null)
        {
            instance = new Receiptdto();
        }
        return instance;
    }

    public static Receipt createReceipt(MyOrder order, ReceiptTypes type, Printing printing, int oldPrice)
    {
        Receipt receipt = null;
        if(type.equals( ReceiptTypes.QR_CODE_RECEIPT ))
        {
            Map<String, String> data = new HashMap<>();
            data.put( PDFStructure.BODY.name()	, "Order ID : "+order.getOrderid()+"\nbuyer ID : "+ UserUtil.getCurrentUser().getUserid()+"sale price"+oldPrice );
            data.put( PDFStructure.FOOTER.name(), "\nFINAL AGREED PRICE : "+order.getPrice());
            data.put( PDFStructure.HEAD.name()	, "RECEIPT ISSUED DATE : "+new Date().toString());
            receipt = new QRCodeReceipt( printing, data );
            // genrate the refined abstarction class which is now is the QRCodeReceipt
        }
        return receipt;
    }
}
