package repositorios;

import java.util.ArrayList;
import java.util.List;

import beans.Produto;

public class RepositorioEstoque implements IRepositorioEstoque {
	
	//ATRIBUTOS
	private List<Produto> produtos = new ArrayList<>();
	//private final static int TAM_MAX = 100;
	//private Produto[] produtos;
	//private int quantSKU;
	private static RepositorioEstoque instancia;
	
	//CONSTRUTOR
	
	private RepositorioEstoque(){
	//	this.produtos = new Produto[TAM_MAX];
	//	this.quantSKU = 0;
	}
	public static RepositorioEstoque getInstancia(){
		if(instancia == null){
			instancia = new RepositorioEstoque();
		}
		return instancia;
	}

	//GET
	
	public List<Produto> getProdutos() {
		return produtos;
	}
//	public Produto[] getProdutos() {
//		return produtos;
//	}
//	public int getQuantSKU() {
//		return quantSKU;
//	}
	
	//METODOS
	
	public void subtrairProduto (int posicao, int quantidade) {
		this.produtos.get(posicao).setQuantidade(produtos.get(posicao).getQuantidade()-quantidade);
	}
	public void inserir(Produto prod){
		this.produtos.add(prod);
	}
	
	public Produto buscar(int posicao){
		return this.produtos.get(posicao);
	}
	
	public void remover(int posicao){
		this.produtos.remove(posicao);
	}
	
	public void alterar(Produto novoProduto, int posicao){
		this.produtos.set(posicao, novoProduto);
	}
	
}
