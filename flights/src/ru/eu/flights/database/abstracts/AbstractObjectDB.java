package ru.eu.flights.database.abstracts;

import ru.eu.flights.database.interfaces.DbWorkable;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractObjectDB<T> implements DbWorkable<T> {

    public static final int INTERVAL = 1;

    public AbstractObjectDB() {
    }

    @Override
    public int insert(PreparedStatement stmt) throws SQLException {
        int result = -1;
        ResultSet rs = null;
        try {
            result = stmt.executeUpdate();
            rs = stmt.getGeneratedKeys();
            rs.next();
            if (rs.first()) {
                result = rs.getInt(1);
            }
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return result;
    }

    @Override
    public Set<T> executeCollection(PreparedStatement stmt) throws SQLException {
        Set<T> list = new HashSet<>();
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery();
            while (rs.next()) {
                list.add(parseObject(rs));
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return list;
    }

    @Override
    public T executeObject(PreparedStatement stmt) throws SQLException {
        T obj = null;
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery();

            rs.next();
            if (rs.isFirst()) {
                obj = parseObject(rs);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return obj;
    }

    protected void clearTime(Calendar c) {
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);
    }
}
