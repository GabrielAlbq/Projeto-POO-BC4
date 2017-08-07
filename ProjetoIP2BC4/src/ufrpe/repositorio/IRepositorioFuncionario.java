package ufrpe.repositorio;

import java.util.List;

import ufrpe.beans.Funcionario;

public interface IRepositorioFuncionario {
	void inserir (Funcionario funcionario);
	
	void remover (int posicao);
	
	void alterar (Funcionario funcionario, int posicao);
	
	Funcionario buscar (int posicao);
	
	List<Funcionario> getFuncionarios();
	
	void salvarArquivo();
}
