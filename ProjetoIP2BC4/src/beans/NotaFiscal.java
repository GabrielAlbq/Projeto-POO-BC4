package beans;

import java.util.ArrayList;
import java.util.List;

public class NotaFiscal {
	
	// ATRIBUTOS 
	
	private Funcionario funcionario; // � o funcionario que vendeu
	private ArrayList<ItemVenda> itensVendidos = new ArrayList<>(); // Lista de itens vendidos
	private int qtdItens;
	private double totalPagar; // Somatorio de cada item da lista, (quantidade de cada item) * (valor do item);
	private int codigoDaNota; // Codigo da nota fiscal
	
	// CONSTRUTOR 
	
	public NotaFiscal(Funcionario funcionario,ArrayList<ItemVenda> itensVendidos, double totalPagar, int codigoDaNota, int qtdItens) {
		this.funcionario = funcionario;
		this.itensVendidos = (ArrayList<ItemVenda>)itensVendidos.clone();
	//	this.itensVendidos = itensVendidos.clone();
		this.totalPagar = totalPagar;
		this.codigoDaNota = codigoDaNota;
		this.qtdItens = qtdItens;
	}

	// GET / SET
	
	public Funcionario getFuncionario() {
		return funcionario;
	}

	public ArrayList<ItemVenda> getItensVendidos() {
		return itensVendidos;
	}

	public double getTotalPagar() {
		return totalPagar;
	}

	public int getCodigoDaNota() {
		return codigoDaNota;
	}
//	public List<ItemVenda> listar(){
//		return itensVendidos;
//	}
	// TO STRING 

//	public String toString() {
//		return "NotaFiscal [funcionario=" + funcionario.getNome() + ", itensVendidos=" + itensVendidos + ", qtdItens=" + qtdItens
//				+ ", totalPagar=" + totalPagar + ", codigoDaNota=" + codigoDaNota + "]";
//	}
	
	public String toString() {
		String teste = "\n\n========================================\n";
		teste = teste+"\n\t\tNota #"+codigoDaNota+"\n\n\tFuncionario: "+funcionario.getNome();
		teste = teste+"\n\nItem Venda\tQuantidade\tPreco\tTotal\n";
		for (int i = 0; i < qtdItens; i++) {
			teste = teste + itensVendidos.get(i).getNome()+ "\t"+itensVendidos.get(i).getQtd()+ "\t\t"+itensVendidos.get(i).getPreco()+"  ";
			teste = teste+"  R$ "+(itensVendidos.get(i).getQtd()*itensVendidos.get(i).getPreco()+"\n");
			//teste = teste+"  R$ "+(itensVendidos[i].getQtd()*itensVendidos[i].getPreco()+"\n";
			//teste = teste+itensVendidos.getNome()+"\t"+itensVendidos[i].getQtd()+"\t\t"+itensVendidos[i].getPreco()+"  ";
			//teste = teste+"  R$ "+(itensVendidos[i].getQtd()*itensVendidos[i].getPreco()+"\n");
		}
		teste = teste+"\n________________________________________\n\n\tTotal a pagar: R$ "+totalPagar;
		return teste;
	}
}
