package src.main.com.concept.designpatterns.behavioral.template;

public class ClientApp {
    public static void main(String[] args) {

        int choice = 1;
        Network network;
        if(choice == 1){
            network = new Facebook("admin", "admin@123");
        } else {
            network = new Twitter("admin11", "admin@12323");
        }
        network.postMessage("Today's message");
    }
}
