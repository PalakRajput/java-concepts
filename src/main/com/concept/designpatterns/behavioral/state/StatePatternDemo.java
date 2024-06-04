package src.main.com.concept.designpatterns.behavioral.state;

/**
 * Each state is represented by a different state object
 */
interface TrafficLightState {
    void handleState(TrafficLight context);
}

// Concrete states
class RedLightState implements TrafficLightState {
    public void handleState(TrafficLight context) {
        System.out.println("Red Light: Stop");
        // Transition to the next state
        context.setState(new GreenLightState());
    }
}

class GreenLightState implements TrafficLightState {
    public void handleState(TrafficLight context) {
        System.out.println("Green Light: Go");
        // Transition to the next state
        context.setState(new YellowLightState());
    }
}

class YellowLightState implements TrafficLightState {
    public void handleState(TrafficLight context) {
        System.out.println("Yellow Light: Prepare to Stop");
        // Transition to the next state
        context.setState(new RedLightState());
    }
}

// Context
class TrafficLight {
    private TrafficLightState currentState;

    public TrafficLight() {
        // Initial state
        currentState = new RedLightState();
    }

    public void setState(TrafficLightState state) {
        currentState = state;
    }

    public void changeState() {
        currentState.handleState(this);
    }
}

// Client code
public class StatePatternDemo {
    public static void main(String[] args) {
        TrafficLight trafficLight = new TrafficLight();

        // Simulate traffic light changes
        trafficLight.changeState(); // Output: Red Light: Stop
        trafficLight.changeState(); // Output: Green Light: Go
        trafficLight.changeState(); // Output: Yellow Light: Prepare to Stop
        trafficLight.changeState(); // Output: Red Light: Stop
    }
}
