package repositorios;

import java.awt.List;
import java.util.ArrayList;

import beans.ItemVenda;
import beans.NotaFiscal;

public class RepositorioVenda implements IRepositorioVenda{
	
	// ATRIBUTOS 
	
	private ArrayList<ItemVenda> itensvenda = new ArrayList<>();
	private ArrayList<NotaFiscal> notas = new ArrayList<>();

	// SINGLETON / CONSTRUTOR
	
	private static RepositorioVenda instancia;
	
	private RepositorioVenda () {
		itensvenda = new ArrayList<>();
		notas = new ArrayList<>();
	}
	
	public static RepositorioVenda getInstancia() {
		if( instancia == null ) {
			instancia = new RepositorioVenda();
		}
		return instancia;
	}

	// METODOS
	
	public String listaItensVenda () {
		String text = "";
		for (int i = 0; i < this.itensvenda.size(); i++){ // Imprimir na tela todos os itens de venda
			text += itensvenda.get(i).toString();
		}
		return text;
	}

	public String listarNotasFiscais () {
		String text = "";
		for (int i = 0; i < this.notas.size(); i++){ // Imprimir na tela todas as notas fiscais
			text += notas.get(i).toString();
		}
		return text;
	}
	
	public void adicionarNotaFiscal(NotaFiscal notaFiscal) { // Adiciona nota fiscal
		notas.add(notaFiscal);
	}
	
	public void limparHistoricoNotasFiscais () { // Limpa todas as notas fiscais
		this.notas.clear();
	}
	
	public void inserirItemVenda (ItemVenda item) { // Adiciona um ItemVenda para o mercadinho
		itensvenda.add(item);
	}
}
