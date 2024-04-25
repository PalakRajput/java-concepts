package src.main.com.concept.designpatterns.behavioral.mediator;

public class ClientApp {
    public static void main(String[] args) {
        Module module = new ReportModule();
        Module module1 = new PurchaseModule();

        ModuleMediator mediator = new ModuleMediator();
        mediator.addModule(module);
        mediator.addModule(module1);

        mediator.performAction("Inventory");
        mediator.performAction("Purchase");
    }
}
