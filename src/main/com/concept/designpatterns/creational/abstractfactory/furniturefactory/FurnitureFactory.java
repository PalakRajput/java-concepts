package src.main.com.concept.designpatterns.creational.abstractfactory.furniturefactory;

import src.main.com.concept.designpatterns.creational.abstractfactory.chair.Chair;
import src.main.com.concept.designpatterns.creational.abstractfactory.table.Table;

public interface FurnitureFactory {
    Chair getChair();
    Table getTable();
}
