package cn.wan.owl.dto.pdf;

import cn.wan.owl.model.NProduct;
import com.itextpdf.text.pdf.PdfPTable;

public interface IReportStructure {
    String buildHeader();
    String buildFooter();
    PdfPTable buildBody(NProduct nProduct, PdfPTable table);
}
