package negocio;

import repositorios.*;
import beans.*;

public class Pedido {
	
	// ATRIBUTOS 
	
	private int TAM_MAX = 100;
	private ControladorEstoque controlEstoque;
	private RepositorioVenda repoVenda;
	private ControladorFinanceiro controladorFinanceiro;
	private int contadorCodigoNota;   // variavel que coloca o codigo da nota fiscal de modo que nao se repetem
	private double totalPagar;        // zera apos metodo encerrarPedido ser chamado
	private ItemVenda[] arrayItem;    // zera apos o metodo encerrarPedido ser chamado
	private int qtdItens; 			  // zera apos o metodo encerrarPedido ser chamado
	private Funcionario funcionario;  // zera apos o metodo encerrarPedido ser chamado
	
	// SINGLETON
	
	private static Pedido instancia;
	
	private Pedido() {
		controlEstoque = ControladorEstoque.getInstancia();
		repoVenda = RepositorioVenda.getInstancia();
		
		arrayItem = new ItemVenda[TAM_MAX];
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
	
	// Controla o pedido do cliente
	// Estes metodos contam a quantidade de itens que o cliente pediu, colocand-os no array para emitir a nota fiscal
	// Efetua a venda e checa se no array ja possui um item repetido para somar o novo
	
	ItemVenda[] itempedido = new ItemVenda[TAM_MAX];
	
	int qtditem = 0;
	int i = 0;
	public void acrescentarPedido(int codigo, int quantidade, Funcionario funcionario) {
		
		this.funcionario = funcionario;
		/*
		 * Este laco busca se o item com este codigo ja foi adionado.
		 * Se sim, ele acrescente a quantidade nele
		 * Se nao, significa que o item nao esta cadastrado
		 * o outro laco faz o trabalho de instancia-lo
		 * o outro laco faz o trabalho de instancia-lo
		 */
		//ItemVenda[] itempedido = new ItemVenda[TAM_MAX];
		System.out.println("Item antes de acrescentar pedido: " + itempedido[0]);
		for (int i = 0; i < qtditem ; i++) {
			if(itempedido[i].getCodigo() == codigo) {
				itempedido[i].setQtd( itempedido[i].getQtd()+quantidade );
				return;
			}
		}
		//for(int i = 0; i < qtditem; i++){
			itempedido[i].setNome(controlEstoque.buscar(codigo).getNome());
			itempedido[i].setCodigo(codigo);
			itempedido[i].setQtd(quantidade);
			itempedido[i].setPreco(controlEstoque.buscar(codigo).getPreco());
			itempedido[i].valorTotal();
			
		//}
			i++;
		qtditem++;
		
//		for (int i = 0; i < qtdItens ; i++) {
//			if(arrayItem[i].getCodigo() == codigo) {
//				arrayItem[i].setQtd( arrayItem[i].getQtd()+quantidade );
//				return;
//			}
//		}
		
//		ItemVenda[] testeArray = repoVenda.getArrayItem(); 
//		
//		// adiciona o item ainda nao instanciado no array
//		for (int i = 0; i < qtdItens; i++) {
//			if( codigo ==  testeArray[i].getCodigo() ) {
//				arrayItem[i] = testeArray[i];
//				qtdItens++;
//				return;
//			}
//			
//		}
		System.out.println("Item depois de acrescentar pedido: " + itempedido[0]);
		return;
	}
	
	// eh usado apos a venda ser concluida ou para cancelar o pedido
	public void resetarPedido () {
		for (int i = 0; i < qtdItens; i++) {
			arrayItem[i] = null;
		}
		this.totalPagar = 0;
		this.qtdItens = 0;
		this.funcionario = null;
	}
	
	// emitir nota fiscal / ocorre quando o pedido eh concluido;
	// envia o dinheiro para as finacas e subtrai a quantidade do estoque
	// apos o envio desses dados este metodo "reseta" a classe para receber um novo pedido
	
	public void encerrarPedido () {
		// este laco faz o somatorio da venda atual
//		for (int i = 0; i < qtdItens; i++) {
//			totalPagar += arrayItem[i].valorTotal();
//		}
		
		
		for (int i = 0; i < repoVenda.getQtdItem(); i++) {
			totalPagar += repoVenda.getArrayItem()[i].valorTotal();
		}


		System.out.println("Total a pagar: " +totalPagar);
		//TODO  o bug das vendas esta nesta parte!


		//controladorFinanceiro.receberDinheiroVenda(totalPagar);
		
		
		
		// laco que subtrai os itens de venda dos produtos estocados
		for (int i = 0; i < repoVenda.getQtdItem(); i++) {
			controlEstoque.subtrairProduto( repoVenda.getArrayItem()[i].getCodigo() , repoVenda.getArrayItem()[i].getQtd());
		}
	//	resetarPedido(); COMENTEI ESSA LINHA PQ O TOTAL A PAGAR DA NOTA FISCAL RECEBIA 0 JA que O RESETAR atribuia 0 p total a pagar.
	}
	
	// gera uma nota fiscal, deve ser usada antes do metodo encerrar pedido
	public NotaFiscal gerarNotaFiscal(Funcionario funcionario) {
		NotaFiscal teste = new NotaFiscal(funcionario, repoVenda.getArrayItem(), totalPagar, contadorCodigoNota, repoVenda.getQtdItem());
		repoVenda.adicionarNotaFiscal( teste );
		contadorCodigoNota++;
		return teste;
	}
}
