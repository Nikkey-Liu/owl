package cn.wan.owl.dto.pdf;

import cn.wan.owl.model.NProduct;
import com.itextpdf.text.pdf.PdfPTable;

public interface IReportStructure {
    String getHeader();
    String getFooter();
    PdfPTable getBody(NProduct nProduct, PdfPTable table);
}
