package cn.wan.owl.dto.pdf;

public class ReportCreator  extends AbstractReportCreator {
    private static ReportCreator instance;

    private ReportCreator()
    {

    }

    public static AbstractReportCreator getReportFactory()
    {
        if(instance == null)
        {
            instance = new ReportCreator();//
        }
        return instance;
    }

    @Override
    public IReport reportCreator( String type ) throws ReportTypeIsNotImplemented
    {
        IReport report = null; // which ever class implementing IReport can return a concrete product
        if(type.equals( "PDF" ))
        {
            report = new PDFReport();
        }
        else if(type.equals( "XL" ))
        {
            report = new ExcelReport();// excel
        }
        else
        {
            throw new ReportTypeIsNotImplemented();
        }
        return report;
    }
}
