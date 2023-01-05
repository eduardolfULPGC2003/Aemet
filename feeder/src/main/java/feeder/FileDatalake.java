package feeder;

import com.google.gson.Gson;
import feeder.model.Measure;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileDatalake implements Datalake {
    private String folder;

    public FileDatalake(String folder) {
        this.folder = folder;
    }

    public String getFolder() {
        return folder;
    }

    @Override
    public void write(List<Measure> measures) {
        measures.stream().forEach(m-> {
            try {
                writeDatalake(folder, m);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

    private void writeDatalake(String folder, Measure m) throws IOException {
        String measure = new Gson().toJson(m);
        File f = new File(getArchiveName(folder, m.getFint()));
        FileWriter fw = new FileWriter(f, true);
        if (!f.exists()) {
            f.createNewFile();
        }
        else {
            if (f.length()==0) fw.append(measure + "\n");
            else {
                FileReader fr = new FileReader(f);
                BufferedReader br = new BufferedReader(fr);
                String linea;
                while ((linea = br.readLine()) != null) {
                    if (m.getTamax()== 0.0 || linea.contains(measure)) {return;}
                    else {}
                }
                fw.append(measure + "\n");
            }
        }
        fw.close();
    }

    private String getArchiveName(String folder, String fint) {
        String archive = folder + fint.substring(0, 4) + fint.substring(5, 7) + fint.substring(8, 10) + ".events";
        return archive;
    }


    @Override
    public List<Measure> read(String archiveName) throws IOException {
        List<Measure> measures = new ArrayList<>();
        File file = new File(folder + archiveName);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line;
        while ((line = br.readLine()) != null){
            measures.add(new Gson().fromJson(line, Measure.class));
        }
        br.close();
        return measures;
    }
}
