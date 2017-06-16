package repositorios;

import java.util.ArrayList;

import beans.ItemVenda;

public class RepositorioVenda {
	private int TAM_MAX = 100;
	private int qtdItem;
	private ItemVenda[] arrayItem;

	// singleton
	
	private static RepositorioVenda instancia;
	
	private RepositorioVenda () {
		arrayItem = new ItemVenda[TAM_MAX];
	}
	
	public static RepositorioVenda intanciar() {
		if( instancia == null ) {
			instancia = new RepositorioVenda();
		}
		return instancia;
	}
	
	// CRUD
	
	public void inserir(ItemVenda itemInserir) {
		//arrayItem[]
	}
	
	public void remover(int codigo) {
		
	}
	
	public void alterar(ItemVenda item, int posicao) {
		
	}
	
	public ItemVenda buscar (int codigo) {
		return null;
	}

	// gets
	
	public ItemVenda[] getArrayItem() {
		return this.arrayItem;
	}
	public int getTAM_MAX() {
		return this.TAM_MAX;
	}
}
