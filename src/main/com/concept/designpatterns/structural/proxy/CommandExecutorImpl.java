package src.main.com.concept.designpatterns.structural.proxy;

public class CommandExecutorImpl implements CommandExecutor {

    @Override
    public void executeCommand(String command) {
        System.out.println(command + " executed.");
    }
}
