package src.main.com.concept.designpatterns.structural.decorator;

interface Pizza {

    String getDesc();

    double getPrice();


}

class BasePizza implements Pizza {

    @Override
    public String getDesc() {
        return "Base Pizza";
    }

    @Override
    public double getPrice() {
        return 60;
    }
}

abstract class PizzaDecorator implements Pizza {
    Pizza pizza;

    protected PizzaDecorator(Pizza pizza) {
        this.pizza = pizza;
    }

    @Override
    public String getDesc() {
        return pizza.getDesc();
    }

    @Override
    public double getPrice() {
        return pizza.getPrice();
    }

}

class Margherita extends PizzaDecorator {


    Margherita(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDesc() {
        return super.getDesc() + ": Margherita";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + 30;
    }

}

class FarmHouse extends PizzaDecorator {


    FarmHouse(Pizza pizza) {
        super(pizza);
    }

    @Override
    public String getDesc() {
        return super.getDesc() + ": FarmHouse";
    }

    @Override
    public double getPrice() {
        return pizza.getPrice() + 50;
    }
}

public class DecoratorImpl {

    public static void main(String[] args) {
        Pizza pizza = new BasePizza();
        Pizza margherita = new Margherita(pizza);
        Pizza farmhouse = new FarmHouse(new Margherita(new BasePizza()));
        System.out.println(pizza.getDesc() + " price: " + pizza.getPrice());
        System.out.println(margherita.getDesc() + " price: " + margherita.getPrice());
        System.out.println(farmhouse.getDesc() + " price: " + farmhouse.getPrice());
    }
}
