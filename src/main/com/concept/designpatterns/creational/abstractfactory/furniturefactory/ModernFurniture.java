package src.main.com.concept.designpatterns.creational.abstractfactory.furniturefactory;

import src.main.com.concept.designpatterns.creational.abstractfactory.chair.Chair;
import src.main.com.concept.designpatterns.creational.abstractfactory.chair.ModernChair;
import src.main.com.concept.designpatterns.creational.abstractfactory.table.ModernTable;
import src.main.com.concept.designpatterns.creational.abstractfactory.table.Table;

public class ModernFurniture implements FurnitureFactory {
    @Override
    public Chair getChair() {
        return new ModernChair("Blue", "Modern");
    }

    @Override
    public Table getTable() {
        return new ModernTable("Brown", "Modern");
    }
}
