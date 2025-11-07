package src.model;

public class MiembroFamiliar {

    private int id;
    private String nombre;
    private String parentesco;
    private int edad;
    private String ocupacion;
    private String telefono;

    // Relación con funcionario
    private int idFuncionario;

    // Constructor vacío
    public MiembroFamiliar() {}

    // Constructor completo
    public MiembroFamiliar(int id, String nombre, String parentesco, int edad, String ocupacion, String telefono, int idFuncionario) {
        this.id = id;
        this.nombre = nombre;
        this.parentesco = parentesco;
        this.edad = edad;
        this.ocupacion = ocupacion;
        this.telefono = telefono;
        this.idFuncionario = idFuncionario;
    }

    // Getters y Setters
    public int getId() { 
        return id; 
    }
    public void setId(int id) { 
        this.id = id; 
    }

    public String getNombre() { 
        return nombre; 
    }
    public void setNombre(String nombre) { 
        this.nombre = nombre; 
    }

    public String getParentesco() { 
        return parentesco; 
    }
    public void setParentesco(String parentesco) { 
        this.parentesco = parentesco; 
    }

    public int getEdad() { 
        return edad; 
    }
    public void setEdad(int edad) { 
        this.edad = edad; 
    }

    public String getOcupacion() { 
        return ocupacion; 
    }
    public void setOcupacion(String ocupacion) { 
        this.ocupacion = ocupacion; 
    }

    public String getTelefono() { 
        return telefono; 
    }
    public void setTelefono(String telefono) { 
        this.telefono = telefono; 
    }

    public int getIdFuncionario() { 
        return idFuncionario; 
    }
    public void setIdFuncionario(int idFuncionario) { 
        this.idFuncionario = idFuncionario; 
    }

    @Override
    public String toString() {
        return nombre + " (" + parentesco + ")";
    }
}
