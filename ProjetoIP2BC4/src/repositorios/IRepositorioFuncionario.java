package repositorios;

import java.util.ArrayList;

import beans.Funcionario;

public interface IRepositorioFuncionario {
	public boolean inserir (Funcionario funcionario);
	
	public boolean remover (int posicao);
	
	public boolean alterar (Funcionario funcionario, int posicao);
	
	public Funcionario buscar (int posicao);
	
}
