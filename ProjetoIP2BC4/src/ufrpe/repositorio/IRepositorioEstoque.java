package ufrpe.repositorio;

import java.util.List;

import ufrpe.negocio.beans.Produto;

public interface IRepositorioEstoque {
	public void subtrairProduto (int posicao, int quantidade);
	
	public void inserir(Produto prod);
	
	public Produto buscar(int posicao);
	
	public void remover(int posicao);
	
	public void alterar(Produto novoProduto, int posicao);
	
	public List<Produto> listar();
	
	public void salvarArquivo();
}
