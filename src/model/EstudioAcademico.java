package src.model;

public class EstudioAcademico {

    private int id;
    private String nivelAcademico;
    private String tituloObtenido;
    private String institucion;
    private int anioFinalizacion;
    private String estado;
    private int idFuncionario;

    public EstudioAcademico() {}

    public EstudioAcademico(int id, String nivelAcademico, String tituloObtenido,
                            String institucion, int anioFinalizacion, String estado, int idFuncionario) {
        this.id = id;
        this.nivelAcademico = nivelAcademico;
        this.tituloObtenido = tituloObtenido;
        this.institucion = institucion;
        this.anioFinalizacion = anioFinalizacion;
        this.estado = estado;
        this.idFuncionario = idFuncionario;
    }

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNivelAcademico() { return nivelAcademico; }
    public void setNivelAcademico(String nivelAcademico) { this.nivelAcademico = nivelAcademico; }

    public String getTituloObtenido() { return tituloObtenido; }
    public void setTituloObtenido(String tituloObtenido) { this.tituloObtenido = tituloObtenido; }

    public String getInstitucion() { return institucion; }
    public void setInstitucion(String institucion) { this.institucion = institucion; }

    public int getAnioFinalizacion() { return anioFinalizacion; }
    public void setAnioFinalizacion(int anioFinalizacion) { this.anioFinalizacion = anioFinalizacion; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public int getIdFuncionario() { return idFuncionario; }
    public void setIdFuncionario(int idFuncionario) { this.idFuncionario = idFuncionario; }

    @Override
    public String toString() {
        return tituloObtenido + " - " + nivelAcademico + " (" + institucion + ")";
    }
}
