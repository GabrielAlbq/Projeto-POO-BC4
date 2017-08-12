package ufrpe.gui;

import java.io.IOException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ufrpe.gui.model.ControladorMain;
import ufrpe.negocio.ControladorFuncionario;
import ufrpe.negocio.Fachada;
import ufrpe.negocio.beans.Funcionario;
import ufrpe.negocio.beans.Login;

public class Principal extends Application {

	// ATRIBUTOS
	private static Principal instance;
	private Stage primaryStage;
	private Funcionario logado;
	private Pane rootScene;
	private ControladorFuncionario control;

	// SINGLETON
	public static Principal getInstance() {
		if (instance == null) {
			instance = new Principal();
		}
		return instance;
	}

	@Override
	public void start(Stage primaryStage) {
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Mercadao mil grau");

	}

	public void showLogin() {
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(Principal.class.getResource("view/Login.fxml"));
			AnchorPane Login = (AnchorPane) loader.load();

			this.rootScene.getChildren().add(Login);
			ControladorMain controller = loader.getController();
			controller.setApp(this);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public static void main(String[] args) {

		launch(args);

		// SCANNER
		Scanner scanf = new Scanner(System.in);

		// FACHADA
		Fachada fachada = Fachada.getInstancia();

		// TODO fazer a parte de arquivo funcionar para inserirFuncionario
		Login log1 = new Login("fabio", "123", "pokemon");
		Login log2 = new Login("elthon", "123", "trator");
		Login log3 = new Login("admin", "admin", "chefe");

	}
}