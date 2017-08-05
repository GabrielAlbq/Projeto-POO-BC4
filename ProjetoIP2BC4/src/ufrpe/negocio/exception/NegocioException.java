package ufrpe.negocio.exception;


/*
 * Representação Genérica das exeções de negocio
 * 
 */
public class NegocioException extends Exception {
	
	public NegocioException(String mensagem){
		super(mensagem);
	}
}
