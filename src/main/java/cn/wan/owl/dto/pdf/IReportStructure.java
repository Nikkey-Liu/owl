package cn.wan.owl.dto.pdf;

import com.itextpdf.text.pdf.PdfPTable;

public interface IReportStructure {
    String getHeader();
    String getFooter();
    PdfPTable getBody(PdfPTable table);
}
