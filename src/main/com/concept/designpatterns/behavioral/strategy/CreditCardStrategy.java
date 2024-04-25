package src.main.com.concept.designpatterns.behavioral.strategy;

public class CreditCardStrategy implements PaymentStrategy {
    //Add some card details instance variables like card number, cvv, name, type, etc
    @Override
    public void pay(int amount) {
        System.out.println(amount + " payed by credit/debit card");
    }
}
