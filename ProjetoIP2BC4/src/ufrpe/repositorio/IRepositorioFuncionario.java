package ufrpe.repositorio;

import java.util.List;

import ufrpe.negocio.beans.Funcionario;
import ufrpe.negocio.beans.Login;

public interface IRepositorioFuncionario {
	void inserir (Funcionario funcionario);
	
	void remover (int posicao);
	
	void alterar (Funcionario funcionario, int posicao);
	
	Funcionario buscar (int posicao);
	
	List<Funcionario> getFuncionarios();
	
	void salvarArquivo();
	
	public void alterarLogin(Login log, int posicao);
}
