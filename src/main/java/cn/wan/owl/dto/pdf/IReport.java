package cn.wan.owl.dto.pdf;

import com.itextpdf.text.pdf.PdfPTable;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public interface IReport {
    ByteArrayOutputStream generateReport(PdfPTable reportObject) ;
    void emailDocument(StringBuilder builder);
}
