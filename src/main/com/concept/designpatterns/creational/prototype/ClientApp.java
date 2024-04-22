package src.main.com.concept.designpatterns.creational.prototype;

public class ClientApp {
    public static void main(String[] args) {
        Shape circle = new Circle(10, "Red");
        Shape rectangle = new Rectangle(4, 5, "Green");

        Circle clonedCircle = ((Circle) circle) .clone();
        Rectangle clonedRectangle = ((Rectangle) rectangle) .clone();

        System.out.println(circle.calculateArea() == clonedCircle.calculateArea());
        System.out.println(rectangle.calculateArea() == clonedRectangle.calculateArea());
    }
}
