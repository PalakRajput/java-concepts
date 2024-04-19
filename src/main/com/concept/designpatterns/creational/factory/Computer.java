package src.main.com.concept.designpatterns.creational.factory;

public interface Computer {
    int getRAM();
    int getCPU();

    default void print(){
        System.out.println("RAM: " + getRAM() + ", CPU: " + getCPU());
    }
}
