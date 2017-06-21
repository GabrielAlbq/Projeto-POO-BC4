package negocio;

import beans.Produto;
import beans.ItemVenda;
import repositorios.RepositorioEstoque;
import repositorios.RepositorioVenda;


public class ControladorEstoque {
	
	//ATRIBUTOS
	
	RepositorioEstoque repoestoque;
	RepositorioVenda repoVenda;
	private static ControladorEstoque instancia;
	
	//SINGLETON
	
	private ControladorEstoque(){
		repoestoque = RepositorioEstoque.getInstancia();
		repoVenda = RepositorioVenda.getInstancia();
	}
	
	public static ControladorEstoque getInstancia(){
		if(instancia == null){
			instancia = new ControladorEstoque();
		}
		return instancia;
	}
	
	//GET
	
	public RepositorioEstoque getRepoestoque() {
		return repoestoque;
	}
	
	//METODOS COM CONTROLE DE NEGOCIOS
	
	public String listarProduto(){
		Produto[] prod = repoestoque.getProdutos();
		String texto = "";
		for(int i = 0 ; i < repoestoque.getQuantSKU() ; i++){
			if( prod[i] == null ) {
				texto = "\n\n\tNao ha produtos cadastrados!\n\n";
			} 
			else {
				texto += "\n#"+(i+1)+prod[i].toString();
			}
		}
		return texto;
	}
	public boolean subtrairProduto (int codigo, int quantidade) {
		Produto[] produtos = repoestoque.getProdutos();
		for (int i = 0; i < repoestoque.getQuantSKU(); i++) {
			if( codigo == produtos[i].getCodigo() ) {
				if( quantidade <= produtos[i].getQuantidade() ) {
					repoestoque.subtrairProduto(i, quantidade); //É pra passar por parametro a posicao
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean inserir(Produto prod){
		if(prod == null || prod.getCodigo() <= 0 || prod.getNome() == null)
			return false;
		if(repoestoque.getQuantSKU() == repoestoque.getProdutos().length)
			return false;
		if(retornarPosicao(prod.getCodigo()) != -1){
			System.out.println("Ja existe um produto com esse codigo!");
			return false;
		}
		repoestoque.inserir(prod);
	//	repoVenda.inserir( new ItemVenda(prod.getCodigo(), prod.getNome(), prod.getPreco(), 0) ); ///////
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
			System.out.println("Produto alterado com sucesso!");
		return true;
		}
		return false;
	}
	
	public boolean remover(int cod){
		if(repoestoque.getQuantSKU() == 0) {
			System.out.println("\n\tErro! Nao ha produtos para remover!");
			return false;
		}
		int posicao = this.retornarPosicao(cod);
		if(posicao == -1){
			System.out.println("Codigo nao encontrado!");
			return false;
		}
		repoestoque.remover(posicao);
		repoVenda.remover(posicao);
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
