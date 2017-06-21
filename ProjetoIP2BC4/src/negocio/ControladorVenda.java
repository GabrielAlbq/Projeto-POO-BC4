  package negocio;

import beans.*;
import repositorios.*;


public class ControladorVenda {
	
	// ATRIBUTOS 
	
	private RepositorioEstoque repoEstoque;
	private RepositorioVenda repoVenda;
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
		
		Produto[] teste = repoEstoque.getProdutos();
		
		for (int i = 0; i < repoEstoque.getQuantSKU() ; i++) {
			if( codigo == teste[i].getCodigo() ) {
				return i;
			}
		}
		return -1;
	}
	

	// este metodo checa se existe o produto com este codigo e quantidade no repositorioEstoque
	// se houver ele retorna verdadeiro, para validar a subtracao deste produto na quantidade

	public boolean checarQuantidade(int codigo, int quantidade) {
		if( codigo <= 0 || quantidade <= 0) {
			return false;
		}
		Produto[] teste = repoEstoque.getProdutos();
		for (int i = 0; i < repoEstoque.getQuantSKU() ; i++) {
			if( codigo == teste[i].getCodigo() ) {
				if( teste[i].getQuantidade() >= quantidade ) {
					return true;
				}
			}
		}
		return false;
	}
	
	public String listarNotasFiscais() {
		return repoVenda.listarNotasFiscais();
	}
	
	public boolean efetuarPedido (int codigo, int quantidade, Funcionario funcionario) {
		if( codigo <= 0 ) {
			System.out.println("\n\n\tErro! codigo invalido!");
			return false;
		}
		if( quantidade <= 0 ) {
			System.out.println("\n\n\tErro! quantidade invalida!");
			return false;
		}
		int posicao = retornaPosicao(codigo);
		if( posicao == -1 ) {
			System.out.println("Erro! item inexistente!");
			return false;
		}
		boolean checagem = checarQuantidade(codigo, quantidade);
		if( checagem == false ) {
			System.out.println("Erro! numero de itens insuficiente!");
			return false;
		}
		//pedido.acrescentarPedido(codigo, quantidade, funcionario);
		return true;
	}
	
	public void adicionarNotaFiscal (NotaFiscal notaFiscal) {
		if( notaFiscal == null ) {
			return;
		}
		repoVenda.adicionarNotaFiscal( notaFiscal);
	}
	
	// apaga as notas fiscais armazenadas no repositorioVenda
	public boolean limparHistoricoNotasFiscais () {
		if(repoVenda.getQtdNotaFiscal() == 0) {
			return false;
		}
		repoVenda.setQtdNotaFiscal(0);
		for (int i = 0 ; i < repoVenda.getQtdNotaFiscal(); i++){
			repoVenda.limparHistoricoNotasFiscais(i);
		}
		return true;
	}
	
	// imprimir todas os produtos - nome(preco)\nqtd\ncodigo
	public String listarProdutos () {
		Produto[] teste = repoEstoque.getProdutos();
		String texto = "";
		for (int i = 0; i < repoEstoque.getQuantSKU(); i++) {
			texto += "\n"+teste[i].toString();
		}
		return texto;
	}
	public boolean alterar(Produto produto) {
		if( produto == null ) {
			System.out.println("\n\n\tErro! itemVenda invalido\n\n");
			return true;
		}
		if(produto.getCodigo() <= 0) {
			System.out.println("\n\n\tErro! codigo invalido!\n\n");
			return false;
		}
		
		int posicao = retornaPosicao(produto.getCodigo());
		
		if(posicao == -1) {
			System.out.println("\n\n\tErro! produto inexistente\n\n");
			return false;
		}
		repoVenda.alterar(posicao, new ItemVenda(produto.getCodigo(), produto.getNome(), produto.getPreco(), produto.getQuantidade()));
		return true;
		
	}
 	
	public String listarVendas () {
		String teste = "";
		NotaFiscal[] notas = repoVenda.getNotaFiscal();
		for (int i = 0; i < repoVenda.getQtdNotaFiscal(); i++) {
			teste = teste+"\n\n"+notas[i].toString();
		}
		return teste;
	}
	
	//PARA A VENDA
	public boolean inserir(ItemVenda itemvenda){
		Produto prod = controlEstoque.buscar(itemvenda.getCodigo());
		if(itemvenda.getQtd() > prod.getQuantidade() ){
			System.out.println("Quantidade maior do que a do produto");
			return false;
		}
		repoVenda.inserir(itemvenda);
		return true;
	}
}
