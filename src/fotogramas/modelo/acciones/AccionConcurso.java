package fotogramas.modelo.acciones;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;

import fotogramas.controlador.Accion;
import fotogramas.modelo.beans.BeanError;
import fotogramas.modelo.beans.BeanFotograma;
import fotogramas.modelo.beans.BeanRanking;
import fotogramas.modelo.beans.ListadoPelicula;
import fotogramas.modelo.beans.ListadoRanking;

public class AccionConcurso  implements Accion {

	

	// Aquí se deben declarar las propiedades de la acción
	private String vista;
	private final String vistaOK = "WEB-INF/concurso.jsp";
	private final String vistaError = "gesError.jsp";
	//private final String vistaForm= "login.jsp";
	private BeanFotograma modelo = new BeanFotograma();
	
	
	private ServletContext sc;
	private HttpSession sesion;
	private DataSource DS;	
	private fotogramas.modelo.beans.BeanError error;


	/** 
	 * Ejecuta el proceso asociado a la acción.
	 * @param request Objeto que encapsula la petición.
	 * @param response Objeto que encapsula la respuesta.
	 * @return true o false en función de que no se hayan producido errores o lo contrario.
	 * @see fotogramas.controlador.Accion#ejecutar(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public boolean ejecutar(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException 
	{
		boolean resultado = true;
		Connection conexion = null;
		Statement st = null;
		ResultSet rs = null;
		//Se debe implementar ajustándose al uso de datasource	
		String archivo, tit;

		//Si la accion es concurso.
		//Se obtienen archivos y titulos de las peliculas

		try {
			conexion = DS.getConnection();
			st = conexion.createStatement();
			ListadoPelicula listaPelicula = new ListadoPelicula();
			rs = st.executeQuery("select archivo, titPelicula from fotogramas");
			if (rs.next()) {
				if (rs.getString("titPelicula").equals(" ")) {
					error = new BeanError(2,"titulo vacio");
					resultado = false;
				}else{
					
					String log, punt,p, pun;
					p=rs.getString("archivo");
					pun=rs.getString("titPelicula");
					System.out.println(p+""+pun);
					BeanFotograma beanfoto = new BeanFotograma(p,pun);
					listaPelicula.add(beanfoto);
					while (rs.next()) {
						log=rs.getString("archivo");
						punt=rs.getString("titPelicula");
						System.out.println(log+""+punt);

						
						BeanFotograma beanfot = new BeanFotograma(log,punt);
						listaPelicula.add(beanfot);
					}
					
					PrintWriter out = response.getWriter();
					out.write("<html>");
					out.write("<head><title>Concurso</title></head>");
					out.write("<body>");
					out.write("<h4>Concurso Fotograma perdido</h4>");
					for (int j = 0; j < listaPelicula.size(); j++) {
						out.write("<p>"+listaPelicula.get(j).getArchivo()+" "+listaPelicula.get(j).getTitPelicula());
						out.write("</p>");						
					}

					out.write("</body>");
					out.write("</html>");		
					
					out.close();
					
				}
			}
			else
			{
				error = new BeanError(3,"El login no existe.");
				resultado = false;
			}
		} catch (SQLException e) {
			error = new BeanError(1,"Error en conexión a base de datos",e);
			resultado = false;
		}
		
		if (resultado==true)
			vista = vistaOK;
		else
			vista = vistaError;
		return resultado;
	}
	
	
	@Override
	public String getVista() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public Object getModelo() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setSc(ServletContext sc) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public BeanError getError() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void setDS(DataSource ds) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void setSesion(HttpSession sesion) {
		// TODO Auto-generated method stub
		
	}
	
	
	
}
