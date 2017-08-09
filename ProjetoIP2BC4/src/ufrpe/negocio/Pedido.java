package ufrpe.negocio;

import ufrpe.beans.Funcionario;
import ufrpe.beans.ItemVenda;
import ufrpe.beans.NotaFiscal;
import ufrpe.negocio.exception.NegocioException;
import ufrpe.repositorio.RepositorioVenda;

public class Pedido {

	// ATRIBUTOS

	private ControladorEstoque controlEstoque;
	private RepositorioVenda repoVenda;
	private ControladorFinanceiro controladorFinanceiro;
	private int contadorCodigoNota; // variavel que coloca o codigo da nota
									// fiscal de modo que nao se repetem
	private double totalPagar; // zera apos metodo encerrarPedido ser chamado
	private ItemVenda[] arrayItem; // zera apos o metodo encerrarPedido ser
									// chamado
	private int qtdItens; // zera apos o metodo encerrarPedido ser chamado
	// SINGLETON

	private static Pedido instancia;

	private Pedido() {
		controlEstoque = ControladorEstoque.getInstancia();
		repoVenda = RepositorioVenda.getInstancia();
		controladorFinanceiro = ControladorFinanceiro.getInstancia();
		qtdItens = 0;
		contadorCodigoNota = 1000;
		totalPagar = 0;
	}

	public static Pedido getInstancia() {
		if (instancia == null) {
			instancia = new Pedido();
		}
		return instancia;
	}

	// METODOS

	public void resetarPedido() {
		repoVenda.getItensvenda().clear();
	}

	public void encerrarPedido() throws NegocioException{

		for (ItemVenda item : repoVenda.getItensvenda()) {
			totalPagar += item.valorTotal();
		}
		System.out.println("Total a pagar: " + totalPagar);
		controladorFinanceiro.receberDinheiroVenda(totalPagar);

		for (ItemVenda item : repoVenda.getItensvenda()) {
			controlEstoque.subtrairProduto(item.getCodigo(), item.getQtd());
		}
	}

	public void gerarNotaFiscal(Funcionario funcionario) {
		NotaFiscal teste = new NotaFiscal(funcionario, repoVenda.getItensvenda(), totalPagar, contadorCodigoNota,
		repoVenda.getItensvenda().size());
		repoVenda.adicionarNotaFiscal(teste);
		contadorCodigoNota++;
		repoVenda.limparArrayItemVenda();
		this.totalPagar = 0;
	}

}