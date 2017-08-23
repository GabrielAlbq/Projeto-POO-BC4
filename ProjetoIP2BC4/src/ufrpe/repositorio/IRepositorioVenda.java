package ufrpe.repositorio;

import java.util.ArrayList;
import java.util.List;

import ufrpe.negocio.beans.ItemVenda;
import ufrpe.negocio.beans.NotaFiscal;

public interface IRepositorioVenda {

	String listaItensVenda();

	List<ItemVenda> listar();

	List<NotaFiscal> listarNotasFiscais();
	
	ArrayList<ItemVenda> getItensvenda();
	 
	void adicionarNotaFiscal(NotaFiscal notaFiscal);

	void limparHistoricoNotasFiscais();

	void inserirItemVenda(ItemVenda item);

	void salvarArquivo();
}