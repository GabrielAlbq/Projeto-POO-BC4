package ufrpe.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
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
		instance = this;
		this.primaryStage = primaryStage;
		this.primaryStage.setTitle("Sistema de Mercado");//  Mercadao mil grau");
		showLogin();

	}

	public void showLogin() {
//		try {
//			FXMLLoader loader = new FXMLLoader();
//			loader.setLocation(Principal.class.getResource("views/ShowLogin.fxml"));
//			AnchorPane Login = (AnchorPane) loader.load();
//
//			this.rootScene.getChildren().add(Login);
//			ControladorMain controller = loader.getController();
//			controller.setApp(this);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		try {
					Parent root = FXMLLoader.load(getClass().getResource("views/ShowLogin.fxml"));
					Scene scene = new Scene(root);
			//		scene.getStylesheets().add(getClass().getResource("NOME DO CSS.css").toExternalForm()); PARA O CSS CASO FOR ADICIONAR
					primaryStage.setScene(scene);
					primaryStage.show();
					} catch(Exception e) {
						e.printStackTrace();
					}
	}
	
	
	public void changeStage(Stage stage){
		  this.primaryStage = stage;
	}
	public Stage getPrimaryStage(){
		return this.primaryStage;
	}
	
	public Pane getRootScene(){
		return this.rootScene;
	}
	
	public static void main(String[] args) {

		launch(args);
		Fachada fachada = Fachada.getInstancia();

		Login log1 = new Login("fabio", "123", "pokemon");
		Login log2 = new Login("elthon", "123", "trator");
		Login log3 = new Login("admin", "admin", "chefe");

	}
}