package ufrpe.negocio.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class NotaFiscal implements Serializable, Cloneable {
	
	
	private static final long serialVersionUID = -4771798705023045490L;
	
	//ATRIBUTOS
	
	private String funcionario; 
	private List<ItemVenda> itensVendidos; 
	private int qtdItens;
	private double totalPagar;
	private int codigoDaNota; 
	
	// CONSTRUTOR 
	@Override
	protected Object clone() throws CloneNotSupportedException {
		return super.clone();
	}
	
	public NotaFiscal(Funcionario funcionario,ArrayList<ItemVenda> itensVendidos,
			          double totalPagar, int codigoDaNota, int qtdItens) {
		this.funcionario = funcionario.getNome();
		this.itensVendidos = (ArrayList<ItemVenda>) itensVendidos.clone();

		this.totalPagar = totalPagar;
		this.codigoDaNota = codigoDaNota;
		this.qtdItens = qtdItens;
	}

	// GET / SET
	
	

	public List<ItemVenda> getItensVendidos() {
		return itensVendidos;
	}

	public String getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	public double getTotalPagar() {
		return totalPagar;
	}

	public int getCodigoDaNota() {
		return codigoDaNota;
	}

	// TO STRING 

	
	public String toString() {
		String teste = "\n\n========================================\n";
		//teste = teste+"\n\t\tNota #"+codigoDaNota+"\n\n\tFuncionario: "+funcionario.getNome();
		teste = teste+"\n\nItem Venda\tQuantidade\tPreco\tTotal\n";
		for (int i = 0; i < qtdItens; i++) {
			teste += itensVendidos.get(i).getNome()+ "\t"+itensVendidos.get(i).getQtd()+ "\t\t"+itensVendidos.get(i).getPreco()+"  ";
			teste +="  R$ "+(itensVendidos.get(i).getQtd()*itensVendidos.get(i).getPreco()+"\n");
		}
		teste = teste+"\n________________________________________\n\n\tTotal a pagar: R$ "+totalPagar;
		return teste;
	}
}
