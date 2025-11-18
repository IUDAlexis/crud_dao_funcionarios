package controller;

import java.util.List;

import model.Funcionario;
import dao.FuncionarioDao;
import dao.FuncionarioDaoJDBC;

public class FuncionarioController {

    private final FuncionarioDao funcionarioDAO;

    public FuncionarioController() {
        this.funcionarioDAO = new FuncionarioDaoJDBC();
    }

    public void guardarFuncionario(Funcionario f) throws Exception {

        if (f == null) {
            throw new Exception("El funcionario no puede ser nulo.");
        }

        if (f.getNumeroIdentificacion() == null || f.getNumeroIdentificacion().trim().isEmpty()) {
            throw new Exception("Número de identificación requerido.");
        }

        if (f.getNombres() == null || f.getNombres().trim().isEmpty()) {
            throw new Exception("Debe ingresar los nombres.");
        }

        funcionarioDAO.insertar(f);
    }

    public List<Funcionario> listarFuncionarios() {
        return funcionarioDAO.listarTodos();
    }

    public void actualizarFuncionario(Funcionario f) throws Exception {

        if (f.getFuncionarioId() <= 0) {
            throw new Exception("Para actualizar debe existir un ID válido.");
        }

        funcionarioDAO.actualizar(f);
    }

    public void eliminarFuncionario(int id) throws Exception {
        if (id <= 0) {
            throw new Exception("ID inválido.");
        }
        funcionarioDAO.eliminar(id);
    }
}
