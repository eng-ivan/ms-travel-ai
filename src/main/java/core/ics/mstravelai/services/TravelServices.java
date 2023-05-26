package core.ics.mstravelai.services;

public interface TravelServices {

    String itinerary(String destination, String startDate, String endDate);

    String weather(String startDate, String destination);

    String violence(String destination);

    String bestWay(String origin, String destination);

}
