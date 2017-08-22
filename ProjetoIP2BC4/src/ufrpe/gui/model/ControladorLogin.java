package ufrpe.gui.model;



import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ufrpe.gui.Principal;
import ufrpe.negocio.Fachada;
import ufrpe.negocio.beans.Admin;
import ufrpe.negocio.beans.Funcionario;
import ufrpe.negocio.beans.Gerente;
import ufrpe.negocio.beans.Login;
import ufrpe.negocio.beans.Vendedor;
import ufrpe.negocio.exception.NegocioException;
import ufrpe.repositorio.IRepositorioFuncionario;
import ufrpe.repositorio.RepositorioFuncionario;

public class ControladorLogin {

	@FXML
	Button bt_logar;

	@FXML
	TextField tf_user;

	@FXML
	PasswordField pf_password;

	private Principal main;
	private Funcionario funcionario;

	String usuario;
	String senha;

	IRepositorioFuncionario repositorioPessoa = RepositorioFuncionario.getInstancia();
	Fachada fachada = Fachada.getInstancia();

	public ControladorLogin() {

	}

	@FXML
	private void initialize() {

	}

	public void setMain(Principal principal) {
		this.main = principal;
	}

	public void usuarioLogado() {

	}

	public void FazerLogin(ActionEvent event) {
		
		Stage stage = null;
		Parent root = null;
		
		Login login = new Login(tf_user.getText().toString().toLowerCase(), pf_password.getText().toString(), "");
		Funcionario f = null;
		boolean logado = false;
		try {
			f = fachada.validarLogin(login);
			if (f != null) {
//				
//				if(f instanceof Gerente){
//					main.showGerente();
//				}
//				if(f instanceof Vendedor){
//					//chamar tela de Vendedor
//				}
//				if(f instanceof Admin){
//					// chamar tela admin
//				}
				logado = true;
				if (logado) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirmacao de login");
					alert.setHeaderText("Logado com sucesso!");
					alert.showAndWait();
					
					bt_logar.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("/ufrpe/gui/views/Gerente.fxml")); //NOVO FXML
					
					Scene scene = new Scene(root);
					stage.setScene(scene);
					String tituloAtual = stage.getTitle();
					stage.setTitle(tituloAtual +"Bem vindo, "+ (""+f.getNome().charAt(0)).toUpperCase() + f.getNome().substring(1, f.getNome().length()));
					stage.setResizable(true);
					main.changeStage(stage);
					
				}
			}
			} catch (NegocioException exception) {
			exception.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
		
//		//Esse de baixo eh so pra testar, o de cima eh o que iremos utilizar
//		String usuario = "gabriel";
//		String senha = "123";
//		if(tf_user.getText().toString().toLowerCase().equals(usuario) == true){
//				 if(pf_password.getText().toString().equals(senha) == true){
//				 logado = true;
//				 }
//				 try{ 
//					 if (logado) {
//						Alert alert = new Alert(AlertType.CONFIRMATION);
//						alert.setTitle("Confirmacao de login");
//						alert.setHeaderText("Logado com sucesso!");
//						alert.showAndWait();
//						
//						stage = (Stage) bt_logar.getScene().getWindow();
//						
//						root = FXMLLoader.load(getClass().getResource("/ufrpe/gui/views/OverviewTemplate.fxml")); //NOVO FXML
//						Scene scene = new Scene(root);
//						stage.setScene(scene);
//						String tituloAtual = stage.getTitle();
//						//stage.setTitle(tituloAtual +"Bem vindo, "+ (""+f.getNome().charAt(0)).toUpperCase() + f.getNome().substring(1, f.getNome().length()));
//						stage.setTitle("Bem vindo, "+usuario);
//						stage.setResizable(true);
//						main.changeStage(stage);
//						
//					}
//				 } catch (IOException e){
//					 e.printStackTrace();
//				 }
//				 
//				 
//				 
//		}
		
		}
	}

