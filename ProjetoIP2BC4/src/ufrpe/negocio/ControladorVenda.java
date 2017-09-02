package ufrpe.negocio;

import java.util.List;

import ufrpe.negocio.beans.Funcionario;
import ufrpe.negocio.beans.ItemVenda;
import ufrpe.negocio.beans.NotaFiscal;
import ufrpe.negocio.beans.Produto;
import ufrpe.negocio.exception.IdentificacaoInvalidaException;
import ufrpe.negocio.exception.InstanciaInexistenteException;
import ufrpe.negocio.exception.NegocioException;
import ufrpe.negocio.exception.QuantidadeInvalidaException;
import ufrpe.repositorio.IRepositorioVenda;
import ufrpe.repositorio.RepositorioVenda;

public class ControladorVenda {

	// ATRIBUTOS
	private ControladorFinanceiro controladorFinanceiro;

	private IRepositorioVenda repoVenda;
	private ControladorEstoque controlEstoque;
	private int contadorCodigoNota;
	private double totalPagar;

	// SINGLETON

	private static ControladorVenda instancia;

	private ControladorVenda() {
		repoVenda = RepositorioVenda.getInstancia();
		controladorFinanceiro = ControladorFinanceiro.getInstancia();
		controlEstoque = ControladorEstoque.getInstancia();
		contadorCodigoNota = repoVenda.getContadorCodigoNota();
		totalPagar = 0;
	}

	public static ControladorVenda getInstancia() {
		if (instancia == null) {
			instancia = new ControladorVenda();
		}
		return instancia;
	}
	
	// METODOS

	public List<NotaFiscal> listarNotasFiscais() {
		return repoVenda.listarNotasFiscais();
	}

	public List<ItemVenda> listarItensVenda() throws NegocioException {
		if (repoVenda.listar().isEmpty() == true) {
			throw new InstanciaInexistenteException("\nNao ha produtos cadastrados!\n");
		}
		return repoVenda.listar();
	}

	public void limparHistoricoNotasFiscais() {
		repoVenda.limparHistoricoNotasFiscais();
	}

	// CRUD DA PARTE DE VENDAS

	public void inserir(ItemVenda itemvenda) throws NegocioException {
		if (itemvenda == null) {
			throw new RuntimeException("\nInstancia de NotaFiscal nula!\n");
		}
		Produto prod = controlEstoque.buscar(itemvenda.getCodigo());
		if (itemvenda.getQtd() > prod.getQuantidade() || itemvenda.getQtd() < 0) {
			throw new QuantidadeInvalidaException("\nQuantidade maior do que a do produto ou menor que 0\n");
		}
		repoVenda.inserirItemVenda(itemvenda);
	}

	public void remover(int cod) throws NegocioException {
		if (cod <= 0) {
			throw new IdentificacaoInvalidaException("\nCodigo (" + cod + ") invalido\n");
		}
		if (repoVenda.listar().isEmpty() == true) {
			throw new InstanciaInexistenteException("\nNao há item cadastrados\n");
		}
		int posicao = this.retornarPosicao(cod);
		if (posicao == -1) {
			throw new InstanciaInexistenteException("\nNao ha item de codigo (" + cod + ") para remover\n");
		}
		repoVenda.removerItemVenda(posicao);

	}

	private int retornarPosicao(int codigo) {

		if (codigo <= 0) {
			return -1;
		}
		for (ItemVenda iv : repoVenda.listar()) {
			if (codigo == iv.getCodigo()) {
				return repoVenda.listar().indexOf(iv);
			}
		}
		return -1;
	}

	public void resetarPedido() {
		repoVenda.listar().clear();
	}

	public void encerrarPedido() throws NegocioException {
		for (ItemVenda item : repoVenda.listar()) {
			totalPagar += item.valorTotal();
		}
		controladorFinanceiro.receberDinheiroVenda(totalPagar);

		for (ItemVenda item : repoVenda.listar()) {
			controlEstoque.subtrairProduto(item.getCodigo(), item.getQtd());
		}
	}

	public void gerarNotaFiscal(Funcionario funcionario) {
		NotaFiscal teste = new NotaFiscal(funcionario, repoVenda.getItensvenda(), totalPagar, contadorCodigoNota,
				repoVenda.listar().size());
		repoVenda.adicionarNotaFiscal(teste);
		repoVenda.listar().clear();
		this.totalPagar = 0;
		instancia.repoVenda.salvarArquivo();
	}

}
