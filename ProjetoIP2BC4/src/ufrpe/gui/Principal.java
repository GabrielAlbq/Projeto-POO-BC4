package ufrpe.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import ufrpe.negocio.beans.Admin;
import ufrpe.negocio.beans.Funcionario;

public class Principal extends Application {

	// ATRIBUTOS
	private static Principal instance;
	private Stage primaryStage;

	private Pane rootScene;

	private Funcionario funcionariologado;

	public Funcionario getFuncionariologado() {
		return funcionariologado;
	}

	public void setFuncionariologado(Funcionario funcionariologado) {

		this.funcionariologado = funcionariologado;
	}

	// SINGLETON
	public static Principal getInstance() {
		if (instance == null) {
			instance = new Principal();
		}
		return instance;
	}

	@Override
	public void start(Stage primaryStage) {
		instance = this;
		this.funcionariologado = new Admin();
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Sistema de Mercado");
		showLogin();
	}

	public void showLogin() {

		try {
			Parent root = FXMLLoader.load(getClass().getResource("views/ShowLogin.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add("LayoutPrincipal.css");// PARA O CSS CASO FOR ADICIONAR
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void changeStage(Stage stage) {
		this.primaryStage = stage;
	}

	public Stage getPrimaryStage() {
		return this.primaryStage;
	}

	public Pane getRootScene() {
		return this.rootScene;
	}

	public static void main(String[] args) {
		launch(args);
	}
}