package src.main.com.concept.designpatterns.structural.bridge;

public class Rectangle extends Shape {

    public Rectangle(Color c){
        super(c);
    }


    @Override
    void applyColor() {
        System.out.print("Rectangle is ");
        color.applyColour();
    }
}
