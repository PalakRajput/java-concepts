package src.main.com.concept.designpatterns.structural.facade;

import java.util.List;

public class ReportGeneratorFacade {
    private final ReportGenerator csvReportGenerator;
    private final ReportGenerator excelReportGenerator;

    public ReportGeneratorFacade(ReportGenerator csvReportGenerator, ReportGenerator excelReportGenerator) {
        this.csvReportGenerator = csvReportGenerator;
        this.excelReportGenerator = excelReportGenerator;
    }

    public void generateCSVReport(){
        csvReportGenerator.generateReport(List.of("A", "B"));
    }

    public void generateExcelReport(){
        excelReportGenerator.generateReport(List.of("A", "B"));
    }
}
