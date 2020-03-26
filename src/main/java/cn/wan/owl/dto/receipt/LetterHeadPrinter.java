package cn.wan.owl.dto.receipt;

import cn.wan.owl.dto.pdf.IReportStructure;
import cn.wan.owl.dto.pdf.PDFStructure;
import cn.wan.owl.dto.pdf.ReportCreator;
import cn.wan.owl.dto.pdf.ReportService;
import cn.wan.owl.model.NProduct;
import com.itextpdf.text.Element;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class LetterHeadPrinter {
    public static void printDocumentWithQRCode(Map<String,String> data)
    {
        System.out.println("LetterHead Printer With QRCode :"+data);
    }

    public static void printDocumentWithBarcode(Map<String,String> data)
    {
        System.out.println("LetterHead Printer With Barcode :"+data);
    }
}
