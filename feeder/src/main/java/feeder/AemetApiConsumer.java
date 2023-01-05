package feeder;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import feeder.model.Measure;
import feeder.model.Response;
import org.jsoup.Connection;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AemetApiConsumer implements ApiConsumer{
    private final String apiKey;

    public AemetApiConsumer(String apiKey) {
        this.apiKey = apiKey;
    }

    @Override
    public Response petition(String url) throws IOException {
        String json = Jsoup.connect(url)
                .validateTLSCertificates(false)
                .timeout(60000)
                .ignoreContentType(true)
                .header("accept", "application/json")
                .header("api_key", apiKey)
                .header("lon", "-16")
                .method(Connection.Method.GET)
                .maxBodySize(0).execute().body();
        return new Gson().fromJson(json, Response.class);
    }

    @Override
    public List<Measure> getData(String url) throws IOException {
        String json = Jsoup.connect(url)
                .validateTLSCertificates(false)
                .timeout(60000000)
                .ignoreContentType(true)
                .method(Connection.Method.GET)
                .maxBodySize(0).execute().body();
        Type userListType = new TypeToken<ArrayList<Measure>>(){}.getType();
        return new Gson().fromJson(json, userListType);
    }

    @Override
    public List<Measure> filterByUbi(List<Measure> measures, Map<String, ArrayList<Double>> coordinates) {
        return measures.stream()
                .filter(m -> m.getLon() > coordinates.get("lon").get(0)).filter(m -> m.getLon() < coordinates.get("lon").get(1))
                .filter(m -> m.getLat() > coordinates.get("lat").get(0)).filter(m -> m.getLat() < coordinates.get("lat").get(1))
                .collect(Collectors.toList());
    }
}
