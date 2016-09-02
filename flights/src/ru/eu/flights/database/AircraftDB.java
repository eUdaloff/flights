package ru.eu.flights.database;

import ru.eu.flights.database.abstracts.AbstractObjectDB;
import ru.eu.flights.objects.spr.Aircraft;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AircraftDB extends AbstractObjectDB<Aircraft> {

    private static AircraftDB instance;

    private AircraftDB() {
    }

    public static AircraftDB getInstance() {
        if (instance == null)
            instance = new AircraftDB();
        return instance;
    }

    @Override
    public Aircraft parseObject(ResultSet rs) throws SQLException {
        Aircraft a = new Aircraft();
        a.setId(rs.getLong("id"));
        a.setName(rs.getString("name"));
        a.setDesc(rs.getString("desc"));
        a.setCompany(CompanyDB.getInstance().executeObject(CompanyDB.getInstance().getObjectByIdStmt(rs.getLong("company_id"))));
        return a;
    }

    @Override
    public PreparedStatement getObjectByIdStmt(long id) throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT a.id, a.name, a.company_id, a.desc FROM avia.spr_aircraft AS a WHERE a.id=?");
        stmt.setLong(1, id);
        return stmt;
    }

    @Override
    public PreparedStatement getAllObjectsStmt() throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT a.id, a.name, a.company_id, a.desc FROM avia.spr_aircraft AS a");
        return stmt;
    }
}
