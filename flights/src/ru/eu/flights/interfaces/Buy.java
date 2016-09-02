package ru.eu.flights.interfaces;


import ru.eu.flights.objects.Flight;
import ru.eu.flights.objects.Passenger;
import ru.eu.flights.objects.Reservation;
import ru.eu.flights.objects.spr.Place;

public interface Buy {

    boolean buyTicket(Passenger passenger, Flight flight, Place place, String addInfo);

}
