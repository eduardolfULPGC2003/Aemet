package feeder;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<String, ArrayList<Double>> granCanaria = new HashMap<>();
        String datalakePath = "C:/Users/Eduardo/IdeaProjects/aemet/feeder/datalake/";
        granCanaria.put("lon", new ArrayList<>(Arrays.asList(-16.00, -15.00)));
        granCanaria.put("lat", new ArrayList<>(Arrays.asList(27.5, 28.4)));
        Controller controller = new Controller(granCanaria, datalakePath);
        controller.start();
        /*
        Timer task = new Timer();
        task.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                try {
                    System.out.println("Recogiendo datos a las " + LocalDateTime.now());
                    controller.start();
                    System.out.println("Tarea finalizada. Going to sleep");
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }, 0, 3600*1000);

         */
    }
}
