package ru.eu.flights.database;

import ru.eu.flights.database.abstracts.AbstractObjectDB;
import ru.eu.flights.objects.Flight;
import ru.eu.flights.objects.spr.Aircraft;
import ru.eu.flights.objects.spr.City;
import ru.eu.flights.objects.spr.Place;
import ru.eu.flights.utils.GMTCalendar;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public class FlightDB extends AbstractObjectDB<Flight> {

    public static final String DAYS = " д. ";
    public static final String HOURS = " ч. ";
    public static final String MINUTES = " мин. ";

    private AircraftDB aircraftDB = AircraftDB.getInstance();
    private PlaceDB placeDB = PlaceDB.getInstance();
    private CityDB cityDB = CityDB.getInstance();

    private static FlightDB instance;

    private FlightDB() {
    }

    public static FlightDB getInstance() {
        if (instance == null)
            instance = new FlightDB();
        return instance;
    }

    @Override
    public Flight parseObject(ResultSet rs) throws SQLException {
        Calendar dateDepart = GMTCalendar.getInstance();
        Calendar dateArrive = GMTCalendar.getInstance();

        dateDepart.setTimeInMillis(rs.getLong("date_depart"));
        dateArrive.setTimeInMillis(rs.getLong("date_arrive"));

        Flight flight = new Flight();
        flight.setId(rs.getLong("id"));
        flight.setCode(rs.getString("code"));
        flight.setDateDepart(dateDepart);
        flight.setDateArrive(dateArrive);

        Aircraft aircraft = aircraftDB.executeObject(aircraftDB.getObjectByIdStmt(rs.getLong("aircraft_id")));
        flight.setAircraft(aircraft);

        Set<Place> places = placeDB.executeCollection(placeDB.getPlacesByAircraftAndFlightBusyStmt(aircraft.getId(), flight.getId()));
        aircraft.setPlaceList(places);

        Set<Place> freePlaces = new HashSet<>();
        for (Place p : places) {
            if (!p.isBusy())
                freePlaces.add(p);
        }
        aircraft.setFreePlaceList(freePlaces);

        if (freePlaces.size() > 0)
            flight.setExistFreePlaces(true);

        City from = cityDB.executeObject(cityDB.getObjectByIdStmt(rs.getLong("city_from_id")));
        flight.setCityFrom(from);

        City to = cityDB.executeObject(cityDB.getObjectByIdStmt(rs.getLong("city_to_id")));
        flight.setCityTo(to);

        int dayDiff = dateArrive.get(Calendar.DATE) - dateDepart.get(Calendar.DATE);
        int hourDiff = dateArrive.get(Calendar.HOUR_OF_DAY) - dateDepart.get(Calendar.HOUR_OF_DAY);
        int minDiff = dateArrive.get(Calendar.MINUTE) - dateDepart.get(Calendar.MINUTE);

        StringBuilder sb = new StringBuilder();
        if (dayDiff > 0)
            sb.append(dayDiff).append(DAYS);
        if (hourDiff > 0)
            sb.append(hourDiff).append(HOURS);
        if (minDiff > 0)
            sb.append(minDiff).append(MINUTES);
        flight.setDuration(sb.toString().trim());

        return flight;
    }

    @Override
    public PreparedStatement getObjectByIdStmt(long id) throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT f.id, f.code, f.date_depart, f.date_arrive, f.aircraft_id, f.city_from_id, f.city_to_id " +
                "FROM avia.flight AS f WHERE f.id = ?");
        stmt.setLong(1, id);
        return stmt;
    }

    @Override
    public PreparedStatement getAllObjectsStmt() throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT f.id, f.code, f.date_depart, f.date_arrive, f.aircraft_id, f.city_from_id, f.city_to_id " +
                "FROM avia.flight AS f");
        return stmt;
    }

    public PreparedStatement getFlightsByDateAndCitiesStmt(Calendar dateDepart, City from, City to) throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT f.id, f.code, f.date_depart, f.date_arrive, f.aircraft_id, f.city_from_id, f.city_to_id " +
                "FROM avia.flight AS f " +
                "WHERE f.date_depart >= ? AND f.date_depart < ? AND f.city_from_id = ? AND f.city_to_id = ?");

        clearTime(dateDepart);

        Calendar dateDepartInterval = (Calendar) dateDepart.clone();
        dateDepartInterval.add(Calendar.DATE, INTERVAL);

        stmt.setLong(1, dateDepart.getTimeInMillis());
        stmt.setLong(2, dateDepartInterval.getTimeInMillis());
        stmt.setLong(3, from.getId());
        stmt.setLong(4, to.getId());

        return stmt;
    }

    public PreparedStatement getFlightsByDateDepartStmt(Calendar dateDepart) throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT f.id, f.code, f.date_depart, f.date_arrive, f.aircraft_id, f.city_from_id, f.city_to_id " +
                "FROM avia.flight AS f " +
                "WHERE f.date_depart >= ? AND f.date_depart < ?");

        clearTime(dateDepart);

        Calendar dateDepartInterval = (Calendar) dateDepart.clone();
        dateDepartInterval.add(Calendar.DATE, INTERVAL);

        stmt.setLong(1, dateDepart.getTimeInMillis());
        stmt.setLong(2, dateDepartInterval.getTimeInMillis());

        return stmt;
    }
}
