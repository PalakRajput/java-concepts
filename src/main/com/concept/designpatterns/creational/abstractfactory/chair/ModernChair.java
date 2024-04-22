package src.main.com.concept.designpatterns.creational.abstractfactory.chair;

public class ModernChair implements Chair {
    private String colour;
    private String style;


    public ModernChair(String colour, String style){
        this.colour = colour;
        this.style = style;
    }


    @Override
    public String getColour() {
        return colour;
    }

    @Override
    public String getStyle() {
        return style;
    }
}
