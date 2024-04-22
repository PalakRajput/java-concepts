package src.main.com.concept.designpatterns.creational.prototype;

public class Circle implements Shape {
    private double radius;
    private String colour;

    public Circle(int radius, String colour){
        this.radius = radius;
        this.colour = colour;
    }

    public Circle(){}

    @Override
    public double calculateArea() {
        return (double) 22/7 * radius * radius;
    }

    @Override
    public String getColour() {
        return colour;
    }


    @Override
    public Circle clone() {
        try {
            Circle clone = (Circle) super.clone();
            // TODO: copy mutable state here, so the clone can't change the internals of the original
            return clone;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}
