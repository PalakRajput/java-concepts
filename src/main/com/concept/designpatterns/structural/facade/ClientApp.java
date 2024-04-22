package src.main.com.concept.designpatterns.structural.facade;

public class ClientApp {
    public static void main(String[] args) {
        ReportGenerator csvReportGenerator = new CSVReportGenerator();
        ReportGenerator excelReportGenerator = new ExcelReportGenerator();
        ReportGeneratorFacade reportGeneratorFacade = new ReportGeneratorFacade(csvReportGenerator, excelReportGenerator);

        reportGeneratorFacade.generateCSVReport();
        reportGeneratorFacade.generateExcelReport();
    }
}
