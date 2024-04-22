package src.main.com.concept.designpatterns.structural.bridge;

public class Circle extends Shape {
    public Circle(Color c) {
        super(c);
    }

    @Override
    void applyColor() {
        System.out.print("Circle is ");
        color.applyColour();
    }
}
