package src.main.com.concept.designpatterns.creational.abstractfactory;

import src.main.com.concept.designpatterns.creational.abstractfactory.chair.Chair;
import src.main.com.concept.designpatterns.creational.abstractfactory.furniturefactory.FurnitureFactory;
import src.main.com.concept.designpatterns.creational.abstractfactory.furniturefactory.ModernFurniture;
import src.main.com.concept.designpatterns.creational.abstractfactory.furniturefactory.SimpleFurniture;
import src.main.com.concept.designpatterns.creational.abstractfactory.table.Table;

public class ClientApp {


    public static void main(String[] args) {
        FurnitureFactory furnitureFactory;
        String type = "Modern";

        if (type.equals("Modern")) {
            furnitureFactory = new ModernFurniture();
        } else {
            furnitureFactory = new SimpleFurniture();
        }

        Chair chair = furnitureFactory.getChair();
        Table table = furnitureFactory.getTable();

        System.out.println("Chair: " + chair.getColour() + ", " + chair.getStyle());
        System.out.println("Table: " + table.getColour() + ", " + table.getStyle());
    }
}
