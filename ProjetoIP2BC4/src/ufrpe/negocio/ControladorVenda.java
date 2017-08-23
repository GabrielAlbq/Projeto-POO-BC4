package ufrpe.negocio;

import java.util.List;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import ufrpe.negocio.beans.Funcionario;
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
	private ControladorFinanceiro controladorFinanceiro;
	private IRepositorioEstoque repoEstoque;
	private IRepositorioVenda repoVenda;
	//private Pedido pedido;
	private ControladorEstoque controlEstoque; // PARA COMPARAR A QUANTIDADE DO
	private int contadorCodigoNota; // variavel que coloca o codigo da nota
	// fiscal de modo que nao se repetem
	private double totalPagar;			// PRODUTO ORIGINAL
	private int qtdItens;
	// SINGLETON

	private static ControladorVenda instancia;

	private ControladorVenda() {
		repoVenda = RepositorioVenda.getInstancia();
		repoEstoque = RepositorioEstoque.getInstancia();
		//pedido = Pedido.getInstancia();
		controladorFinanceiro = ControladorFinanceiro.getInstancia();
		controlEstoque = ControladorEstoque.getInstancia();
		qtdItens = 0;
		contadorCodigoNota = 1000;
		totalPagar = 0;
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
		instancia.repoVenda.salvarArquivo();
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
	
	public void resetarPedido() {
		repoVenda.listar().clear();
	}

	public void encerrarPedido() throws NegocioException{
		for (ItemVenda item : repoVenda.listar()) {
			totalPagar += item.valorTotal();
		}
		controladorFinanceiro.receberDinheiroVenda(totalPagar);

		for (ItemVenda item : repoVenda.listar()) {
			controlEstoque.subtrairProduto(item.getCodigo(), item.getQtd());
		}
	}

	public void gerarNotaFiscal(Funcionario funcionario) {
		NotaFiscal teste = new NotaFiscal(funcionario, repoVenda.getItensvenda(), totalPagar, contadorCodigoNota, repoVenda.listar().size());
		//controlvenda.adicionarNotaFiscal(teste);
		contadorCodigoNota++;
		repoVenda.adicionarNotaFiscal(teste);
		//instancia.repoVenda.salvarArquivo();
		repoVenda.listar().clear();
		//repoVenda.limparArrayItemVenda();
		this.totalPagar = 0;
		instancia.repoVenda.salvarArquivo();
		Alert alert = new Alert(AlertType.CONFIRMATION);
		alert.setTitle("Nota fiscal gerada");
		alert.setHeaderText(null);
		alert.setContentText("Nota fiscal gerada com sucesso!!!!");
		alert.showAndWait();
	}

}
