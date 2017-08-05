package ufrpe.repositorio;

import java.util.List;

import ufrpe.beans.ItemVenda;
import ufrpe.beans.NotaFiscal;

public interface IRepositorioVenda {

	String listaItensVenda();

	List<ItemVenda> listar();

	String listarNotasFiscais();

	void adicionarNotaFiscal(NotaFiscal notaFiscal);

	void limparHistoricoNotasFiscais();

	void inserirItemVenda(ItemVenda item);

	void salvarArquivo();
}