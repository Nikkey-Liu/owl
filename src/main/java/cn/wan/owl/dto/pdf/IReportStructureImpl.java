package cn.wan.owl.dto.pdf;

import cn.wan.owl.dto.pdf.IReportStructure;
import cn.wan.owl.model.NProduct;
import cn.wan.owl.model.NUser;
import cn.wan.owl.service.NUserService;
import cn.wan.owl.service.NUserServiceImpl;
import cn.wan.owl.util.UserUtil;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

public class IReportStructureImpl implements IReportStructure {
  @Autowired
    NUserService nUserServiceimpl;
    @Override
    public String buildFooter() {
        return "Approve Date : "+new Date().toString();
    }
    @Override
    public String buildHeader() {
        return "Product Approve File";
    }
    @Override
   public PdfPTable buildBody(NProduct nProduct, PdfPTable table)
    {
        System.out.println(nProduct.getUserid());
        Integer userid= nProduct.getUserid();

        table.addCell(new PdfPCell(new Phrase("ProductId :  "+ nProduct.getProductid())));
        table.addCell(new PdfPCell(new Phrase("Product Name : "+nProduct.getProductname())));
        table.addCell(new PdfPCell(new Phrase("Product Description : "+nProduct.getProductdescription())));
        table.addCell(new PdfPCell(new Phrase("Product Quantity :" +nProduct.getQuantity() )));
        table.addCell(new PdfPCell(new Phrase("Procuct Price :  "+ nProduct.getPrice())));
        table.addCell(new PdfPCell(new Phrase("Product Provider : "+nProduct.getUserid())));
        table.addCell(new PdfPCell(new Phrase("Product Approved by  : "+ UserUtil.getCurrentUser().getUsername())));
        table.addCell(new PdfPCell(new Phrase("Approved Constraints :\n"+" >  This is a new product which could be bought on this website.")));
        return table;
    }
}
