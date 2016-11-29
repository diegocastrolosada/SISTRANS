package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Ciudad;
import vos.Nacionalidad;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 */

public class DAONacionalidades
{
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Método constructor que crea DAOCiudades
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAONacionalidades() 
	{
		recursos = new ArrayList<Object>();
	}

	/**
	 * Método que cierra todos los recursos que estan en el arreglo de recursos
	 * <b>post: </b> Todos los recurso del arreglo de recursos han sido cerrados
	 */
	public void cerrarRecursos() 
	{
		for(Object ob : recursos)
		{
			if(ob instanceof PreparedStatement)
			try 
			{
					((PreparedStatement) ob).close();
			} 
			catch (Exception ex) 
			{
					ex.printStackTrace();
			}
		}
	}

	/**
	 * Método que inicializa la connection del DAO a la base de datos con la conexión que entra como parámetro.
	 * @param con  - connection a la base de datos
	 */
	public void setConn(Connection con)
	{
		this.conn = con;
	}


	/**
	 * Método que, usando la conexión a la base de datos, saca todos las ciudades de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM CIUDADES;
	 * @return Arraylist con las ciudades de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Nacionalidad> darNacionalidades() throws SQLException, Exception 
	{
		ArrayList<Nacionalidad> nacionalidades = new ArrayList<Nacionalidad>();

		String sql = "SELECT * FROM ISIS2304A091620.NACIONALIDADES";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{		
			int idPersona = Integer.parseInt(rs.getString("ID_PERSONA"));
			int idPais = Integer.parseInt(rs.getString("ID_PAIS"));
			nacionalidades.add(new Nacionalidad(idPersona, idPais));
		}
		return nacionalidades;
	}


	/**
	 * Método que busca la/las nacionalidades con el id que entra como parámetro.
	 * @param id - id de la/las nacionalidad a buscar
	 * @return ArrayList con las nacionalidades encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Nacionalidad> buscarNacionalidadPorIdPersona(int idPersona) throws SQLException, Exception
	{
		ArrayList<Nacionalidad> nacionalidades = new ArrayList<Nacionalidad>();

		String sql = "SELECT * FROM ISIS2304A091620.NACIONALIDADES WHERE ID_PERSONA ='" + idPersona + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{		
			int idPersona2 = Integer.parseInt(rs.getString("ID_PERSONA"));
			int idPais = Integer.parseInt(rs.getString("ID_PAIS"));
			nacionalidades.add(new Nacionalidad(idPersona, idPais));
		}
		return nacionalidades;
	}
	
	/**
	 * Método que busca la/las nacionalidades con el id que entra como parámetro.
	 * @param id - id de la/las nacionalidades a buscar
	 * @return ArrayList con las nacionalidades encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Nacionalidad> buscarCiudadPorIdPais(int idPais) throws SQLException, Exception
	{
		ArrayList<Nacionalidad> nacionalidades = new ArrayList<Nacionalidad>();

		String sql = "SELECT * FROM ISIS2304A091620.NACIONALIDADES WHERE ID_PAIS ='" + idPais + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{		
			int idPersona = Integer.parseInt(rs.getString("ID_PERSONA"));
			int idPais2 = Integer.parseInt(rs.getString("ID_PAIS"));
			nacionalidades.add(new Nacionalidad(idPersona, idPais));
		}
		return nacionalidades;
	}
	
	

	
	/**
	 * Método que agrega la nacionalidad que entra como parámetro a la base de datos.
	 * @param nacionalidad - la nacionalidad a agregar. nacionalidad !=  null
	 * <b> post: </b> se ha agregado la nacionalidad a la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que la nacionalidad baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar la nacionalidad a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addNacionalidad(Nacionalidad nacionalidad) throws SQLException, Exception 
	{
		String sql = "INSERT ISIS2304A091620.NACIONALIDADES VALUES (";
		sql += nacionalidad.getIdPersona() + ",'";
		sql += nacionalidad.getIdPais() + ")";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		String sql2 = "COMMIT";
		System.out.println("SQL stmt:" + sql2);

		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();
	}

	/**
	 * Método que actualiza la nacionalidad que entra como parámetro en la base de datos.
	 * @param nacionalidad - la nacionalidad a actualizar. nacionalidad !=  null
	 * <b> post: </b> se ha actualizado la nacionalidad en la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar la nacionalidad.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateNacionalidad(Nacionalidad nacionalidad) throws SQLException, Exception 
	{
		String sql = "UPDATE ISIS2304A091620.NACIONALIDADES SET ";
		sql += "idPersona='" +nacionalidad.getIdPersona() + ",'";
		sql += "idPais='" +nacionalidad.getIdPais() ;
		sql += " WHERE ID_PERSONA = " + nacionalidad.getIdPersona();
		
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		String sql2 = "COMMIT";
		System.out.println("SQL stmt:" + sql2);

		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();
	}

	/**
	 * Método que elimina la nacionalidad que entra como parámetro en la base de datos.
	 * @param nacionalidad - la nacionalidad a eliminar. nacionalidad !=  null
	 * <b> post: </b> se ha borrado la nacionalidad en la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar la nacionalidad.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteNacionalidad(Nacionalidad nacionalidad) throws SQLException, Exception 
	{
		String sql = "DELETE FROM ISIS2304A091620.NACIONALIDADES";
		sql += " WHERE ID_PERSONA = " + nacionalidad.getIdPersona();

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		String sql2 = "COMMIT";
		System.out.println("SQL stmt:" + sql2);

		PreparedStatement prepStmt2 = conn.prepareStatement(sql2);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();
	}

}
