package ru.eu.flights.database.interfaces;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

public interface DbWorkable<T> {

    int insert(PreparedStatement stmt) throws SQLException;

    T executeObject(PreparedStatement stmt) throws SQLException;

    Set<T> executeCollection(PreparedStatement stmt) throws SQLException;

    T parseObject(ResultSet rs) throws SQLException;

    PreparedStatement getObjectByIdStmt(long id) throws SQLException;

    PreparedStatement getAllObjectsStmt() throws SQLException;
}
