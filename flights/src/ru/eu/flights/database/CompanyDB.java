package ru.eu.flights.database;

import ru.eu.flights.database.abstracts.AbstractObjectDB;
import ru.eu.flights.objects.spr.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CompanyDB extends AbstractObjectDB<Company> {

    private static CompanyDB instance;

    private CompanyDB() {
    }

    public static CompanyDB getInstance() {
        if (instance == null)
            instance = new CompanyDB();
        return instance;
    }

    @Override
    public Company parseObject(ResultSet rs) throws SQLException {
        Company c = new Company();
        c.setId(rs.getLong("id"));
        c.setName(rs.getString("name"));
        c.setDesc(rs.getString("desc"));
        return c;
    }

    @Override
    public PreparedStatement getObjectByIdStmt(long id) throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT c.id, c.name, c.desc FROM avia.spr_company AS c WHERE c.id = ?");
        stmt.setLong(1, id);
        return stmt;
    }

    @Override
    public PreparedStatement getAllObjectsStmt() throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT c.id, c.name, c.desc FROM avia.spr_company AS c");
        return stmt;
    }
}
