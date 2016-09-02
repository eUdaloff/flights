package ru.eu.flights.database;

import ru.eu.flights.database.abstracts.AbstractObjectDB;
import ru.eu.flights.objects.spr.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CountryDB extends AbstractObjectDB<Country> {

    private static CountryDB instance;

    private CountryDB() {
    }

    public static CountryDB getInstance() {
        if (instance == null)
            instance = new CountryDB();
        return instance;
    }

    @Override
    public Country parseObject(ResultSet rs) throws SQLException {
        Country country = new Country();
        country.setId(rs.getLong("id"));
        country.setCode(rs.getString("code"));
        country.setFlag(rs.getBytes("flag"));
        country.setDesc(rs.getString("desc"));
        country.setName(rs.getString("name"));
        return country;
    }

    @Override
    public PreparedStatement getObjectByIdStmt(long id) throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT c.id, c.code, c.flag, c.desc, c.name FROM spr_country AS c WHERE id = ?");
        stmt.setLong(1, id);
        return stmt;
    }

    @Override
    public PreparedStatement getAllObjectsStmt() throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT c.id, c.code, c.flag, c.desc, c.name FROM spr_country AS c");
        return stmt;
    }
}
