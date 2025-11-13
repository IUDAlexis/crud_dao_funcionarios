package model;

public class Universidad {
    private long universidadId;
    private String nombre;

    public Universidad() {}

    public Universidad(long universidadId, String nombre) {
        this.universidadId = universidadId;
        this.nombre = nombre;
    }

    public long getUniversidadId() { return universidadId; }
    public void setUniversidadId(long universidadId) { this.universidadId = universidadId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return "Universidad{" +
                "universidadId=" + universidadId +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
