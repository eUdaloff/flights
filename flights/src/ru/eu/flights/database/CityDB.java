package ru.eu.flights.database;


import ru.eu.flights.database.abstracts.AbstractObjectDB;
import ru.eu.flights.objects.spr.City;
import ru.eu.flights.objects.spr.Country;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CityDB extends AbstractObjectDB<City> {

    private CityDB() {
    }

    private static CityDB instance;

    public static CityDB getInstance() {
        if (instance == null)
            instance = new CityDB();
        return instance;
    }

    @Override
    public City parseObject(ResultSet rs) throws SQLException {
        City city = new City();
        city.setId(rs.getLong("id"));
        city.setCode(rs.getString("code"));
        city.setCountry(CountryDB.getInstance().executeObject(CountryDB.getInstance().getObjectByIdStmt(rs.getLong("country_id"))));
        city.setDesc(rs.getString("desc"));
        city.setName(rs.getString("name"));
        return city;
    }

    @Override
    public PreparedStatement getObjectByIdStmt(long id) throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT c.id, c.name, c.code, c.country_id, c.desc FROM avia.spr_city AS c WHERE c.id=?");
        stmt.setLong(1, id);
        return stmt;
    }

    public PreparedStatement getCityByNameStmt(String name) throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT c.id, c.name, c.code, c.country_id, c.desc FROM avia.spr_city AS c WHERE c.name=?");
        stmt.setString(1, name);
        return stmt;
    }

    @Override
    public PreparedStatement getAllObjectsStmt() throws SQLException {
        Connection c = AviaDB.getInstance().getConnection();
        PreparedStatement stmt = c.prepareStatement("SELECT c.id, c.name, c.code, c.country_id, c.desc FROM avia.spr_city AS c");
        return stmt;
    }


}
