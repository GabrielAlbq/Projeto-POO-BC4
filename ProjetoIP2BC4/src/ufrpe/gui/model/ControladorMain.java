package ufrpe.gui.model;

import ufrpe.gui.Principal;


public class ControladorMain {
	
	private Principal principal;
	
	public void setApp(Principal principal){
		this.principal = principal;
	}
	
	public void initialize(){
		this.principal = Principal.getInstance();
	}
}
