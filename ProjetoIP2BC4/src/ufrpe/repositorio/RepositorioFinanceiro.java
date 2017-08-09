package ufrpe.repositorio;

import java.util.ArrayList;

import ufrpe.negocio.beans.Funcionario;

public class RepositorioFinanceiro implements IRepositorioFinanceiro{
	
	IRepositorioFuncionario repositorioFuncionario;
	IRepositorioFinanceiro repositorioFinanceiro;
	
	// ATRIBUTO
		
	private double rendaBruta; 			// Total inicial do mes
	private double totalFuncionario; 	// Pagamento dos funcionarios
	private double totalFornecedor; 	// Falta implementar (2 VA)
	private double rendaLiquida; 	    // Total de dinheiro apos o pagamento de func + forn 
	private double totalVendas; 		// Total de vendas - total fornecedor = lucro das vendas
	
	// SINGLETON
	
	private static RepositorioFinanceiro instancia;
		
	public static RepositorioFinanceiro getInstancia () {
		if( instancia == null ) {
			instancia = new RepositorioFinanceiro();
			//instancia = RepositorioFinanceiro.carregarbd();
		}
		return instancia;
	}
	
	// CONSTRUTOR 
	
	private RepositorioFinanceiro() {
		this.repositorioFuncionario = RepositorioFuncionario.getInstancia();
//		this.repositorioFinanceiro = RepositorioFinanceiro.getInstancia();
		this.rendaBruta = 100000;
		this.totalFuncionario = totalSalarioFuncionarios();
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
	
	// METODOS
	
	public double totalSalarioFuncionarios () {
		totalFuncionario = 0;
		ArrayList<Integer> func = new ArrayList<>();
		for (int i = 0; i < func.size(); i++) {
			totalFuncionario += func.get(i);
		}
		return totalFuncionario;
	}
	
	public double totalFornecedor () {
		totalFornecedor = 0;
		totalFornecedor = totalFornecedor*(0.75);
		return totalFornecedor;
	}
	
	public void pagarFuncionario (Funcionario func){
		rendaBruta -= func.getSalario();
		System.out.println("Funcionario pago!");
	}
	
	public double rendaLiquida() {
		return rendaLiquida = rendaBruta - totalFornecedor() - totalSalarioFuncionarios();
	}
	
	public void receberDinheiroVenda (double valor){
		totalVendas += valor;		
	}
	
	// TO STRING 
	
	public String exibirFinancas (){
		String teste = "";
		teste += "\n\n\t\tExibir Financas\n\tRenda total: R$ "+rendaBruta + "\n\tSalario dos funcionarios: R$ ";
		teste += totalSalarioFuncionarios()+"\n\tTotal fornecedor: R$ "+totalFornecedor()+"\n\tRenda liquida: R$ ";
		teste += rendaLiquida()+"\n\tTotal Vendas: R$ "+totalVendas+"\n\n";
		return teste;
	}
	
	/*private static RepositorioFinanceiro carregarbd() {

		RepositorioFinanceiro repositorio = null;

		File bd = new File("RepositorioFinanceiro.Mercadinho");
		FileInputStream fis = null;
		ObjectInputStream ois = null;

		try {

			fis = new FileInputStream(bd);
			ois = new ObjectInputStream(fis);

			repositorio = (RepositorioFinanceiro) ois.readObject();
		} catch (Exception e) {
			repositorio = new RepositorioFinanceiro();

			try {
				if (!bd.exists()) {
					bd.createNewFile();
				}

				FileOutputStream fos = new FileOutputStream(bd);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(repositorio);
				oos.flush();
				oos.close();
				fos.flush();
				fos.close();
			} catch (Exception e1) {
				e1.printStackTrace();
			}

			//e.printStackTrace();
		} finally {
			if (ois != null) {
				try {
					ois.close();
				} catch (IOException e) {
					System.out.println("N�o foi poss�vel fechar o arquivo!");
					e.printStackTrace();
				}
			}
		}

		return repositorio;
	}

	@Override
	public void salvarbd() {
		if (!(instancia == null)) {

			File bd = new File("RepositorioFinanceiro.Mercadinho");

			try {

				if (!bd.exists()) {
					bd.createNewFile();
				}

				FileOutputStream fos = new FileOutputStream(bd);
				ObjectOutputStream oos = new ObjectOutputStream(fos);

				oos.writeObject(instancia);
				oos.flush();
				oos.close();
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
	}*/
	
	// TODO pagar fornecedor
}
