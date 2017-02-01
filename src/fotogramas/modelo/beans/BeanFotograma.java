package fotogramas.modelo.beans;

import java.io.Serializable;

public class BeanFotograma implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5963662929608482829L;

	private String archivo;
	private String titPelicula;	
	private String anyoEstreno;
	private String director;
	private String genero;
	
	
	
	public BeanFotograma(){
		
	}

	public BeanFotograma(String archivo, String titPelicula) {

		this.archivo = archivo;
		this.titPelicula = titPelicula;
	}

	public BeanFotograma(String archivo, String titPelicula, String anyoEstreno, String director, String genero) {

		this.archivo = archivo;
		this.titPelicula = titPelicula;
		this.anyoEstreno = anyoEstreno;
		this.director = director;
		this.genero = genero;
	}



	public String getArchivo() {
		return archivo;
	}



	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}



	public String getTitPelicula() {
		return titPelicula;
	}



	public void setTitPelicula(String titPelicula) {
		this.titPelicula = titPelicula;
	}



	public String getAnyoEstreno() {
		return anyoEstreno;
	}



	public void setAnyoEstreno(String anyoEstreno) {
		this.anyoEstreno = anyoEstreno;
	}



	public String getDirector() {
		return director;
	}



	public void setDirector(String director) {
		this.director = director;
	}



	public String getGenero() {
		return genero;
	}



	public void setGenero(String genero) {
		this.genero = genero;
	}



	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	
	
	
}
