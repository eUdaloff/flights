package ru.eu.flights.database;

import ru.eu.flights.database.abstracts.AbstractObjectDB;
import ru.eu.flights.objects.spr.Place;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PlaceDB extends AbstractObjectDB<Place> {

    private static PlaceDB instance;

    private PlaceDB() {
    }

    public static PlaceDB getInstance() {
        if (instance == null)
            instance = new PlaceDB();
        return instance;
    }

    @Override
    public Place parseObject(ResultSet rs) throws SQLException {
        Place p = new Place();
        p.setId(rs.getLong("id"));
        p.setSeatLetter(rs.getString("seat_letter").charAt(0));
        p.setSeatNumber(rs.getInt("seat_number"));

        try {
            p.setBusy(getBooleanFrom(rs.getInt("busy")));
        } catch (Exception e) {
            p.setBusy(false);
        }

        p.setFlightClass(FlightClassDB.getInstance().executeObject(FlightClassDB.getInstance().getObjectByIdStmt(rs.getLong("flight_class_id"))));
        return p;
    }

    private boolean getBooleanFrom(int busy) {
        return busy > 0 ? true : false;
    }

    @Override
    public PreparedStatement getObjectByIdStmt(long id) throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT p.id, p.seat_letter, p.seat_number, p.flight_class_id FROM avia.spr_place AS p WHERE p.id = ?");
        stmt.setLong(1, id);
        return stmt;
    }

    @Override
    public PreparedStatement getAllObjectsStmt() throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT p.id, p.seat_letter, p.seat_number, p.flight_class_id FROM avia.spr_place AS p");
        return stmt;
    }

    public PreparedStatement getPlacesByAircraftStmt(long aircraftId) throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT p.id, p.seat_letter, p.seat_number, p.flight_class_id FROM avia.spr_place AS p " +
                "WHERE p.id IN (SELECT ap.place_id FROM avia.spr_aircraft_place AS ap WHERE ap.aircraft_id = ?) " +
                "ORDER BY p.flight_class_id, p.seat_letter");
        stmt.setLong(1, aircraftId);
        return stmt;
    }

    public PreparedStatement getPlacesByFlightClassStmt(long flightClassId) throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT p.id, p.seat_letter, p.seat_number, p.flight_class_id FROM avia.spr_place AS p WHERE p.flight_class_id = ?");
        stmt.setLong(1, flightClassId);
        return stmt;
    }

    public PreparedStatement getPlacesByAircraftAndFlightBusyStmt(long aircraftId, long flightId) throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT sp.id, sp.seat_letter, sp.seat_number, sp.flight_class_id, " +
                "if((SELECT r.id FROM avia.reservation AS r WHERE r.flight_id = ? AND r.place_id = sp.id) > 0, 1, 0) AS busy " +
                "FROM avia.spr_place AS sp " +
                "WHERE sp.id IN (SELECT ap.id FROM avia.spr_aircraft_place AS ap WHERE ap.aircraft_id = ?)");
        stmt.setLong(1, flightId);
        stmt.setLong(2, aircraftId);
        return stmt;
    }
}
