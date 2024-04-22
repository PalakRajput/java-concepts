package src.main.com.concept.designpatterns.creational.abstractfactory.furniturefactory;

import src.main.com.concept.designpatterns.creational.abstractfactory.chair.Chair;
import src.main.com.concept.designpatterns.creational.abstractfactory.chair.SimpleChair;
import src.main.com.concept.designpatterns.creational.abstractfactory.table.SimpleTable;
import src.main.com.concept.designpatterns.creational.abstractfactory.table.Table;

public class SimpleFurniture implements FurnitureFactory {
    @Override
    public Chair getChair() {
        return new SimpleChair("Black", "Simple");
    }

    @Override
    public Table getTable() {
        return new SimpleTable("Black", "Simple");
    }
}
