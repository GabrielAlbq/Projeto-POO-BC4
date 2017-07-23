package repositorios;

import java.util.List;

import beans.ItemVenda;
import beans.NotaFiscal;

public interface IRepositorioVenda {
	
	String listaItensVenda ();
	
	List<ItemVenda> listar();
	
	String listarNotasFiscais ();
	
	void adicionarNotaFiscal(NotaFiscal notaFiscal);
	
	void limparHistoricoNotasFiscais ();
	
	void inserirItemVenda (ItemVenda item);
}