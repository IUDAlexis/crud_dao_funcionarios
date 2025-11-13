package model;

public class RolGrupoFamiliar {
    private long rolId;
    private String nombre;

    public RolGrupoFamiliar() {}

    public RolGrupoFamiliar(long rolId, String nombre) {
        this.rolId = rolId;
        this.nombre = nombre;
    }

    public long getRolId() { return rolId; }
    public void setRolId(long rolId) { this.rolId = rolId; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    @Override
    public String toString() {
        return "RolGrupoFamiliar{" +
                "rolId=" + rolId +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
