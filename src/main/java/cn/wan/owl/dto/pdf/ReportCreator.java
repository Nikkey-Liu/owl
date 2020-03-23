package cn.wan.owl.dto.pdf;

public class ReportCreator  extends AbstractReportCreator {
    public static ReportCreator instance;

    public ReportCreator()
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
    public IReport reportCreator( String type )
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

        return report;
    }
}
