package beans;

public class NotaFiscal {
	private Funcionario funcionario; // É o funcionario que vendeu
	private ItemVenda[] itensVendidos; // Lista de itens vendidos
	private int qtdItens;
	private double totalPagar; // Somatorio de cada item da lista, (quantidade de cada item) * (valor do item);
	private int codigoDaNota; // Codigo da nota fiscal
	
	public NotaFiscal(Funcionario funcionario, ItemVenda[] itensVendidos, double totalPagar, int codigoDaNota, int qtdItens) {
		this.funcionario = funcionario;
		this.itensVendidos = itensVendidos;
		this.totalPagar = totalPagar;
		this.codigoDaNota = codigoDaNota;
		this.qtdItens = qtdItens;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public ItemVenda[] getItensVendidos() {
		return itensVendidos;
	}

	public double getTotalPagar() {
		return totalPagar;
	}

	public int getCodigoDaNota() {
		return codigoDaNota;
	}
	
	public String toString() {
		String teste = "\n\n========================================\n";
		teste = teste+"\n\t\tNota #"+codigoDaNota+"\n\n\tFuncionario: "+funcionario.getPessoa().getNome();
		teste = teste+"\n\n\tItem Venda\tQuantidade\tPreco\tTotal\n";
		for (int i = 0; i < qtdItens; i++) {
			teste = teste+itensVendidos[i].getNome()+"\t\t"+itensVendidos[i].getQtd()+"\t\t"+itensVendidos[i].getPreco()+"\t\t";
			teste = teste+"R$ "+(itensVendidos[i].getQtd()*itensVendidos[i].getPreco());
		}
		teste = teste+"\n________________________________________\n\n\tTotal a pagar: R$ "+totalPagar;
		return teste;
	}
}
