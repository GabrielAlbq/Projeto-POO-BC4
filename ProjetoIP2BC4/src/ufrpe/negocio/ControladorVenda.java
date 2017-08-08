package ufrpe.negocio;

import ufrpe.beans.ItemVenda;
import ufrpe.beans.NotaFiscal;
import ufrpe.beans.Produto;
import ufrpe.negocio.exception.InstanciaInexistenteException;
import ufrpe.negocio.exception.NegocioException;
import ufrpe.negocio.exception.QuantidadeInvalidaException;
import ufrpe.repositorio.IRepositorioEstoque;
import ufrpe.repositorio.IRepositorioVenda;
import ufrpe.repositorio.RepositorioEstoque;
import ufrpe.repositorio.RepositorioVenda;

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

	public String listarNotasFiscais() { // lista todas as notas fiscais
		return repoVenda.listarNotasFiscais();
	}

	public String listarItensVenda() throws NegocioException{ // lista todas os ItensVenda do
										// repositorioVenda
		if (repoVenda.listar().isEmpty() == true) {
			throw new InstanciaInexistenteException("\nNao ha produtos cadastrados!\n");
		}
		String texto = "";
		for (ItemVenda iv : repoVenda.listar()) {
			texto += "\n" + iv.toString();
		}
		return texto;
		// return repoVenda.listaItensVenda();
	}

	public void adicionarNotaFiscal(NotaFiscal notaFiscal) throws NegocioException{ // adiciona uma
																// nova nota
																// fiscal no
																// repositorioVenda
		if (notaFiscal == null) {
			throw new RuntimeException("\nInstancia de NotaFiscal nula!\n");
		}
		repoVenda.adicionarNotaFiscal(notaFiscal);
	}

	public void limparHistoricoNotasFiscais() { // Apaga as notas fiscais
												// armazenadas no repositorioVenda
		repoVenda.limparHistoricoNotasFiscais();
	}

	// CRUD DA PARTE DE VENDAS

	public void inserir(ItemVenda itemvenda) throws NegocioException{
		if (itemvenda == null) {
			System.out.println("Item nao existe!");
			throw new RuntimeException("\nInstancia de NotaFiscal nula!\n");
		}
		Produto prod = controlEstoque.buscar(itemvenda.getCodigo());
		if (itemvenda.getQtd() > prod.getQuantidade()) {
			//System.out.println("Quantidade maior do que a do produto");
			throw new QuantidadeInvalidaException("\nQuantidade maior do que a do produto\n");
		}
		repoVenda.inserirItemVenda(itemvenda);
		instancia.repoVenda.salvarArquivo();
	}
}
