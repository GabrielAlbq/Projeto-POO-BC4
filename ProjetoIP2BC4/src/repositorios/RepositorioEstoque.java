package repositorios;

import beans.Produto;

public class RepositorioEstoque {
	//ATRIBUTOS
	private final static int TAM_MAX = 100;
	private Produto[] produtos;
	private int quantSKU;
	private static RepositorioEstoque instancia;
	
	//CONSTRUTOR
	private RepositorioEstoque(){
		this.produtos = new Produto[TAM_MAX];
		this.quantSKU = 0;
	}
	public static RepositorioEstoque getInstancia(){
		if(instancia == null){
			instancia = new RepositorioEstoque();
		}
		return instancia;
	}

	//GET
	public Produto[] getProdutos() {
		return produtos;
	}
	public int getQuantSKU() {
		return quantSKU;
	}
	
	//METODOS
	public void subtrairProduto (int posicao, int quantidade) {
		produtos[posicao].setQuantidade( produtos[posicao].getQuantidade()-quantidade );
	}
	public void inserir(Produto prod){
		this.produtos[quantSKU] = prod;
		this.quantSKU++;
	}
	
	public Produto buscar(int posicao){
		return this.produtos[posicao];
	}
	
	public void remover(int posicao){
		this.produtos[posicao] = this.produtos[this.quantSKU-1];
		this.produtos[this.quantSKU-1] = null;
		this.quantSKU--;
	}
	
	public void alterar(Produto novoProduto, int posicao){
		this.produtos[posicao] = novoProduto;
	}
	
}
