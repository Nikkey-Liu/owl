package cn.wan.owl.dto.pdf;

public  abstract class AbstractReportCreator {
    public abstract IReport reportCreator(String type) throws ReportTypeIsNotImplemented;
}
