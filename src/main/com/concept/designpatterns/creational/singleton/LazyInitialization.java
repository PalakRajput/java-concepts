package src.main.com.concept.designpatterns.creational.singleton;

public class LazyInitialization {
    private static LazyInitialization instance;

    private LazyInitialization(){}

    //Works fine for single threaded application.
    public static LazyInitialization getInstance(){
        if(instance == null){
            instance = new LazyInitialization();
        }
        return instance;
    }
}
