package ru.eu.flights.interfaces.sei;


import ru.eu.flights.objects.Flight;
import ru.eu.flights.objects.Passenger;
import ru.eu.flights.objects.Reservation;
import ru.eu.flights.objects.spr.City;
import ru.eu.flights.objects.spr.Place;
import ru.eu.flights.ws.exceptions.ArgumentException;

import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.xml.ws.soap.MTOM;
import java.util.Set;

@MTOM
@WebService(name = "FlightWS", targetNamespace = "http://flights.eu.ru/ws")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL)
public interface FlightSEI {

    boolean buyTicket(Passenger passenger, Flight flight, Place place, String addInfo) throws ArgumentException;

    Reservation checkReservationByCode(String code) throws ArgumentException;

    Set<Flight> searchFlights(Long date, City cityFrom, City cityTo, Integer placeCount) throws ArgumentException;

    Set<City> getAllCities();

}
