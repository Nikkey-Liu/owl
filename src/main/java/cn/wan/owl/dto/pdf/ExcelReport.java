package cn.wan.owl.dto.pdf;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ExcelReport implements IReport {
    @Override
    public void generateReport(Object reportObject, HttpServletResponse response) throws IOException {
        System.out.println( "Code for generating Excel report" );// excel
    }

    @Override
    public void emailDocument(StringBuilder builder) {
        System.out.println( "Code for emailing Excel report" );
    }
}
