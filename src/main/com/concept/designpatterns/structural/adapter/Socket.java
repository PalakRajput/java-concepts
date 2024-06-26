package src.main.com.concept.designpatterns.structural.adapter;

public class Socket {
    public Volt getVolt(){
        return new Volt(120);
    }
}

class Volt{
    int volts;

    public Volt(int volts) {
        this.volts = volts;
    }

    public int getVolts() {
        return volts;
    }

    public void setVolts(int volts) {
        this.volts = volts;
    }
}

interface SocketAdapter{
    Volt get120Volt();

    Volt get12Volt();

    Volt get3Volt();

}

class SocketAdapterImpl implements SocketAdapter{

    private Socket socket;

    public SocketAdapterImpl(Socket socket){
        this.socket = socket;
    }

    @Override
    public Volt get120Volt() {
        return socket.getVolt();
    }

    @Override
    public Volt get12Volt() {
        return new Volt(socket.getVolt().getVolts()/10);
    }

    @Override
    public Volt get3Volt() {
        return new Volt(socket.getVolt().getVolts()/40);
    }
}
