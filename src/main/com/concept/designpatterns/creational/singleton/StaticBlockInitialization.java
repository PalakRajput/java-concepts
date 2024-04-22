package src.main.com.concept.designpatterns.creational.singleton;

public class StaticBlockInitialization {

    private static final StaticBlockInitialization instance;

    static {
        instance = new StaticBlockInitialization();
        //Can write try catch for error handling
    }

    // private constructor to avoid client applications using the constructor
    private StaticBlockInitialization() {
    }

    public static StaticBlockInitialization getInstance() {
        return instance;
    }
}
