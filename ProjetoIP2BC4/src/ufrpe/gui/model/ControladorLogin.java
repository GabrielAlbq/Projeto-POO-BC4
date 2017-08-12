package ufrpe.gui.model;

import java.security.Principal;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ControladorLogin {
	
	@FXML
	Button bt_logar;
	@FXML
	Label lb_password;
	@FXML
	Label lb_user;
	@FXML
	Label lb_welcome;
	@FXML
	TextField tf_user;
	@FXML
	PasswordField pf_password;
	
	String usuario;
	String senha;
	public void usuarioLogado (){
		
		tf_user.getText();
		
		
		
	}
	public void FazerLogin(ActionEvent event){
				if(tf_user.getText().toString().toLowerCase().equals(usuario) == true){
					if(pf_password.getText().toString().equals(senha) == true){
					System.out.println("logado");
					}
					else if(pf_password.getText().toString().isEmpty() == true){
						System.out.println("Senha n inserida");
					}
					else{
						System.out.println("Senha incorreta");
					}
				}
				else if(tf_user.getText().toString().isEmpty()){
					System.out.println("Usuario nao inserido");
				}
				else{
					System.out.println("Usuario incorreto");
				}
			}
		

//	public void setMainApp(ufrpe.Principal principal) {
//		// TODO Auto-generated method stub
//		
//	}
//	

}
