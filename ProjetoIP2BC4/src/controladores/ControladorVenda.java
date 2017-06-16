package controladores;

import beans.ItemVenda;
import repositorios.RepositorioVenda;


public class ControladorVenda {
	private RepositorioVenda repoItemVenda;
	
	// singleton
	
	private static ControladorVenda instancia;

	private ControladorVenda() {
		instancia = new ControladorVenda();
	}
	
	public static ControladorVenda instanciar() {
		if( instancia == null ) {
			instancia = new ControladorVenda();
		}
		return instancia;
	}
	
	// crud
	
	public int retornaPosicao(int id) {
		if( id <= 0 ) {
			return -1;
		}
		
		ItemVenda[] teste = repoItemVenda.getArrayItem();
		
		for (int i = 0; i < repoItemVenda.getTAM_MAX() ; i++) {
			if( id == teste[i].getCodigo() ) {
				return i;
			}
		}
		return -1;
	}
	
	public boolean inserir(ItemVenda itemInserir) {
		if( itemInserir == null ) {
			System.out.println("\n\n\tErro, item nulo!\n\n");
			return false;
		}
		if( itemInserir.getCodigo() <= 0 ) {
			System.out.println("\n\n\tErro, código inválido!\n\n");
		}
		if( itemInserir.getNome() == null ) {
			System.out.println("\n\n\tErro, nome nulo!\n\n");
		}
		if( itemInserir.getPreco() <= 0 ) {
			System.out.println("\n\n\tErro, preço invalido!\n\n");
		}
		int checar = retornaPosicao(itemInserir.getCodigo());
		if( checar != -1 ) {
			System.out.println("\n\n\tErro, item já inserido!\n\n");
			return false;
		}
		repoItemVenda.inserir(itemInserir);
		return true;
	}
	
	public boolean remover(int codigo) {
		if(codigo <= 0 ) {
			System.out.println("\n\n\tErro, identificacao inválida!\n\n");
			return false;
		}
		int checar = retornaPosicao(codigo);
		if( checar == -1 ) {
			System.out.println("\n\n\tErro, item inexistente!\n\n");
			return false;
		}
		repoItemVenda.remover(codigo);
		return true;
	}
}
