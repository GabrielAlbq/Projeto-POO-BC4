package repositorios;

import beans.ItemVenda;
import beans.NotaFiscal;

public interface IRepositorioVenda {
	
	public void limparHistoricoNotasFiscais ();
	
	public void listarVendas ();
	
	public void listarNotasFiscais ();
	
	public void adicionarNotaFiscal(NotaFiscal notaFiscal);
	
	public void inserir(ItemVenda itemvenda);
	
//	public void remover(int posicao);
//	
//	public void alterar(int posicao, ItemVenda item);
	
}