  package negocio;

import beans.*;
import repositorios.*;


public class ControladorVenda {
	
	// ATRIBUTOS 
	
	private IRepositorioEstoque repoEstoque;
	private IRepositorioVenda repoVenda;
	private Pedido pedido;
	private ControladorEstoque controlEstoque; //PARA COMPARAR A QUANTIDADE DO PRODUTO ORIGINAL
	
	// SINGLETON

	private static ControladorVenda instancia;

	private ControladorVenda() {
		repoVenda = RepositorioVenda.getInstancia();
		repoEstoque = RepositorioEstoque.getInstancia();
		pedido = Pedido.getInstancia();
		controlEstoque = ControladorEstoque.getInstancia();
	}
	
	public static ControladorVenda getInstancia() {
		if( instancia == null ) {
			instancia = new ControladorVenda();
		}
		return instancia;
	}
	
	// CRUD E METODOS
	
	public int retornaPosicao(int codigo) {
		if( codigo <= 0 ) {
			return -1;
		}
		
//		Produto[] teste = repoEstoque.getProdutos();
////		
////		for(Produto prod : ((RepositorioEstoque)repoestoque).getProdutos()){
////			if(codigo == prod.getCodigo()){
////				return ((RepositorioEstoque)repoestoque).getProdutos().indexOf(prod);
////			}
////		}
//		
//		for (int i = 0; i < repoEstoque.getQuantSKU() ; i++) {
//			if( codigo == teste[i].getCodigo() ) {
//				return i;
//			}
//		}
		return -1;
	}
	
	public void listarNotasFiscais() {
		this.repoVenda.listarNotasFiscais();
	}
	
	public void adicionarNotaFiscal (NotaFiscal notaFiscal) {
		if( notaFiscal == null ) {
			return;
		}
		repoVenda.adicionarNotaFiscal( notaFiscal);
	}
	
	public void limparHistoricoNotasFiscais () { // Apaga as notas fiscais armazenadas no repositorioVenda
		this.repoVenda.limparHistoricoNotasFiscais();
	}
 	
	public void listarVendas () {
		this.repoVenda.listarVendas();
	}
	
	//PARA A VENDA
	
	public boolean inserir(ItemVenda itemvenda){
		if (itemvenda == null){
			System.out.println("Item não existe!");
			return false;
		}
		Produto prod = controlEstoque.buscar(itemvenda.getCodigo());
		if(itemvenda.getQtd() > prod.getQuantidade() ){
			System.out.println("Quantidade maior do que a do produto");
			return false;
		}
		repoVenda.inserir(itemvenda);
		return true;
	}
}
