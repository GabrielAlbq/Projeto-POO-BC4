package repositorios;

import beans.Funcionario;

public class RepositorioFinanceiro {
	
	RepositorioFuncionario repositorioFuncionario;
	
	// ATRIBUTO
	
	private double rendaBruta; // Total inicial do mes
	private double totalFuncionarios; // Pagamento dos funcionarios
	private double totalFornecedor; // Falta implementar (2 VA)
	private double rendaLiquida;  // Total de dinheiro após o pagamento de func + forn 
	private double totalVendas; // Total de vendas - total fornecedor = lucro das vendas
	
	// SINGLETON
	
	private static RepositorioFinanceiro instancia;
		
	public static RepositorioFinanceiro instanciarRepoFinanceiro () {
		if( instancia == null ) {
			instancia = new RepositorioFinanceiro();
		}
		return instancia;
	}
	
	// CONSTRUTOR 
	
	public RepositorioFinanceiro() {
		this.repositorioFuncionario = RepositorioFuncionario.instanciarRepoFuncionario();
		this.rendaBruta = 100000;
		this.totalFuncionarios = 0;
		this.totalFornecedor = 0;
		this.rendaLiquida = 0;
		this.totalVendas = 0;
	}

	// GET E SET
	
	public double getRendaBruta() {
		return rendaBruta;
	}

	public void setRendaBruta(double rendaBruta) {
		this.rendaBruta = rendaBruta;
	}
	
	// MÉTODOS
	
	public void pagarFuncionario (Funcionario func, boolean recebeuSalario, int posicao){
		Funcionario [] funcionario = repositorioFuncionario.getFuncionario();
		repositorioFuncionario.pagarFancionario(posicao);
		rendaBruta -= func.getSalario();
		System.out.println("Funcionario pago!");
	}
	
	public void receberDinheiroVenda (double valor){
		totalVendas += valor;		
	}
	
	public String exibirFinancas (){
		String teste = "";
		teste = teste + "\n\n\t\tExibir Financas\n\tRenda total: "+rendaBruta + "\n\tSalario dos funcionarios: ";
		
		
		return null;
	}
	
	// TODO pagar fornecedor
}
