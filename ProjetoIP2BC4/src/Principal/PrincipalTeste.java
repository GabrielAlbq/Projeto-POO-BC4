package Principal;

import java.util.Scanner;

import Beans.*;
import Controladores.ControladorFuncionario;

public class PrincipalTeste {
	public static void main(String[] args) {
		
		/*================================================================================*/
		
		Scanner scanf = new Scanner(System.in);
		
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
		
		/*================================================================================*/
		
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
	}
}

