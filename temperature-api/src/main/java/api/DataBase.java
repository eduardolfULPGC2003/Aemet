package api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public interface DataBase {
    ResultSet readRegister(String min, LocalDate localDate) throws SQLException;
}
