package cn.wan.owl.dto.pdf;

import cn.wan.owl.model.NProduct;
import com.itextpdf.text.pdf.PdfPTable;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

public class ReportService {private static ReportService reportService;
    private static AbstractReportCreator reportCreator =new ReportCreator();

    private ReportService()
    {

    }

    public static ReportService getReportServiceInstance(AbstractReportCreator creator)
    {
        if(reportService == null)
        {
            reportService = new ReportService();
            reportCreator = creator;
        }
        return reportService;
    }

    public static void setReportCreator( AbstractReportCreator reportCreator )
    {
        ReportService.reportCreator = reportCreator;
    }

    public void generateContractReport(List<NProduct> contracts, String docType, HttpServletResponse response) throws IOException
    {
//		IReport report = createDoc( docType );
//		StringBuilder builder = new StringBuilder();
//		for(Contract contract : contracts)
//		{
//			builder.append( contract.toString() );
//			builder.append( "\n" );
//		}
//		report.generateReport( builder, response );
    }

    public ByteArrayInputStream printDocument(String docType, NProduct nProduct )
    {
        ReportCreator reportCreator =new ReportCreator();
        MyPDFTable table = new MyPDFTable(1, 5);
        IReport report;


            System.out.println("report creator processing");
            report = reportCreator.reportCreator( docType );
            System.out.println("report creator finish on processing with PDF");
        PdfPTable o =PDFBuilder.getCompleteTable(table,nProduct);
        if (o==null)
        {
            System.out.println("-=======================");
        }
            System.out.println("getCompleteTable ");



        return new ByteArrayInputStream(report.generateReport(o).toByteArray());
    }

}
