package model;

import java.time.LocalDateTime;

public class GrupoFamiliar {
    private long grupoFamiliarId;
    private String nombres;
    private String apellidos;
    private String direccion;
    private String telefono;
    private RolGrupoFamiliar rol;
    private Funcionario funcionario;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    public GrupoFamiliar() {}

    public GrupoFamiliar(long grupoFamiliarId, String nombres, String apellidos, String direccion,
                         String telefono, RolGrupoFamiliar rol, Funcionario funcionario,
                         LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
        this.grupoFamiliarId = grupoFamiliarId;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
        this.rol = rol;
        this.funcionario = funcionario;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public long getGrupoFamiliarId() { return grupoFamiliarId; }
    public void setGrupoFamiliarId(long grupoFamiliarId) { this.grupoFamiliarId = grupoFamiliarId; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public RolGrupoFamiliar getRol() { return rol; }
    public void setRol(RolGrupoFamiliar rol) { this.rol = rol; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }

    @Override
    public String toString() {
        return "GrupoFamiliar{" +
                "grupoFamiliarId=" + grupoFamiliarId +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", rol=" + rol +
                ", funcionario=" + funcionario +
                '}';
    }
}
