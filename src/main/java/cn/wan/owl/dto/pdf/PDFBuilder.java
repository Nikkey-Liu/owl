package cn.wan.owl.dto.pdf;

import cn.wan.owl.model.NProduct;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PDFBuilder {
    public static PdfPTable getCompleteTable(MyPDFTable table , NProduct nProduct)
    { IReportStructureImpl structure = new IReportStructureImpl();
        System.out.println("getCompleteTable1 ");
        System.out.println(nProduct);
        PdfPCell header = new PdfPCell(new Phrase(structure.buildHeader().toString()));
        header.setRowspan(table.getColumns());
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getpTable().addCell(header); // adding header
        System.out.println("getCompleteTable 2" +table);
        table.getpTable().addCell(structure.buildBody(nProduct,new PdfPTable(1) )); // this is the body
        System.out.println("getCompleteTable 3" +table);
        PdfPCell footer = new PdfPCell(new Phrase(structure.buildFooter().toString()));
        System.out.println("getCompleteTable 4"+table);
        footer.setRowspan(table.getColumns());
        table.getpTable().addCell(footer); // this is the footer
        return table.getpTable();
    }

}
