package api;

import api.model.Data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Controller {
    private DataBase database;

    public Controller(String databasePath) throws SQLException {
        this.database = new SqliteDataBase(databasePath);
    }

    public List<Data> getMinTemperature(String from, String to) throws SQLException {
        List<Data> data = new ArrayList<>();
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        LocalDate box;
        if (fromDate.isAfter(toDate)){box = fromDate; fromDate = toDate; toDate = box;}
        for (long i = 0; i < toDate.compareTo(fromDate) + 1; i++) {
            ResultSet result = database.readRegister("min", fromDate.plusDays(i));
            String date = result.getString("Date");
            if (date == null) continue;
            String place = result.getString("Place");
            String time = result.getString("Time");
            String station = result.getString("Station");
            double temperature = result.getDouble("Value");
            data.add(new Data(date, place, time, station, temperature));
        }
        return data;
    }

    public List<Data> getMaxTemperature(String from, String to) throws SQLException {
        List<Data> data = new ArrayList<>();
        LocalDate fromDate = LocalDate.parse(from);
        LocalDate toDate = LocalDate.parse(to);
        LocalDate box;
        if (fromDate.isAfter(toDate)){box = fromDate; fromDate = toDate; toDate = box;}
        for (long i = 0; i < toDate.compareTo(fromDate) + 1; i++) {
            ResultSet result = database.readRegister("max", fromDate.plusDays(i));
            String date = result.getString("Date");
            if (date == null) continue;
            String place = result.getString("Place");
            String time = result.getString("Time");
            String station = result.getString("Station");
            double temperature = result.getDouble("Value");
            data.add(new Data(date, place, time, station, temperature));
        }
        return data;
    }
}
