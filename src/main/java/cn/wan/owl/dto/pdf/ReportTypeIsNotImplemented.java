package cn.wan.owl.dto.pdf;

public class ReportTypeIsNotImplemented extends Exception {
    @Override
    public String getMessage()
    {
        return "Report type is not implemented";
    }
}
