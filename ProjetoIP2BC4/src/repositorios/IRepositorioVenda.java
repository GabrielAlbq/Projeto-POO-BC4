package repositorios;

import beans.ItemVenda;
import beans.NotaFiscal;

public interface IRepositorioVenda {
	
	String listaItensVenda ();

	String listarNotasFiscais ();
	
	void adicionarNotaFiscal(NotaFiscal notaFiscal);
	
	void limparHistoricoNotasFiscais ();
	
	void inserirItemVenda (ItemVenda item);
}