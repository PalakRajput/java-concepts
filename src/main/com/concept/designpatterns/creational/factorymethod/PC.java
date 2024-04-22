package src.main.com.concept.designpatterns.creational.factorymethod;

public class PC implements Computer {
    private final int ram;
    private final int cpu;

    @Override
    public int getRAM() {
        return ram;
    }

    @Override
    public int getCPU() {
        return cpu;
    }

    public PC(int ram, int cpu) {
        this.ram = ram;
        this.cpu = cpu;
    }
}
