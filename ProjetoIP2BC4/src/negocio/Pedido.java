package negocio;

import repositorios.*;
import beans.*;

public class Pedido {
	
	// ATRIBUTOS 
	
	private ControladorEstoque controlEstoque;
	private RepositorioVenda repoVenda;
	private ControladorFinanceiro controladorFinanceiro;
	private int contadorCodigoNota;   // variavel que coloca o codigo da nota fiscal de modo que nao se repetem
	private double totalPagar;        // zera apos metodo encerrarPedido ser chamado
	private ItemVenda[] arrayItem;    // zera apos o metodo encerrarPedido ser chamado
	private int qtdItens; 			  // zera apos o metodo encerrarPedido ser chamado
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
		if( instancia == null ) {
			instancia = new Pedido();
		}
		return instancia;
	}
	
	// METODOS 

	public void resetarPedido () {
		for (int i = 0; i < qtdItens; i++) {
			arrayItem[i] = null;
		}
		this.totalPagar = 0;
		this.qtdItens = 0;
		repoVenda.getItensvenda().clear();
	}

	
	public void encerrarPedido () {
		
		for(ItemVenda item : repoVenda.getItensvenda()){
			totalPagar += item.valorTotal() ;
		}


		System.out.println("Total a pagar: " +totalPagar);

		controladorFinanceiro.receberDinheiroVenda(totalPagar);
		
		
		for(ItemVenda item : repoVenda.getItensvenda()){
			controlEstoque.subtrairProduto(item.getCodigo(), item.getQtd());
		}
	}
	
//	public NotaFiscal gerarNotaFiscal(Funcionario funcionario) {
//		NotaFiscal teste = new NotaFiscal(funcionario, repoVenda.getItensvenda(), totalPagar, contadorCodigoNota, repoVenda.getItensvenda().size());
//		repoVenda.adicionarNotaFiscal( teste );
//		contadorCodigoNota++;
//		return teste;
//	}
	public void gerarNotaFiscal(Funcionario funcionario) {
		NotaFiscal teste = new NotaFiscal(funcionario, repoVenda.getItensvenda(), totalPagar, contadorCodigoNota, repoVenda.getItensvenda().size());
		repoVenda.adicionarNotaFiscal( teste );
		contadorCodigoNota++;
		repoVenda.limparArrayItemVenda();
		this.totalPagar = 0;
	}
	
}
