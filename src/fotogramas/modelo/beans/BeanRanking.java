package fotogramas.modelo.beans;

import java.io.Serializable;

public class BeanRanking implements Serializable{

	private String login;
	private String puntos;
	
	public BeanRanking(){
		
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPuntos() {
		return puntos;
	}

	public void setPuntos(String puntos) {
		this.puntos = puntos;
	}
	
	
	
	
	
}
