package repositorios;

import java.awt.List;
import java.util.ArrayList;

import beans.ItemVenda;
import beans.NotaFiscal;

public class RepositorioVenda implements IRepositorioVenda{
	
	// ATRIBUTOS 
	
	private ArrayList<ItemVenda> vendas = new ArrayList<>();
	private ArrayList<NotaFiscal> nota = new ArrayList<>();

	// SINGLETON / CONSTRUTOR
	
	private static RepositorioVenda instancia;
	
	private RepositorioVenda () {
		vendas = new ArrayList<>();
		nota = new ArrayList<>();
	}
	
	public static RepositorioVenda getInstancia() {
		if( instancia == null ) {
			instancia = new RepositorioVenda();
		}
		return instancia;
	}

	// METODOS
	
	public void limparHistoricoNotasFiscais () { // Limpa todas as notas fiscais
		this.vendas.clear();
	}
	
	public void listarVendas () {
		for (int i = 0; i < this.vendas.size(); i++){ // Imprimir na tela todas as vendas realizadas
			this.vendas.get(i).toString();
		}
	}

	public void listarNotasFiscais () {
		for (int i = 0; i < this.vendas.size(); i++){ // Imprimir na tela todas as notas fiscais
			this.nota.get(i).toString();
		}
	}
	
	public void adicionarNotaFiscal(NotaFiscal notaFiscal) { // Adiciona nota fiscal
		this.nota.add(notaFiscal);
	}
	
	public void inserir(ItemVenda itemvenda) { // Adiciona um produto para o mercadinho
		this.vendas.add(itemvenda);
	}
	
//	public void remover(int posicao) { // Remove um produto do mercadinho
//		this.vendas.remove(posicao);
//	}
//	
//	public void alterar(int posicao, ItemVenda item) { // Altera um produto do mercadinho
//		this.vendas.set(posicao, item);
//	}


}
