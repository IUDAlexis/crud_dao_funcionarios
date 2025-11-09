package dao;
import java.util.List;
import model.*;

public interface FuncionarioDao {
    void insertar(Funcionario funcionario);
    void actualizar(Funcionario funcionario);
    void eliminar(int idFuncionario);
    Funcionario buscarPorId(int idFuncionario);
    List<Funcionario> listarTodos();
}
