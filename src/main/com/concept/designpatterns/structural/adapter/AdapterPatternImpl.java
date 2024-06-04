package src.main.com.concept.designpatterns.structural.adapter;

public class AdapterPatternImpl {
    public static void main(String[] args) {
        // Create an instance of the new API
        ExternalWeatherService externalService = new ExternalWeatherService();

        // Create an adapter for the new API
        WeatherService weatherServiceAdapter = new WeatherServiceAdapter(externalService);

        // Use the adapter to fetch weather data
        String weather = weatherServiceAdapter.getWeather();
        System.out.println(weather);
    }
}

interface WeatherService {
    String getWeather();
}

// ExternalWeatherService representing the new API with a different interface
class ExternalWeatherService {
    String fetchWeatherData() {
        // Logic to fetch weather data from the new API
        return "Sunny";
    }
}

// Adapter class that implements the WeatherService interface
class WeatherServiceAdapter implements WeatherService {
    private ExternalWeatherService externalService;

    public WeatherServiceAdapter(ExternalWeatherService externalService) {
        this.externalService = externalService;
    }

    @Override
    public String getWeather() {
        // Convert the response from the new API to match the expected format
        String weatherData = externalService.fetchWeatherData();
        return "Today's weather is: " + weatherData;
    }
}