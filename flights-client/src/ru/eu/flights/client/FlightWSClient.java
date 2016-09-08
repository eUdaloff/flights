package ru.eu.flights.client;


import ru.eu.flights.client.generated.*;
import ru.eu.flights.object.ExtCity;

import javax.xml.ws.AsyncHandler;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Future;

public class FlightWSClient {

    private static FlightWSClient instance;

    private FlightWebService flightService;
    private FlightWS port;

    private FlightWSClient() {
        flightService = new FlightWebService();
        port = flightService.getFlightWSPort();
    }

    public static FlightWSClient getInstance() {
        if (instance == null)
            instance = new FlightWSClient();
        return instance;
    }

    public List<City> getAllCities() {
        List<City> cities = new ArrayList<>();
        for (City c : port.getAllCities()) {
            ExtCity extCity = new ExtCity();
            extCity.setId(c.getId());
            extCity.setCode(c.getCode());
            extCity.setCountry(c.getCountry());
            extCity.setDesc(c.getDesc());
            extCity.setName(c.getName());
            cities.add(extCity);
        }
        Collections.sort(cities, new Comparator<City>() {
            @Override
            public int compare(City c1, City c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });
        return cities;
    }

    public List<Flight> searchFlights(long date, City from, City to, int placeCount) throws InvalidArgumentMN {
        List<Flight> flights = new ArrayList<>();
        flights.addAll(port.searchFlights(date, from, to, placeCount));
        return flights;
    }

    public Future<?> searchFlightsAsyncCallback(long date, City from, City to, int placeCount, AsyncHandler<SearchFlightsResponse> handler) {
        return port.searchFlightsAsync(date, from, to, placeCount, handler);
    }

    public boolean buyTicket(Flight flight, Place place, Passenger passenger, String addInfo) throws InvalidArgumentMN {
        return port.buyTicket(passenger, flight, place, addInfo);
    }

    public Future<?> buyTicketAsyncCallback(Flight flight, Place place, Passenger passenger, String addInfo, AsyncHandler<BuyTicketResponse> handler) {
        return port.buyTicketAsync(passenger, flight, place, addInfo, handler);
    }

    public Reservation checkReservationByCode(String code) throws InvalidArgumentMN {
        return port.checkReservationByCode(code);
    }

    public Future<?> checkReservationByCodeAsyncCallback(String code, AsyncHandler<CheckReservationByCodeResponse> handler) {
        return port.checkReservationByCodeAsync(code, handler);
    }
}
