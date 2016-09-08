package ru.eu.flights.database;


import ru.eu.flights.database.abstracts.AbstractObjectDB;
import ru.eu.flights.objects.Reservation;
import ru.eu.flights.utils.GMTCalendar;

import java.sql.*;
import java.util.Calendar;

public class ReservationDB extends AbstractObjectDB<Reservation> {

    private static ReservationDB instance;

    private ReservationDB() {
    }

    public static ReservationDB getInstance() {
        if (instance == null)
            instance = new ReservationDB();
        return instance;
    }

    @Override
    public Reservation parseObject(ResultSet rs) throws SQLException {
        Reservation r = new Reservation();
        r.setId(rs.getLong("id"));
        r.setFlight(FlightDB.getInstance().executeObject(FlightDB.getInstance().getObjectByIdStmt(rs.getLong("flight_id"))));
        r.setPassenger(PassengerDB.getInstance().executeObject(PassengerDB.getInstance().getObjectByIdStmt(rs.getLong("passenger_id"))));
        r.setPlace(PlaceDB.getInstance().executeObject(PlaceDB.getInstance().getObjectByIdStmt(rs.getLong("place_id"))));
        r.setAddInfo(rs.getString("add_info"));
        Calendar c = GMTCalendar.getInstance();
        c.setTimeInMillis(rs.getLong("reserve_datetime"));
        r.setReserveDateTime(c);
        r.setCode(rs.getString("code"));
        return r;
    }

    @Override
    public PreparedStatement getObjectByIdStmt(long id) throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT r.id, r.flight_id, r.passenger_id, r.place_id, r.add_info, r.reserve_datetime, r.code " +
                "FROM avia.reservation AS r WHERE id=?");
        stmt.setLong(1, id);
        return stmt;
    }

    @Override
    public PreparedStatement getAllObjectsStmt() throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT r.id, r.flight_id, r.passenger_id, r.place_id, r.add_info, r.reserve_datetime, r.code " +
                "FROM avia.reservation AS r");
        return stmt;
    }

    public PreparedStatement getInsertStmt(Reservation r) throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement(
                "INSERT INTO reservation(flight_id, passenger_id, place_id, add_info, reserve_datetime, code) VALUES (?,?,?,?,?,?)",
                Statement.RETURN_GENERATED_KEYS);
        stmt.setLong(1, r.getFlight().getId());
        stmt.setLong(2, r.getPassenger().getId());
        stmt.setLong(3, r.getPlace().getId());
        stmt.setString(4, r.getAddInfo());
        stmt.setLong(5, r.getReserveDateTime().getTimeInMillis());
        stmt.setString(6, r.getCode());
        return stmt;
    }

    public PreparedStatement getStmtByCode(String code) throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT r.id, r.flight_id, r.passenger_id, r.place_id, r.add_info, r.reserve_datetime, r.code " +
                "FROM avia.reservation AS r WHERE r.code = ?");
        stmt.setString(1, code);
        return stmt;
    }

    public PreparedStatement getStmtByDateReserv(Calendar dateReserv) throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT r.id, r.flight_id, r.passenger_id, r.place_id, r.add_info, r.reserve_datetime, r.code " +
                "FROM avia.reservation AS r WHERE r.reserve_datetime >= ? AND r.reserve_datetime < ?");

        clearTime(dateReserv);

        Calendar dateReservInterval = (Calendar) dateReserv.clone();
        dateReservInterval.add(Calendar.DATE, INTERVAL);
        stmt.setLong(1, dateReserv.getTimeInMillis());
        stmt.setLong(2, dateReservInterval.getTimeInMillis());
        return stmt;
    }

    public PreparedStatement getStmtByDocumentNumber(String docNum) throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT r.id, r.flight_id, r.passenger_id, r.place_id, r.add_info, r.reserve_datetime, r.code " +
                "FROM avia.reservation AS r INNER JOIN passenger AS p ON r.passenger_id = p.id " +
                "WHERE p.document_number = ?");
        stmt.setString(1, docNum);
        return stmt;
    }

    public PreparedStatement getStmtByFamilyName(String familyName) throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("SELECT r.id, r.flight_id, r.passenger_id, r.place_id, r.add_info, r.reserve_datetime, r.code " +
                "FROM avia.reservation AS r INNER JOIN passenger AS p ON r.passenger_id = p.id " +
                "WHERE p.family_name = ?");
        stmt.setString(1, familyName);
        return stmt;
    }
}