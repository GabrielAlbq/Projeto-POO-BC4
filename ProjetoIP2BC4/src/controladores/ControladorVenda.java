package controladores;

import beans.*;
import repositorios.*;


public class ControladorVenda {
	private RepositorioEstoque repoEstoque;
	private RepositorioVenda repoVenda;
	private Pedido pedido;
	
	// singleton
	
	private static ControladorVenda instancia;

	private ControladorVenda() {
		repoVenda = RepositorioVenda.intanciar();
		repoEstoque = RepositorioEstoque.getInstancia();
		pedido = Pedido.instanciar();
	}
	
	public static ControladorVenda instanciar() {
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
	
	public boolean inserir(ItemVenda item) {
		// TODO
		return false;
	}
	
	public boolean remover(int codigo) {
		// TODO
		
		return false;
	}
	
	public boolean alterar(int codigo) {
		// TODO
		return false;
	}
	
	public ItemVenda buscar() {
		// TODO
		return null;
	}
	// este metodo checa se existe o produto com este codigo e quantidade no repositorioEstoque
	// se houver ele retorna verdadeiro, para validar a subtração deste produto na quantidade
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
	
	public boolean efetuarPedido(int codigo, int quantidade, Funcionario funcionario) {
		if( codigo <= 0 ) {
			System.out.println("\n\n\tErro! código inválido!");
			return false;
		}
		if( quantidade <= 0 ) {
			System.out.println("\n\n\tErro! quantidade inválida!");
			return false;
		}
		int posicao = retornaPosicao(codigo);
		if( posicao == -1 ) {
			System.out.println("Erro! item inexistente!");
			return false;
		}
		boolean checagem = checarQuantidade(codigo, quantidade);
		if( checagem == false ) {
			System.out.println("Erro! número de itens insuficiente!");
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
	public void limparHistorico () {
		repoVenda.setQtdNotaFiscal(0);
		for (int i = 0 ; i < repoVenda.getQtdNotaFiscal(); i++){
			repoVenda.limparHistorico(i);
		}
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
