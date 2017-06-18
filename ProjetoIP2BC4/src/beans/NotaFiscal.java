package beans;

public class NotaFiscal {
	private Funcionario funcionario; // é o funcionario que vendeu
	private ItemVenda[] itensVendidos; // lista de itens vendidos
	private int qtdItens;
	private double totalPagar; // somatorio de cada item da lista, (quantidade de cada item) * (valor do item);
	private static int codigoDaNota; // codigo da nota fiscal
	
	public NotaFiscal(Funcionario funcionario, ItemVenda[] itensVendidos, double totalPagar, int codigoDaNota) {
		this.funcionario = funcionario;
		this.itensVendidos = itensVendidos;
		this.totalPagar = totalPagar;
		this.codigoDaNota = codigoDaNota;
		qtdItens = 0;
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
		String teste = "Código: "+codigoDaNota+"\nFuncionario: "+funcionario.getPessoa().getNome()+"\n";
		for (int i = 0; i < qtdItens; i++) {
			teste = teste+itensVendidos[i].toString()+"\n"; 
		}
		teste = teste+"\nTotal a pagar: R$"+totalPagar;
		return teste;
	}
}
