package src.main.com.concept.designpatterns.structural.bridge;

public abstract class Shape {
    Color color;

    protected Shape(Color c){
        this.color = c;
    }

    abstract void applyColor();
}
