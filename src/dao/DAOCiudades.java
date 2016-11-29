package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Ciudad;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 */
public class DAOCiudades 
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
	public DAOCiudades() 
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
	public ArrayList<Ciudad> darCiudades() throws SQLException, Exception 
	{
		ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();

		String sql = "SELECT * FROM ISIS2304A091620.CIUDADES";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{		
			int id = Integer.parseInt(rs.getString("ID"));
			int idPais = Integer.parseInt(rs.getString("ID_PAIS"));
			String nombre = rs.getString("NOMBRE");
			ciudades.add(new Ciudad(id, idPais, nombre));
		}
		return ciudades;
	}


	/**
	 * Método que busca la/las ciudades con el id que entra como parámetro.
	 * @param id - id de la/las ciudades a buscar
	 * @return ArrayList con las ciudades encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Ciudad> buscarCiudadPorId(int id) throws SQLException, Exception
	{
		ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();

		String sql = "SELECT * FROM ISIS2304A091620.CIUDADES WHERE ID ='" + id + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{		
			int id2 = Integer.parseInt(rs.getString("ID"));
			int idPais = Integer.parseInt(rs.getString("ID_PAIS"));
			String nombre = rs.getString("NOMBRE");
			ciudades.add(new Ciudad(id, idPais, nombre));
		}
		return ciudades;
	}
	
	/**
	 * Método que busca la/las ciudades con el id que entra como parámetro.
	 * @param id - id de la/las ciudades a buscar
	 * @return ArrayList con las ciudades encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Ciudad> buscarCiudadPorIdPais(int idPais) throws SQLException, Exception
	{
		ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();

		String sql = "SELECT * FROM ISIS2304A091620.CIUDADES WHERE ID_PAIS ='" + idPais + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{		
			int id = Integer.parseInt(rs.getString("ID"));
			int idPais2 = Integer.parseInt(rs.getString("ID_PAIS"));
			String nombre = rs.getString("NOMBRE");
			ciudades.add(new Ciudad(id, idPais, nombre));
		}
		return ciudades;
	}
	
	/**
	 * Método que busca la/las ciudades con el nombre que entra como parámetro.
	 * @param nombre - nombre de la/las ciudades a buscar
	 * @return ArrayList con las ciudades encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Ciudad> buscarCiudadPorNombre(String nombre) throws SQLException, Exception
	{
		ArrayList<Ciudad> ciudades = new ArrayList<Ciudad>();

		String sql = "SELECT * FROM ISIS2304A091620.CIUDADES WHERE NOMBRE ='" + nombre + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{		
			int id = Integer.parseInt(rs.getString("ID"));
			int idPais = Integer.parseInt(rs.getString("ID_PAIS"));
			String nombre2 = rs.getString("NOMBRE");
			ciudades.add(new Ciudad(id, idPais, nombre));
		}
		return ciudades;
	}

	
	/**
	 * Método que agrega la ciudad que entra como parámetro a la base de datos.
	 * @param ciudad - la ciudad a agregar. ciudad !=  null
	 * <b> post: </b> se ha agregado la ciudad a la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que la ciudad baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar la ciudad a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addCiudad(Ciudad ciudad) throws SQLException, Exception 
	{
		String sql = "INSERT ISIS2304A091620.CIUDADES VALUES (";
		sql += ciudad.getId() + ",'";
		sql += ciudad.getIdPais() + ",'";
		sql += ciudad.getNombre() + ")";

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
	 * Método que actualiza la ciudad que entra como parámetro en la base de datos.
	 * @param ciudad - la ciudad a actualizar. ciudad !=  null
	 * <b> post: </b> se ha actualizado la ciudad en la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar la ciudad.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateCiudad(Ciudad ciudad) throws SQLException, Exception 
	{
		String sql = "UPDATE ISIS2304A091620.CIUDADES SET ";
		sql += "id='" +ciudad.getId() + ",'";
		sql += "idPais='" +ciudad.getIdPais() + ",'";
		sql += "nombre='" +ciudad.getNombre();
		sql += " WHERE ID = " + ciudad.getId();

		
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
	 * Método que elimina la ciudad que entra como parámetro en la base de datos.
	 * @param ciudad - la ciudad a eliminar. ciudad !=  null
	 * <b> post: </b> se ha borrado la ciudad en la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar la ciudad.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteCiudad(Ciudad ciudad) throws SQLException, Exception 
	{
		String sql = "DELETE FROM ISIS2304A091620.CIUDADES";
		sql += " WHERE ID = " + ciudad.getId();

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
