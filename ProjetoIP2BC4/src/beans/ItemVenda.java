package beans;

public class ItemVenda {
	
	// ATRIBUTOS 
	
	private String nome;
	private double preco;
	private int codigo;
	private int qtd;
	
	// CONSTRUTORES
	
	public ItemVenda(int codigo, String nome, double preco, int qtd) {
		this.nome = nome;
		this.preco = preco;
		this.codigo = codigo;
		this.qtd = qtd;
	}
	
	public double valorTotal() {
		return qtd * preco;
	}
	

	// GET E SET 
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}	
	
	public int getQtd() {
		return qtd;
	}

	public void setQtd(int qtd) {
		this.qtd = qtd;
	}
	
	// EQUALS
	
	public boolean equals(ItemVenda item) {
		if(item == null)
			return false;
		
		if( item.codigo == this.codigo )
			return true;
		
		return false;
	}
	
	// TO STRING

	@Override
	public String toString() {
		return "[ Nome: " + nome + ", Preco: R$" + preco + ", Quantidade: " + qtd + " ]";
	}

}

