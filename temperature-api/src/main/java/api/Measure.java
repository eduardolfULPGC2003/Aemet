package api;

public class Measure {
    private String idema;
    private double lon;
    private String fint;
    private double lat;
    private String ubi;
    private double tamax;

    public Measure(String idema, double lon, String fint, double lat, String ubi, double tamax) {
        this.idema = idema;
        this.lon = lon;
        this.fint = fint;
        this.lat = lat;
        this.ubi = ubi;
        this.tamax = tamax;
    }

    public String getIdema() {
        return idema;
    }

    public double getLon() {
        return lon;
    }

    public String getFint() {
        return fint;
    }

    public double getLat() {
        return lat;
    }

    public String getUbi() {
        return ubi;
    }

    public double getTamax() {
        return tamax;
    }
}
