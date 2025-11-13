package model;

public class EstadoFormacion {
    private long estadoFormacionId;
    private String nombre;

    public EstadoFormacion() {}

    public EstadoFormacion(long estadoFormacionId, String nombre) {
        this.estadoFormacionId = estadoFormacionId;
        this.nombre = nombre;
    }

    public long getEstadoFormacionId() { return estadoFormacionId; }
    public void setEstadoFormacionId(long estadoFormacionId) { this.estadoFormacionId = estadoFormacionId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return "EstadoFormacion{" +
                "estadoFormacionId=" + estadoFormacionId +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
