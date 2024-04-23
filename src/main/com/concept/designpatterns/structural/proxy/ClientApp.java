package src.main.com.concept.designpatterns.structural.proxy;

public class ClientApp {
    public static void main(String[] args) {
        try {
            CommandExecutor executor = new CommandExecutorProxy("ad", "ad");
            executor.executeCommand("ls");
            executor.executeCommand("rm abc");
        } catch (RuntimeException re){
            System.out.println(re.getMessage());
        }
    }
}
