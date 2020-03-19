package cn.wan.owl.dto.pdf;

import cn.wan.owl.model.NProduct;
import cn.wan.owl.model.Product;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ReportService {private static ReportService reportService;
    private static AbstractReportCreator reportCreator;

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

    public String printDocument(IReportStructure contract, String docType, HttpServletResponse response) throws IOException
    {
        PDFTable table = new PDFTable(1, 5);
        IReport report;
        String message = "";
        try
        {
            report = reportCreator.reportCreator( docType );
            report.generateReport(PDFBuilder.getCompleteTable(contract, table), response);
        }
        catch ( ReportTypeIsNotImplemented e )
        {
            message = e.getMessage();
        }
        return message;
    }

}
