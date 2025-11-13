package model;

public class EstadoCivil {
    private long estadoCivilId;
    private String nombre;

    public EstadoCivil() {}

    public EstadoCivil(long estadoCivilId, String nombre) {
        this.estadoCivilId = estadoCivilId;
        this.nombre = nombre;
    }

    public long getEstadoCivilId() { return estadoCivilId; }
    public void setEstadoCivilId(long estadoCivilId) { this.estadoCivilId = estadoCivilId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return "EstadoCivil{" +
                "estadoCivilId=" + estadoCivilId +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
