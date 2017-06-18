package controladores;

import beans.Produto;
import repositorios.RepositorioEstoque;

public class ControladorEstoque {
	//ATRIBUTOS
	RepositorioEstoque repoestoque;
	private static ControladorEstoque instancia;
	
	//SINGLETON
	
	private ControladorEstoque(){
		repoestoque = RepositorioEstoque.getInstancia();
	}
	
	public static ControladorEstoque getInstancia(){
		if(instancia == null){
			instancia = new ControladorEstoque();
		}
		return instancia;
	}
	
	public RepositorioEstoque getRepoestoque() {
		return repoestoque;
	}
	
	//METODOS COM CONTROLE DE NEGOCIOS
	
	public boolean subtrairProduto (int codigo, int quantidade) {
		Produto[] produtos = repoestoque.getProdutos();
		for (int i = 0; i < repoestoque.getQuantSKU(); i++) {
			if( codigo == produtos[i].getCodigo() ) {
				if( quantidade <= produtos[i].getQuantidade() ) {
					repoestoque.subtrairProduto(codigo, quantidade);
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean inserir(Produto prod){
		if(prod == null || prod.getCodigo() == 0 || prod.getNome() == null)
			return false;
		if(repoestoque.getQuantSKU() == repoestoque.getProdutos().length)
			return false;
		if(retornarPosicao(prod.getCodigo()) != -1){
			System.out.println("Já existe um produto com esse código!");
			return false;
		}
		repoestoque.inserir(prod);
		System.out.println("Produto adicionado com sucesso!");
		return true;
	}
	public Produto buscar(int cod){
		int posicao;
		posicao = this.retornarPosicao(cod);
		
		if(posicao != -1)
		{
			return repoestoque.buscar(posicao);
		}
		return null;
	}
	public boolean alterar(Produto novoProduto){
		int posicao;
		if(novoProduto == null){
			return false;
		}
		posicao = this.retornarPosicao(novoProduto.getCodigo());
		if(posicao == -1){
			return false;
		}
		if(repoestoque.getProdutos()[posicao].getCodigo() == novoProduto.getCodigo()){
			repoestoque.alterar(novoProduto,posicao);
		return true;
		}
		return false;
	}
	
	public boolean remover(int cod){
		int posicao = this.retornarPosicao(cod);
		if(posicao == -1){
			System.out.println("Codigo nao encontrado!");
			return false;
		}
		repoestoque.remover(posicao);
		System.out.println("Produto removido com sucesso!");
		return true;
	}
	
	private int retornarPosicao(int codigo) {

		if (codigo <= 0) {
			return -1;
		}
		
		for (int i = 0; i< repoestoque.getQuantSKU(); i++){
			if (codigo == repoestoque.getProdutos()[i].getCodigo()) {
				return i;
			} 
		}
		return -1;
	}
}
