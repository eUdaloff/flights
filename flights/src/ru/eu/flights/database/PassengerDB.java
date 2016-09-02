package ru.eu.flights.database;

import ru.eu.flights.database.abstracts.AbstractObjectDB;
import ru.eu.flights.objects.Passenger;

import java.sql.*;

public class PassengerDB extends AbstractObjectDB<Passenger> {

    private static PassengerDB instance;

    private PassengerDB() {
    }

    public static PassengerDB getInstance() {
        if (instance == null)
            instance = new PassengerDB();
        return instance;
    }

    @Override
    public Passenger parseObject(ResultSet rs) throws SQLException {
        Passenger passenger = new Passenger();
        passenger.setId(rs.getLong("id"));
        passenger.setDocumentNumber(rs.getString("document_number"));
        passenger.setEmail(rs.getString("email"));
        passenger.setFamilyName(rs.getString("family_name"));
        passenger.setGivenName(rs.getString("given_name"));
        passenger.setMiddleName(rs.getString("middle_name"));
        passenger.setPhone(rs.getString("phone"));
        return passenger;
    }

    @Override
    public PreparedStatement getObjectByIdStmt(long id) throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT p.id, p.given_name, p.middle_name, p.family_name, p.document_number, p.email, p.phone " +
                "FROM avia.passenger AS p WHERE id = ?");
        stmt.setLong(1, id);
        return stmt;
    }

    @Override
    public PreparedStatement getAllObjectsStmt() throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT p.id, p.given_name, p.middle_name, p.family_name, p.document_number, p.email, p.phone " +
                "FROM avia.passenger AS p");
        return stmt;
    }

    public PreparedStatement getInsertStmt(Passenger passenger) throws SQLException {
        Connection conn = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = conn.prepareStatement("INSERT INTO passenger(given_name, middle_name, family_name, document_number, email, phone) " +
                "VALUES (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);
        stmt.setString(1, passenger.getGivenName());
        stmt.setString(2, passenger.getMiddleName());
        stmt.setString(3, passenger.getFamilyName());
        stmt.setString(4, passenger.getDocumentNumber());
        stmt.setString(5, passenger.getEmail());
        stmt.setString(6, passenger.getPhone());
        return stmt;
    }
}
