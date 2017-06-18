package negocio;

import repositorios.*;
import beans.*;

public class Pedido {
	private int TAM_MAX = 100;

	private ControladorEstoque controlEstoque;
	private RepositorioVenda repoVenda;
	
	private int contadorCodigoNota;   // variavel que coloca o codigo da nota fiscal de modo que nao se repetem
	private double totalPagar;        // zera após o metodo encerrarPedido ser chamado
	private ItemVenda[] arrayItem;    // zera após o metodo encerrarPedido ser chamado
	private int qtdItens; 			  // zera após o metodo encerrarPedido ser chamado
	private Funcionario funcionario;  // zera após o metodo encerrarPedido ser chamado
	
	// instanciar o repositorioFinanceiro
	
	
	// singleton
	
	private static Pedido instancia;
	
	private Pedido() {
		controlEstoque = ControladorEstoque.getInstancia();
		repoVenda = RepositorioVenda.intanciar();
		
		arrayItem = new ItemVenda[TAM_MAX];
		qtdItens = 0;
		contadorCodigoNota = 1000;
		totalPagar = 0;
	}
	
	public static Pedido instanciar() {
		if( instancia == null ) {
			instancia = new Pedido();
		}
		return instancia;
	}
	
	// controla o pedido do cliente
	// estes metodos contam a quantidade de itens que o cliente pediu, colocand-os no array para emitir a nota fiscal
	// efetua a venda e checa se no array já possui um item repetido para somar o novo
	
	public void acrescentarPedido(int codigo, int quantidade, Funcionario funcionario) {
		
		this.funcionario = funcionario;
		/*
		 * Este laço busca se o item com este codigo já foi adionado.
		 * Se sim, ele acrescente a quantidade nele
		 * Se não, significa que o item nao está cadastrado
		 * o outro laço faz o trabalho de instancia-lo
		 */
		for (int i = 0; i < qtdItens ; i++) {
			if(arrayItem[i].getCodigo() == codigo) {
				arrayItem[i].setQtd( arrayItem[i].getQtd()+quantidade );
				return;
			}
		}
		
		ItemVenda[] testeArray = repoVenda.getArrayItem(); 
		
		// adiciona o item ainda nao instanciado no array
		for (int i = 0; i < qtdItens; i++) {
			if( codigo ==  testeArray[i].getCodigo() ) {
				arrayItem[i] = testeArray[i];
				qtdItens++;
				return;
			}
		}
		return;
	}
	
	// é usado após a venda ser concluida ou para cancelar o pedido
	public void resetarPedido () {
		for (int i = 0; i < qtdItens; i++) {
			arrayItem[i] = null;
		}
		this.totalPagar = 0;
		this.qtdItens = 0;
		this.funcionario = null;
	}
	
	// emitir nota fiscal / ocorre quando o pedido é concluido;
	// envia o dinheiro para as finaças e subtrai a quantidade do estoque
	// após o envio desses dados este metodo "reseta" a classe para receber um novo pedido
	
	public void encerrarPedido () {
		// este laço faz o somatorio da venda atual
		for (int i = 0; i < qtdItens; i++) {
			totalPagar += arrayItem[i].valorTotal();
		}
		
		// laço que subtrai os itens de venda dos produtos estocados
		for (int i = 0; i < qtdItens; i++) {
			controlEstoque.subtrairProduto( arrayItem[i].getCodigo() , arrayItem[i].getQtd());
		}
		resetarPedido();
	}
	
	// gera uma nota fiscal, deve ser usada antes do metodo encerrar pedido
	public NotaFiscal gerarNotaFiscal() {
		NotaFiscal teste = new NotaFiscal(funcionario, arrayItem, totalPagar, contadorCodigoNota, qtdItens);
		repoVenda.adicionarNotaFiscal( teste );
		contadorCodigoNota++;
		return teste;
	}
}
