package datamart;

import java.io.IOException;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws IOException, SQLException {
        String datalake = "C:\\Users\\Eduardo\\IdeaProjects\\aemet\\feeder\\datalake\\";
        String dataBase = "C:/Users/Eduardo/IdeaProjects/aemet/datamart-builder/datamart.db";
        Controller controller = new Controller(datalake, dataBase);
        controller.start();
    }
}
