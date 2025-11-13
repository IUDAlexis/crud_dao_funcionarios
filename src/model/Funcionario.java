package model;

import java.time.LocalDate;

public class Funcionario {
    private long funcionarioId;
    private String numeroIdentificacion;
    private String nombres;
    private String apellidos;
    private String sexo;
    private String direccion;
    private String telefono;
    private LocalDate fechaNacimiento;
    private EstadoCivil estadoCivil;
    private TipoDocumento tipoDocumento;

    public Funcionario() {}

    public Funcionario(long funcionarioId, String numeroIdentificacion, String nombres, String apellidos,
                       String sexo, String direccion, String telefono, LocalDate fechaNacimiento,
                       EstadoCivil estadoCivil, TipoDocumento tipoDocumento) {
        this.funcionarioId = funcionarioId;
        this.numeroIdentificacion = numeroIdentificacion;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.sexo = sexo;
        this.direccion = direccion;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.estadoCivil = estadoCivil;
        this.tipoDocumento = tipoDocumento;
    }

    public long getFuncionarioId() { return funcionarioId; }
    public void setFuncionarioId(long funcionarioId) { this.funcionarioId = funcionarioId; }

    public String getNumeroIdentificacion() { return numeroIdentificacion; }
    public void setNumeroIdentificacion(String numeroIdentificacion) { this.numeroIdentificacion = numeroIdentificacion; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getSexo() { return sexo; }
    public void setSexo(String sexo) { this.sexo = sexo; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public EstadoCivil getEstadoCivil() { return estadoCivil; }
    public void setEstadoCivil(EstadoCivil estadoCivil) { this.estadoCivil = estadoCivil; }

    public TipoDocumento getTipoDocumento() { return tipoDocumento; }
    public void setTipoDocumento(TipoDocumento tipoDocumento) { this.tipoDocumento = tipoDocumento; }

    @Override
    public String toString() {
        return "Funcionario{" +
                "funcionarioId=" + funcionarioId +
                ", numeroIdentificacion='" + numeroIdentificacion + '\'' +
                ", nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", sexo='" + sexo + '\'' +
                ", direccion='" + direccion + '\'' +
                ", telefono='" + telefono + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", estadoCivil=" + estadoCivil +
                ", tipoDocumento=" + tipoDocumento +
                '}';
    }
}
