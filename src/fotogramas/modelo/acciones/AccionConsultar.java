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
import fotogramas.modelo.beans.BeanRanking;
import fotogramas.modelo.beans.ListadoRanking;


public class AccionConsultar  implements Accion {
 
	// Aquí se deben declarar las propiedades de la acción
	private String vista;
	private final String vistaOK = "WEB-INF/consultar.jsp";
	private final String vistaError = "gesError.jsp";
	//private final String vistaForm= "login.jsp";
	private BeanRanking modelo = new BeanRanking();
	
	// Estas variables las necesitan todas las acciones 
	private ServletContext sc;
	private HttpSession sesion;
	private DataSource DS;	
	private fotogramas.modelo.beans.BeanError error;
	
	/**
	 * Constructor por defecto
	 */
	public AccionConsultar()
	{

	}
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
		String login, puntos;
		String[] ranking = null;
		//Si la accion es ranking.
		//Se obtienen login y puntos

		try {
			conexion = DS.getConnection();
			st = conexion.createStatement();
			ListadoRanking listaRanking = new ListadoRanking();
			rs = st.executeQuery("select login, puntos from ranking order by puntos desc");
			if (rs.next()) {
				if (rs.getString("login").equals(" ")) {
					error = new BeanError(2,"usuario nulo");
					resultado = false;
				}else{
					int i=0;
					String log, punt,p, pun;
					p=rs.getString("login");
					pun=rs.getString("puntos");
					System.out.println(p+""+pun);
					BeanRanking beanRankin = new BeanRanking(p,pun);
					listaRanking.add(beanRankin);
					while (rs.next()) {
						log=rs.getString("login");
						punt=rs.getString("puntos");
						System.out.println(log+""+punt);

						
						BeanRanking beanRanking = new BeanRanking(log,punt);
						listaRanking.add(beanRanking);
					}
					
					PrintWriter out = response.getWriter();
					out.write("<html>");
					out.write("<head><title>Ranking</title></head>");
					out.write("<body>");
					out.write("<h4>Ranking Fotograma perdido</h4>");
					for (int j = 0; j < listaRanking.size(); j++) {
						out.write("<p>"+listaRanking.get(j).getLogin()+" "+listaRanking.get(j).getPuntos());
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


	public String getVista() 
	{
		return vista;
	}


	public void setVista(String vista)
	{
		this.vista = vista;
	}


	public Object getModelo() 
	{
		return modelo;
	}


	public void setModelo(BeanRanking modelo)
	{
	    this.modelo = modelo;
	}


	public void setSc(ServletContext sc) 
	{
		this.sc = sc;
	}

	public ServletContext getSc()
	{
	    return sc;
	}

	public void setError(fotogramas.modelo.beans.BeanError error)
	{
	    this.error = error;
	}
	
	public BeanError getError() {
		return error;
	}


	public void setSesion(HttpSession sesion) {
		this.sesion = sesion;
	}


	public HttpSession getSesion() {
		return sesion;
	}


	public void setDS(DataSource ds)
	{
		DS = ds;
	}
}

