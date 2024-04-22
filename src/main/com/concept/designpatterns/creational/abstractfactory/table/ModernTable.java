package src.main.com.concept.designpatterns.creational.abstractfactory.table;

public class ModernTable implements Table {
    private String colour;
    private String style;


    public ModernTable(String colour, String style){
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
