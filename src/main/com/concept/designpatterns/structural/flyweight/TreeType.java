package src.main.com.concept.designpatterns.structural.flyweight;

import java.awt.*;

public class TreeType {
    private String name;
    private Color color;
    private String otherDetails;

    public TreeType(String name, Color color, String otherDetails) {
        this.name = name;
        this.color = color;
        this.otherDetails = otherDetails;
    }

    public void draw(Graphics graphics, int x, int y){
        graphics.setColor(color);
        graphics.draw3DRect(x, y, 30, 15, true);
    }
}
