package src.main.com.concept.designpatterns.creational.prototype;

public class Rectangle implements Shape {
    private double width;
    private double height;
    private String colour;

    @Override
    public double calculateArea() {
        return height * width;
    }

    public Rectangle(double width, double height, String colour) {
        this.width = width;
        this.height = height;
        this.colour = colour;
    }

    public Rectangle(){}

    @Override
    public String getColour() {
        return colour;
    }

    @Override
    public Rectangle clone() {
        try {
            Rectangle clone = (Rectangle) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
