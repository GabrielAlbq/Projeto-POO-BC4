package repositorios;

import beans.*;

public class RepositorioFinanceiro {
	
	RepositorioFuncionario repositorioFuncionario;
	RepositorioEstoque repositorioEstoque;
	
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
	
	private RepositorioFinanceiro() {
		this.repositorioFuncionario = RepositorioFuncionario.instanciarRepoFuncionario();
		this.repositorioEstoque = RepositorioEstoque.getInstancia();
		this.rendaBruta = 100000;
		this.totalFuncionarios = totalSalarioFuncionarios();
		this.totalFornecedor = totalFornecedor();
		this.rendaLiquida = rendaBruta - totalFornecedor() - totalSalarioFuncionarios();
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
	
	public double totalSalarioFuncionarios () {
		double totalFuncionario = 0;
		Funcionario[] func = repositorioFuncionario.getFuncionario();
		for (int i = 0; i < repositorioFuncionario.getQtdFuncionario(); i++) {
			totalFuncionario += func[i].getSalario();
		}
		return totalFuncionario;
	}
	
	public double totalFornecedor () {
		double totalFornecedor = 0;
		Produto[] produtos = repositorioEstoque.getProdutos();
		for (int i = 0; i < repositorioEstoque.getQuantSKU(); i++) {
			totalFornecedor += (produtos[i].getPreco()*produtos[i].getQuantidade());
		}
		return totalFornecedor;
	}
	
	public void pagarFuncionario (Funcionario func, int posicao){
		repositorioFuncionario.pagarFancionario(posicao);
		rendaBruta -= func.getSalario();
		System.out.println("Funcionario pago!");
	}
	
	public void receberDinheiroVenda (double valor){
		totalVendas += valor;		
	}
	
	public String exibirFinancas (){
		String teste = "";
		teste += "\n\n\t\tExibir Financas\n\tRenda total: R$ "+rendaBruta + "\n\tSalario dos funcionarios: R$ ";
		teste += totalSalarioFuncionarios()+"\n\tTotal fornecedor: R$ "+totalFornecedor()+"\n\tRenda liquida: R$ ";
		teste += rendaLiquida+"\n\tTotal Vendas: R$ "+totalVendas+"\n\n";
		return teste;
	}
	
	// TODO pagar fornecedor
}
