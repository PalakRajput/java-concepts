package src.main.com.concept.designpatterns.creational.factorymethod;

public class Server implements Computer {
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

    public Server(int ram, int cpu) {
        this.ram = ram;
        this.cpu = cpu;
    }
}
