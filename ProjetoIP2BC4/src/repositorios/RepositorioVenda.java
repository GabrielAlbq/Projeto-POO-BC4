package repositorios;

import java.util.ArrayList;

import beans.ItemVenda;
import beans.Produto;

public class RepositorioVenda {
	private int TAM_MAX = 100;
	private int qtdItem;
	private ItemVenda[] arrayItem;

	// Singleton
	
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
	
	public void efetuarVenda(ItemVenda itemInserir) {
		this.arrayItem[qtdItem] = itemInserir;
		this.qtdItem++;
	}
	
	public void limparHistorico() {
		for (int i = 0 ; i < qtdItem; i++){
			arrayItem[i] = null;
		}
		qtdItem = 0;
	}
	
	public ItemVenda buscar (int codigo) {
		return this.arrayItem[codigo];
	}

	// GETS
	
	public ItemVenda[] getArrayItem() {
		return this.arrayItem;
	}
	public int getTAM_MAX() {
		return this.TAM_MAX;
	}
}
