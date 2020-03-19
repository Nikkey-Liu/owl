package cn.wan.owl.dto.pdf;

import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

public class PDFBuilder {
    public static PdfPTable getCompleteTable(IReportStructure structure, PDFTable table)
    {
        PdfPCell header = new PdfPCell(new Phrase(structure.getHeader().toString()));
        header.setRowspan(table.getColumns());
        header.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getpTable().addCell(header); // adding header

        table.getpTable().addCell(structure.getBody(new PdfPTable(1))); // this is the body

        PdfPCell footer = new PdfPCell(new Phrase(structure.getFooter().toString()));
        footer.setRowspan(table.getColumns());
        table.getpTable().addCell(footer); // this is the footer
        return table.getpTable();
    }

}
