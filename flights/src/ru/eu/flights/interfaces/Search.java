package ru.eu.flights.interfaces;


import ru.eu.flights.objects.Flight;
import ru.eu.flights.objects.spr.City;

import java.util.Date;
import java.util.List;
import java.util.Set;

public interface Search {

    Set<Flight> searchFlights(long date, City cityFrom, City cityTo, int placeCount);

    Set<City> getAllCities();

}
