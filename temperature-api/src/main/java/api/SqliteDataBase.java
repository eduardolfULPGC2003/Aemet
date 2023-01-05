package api;

import java.sql.*;
import java.time.LocalDate;

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
    public ResultSet readRegister(String table, LocalDate date) throws SQLException {
        String sql = "SELECT * FROM " + table + " WHERE Date = \"" + date + "\"";
        ResultSet resultSet = statement.executeQuery(sql);
        return resultSet;
    }
}
