package repositorios;

import java.util.ArrayList;

import beans.*;

public class RepositorioVenda {
	private int TAM_MAX = 100;
	private int TAM_MAX_NOTAS_FISCAIS = 1000;
	private int qtdItem;
	private int qtdNotaFiscal;
	private ItemVenda[] arrayItem;// itens genericos que serao vendidos, copias da classe Produto.
	private NotaFiscal[] notaFiscal; 

	// Singleton
	
	private static RepositorioVenda instancia;
	
	private RepositorioVenda () {
		arrayItem = new ItemVenda[TAM_MAX];
		notaFiscal = new NotaFiscal[TAM_MAX_NOTAS_FISCAIS];
		qtdItem = 0;
		qtdNotaFiscal = 0;
	}
	
	public static RepositorioVenda intanciar() {
		if( instancia == null ) {
			instancia = new RepositorioVenda();
		}
		return instancia;
	}

	// metodos
	
	public void limparHistorico (int posicao) {
		this.notaFiscal[posicao] = null;
	}
	
	public void adicionarNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal[qtdNotaFiscal] = notaFiscal;
		qtdNotaFiscal++;
	}
	
	// GETS
	
	public ItemVenda[] getArrayItem() {
		return arrayItem;
	}

	public NotaFiscal[] getNotaFiscal() {
		return notaFiscal;
	}
	
	public int getQtdNotaFiscal() {
		return qtdNotaFiscal;
	}
	
	public void setQtdNotaFiscal(int n) {
		qtdNotaFiscal = n;
	}
}
