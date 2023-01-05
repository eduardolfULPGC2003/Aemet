package api;

import api.model.Data;
import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import spark.Spark;

import java.sql.SQLException;
import java.util.List;
import java.util.regex.Pattern;

import static spark.Spark.get;
import static spark.Spark.halt;

public class SparkWebService {
    private Controller controller;

    public SparkWebService(Controller controller) {
        this.controller = controller;
    }

    public void start(){
        Spark.port(4567);
        get("v1/places/with-max-temperature", (req, res) -> getMax(req, res));
        get("v1/places/with-min-temperature", (req, res) -> getMin(req, res));
    }

    private String getMin(Request req, Response res) throws SQLException {
        String from = req.queryParams("from");
        String to = req.queryParams("to");
        String patron = "\\d{4}-\\d{2}-\\d{2}";
        Pattern p = Pattern.compile(patron);
        if (from == null || to == null || !(p.matcher(from).find() && p.matcher(to).find())) halt(400, "No date provided");
        List<Data> data = controller.getMinTemperature(from, to);
        return new Gson().toJson(data);
    }

    private String getMax(Request req, Response res) throws SQLException {
        String from = req.queryParams("from");
        String to = req.queryParams("to");
        String patron = "\\d{4}-\\d{2}-\\d{2}";
        Pattern p = Pattern.compile(patron);
        if (from == null || to == null || !(p.matcher(from).find() && p.matcher(to).find())) halt(400, "No date provided");
        List<Data> data = controller.getMaxTemperature(from, to);
        return new Gson().toJson(data);
    }
}
