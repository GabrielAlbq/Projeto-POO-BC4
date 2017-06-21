package repositorios;

import beans.ItemVenda;
import beans.NotaFiscal;

public class RepositorioVenda {
	private int TAM_MAX = 100;
	private int TAM_MAX_NOTAS_FISCAIS = 1000;
	private int qtdItem;
	private int qtdNotaFiscal;
	private ItemVenda[] arrayItem;// itens genericos que serao vendidos, copias da classe Produto.
	private NotaFiscal[] notaFiscal; 

	// SINGLETON
	
	private static RepositorioVenda instancia;
	
	private RepositorioVenda () {
		arrayItem = new ItemVenda[TAM_MAX];
		notaFiscal = new NotaFiscal[TAM_MAX_NOTAS_FISCAIS];
		qtdItem = 0;
		qtdNotaFiscal = 0;
	}
	
	public static RepositorioVenda getInstancia() {
		if( instancia == null ) {
			instancia = new RepositorioVenda();
		}
		return instancia;
	}

	// METODOS
	
	
	// limpa todas as notas fiscais
	public void limparHistoricoNotasFiscais (int posicao) {
		this.notaFiscal[posicao] = null;
	}

	// imprimir todas as notas fiscais

	public String listarNotasFiscais () {
		String texto = "";
		if( qtdNotaFiscal == 0 ) {
			texto += "\n\n\tO historico ja esta vazio!\n\n";
		}
		else {
			for (int i = 0; i < qtdNotaFiscal; i++) {
				texto = texto+notaFiscal[i].toString();
			}
		}
		return texto;
	}
	
	
	public void adicionarNotaFiscal(NotaFiscal notaFiscal) {
		this.notaFiscal[qtdNotaFiscal] = notaFiscal;
		qtdNotaFiscal++;
	}
	

	// nao prescisa de validacoes, pois estas ja foram feitas no controlador estoque!
	// nao precisa de validacoes, pois estas ja foram feitas no controlador estoque!
	public void inserir(ItemVenda itemvenda) {
		arrayItem[qtdItem] = itemvenda;
		qtdItem++;
	}
	
	public void remover(int posicao) {
		arrayItem[posicao] = null;
		qtdItem--;
	}
	
	public void alterar(int posicao, ItemVenda item) {
		arrayItem[posicao] = item;
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
	
	public int getQtdItem() {
		return qtdItem;
	}
	
	public void setQtdNotaFiscal(int n) {
		qtdNotaFiscal = n;
	}
}
