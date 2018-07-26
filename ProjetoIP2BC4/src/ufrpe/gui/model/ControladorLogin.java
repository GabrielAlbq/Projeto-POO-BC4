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

public class ControladorLogin {

	@FXML
	Button bt_logar;
	@FXML
	TextField tf_user;
	@FXML
	PasswordField pf_password;

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

		try {
			funcionariologado = fachada.validarLogin(login);
			if (funcionariologado != null) {

				if (funcionariologado instanceof Gerente) {
					stage = (Stage) bt_logar.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("/ufrpe/gui/views/Gerente.fxml"));
					root.setUserData(funcionariologado.getNome().toString());
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.setTitle("Bem vindo, " + funcionariologado.getNome());

					stage.setResizable(true);
					main.changeStage(stage);
				}
				if (funcionariologado instanceof Vendedor) {

					stage = (Stage) bt_logar.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("/ufrpe/gui/views/Vendedor.fxml"));
					root.setUserData(funcionariologado.getNome().toString());
					Scene scene = new Scene(root);
					stage.setScene(scene);
					stage.setTitle("Bem vindo, " + funcionariologado.getNome());

					stage.setResizable(true);
					main.changeStage(stage);
				}
				if (funcionariologado instanceof Admin) {

					stage = (Stage) bt_logar.getScene().getWindow();
					root = FXMLLoader.load(getClass().getResource("/ufrpe/gui/views/Admin.fxml"));
					root.setUserData(funcionariologado.getNome().toString());
					Scene scene = new Scene(root);
					scene.getStylesheets().add("Logado.css");
					stage.setScene(scene);
					stage.setTitle("Bem vindo, " + funcionariologado.getNome());

					stage.setResizable(true);
					main.changeStage(stage);
				}
			}
		} catch (NegocioException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Falha de Login");
			alert.setHeaderText(null);
			alert.setContentText(e.getMessage());
			alert.showAndWait();
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
