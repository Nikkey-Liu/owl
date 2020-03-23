package cn.wan.owl.dto.pdf;

import com.itextpdf.text.pdf.PdfPTable;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class ExcelReport implements IReport {
    @Override
    public ByteArrayOutputStream generateReport(PdfPTable reportObject){
        System.out.println( "Code for generating Excel report" );// excel
        return null;
    }

    @Override
    public void emailDocument(StringBuilder builder) {
        System.out.println( "Code for emailing Excel report" );
    }
}
