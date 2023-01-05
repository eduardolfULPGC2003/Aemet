package feeder.model;

public class Response {
    private String descripcion;
    private Integer estado;
    private String datos;
    private String metadatos;

    public Response(String descripcion, Integer estado, String datos, String metadatos) {
        this.descripcion = descripcion;
        this.estado = estado;
        this.datos = datos;
        this.metadatos = metadatos;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getEstado() {
        return estado;
    }

    public String getDatos() {
        return datos;
    }

    public String getMetadatos() {
        return metadatos;
    }
}
