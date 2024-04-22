package src.main.com.concept.designpatterns.structural.facade;

import java.util.List;

public class ExcelReportGenerator implements ReportGenerator {

    @Override
    public void generateReport(List<String> products) {
        System.out.println("Excel");
    }
}
