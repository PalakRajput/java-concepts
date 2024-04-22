package src.main.com.concept.designpatterns.structural.bridge;

public class ClientApp {
    public static void main(String[] args) {
        Shape circle = new Circle(new RedColor());
        Shape rectangle  = new Rectangle(new BlueColor());

        circle.applyColor();
        rectangle.applyColor();
    }
}
