package negocio;

import beans.ItemVenda;
import beans.NotaFiscal;
import beans.Produto;
import repositorios.IRepositorioEstoque;
import repositorios.IRepositorioVenda;
import repositorios.RepositorioEstoque;
import repositorios.RepositorioVenda;

public class ControladorVenda {

	// ATRIBUTOS

	private IRepositorioEstoque repoEstoque;
	private IRepositorioVenda repoVenda;
	private Pedido pedido;
	private ControladorEstoque controlEstoque; // PARA COMPARAR A QUANTIDADE DO
												// PRODUTO ORIGINAL

	// SINGLETON

	private static ControladorVenda instancia;

	private ControladorVenda() {
		repoVenda = RepositorioVenda.getInstancia();
		repoEstoque = RepositorioEstoque.getInstancia();
		pedido = Pedido.getInstancia();
		controlEstoque = ControladorEstoque.getInstancia();
	}

	public static ControladorVenda getInstancia() {
		if (instancia == null) {
			instancia = new ControladorVenda();
		}
		return instancia;
	}

	// METODOS

	public int retornaPosicao(int codigo) {
		if (codigo <= 0) {
			return -1;
		}
		return -1;
	}

	public String listarNotasFiscais() { // lista todas as notas fiscais
		return repoVenda.listarNotasFiscais();
	}

	public String listarItensVenda() { // lista todas os ItensVenda do
										// repositorioVenda
		String texto = "";
		for (ItemVenda iv : repoVenda.listar()) {
			if (repoVenda.listar().isEmpty() == true) {
				texto = "\n\n\tNao ha produtos cadastrados!\n\n";
			} else {
				texto += "\n" + iv.toString();
			}
		}
		return texto;
		// return repoVenda.listaItensVenda();
	}

	public void adicionarNotaFiscal(NotaFiscal notaFiscal) { // adiciona uma
																// nova nota
																// fiscal no
																// repositorioVenda
		if (notaFiscal == null) {
			return;
		}
		repoVenda.adicionarNotaFiscal(notaFiscal);
	}

	public void limparHistoricoNotasFiscais() { // Apaga as notas fiscais
												// armazenadas no
												// repositorioVenda
		repoVenda.limparHistoricoNotasFiscais();
	}

	// CRUD DA PARTE DE VENDAS

	public void inserir(ItemVenda itemvenda) {
		if (itemvenda == null) {
			System.out.println("Item nao existe!");
		}
		Produto prod = controlEstoque.buscar(itemvenda.getCodigo());
		if (itemvenda.getQtd() > prod.getQuantidade()) {
			System.out.println("Quantidade maior do que a do produto");
			return;
		}
		repoVenda.inserirItemVenda(itemvenda);
	}
}
