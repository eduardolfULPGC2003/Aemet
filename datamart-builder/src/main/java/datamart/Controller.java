package datamart;

import datamart.model.Measure;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Comparator;
import java.util.List;

public class Controller {
    private String folderDatalake;
    private String dataBasePath;

    public Controller(String datalake, String dataBase) {
        this.folderDatalake = datalake;
        this.dataBasePath = dataBase;
    }

    public void start() throws IOException, SQLException {
        File db = new File(dataBasePath);
        if(db.exists()) db.delete();
        Datalake datalake = new FileDatalake(folderDatalake);
        File directory = new File(folderDatalake);
        String[] archives;
        DataBase datamart = new SqliteDataBase(dataBasePath);
        if (directory.isDirectory() && (archives = directory.list()) != null) {
            for (String archive: archives){
                List<Measure> measures = datalake.read(archive);
                System.out.println(measures.size());
                Measure minMeasure = measures.stream().min(Comparator.comparing(m -> m.getTamax())).orElse(null);
                datamart.addRegister("min", minMeasure);
                Measure maxMeasure = measures.stream().max(Comparator.comparing(m -> m.getTamax())).orElse(null);
                datamart.addRegister("max", maxMeasure);
            }
        }
    }
}
