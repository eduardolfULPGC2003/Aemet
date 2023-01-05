package feeder;

import feeder.model.Measure;
import feeder.model.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public interface ApiConsumer {
    Response petition(String url) throws IOException;
    List<Measure> getData(String url) throws IOException;
    List<Measure> filterByUbi(List<Measure> mesaures, Map<String, ArrayList<Double>> coordinates);
}
