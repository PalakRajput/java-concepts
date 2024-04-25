package src.main.com.concept.designpatterns.behavioral.strategy;

public class UPIStrategy implements PaymentStrategy {
    //Add some UPI instance variables like upi id, name, etc
    @Override
    public void pay(int amount) {
        System.out.println(amount + " payed for UPI.");
    }
}
