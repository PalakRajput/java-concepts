package src.main.com.concept.designpatterns.behavioral.template;

public abstract class Network {
    String username;
    String password;

    Network(){}

    public void postMessage(String message){
        if(checkCreds(username, password)){
            sendData(message.getBytes());
            logout();
        }
    }

    protected abstract boolean logout();

    protected abstract void sendData(byte[] bytes);

    protected abstract boolean checkCreds(String username, String password);
}
