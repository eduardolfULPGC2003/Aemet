package datamart;

import datamart.model.Measure;

import java.io.IOException;
import java.util.List;

public interface Datalake {
    void write(List<Measure> measures);
    List<Measure> read(String startDate) throws IOException;
}
