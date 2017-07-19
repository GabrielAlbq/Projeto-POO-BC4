package repositorios;

import beans.Funcionario;
import beans.Produto;

public interface IRepositorioFinanceiro {
	
	public double totalSalarioFuncionarios ();
	
	public double totalFornecedor ();
	
	public void pagarFuncionario (Funcionario func, int posicao);
	
	public double rendaLiquida();
	
	public void receberDinheiroVenda (double valor);
	
	public String exibirFinancas ();

}
