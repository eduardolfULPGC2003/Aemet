package api;

import api.model.Data;
import com.google.gson.Gson;

import java.sql.SQLException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {
        Controller controller = new Controller("C:/Users/Eduardo/IdeaProjects/aemet/datamart-builder/datamart.db");
        SparkWebService api = new SparkWebService(controller);
        api.start();
    }
}
