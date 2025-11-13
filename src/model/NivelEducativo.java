package model;

public class NivelEducativo {
    private long nivelEducativoId;
    private String nombre;

    public NivelEducativo() {}

    public NivelEducativo(long nivelEducativoId, String nombre) {
        this.nivelEducativoId = nivelEducativoId;
        this.nombre = nombre;
    }

    public long getNivelEducativoId() { return nivelEducativoId; }
    public void setNivelEducativoId(long nivelEducativoId) { this.nivelEducativoId = nivelEducativoId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return "NivelEducativo{" +
                "nivelEducativoId=" + nivelEducativoId +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
