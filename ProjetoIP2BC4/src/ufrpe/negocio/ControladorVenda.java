package ufrpe.negocio;

import java.util.List;

import ufrpe.negocio.beans.ItemVenda;
import ufrpe.negocio.beans.NotaFiscal;
import ufrpe.negocio.beans.Produto;
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

	public List<NotaFiscal> listarNotasFiscais() { // lista todas as notas fiscais
		return repoVenda.listarNotasFiscais();
	}

	public List<ItemVenda> listarItensVenda() throws NegocioException{ // lista todas os ItensVenda do
										// repositorioVenda
		if (repoVenda.listar().isEmpty() == true) {
			throw new InstanciaInexistenteException("\nNao ha produtos cadastrados!\n");
		}
		return repoVenda.listar();
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
		if (itemvenda.getQtd() > prod.getQuantidade() || itemvenda.getQtd() < 0) {
			//System.out.println("Quantidade maior do que a do produto");
			throw new QuantidadeInvalidaException("\nQuantidade maior do que a do produto ou menor que 0\n");
		}
		repoVenda.inserirItemVenda(itemvenda);
		//instancia.repoVenda.salvarArquivo();
	}
}
