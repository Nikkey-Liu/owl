package cn.wan.owl.dto.pdf;

import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.Document;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;

public class PDFReport implements IReport{
    @Override
    public ByteArrayOutputStream generateReport(PdfPTable table )
    {
        // this is the place we really create PDF files and dispatch to the browser
        ByteArrayOutputStream  out=new ByteArrayOutputStream();
        Document document = null;
        try
        {
            System.out.println("generate document" +table);
            document = new Document();
            PdfWriter.getInstance(document, out);
            document.open();
            document.add(table);
            document.close();
            System.out.println("generate finished");
        } catch (Exception de) {
            System.out.println(" errowssssssssssssssssssssssssss");
            de.printStackTrace();
        }
        return out;
    }

    @Override
    public void emailDocument( StringBuilder builder )
    {
        // TODO Auto-generated method stub
    }
}
