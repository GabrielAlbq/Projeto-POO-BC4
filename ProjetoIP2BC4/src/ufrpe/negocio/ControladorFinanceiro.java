package ufrpe.negocio;

import ufrpe.negocio.beans.Funcionario;
import ufrpe.negocio.exception.NegocioException;
import ufrpe.negocio.exception.PagamentoInvalidoException;
import ufrpe.negocio.exception.QuantidadeInvalidaException;
import ufrpe.repositorio.IRepositorioFinanceiro;
import ufrpe.repositorio.IRepositorioFuncionario;
import ufrpe.repositorio.RepositorioFinanceiro;
import ufrpe.repositorio.RepositorioFuncionario;

public class ControladorFinanceiro {

	// ATRIBUTOS

	private ControladorFuncionario controladorFuncionario;
	private IRepositorioFuncionario repoFuncionario;
	private IRepositorioFinanceiro repoFinanceiro;

	// SINGLETON

	private static ControladorFinanceiro instancia;

	public static ControladorFinanceiro getInstancia() {
		if (instancia == null) {
			instancia = new ControladorFinanceiro();
		}
		return instancia;
	}

	// CONSTRUTOR

	private ControladorFinanceiro() {
		controladorFuncionario = ControladorFuncionario.getInstancia();
		repoFinanceiro = RepositorioFinanceiro.getInstancia();
		repoFuncionario = RepositorioFuncionario.getInstancia();
	}

	// METODOS

	public String exibirFinancas() {
		return repoFinanceiro.exibirFinancas();
	}

	public void pagarFuncionario(int identificacao) throws NegocioException{
		Funcionario func = controladorFuncionario.buscar(identificacao);
		if (func == null) {
			throw new RuntimeException("\nInstancia de Funcionario nula!\n");
		}
		if (repoFinanceiro.getRendaBruta() - func.getSalario() >= 0) {
			if (func.getRecebeuSalario() == false) {
				repoFinanceiro.pagarFuncionario(func);
			}
			else {
				throw new PagamentoInvalidoException("\nPagamento do funcionario("+
														identificacao+") ja efetuado\n");
			}
		}
	}

	public void receberDinheiroVenda(double valor) throws NegocioException{
		if (valor <= 0) {
			//System.out.println("\n\n\tErro! Valor invalido\n");
			throw new QuantidadeInvalidaException("\nQuantia de dinheiro invalida (R$ "+valor+")\n");
		}
		repoFinanceiro.receberDinheiroVenda(valor);
	}
}
