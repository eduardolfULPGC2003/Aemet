package api.model;

public class Data {
    private String place;
    private String date;
    private String time;
    private String station;
    private double temperature;

    public Data(String date, String time, String place, String station, double temperature) {
        this.date = date;
        this.time = time;
        this.place = place;
        this.station = station;
        this.temperature = temperature;
    }
}
