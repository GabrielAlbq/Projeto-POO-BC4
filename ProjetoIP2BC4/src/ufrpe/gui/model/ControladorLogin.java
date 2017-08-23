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

	@FXML Button bt_logar;
	@FXML TextField tf_user;
	@FXML PasswordField pf_password;

	private Principal main;
	private Funcionario funcionariologado = null;
	
	Fachada fachada = Fachada.getInstancia();

	@FXML
	private void initialize() {

	}

	public void setMain(Principal principal) {
		this.main = principal;
	}

	public void FazerLogin(ActionEvent event) {

		Stage stage = new Stage();
		Parent root = null;

		Login login = new Login(tf_user.getText().toString().toLowerCase(), pf_password.getText().toString(), "");
		
		boolean logado = false;
		try {
			funcionariologado = fachada.validarLogin(login);
			if (funcionariologado != null) {
				//
				// if(funcionariologado instanceof Gerente){
				// main.showGerente();
				// }
				// if(funcionariologado instanceof Vendedor){
				// //chamar tela de Vendedor
				// }
				// if(funcionariologado instanceof Admin){
				// // chamar tela admin
				// }
				logado = true;
				//main.setFuncionariologado(funcionariologado);
				if (logado) {
					Alert alert = new Alert(AlertType.CONFIRMATION);
					alert.setTitle("Confirmacao de login");
					alert.setHeaderText(null);
					alert.setContentText("Logado com sucesso!");
					alert.showAndWait();

					stage = (Stage) bt_logar.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("/ufrpe/gui/views/Gerente.fxml"));
					root.setUserData(funcionariologado.getNome().toString());
					Scene scene = new Scene(root);
					stage.setScene(scene);
					//String tituloAtual = stage.getTitle();
					stage.setTitle("Bem vindo, " + funcionariologado.getNome());
				//	stage.setUserData(funcionariologado);
					stage.setResizable(true);
					main.changeStage(stage);

				}
			}
		} catch (NegocioException exception) {
			exception.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
