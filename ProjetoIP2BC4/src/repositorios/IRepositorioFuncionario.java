package repositorios;

import java.util.ArrayList;

import beans.Funcionario;

public interface IRepositorioFuncionario {
	void inserir (Funcionario funcionario);
	
	void remover (int posicao);
	
	void alterar (Funcionario funcionario, int posicao);
	
	Funcionario buscar (int posicao);
	
	ArrayList<Funcionario> getFuncionarios();
	
	public String listarFuncionarios();
}
