package src.main.com.concept.designpatterns.behavioral.strategy;

import java.util.List;

public class ShoppingCart {
    private List<Item> items;

    public ShoppingCart(List<Item> items) {
        this.items = items;
    }

    private int calculateTotal(){
        return items.stream().map(Item::getPrice).reduce(0, Integer::sum);
    }

    public void makePayment(PaymentStrategy paymentStrategy){
        int total = calculateTotal();
        paymentStrategy.pay(total);
    }
}

class Item{
    int price;
    String code;

    public Item(int price, String code) {
        this.price = price;
        this.code = code;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
