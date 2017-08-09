package ufrpe.view;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class MainController {
	
	@FXML
	private TextField FXlogin;
	
	@FXML
	private PasswordField FXpassword;
	
	String usuario = "gabriel"; ///Essas strings vao pegar do arquivo para comparar
	String senha = "123";		///Essas strings vao pegar do arquivo para comparar
	
	
	public void FazerLogin(ActionEvent event){
		if(FXlogin.getText().toString().toLowerCase().equals(usuario) == true){
			if(FXpassword.getText().toString().equals(senha) == true){
			System.out.println("logado");
			}
			else if(FXpassword.getText().toString().isEmpty() == true){
				System.out.println("Senha n inserida");
			}
			else{
				System.out.println("Senha incorreta");
			}
		}
		else if(FXlogin.getText().toString().isEmpty()){
			System.out.println("Usuario nao inserido");
		}
		else{
			System.out.println("Usuario incorreto");
		}
	}
}
