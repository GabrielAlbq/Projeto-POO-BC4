  package negocio;

import beans.*;
import repositorios.*;


public class ControladorVenda {
	private RepositorioEstoque repoEstoque;
	private RepositorioVenda repoVenda;
	private Pedido pedido;
	
	// singleton
	
	private static ControladorVenda instancia;

	private ControladorVenda() {
		repoVenda = RepositorioVenda.getInstancia();
		repoEstoque = RepositorioEstoque.getInstancia();
		pedido = Pedido.instanciar();
	}
	
	public static ControladorVenda getInstancia() {
		if( instancia == null ) {
			instancia = new ControladorVenda();
		}
		return instancia;
	}
	
	// crud e metodos
	
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
	// se houver ele retorna verdadeiro, para validar a subtra��o deste produto na quantidade
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
			System.out.println("\n\n\tErro! c�digo invalido!");
			return false;
		}
		if( quantidade <= 0 ) {
			System.out.println("\n\n\tErro! quantidade inv�lida!");
			return false;
		}
		int posicao = retornaPosicao(codigo);
		if( posicao == -1 ) {
			System.out.println("Erro! item inexistente!");
			return false;
		}
		boolean checagem = checarQuantidade(codigo, quantidade);
		if( checagem == false ) {
			System.out.println("Erro! n�mero de itens insuficiente!");
			return false;
		}
		pedido.acrescentarPedido(codigo, quantidade, funcionario);
		return true;
	}
	
	public void adicionarNotaFiscal (NotaFiscal notaFiscal) {
		if( notaFiscal == null ) {
			return;
		}
		repoVenda.adicionarNotaFiscal( pedido.gerarNotaFiscal() );
	}
	
	// apaga as notas fiscais armazenadas no repositorioVenda
	public void limparHistoricoNotasFiscais () {
		repoVenda.setQtdNotaFiscal(0);
		for (int i = 0 ; i < repoVenda.getQtdNotaFiscal(); i++){
			repoVenda.limparHistoricoNotasFiscais(i);
		}
	}
	
	// imprimir todas os produtos - nome(pre�o)\nqtd\ncodigo
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
}
