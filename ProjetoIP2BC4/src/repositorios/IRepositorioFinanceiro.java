package repositorios;

import java.util.ArrayList;

import beans.Funcionario;
import beans.Produto;

public interface IRepositorioFinanceiro {
	
	double getRendaBruta();

	void setRendaBruta(double rendaBruta);
	
	double totalSalarioFuncionarios ();
	
	double totalFornecedor ();
	
	void pagarFuncionario (Funcionario func);//, int posicao);
	
	double rendaLiquida();
	
	void receberDinheiroVenda (double valor);
	
	String exibirFinancas ();

}
