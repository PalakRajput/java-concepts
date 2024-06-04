package src.main.com.concept.designpatterns.behavioral.command;

public class CommandPattern {
    public static void main(String[] args) {
        Light light = new Light();
        ConcCommand1 c1 = new ConcCommand1(light);
        ConcCommand2 c2 = new ConcCommand2(light);

        RemoteControl remoteControl = new RemoteControl();
        remoteControl.setCommand(c1);
        remoteControl.pressButton();

        remoteControl.setCommand(c2);
        remoteControl.pressButton();
    }
}

class Light {
    public void switchOn() {
        System.out.println("Light is on");
    }

    public void switchOff() {
        System.out.println("Light is off");
    }
}

class RemoteControl {
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

    public void pressButton() {
        command.execute();
    }
}

interface Command {
    void execute();
}

class ConcCommand1 implements Command {

    Light light;

    public ConcCommand1(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.switchOn();
    }
}

class ConcCommand2 implements Command {

    Light light;

    public ConcCommand2(Light light) {
        this.light = light;
    }

    @Override
    public void execute() {
        light.switchOff();
    }
}