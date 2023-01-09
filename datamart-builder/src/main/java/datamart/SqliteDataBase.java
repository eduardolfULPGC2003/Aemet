package datamart;

import datamart.model.Measure;

import java.sql.*;

public class SqliteDataBase implements DataBase{
    private String path;
    private Connection conn;
    private Statement statement;

    public SqliteDataBase(String path) throws SQLException {
        this.path = path;
        this.conn = connect(path);
        this.statement = conn.createStatement();
    }

    private Connection connect(String path) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:" + path;
            conn = DriverManager.getConnection(url);
            System.out.println("Connection to SQLite has been established.");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return conn;
    }

    @Override
    public void createTable(String name, String fields) throws SQLException {
        statement.execute("CREATE TABLE IF NOT EXISTS " + name + "(" + fields + ")");
    }

    @Override
    public void addRegister(String table, Measure measure) throws SQLException {
        createTable(table, "Date TEXT PRIMARY KEY, " +
                "Time TEXT NOT NULL, " +
                "Place TEXT NOT NULL, " +
                "Station TEXT NOT NULL, " +
                "Value REAL NOT NULL");
        PreparedStatement preparedStatement = conn.prepareStatement("INSERT INTO " + table + "(Date,Time,Place,Station,Value) " +
                "VALUES (?,?,?,?,?)");
        preparedStatement.setString(1,  measure.getFint().substring(0,10));
        preparedStatement.setString(2,  measure.getFint().substring(11));
        preparedStatement.setString(3,  measure.getUbi());
        preparedStatement.setString(4,  measure.getIdema());
        preparedStatement.setDouble(5, measure.getTamax());
        preparedStatement.execute();
    }
}
