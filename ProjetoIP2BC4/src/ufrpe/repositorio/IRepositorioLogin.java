package ufrpe.repositorio;

import java.util.List;

import ufrpe.negocio.beans.Login;

public interface IRepositorioLogin {
	void inserirLogin (Login login);
	
	void removerLogin (int posicao);
	
	void alterarSenha (Login login, int posicao);
	
	Login buscarLogin (int posicao);
	
	List<Login> listar();
	
	//void salvarArquivo();
}
