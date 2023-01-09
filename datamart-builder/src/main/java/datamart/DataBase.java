package datamart;

import datamart.model.Measure;

import java.sql.SQLException;

public interface DataBase {
    void createTable(String name, String fields) throws SQLException;
    void addRegister(String table, Measure measure) throws SQLException;
}
