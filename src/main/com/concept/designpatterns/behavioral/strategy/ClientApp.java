package src.main.com.concept.designpatterns.behavioral.strategy;

import java.util.List;

public class ClientApp {
    public static void main(String[] args) {
        Item i1 = new Item(100, "P11");
        Item i2 = new Item(200, "G11");

        ShoppingCart shoppingCart = new ShoppingCart(List.of(i1, i2));
        shoppingCart.makePayment(new CreditCardStrategy());
//        shoppingCart.makePayment(new UPIStrategy());
    }
}
