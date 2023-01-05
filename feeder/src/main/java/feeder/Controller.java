package feeder;

import feeder.model.Measure;

import java.io.IOException;
import java.util.*;

public class Controller {
    private Map<String, ArrayList<Double>> coordinates;
    private String datalake;

    public Controller(Map<String, ArrayList<Double>> coordinates, String datalake) {
        this.coordinates = coordinates;
        this.datalake = datalake;
    }

    public Map<String, ArrayList<Double>> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Map<String, ArrayList<Double>> coordinates) {
        this.coordinates = coordinates;
    }

    public void start() throws IOException {
        ApiConsumer consumer = new AemetApiConsumer("eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJlZHVhcmRvbGYyMDAzQGdtYWlsLmNvbSIsImp0aSI6IjQxNjdlOGJkLWE1NmYtNDhjZS05ZTNjLTYwNTY1MDZhZTYxZiIsImlzcyI6IkFFTUVUIiwiaWF0IjoxNjcxMTIzOTQyLCJ1c2VySWQiOiI0MTY3ZThiZC1hNTZmLTQ4Y2UtOWUzYy02MDU2NTA2YWU2MWYiLCJyb2xlIjoiIn0.ww9TV05EqM6Prui9WxAewWZsNWC_zhRy8RDQndVv62U");
        String urlDatos = consumer.petition("https://opendata.aemet.es/opendata/api/observacion/convencional/todas").getDatos();
        List<Measure> measures = consumer.getData(urlDatos);
        List<Measure> filteredMeasures = consumer.filterByUbi(measures, coordinates);
        Datalake datalake = new FileDatalake(this.datalake);
        datalake.write(filteredMeasures);
    }
}
