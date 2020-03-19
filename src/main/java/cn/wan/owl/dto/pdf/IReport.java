package cn.wan.owl.dto.pdf;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface IReport {
    void generateReport(Object reportObject, HttpServletResponse response) throws IOException;
    void emailDocument(StringBuilder builder);
}
