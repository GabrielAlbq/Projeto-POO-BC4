package principal;

import java.util.Scanner;

import beans.Endereco;
import beans.Funcionario;
import beans.ItemVenda;
import beans.Pessoa;
import controladores.ControladorEstoque;
import controladores.ControladorFuncionario;
import controladores.ControladorVenda;
import repositorios.RepositorioEstoque;
import repositorios.RepositorioFuncionario;
import repositorios.RepositorioVenda;

public class PrincipalTeste {
	public static void main(String[] args) {
		
		Scanner scanf = new Scanner(System.in);
		
		// TESTES
		
		ItemVenda item1 = new ItemVenda (10, "Pão", 0.50, 100);
		ItemVenda item2 = new ItemVenda (20, "Arroz", 3.50, 100);
		ItemVenda item3 = new ItemVenda (30, "Feijão", 4.00, 100);
		ItemVenda item4 = new ItemVenda (40, "Macarrão", 2.50, 100);
		ItemVenda item5 = new ItemVenda (50, "Galinha", 10.00, 100);
		ItemVenda item6 = new ItemVenda (60, "Guaraná em Lata", 3.00, 100);
		ItemVenda item7 = new ItemVenda (70, "Sorvete 2L", 13.00, 100);
		ItemVenda item8 = new ItemVenda (80, "Biscoito", 1.50, 100);
		ItemVenda item9 = new ItemVenda (90, "Salgadinho", 1.50, 100);
		ItemVenda item10 = new ItemVenda (100, "água", 1.00, 100);
		
		Endereco e1 = new Endereco("Rua A", "Cidade A", "12345-100", "999");
		Endereco e2 = new Endereco("Rua B", "Cidade B", "98765-100", "333");
		Endereco enderecoTeste;
		
		Pessoa p1 = new Pessoa(e1, "fabio", "11111111111");
		Pessoa p2 = new Pessoa(e2, "duda", "99999999999");
		Pessoa pessoaTeste;
		
		Funcionario f1 = new Funcionario(p1,"vendedor",2000,1);
		Funcionario f2 = new Funcionario(p2,"vendedor",2000,2);
		Funcionario funcionarioTeste;
			
		// CONTROLADORES INSTANCIADOS
		
		ControladorFuncionario controladorFuncionario = ControladorFuncionario.instanciarControlFuncionario();
		ControladorEstoque controladorEstoque = ControladorEstoque.getInstancia();
		ControladorVenda controladorVenda = ControladorVenda.instanciar();
		
		// REPOSITORIOS INSTANCIADOS
		
		RepositorioFuncionario repositorioFuncionario = RepositorioFuncionario.instanciarRepoFuncionario();
		RepositorioEstoque repositorioEstoque = RepositorioEstoque.getInstancia();
		RepositorioVenda repositorioVenda = RepositorioVenda.intanciar();
		
		controladorFuncionario.inserir(f1);
		controladorFuncionario.inserir(f2);
		
		int opcao, identificacao;
		boolean parar = false;
		boolean vender = true;
		
		
		while(!parar) {
			System.out.println("\n\n=========================================\n\n\tMercadinho mil grau\n\n(1) - Sistema de Vendas\n"
							 + "(2) - Sistema de Estoque\n(3) - Sistema Financeiro\n(4) - Sistema de Funcionarios\n\n==> ");
			opcao = scanf.nextInt();
			
			switch(opcao) {
			case 1: {
				System.out.println("\n\n=========================================\n\n\tSistema de Vendas\n\n(1) - Registrar Venda"
						         + "\n(2) - Listar Vendas\n(3) - Deletar Histórico\n");
				opcao = scanf.nextInt();
				
				if( opcao == 1 ) {
					// tem um laço e nele um sysout com todos os itens a serem vendidos, o usuario escolhe o item, e depois sua quantidade
					// apos a quantidade do produto, o usuario dirá se quer mais algum produto, se ele nao quiser a venda será encerrada.
					// É emitida uma nota fiscal, que será armazenada no repositorio venda, o dinheiro total é enviado para o repositorio financeiro
					//  e os produtos vendidos sao retirados do estoque.
					// essa informação será enviada para a nota fiscal desta venda, que terá o funcionario que efetuou a venda, indentificacao
					System.out.println("\n\n=========================================\n\n\tInforme a identificação do funcionario: ");
					identificacao = scanf.nextInt();
					if( controladorFuncionario.buscar(identificacao) == null) {
						System.out.println("\n\tErro! Funcionario inexistente\n\n");
						vender  = false;
					}
					System.out.println("\n\n=========================================\n\n\tFuncionario "
					+repositorioFuncionario.retornarFuncionario(identificacao).getPessoa().getNome() + " irá registrar a venda\n");
					System.out.println("\n\n==========================================\n\n\tTabela de produtos\n\n");
					//for (int i = 0; i < ; i++)
					
					vender = true;
				} 
				else if (opcao == 2) {
					// chamar controlador venda, que possui o metodo listar todas as vendas
				} 
				else if(opcao == 3) {
					// chamar o controlador venda, que apaga o historico
				}
				break;
			}
			case 2: {
				
				
				break;
			}
			case 3: {
				
				
				break;
			}
			case 4: {
				
				
				break;
			}			
		}
		} // fechamento do switch externo
	}
}

	  		
	/*
		String rua, cep, cidade, nome, cpf, numero, funcao = "vendedor";
		boolean parar = false;
		int identificacao, opcao;
		
		Endereco e1 = new Endereco("Rua A", "Cidade A", "12345-100", "999");
		Endereco e2 = new Endereco("Rua B", "Cidade B", "98765-100", "333");
		Endereco enderecoTeste;
		
		Pessoa p1 = new Pessoa(e1, "fabio", "11111111111");
		Pessoa p2 = new Pessoa(e2, "duda", "99999999999");
		Pessoa pessoaTeste;
		
		Funcionario f1 = new Funcionario(p1,"vendedor",2000,1);
		Funcionario f2 = new Funcionario(p2,"vendedor",2000,2);
		Funcionario funcionarioTeste;
		
	
		
		ControladorFuncionario controladorFuncionario;
		controladorFuncionario = ControladorFuncionario.instanciarControlFuncionario();
		
		controladorFuncionario.inserir(f1);
		controladorFuncionario.inserir(f2);
		
		do {
			//controladorFuncionario.imprimirTodos();
			
			System.out.println("\n==============================================\n");
			System.out.println("\n\tSistema de Funcionarios\n\n\t(1) inserir");
			System.out.print("\t(2) alterar\n\t(3) buscar\n\t(4) remover\n\n\t==>");
			opcao = scanf.nextInt();
			switch(opcao) {
				case 1: {
					
					System.out.println("\n==============================================\n");
					System.out.println("\n\tinsira a identifica��o do funcionario: ");
					identificacao = scanf.nextInt();
					System.out.println("insira o numero da sua resid�ncia: ");
					scanf.nextLine();
					numero = scanf.nextLine();
					System.out.println("insira sua rua: ");
					rua = scanf.nextLine();
					System.out.println("insira seu cep: ");
					cep = scanf.nextLine();
					System.out.println("insira sua cidade: ");
					cidade = scanf.nextLine();
					System.out.println("insira seu nome: ");
					nome =scanf.nextLine();
					System.out.println("insira seu CPF: ");
					cpf =scanf.nextLine();
					
					enderecoTeste = new Endereco(rua, cidade, cep, numero);
					pessoaTeste = new Pessoa(enderecoTeste, nome, cpf);
					funcionarioTeste = new Funcionario(pessoaTeste, funcao, 2000, identificacao);
					
					controladorFuncionario.inserir(funcionarioTeste);
					
					break;
				}
				case 2: {
					System.out.println("\n==============================================\n");
					System.out.println("\n\tinsira a identifica��o do funcionario: ");
					identificacao = scanf.nextInt();
					System.out.println("\ninsira o numero da sua resid�ncia: ");
					scanf.nextLine();
					numero = scanf.nextLine();
					System.out.println("insira sua rua: ");
					rua = scanf.nextLine();
					System.out.println("insira seu cep: ");
					cep = scanf.nextLine();
					System.out.println("insira sua cidade: ");
					cidade = scanf.nextLine();
					System.out.println("insira seu nome: ");
					nome =scanf.nextLine();
					System.out.println("insira seu CPF: ");
					cpf =scanf.nextLine();
					
					enderecoTeste = new Endereco(rua, cidade, cep, numero);
					pessoaTeste = new Pessoa(enderecoTeste, nome, cpf);
					funcionarioTeste = new Funcionario(pessoaTeste, funcao, 2000, identificacao);
					
					controladorFuncionario.inserir(funcionarioTeste);
					break;
				}
				case 3: {
					System.out.println("\n==============================================\n");
					System.out.println("\n\tinsira a identifica��o do funcionario: ");
					funcionarioTeste = controladorFuncionario.buscar(scanf.nextInt());
					System.out.println("\n\t"+funcionarioTeste+"\n\n");
					break;
				}
				case 4: {
					System.out.println("\n==============================================\n");
					System.out.println("\n\tinsira a identifica��o do funcionario: ");
					controladorFuncionario.remover(scanf.nextInt());
					break;
				}
			}
		}while(!parar);
		
		if(scanf != null)
			scanf.close();
	 */
