package ru.eu.flights.interfaces.impls;

import ru.eu.flights.database.CityDB;
import ru.eu.flights.database.FlightDB;
import ru.eu.flights.interfaces.Search;
import ru.eu.flights.objects.Flight;
import ru.eu.flights.objects.spr.City;
import ru.eu.flights.utils.GMTCalendar;

import javax.jws.WebService;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SearchImpl implements Search {

    FlightDB flightDB = FlightDB.getInstance();
    CityDB cityDB = CityDB.getInstance();

    @Override
    public Set<Flight> searchFlights(long date, City cityFrom, City cityTo, int placeCount) {
        Calendar cal = GMTCalendar.getInstance();
        cal.setTimeInMillis(date);
        Set<Flight> flights = new HashSet<>();
        try {
            flights.addAll(flightDB.executeCollection(flightDB.getFlightsByDateAndCitiesStmt(cal, cityFrom, cityTo)));
        } catch (SQLException e) {
            Logger.getLogger(SearchImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return flights;
    }

    @Override
    public Set<City> getAllCities() {
        Set<City> cities = new HashSet<>();
        try {
            cities.addAll(cityDB.executeCollection(cityDB.getAllObjectsStmt()));
        } catch (SQLException e) {
            Logger.getLogger(SearchImpl.class.getName()).log(Level.SEVERE, null, e);
        }
        return cities;
    }
}
