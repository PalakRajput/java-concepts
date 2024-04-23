package src.main.com.concept.designpatterns.structural.proxy;

public class CommandExecutorProxy implements CommandExecutor {

    private boolean isAdmin;
    private final CommandExecutor executor;

    public CommandExecutorProxy(String user, String pwd){
        if("admin".equals(user) && "admin123".equals(pwd)) isAdmin=true;
        executor = new CommandExecutorImpl();
    }

    @Override
    public void executeCommand(String cmd) {
        if(isAdmin){
            executor.executeCommand(cmd);
        }else{
            if(cmd.trim().startsWith("rm")){
                throw new RuntimeException("rm command is not allowed for non-admin users.");
            }else{
                executor.executeCommand(cmd);
            }
        }
    }
}
