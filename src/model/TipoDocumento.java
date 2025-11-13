package model;

public class TipoDocumento {
    private long tipoDocumentoId;
    private String nombre;

    public TipoDocumento() {}

    public TipoDocumento(long tipoDocumentoId, String nombre) {
        this.tipoDocumentoId = tipoDocumentoId;
        this.nombre = nombre;
    }

    public long getTipoDocumentoId() { return tipoDocumentoId; }
    public void setTipoDocumentoId(long tipoDocumentoId) { this.tipoDocumentoId = tipoDocumentoId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return "TipoDocumento{" +
                "tipoDocumentoId=" + tipoDocumentoId +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
