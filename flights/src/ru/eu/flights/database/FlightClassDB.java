package ru.eu.flights.database;

import ru.eu.flights.database.abstracts.AbstractObjectDB;
import ru.eu.flights.objects.spr.FlightClass;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class FlightClassDB extends AbstractObjectDB<FlightClass> {

    private static FlightClassDB instance;

    private FlightClassDB() {
    }

    public static FlightClassDB getInstance() {
        if (instance == null)
            instance = new FlightClassDB();
        return instance;
    }

    @Override
    public FlightClass parseObject(ResultSet rs) throws SQLException {
        FlightClass fc = new FlightClass();
        fc.setId(rs.getLong("id"));
        fc.setDesc(rs.getString("desc"));
        fc.setName(rs.getString("name"));
        return fc;
    }

    @Override
    public PreparedStatement getObjectByIdStmt(long id) throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT fc.id, fc.name, fc.desc FROM avia.spr_flight_class AS fc WHERE fc.id = ?");
        stmt.setLong(1, id);
        return stmt;
    }

    @Override
    public PreparedStatement getAllObjectsStmt() throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT fc.id, fc.name, fc.desc FROM avia.spr_flight_class AS fc");
        return stmt;
    }
}
