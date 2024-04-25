package src.main.com.concept.designpatterns.behavioral.template;

import java.util.Objects;

public class Facebook extends Network {

    public Facebook(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    protected boolean logout() {
        System.out.println(username + " logged out successfully from facebook.");
        return true;
    }

    @Override
    protected void sendData(byte[] bytes) {
        System.out.println(new String(bytes) + " from facebook.");
    }

    @Override
    protected boolean checkCreds(String username, String password) {
        //DoSomeValidation
        return Objects.equals(username, "admin") && password.equals("admin@123");
    }
}
