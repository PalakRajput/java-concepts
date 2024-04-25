package src.main.com.concept.designpatterns.behavioral.template;

import java.util.Objects;

public class Twitter extends Network {

    public Twitter(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected boolean logout() {
        System.out.println(username + " logged out successfully from twitter.");
        return true;
    }

    @Override
    protected void sendData(byte[] bytes) {
        System.out.println(new String(bytes) + " from twitter.");
    }

    @Override
    protected boolean checkCreds(String username, String password) {
        //DoSomeValidation
        return Objects.equals(username, "admin11") && password.equals("admin@12323");
    }
}
