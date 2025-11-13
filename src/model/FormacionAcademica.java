package model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class FormacionAcademica {
    private long formacionAcademicaId;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private EstadoFormacion estadoFormacion;
    private NivelEducativo nivelEducativo;
    private Universidad universidad;
    private Funcionario funcionario;
    private LocalDateTime fechaCreacion;
    private LocalDateTime fechaActualizacion;

    public FormacionAcademica() {}

    public FormacionAcademica(long formacionAcademicaId, LocalDate fechaInicio, LocalDate fechaFin,
                              EstadoFormacion estadoFormacion, NivelEducativo nivelEducativo,
                              Universidad universidad, Funcionario funcionario,
                              LocalDateTime fechaCreacion, LocalDateTime fechaActualizacion) {
        this.formacionAcademicaId = formacionAcademicaId;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.estadoFormacion = estadoFormacion;
        this.nivelEducativo = nivelEducativo;
        this.universidad = universidad;
        this.funcionario = funcionario;
        this.fechaCreacion = fechaCreacion;
        this.fechaActualizacion = fechaActualizacion;
    }

    public long getFormacionAcademicaId() { return formacionAcademicaId; }
    public void setFormacionAcademicaId(long formacionAcademicaId) { this.formacionAcademicaId = formacionAcademicaId; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public EstadoFormacion getEstadoFormacion() { return estadoFormacion; }
    public void setEstadoFormacion(EstadoFormacion estadoFormacion) { this.estadoFormacion = estadoFormacion; }

    public NivelEducativo getNivelEducativo() { return nivelEducativo; }
    public void setNivelEducativo(NivelEducativo nivelEducativo) { this.nivelEducativo = nivelEducativo; }

    public Universidad getUniversidad() { return universidad; }
    public void setUniversidad(Universidad universidad) { this.universidad = universidad; }

    public Funcionario getFuncionario() { return funcionario; }
    public void setFuncionario(Funcionario funcionario) { this.funcionario = funcionario; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public LocalDateTime getFechaActualizacion() { return fechaActualizacion; }
    public void setFechaActualizacion(LocalDateTime fechaActualizacion) { this.fechaActualizacion = fechaActualizacion; }

    @Override
    public String toString() {
        return "FormacionAcademica{" +
                "formacionAcademicaId=" + formacionAcademicaId +
                ", fechaInicio=" + fechaInicio +
                ", fechaFin=" + fechaFin +
                ", estadoFormacion=" + estadoFormacion +
                ", nivelEducativo=" + nivelEducativo +
                ", universidad=" + universidad +
                ", funcionario=" + funcionario +
                '}';
    }
}
