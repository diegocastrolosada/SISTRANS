package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Pais;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 */

public class DAOPaises 
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
	public DAOPaises() 
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
	 * Método que, usando la conexión a la base de datos, saca todos los paises de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM PAISES;
	 * @return Arraylist con los paises de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Pais> darPaises() throws SQLException, Exception 
	{
		ArrayList<Pais> paises = new ArrayList<Pais>();

		String sql = "SELECT * FROM ISIS2304A091620.PAISES";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{		
			int id = Integer.parseInt(rs.getString("ID"));
			String nombre = rs.getString("NOMBRE");
			paises.add(new Pais(id,nombre));
		}
		return paises;
	}


	/**
	 * Método que busca el/los paises con el id que entra como parámetro.
	 * @param id - id de el/los paises a buscar
	 * @return ArrayList con los paises encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Pais> buscarPaisPorId(int id) throws SQLException, Exception
	{
		ArrayList<Pais> paises = new ArrayList<Pais>();

		String sql = "SELECT * FROM ISIS2304A091620.PAISES WHERE ID ='" + id + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{		
			int id2 = Integer.parseInt(rs.getString("ID"));
			String nombre = rs.getString("NOMBRE");
			paises.add(new Pais(id,nombre));
		}
		return paises;
	}
	

	/**
	 * Método que busca el/los paises con el nombre que entra como parámetro.
	 * @param nombre - nombre de el/los paises a buscar
	 * @return ArrayList con los paises encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Pais> buscarPaisPorNombre(String nombre) throws SQLException, Exception
	{
		ArrayList<Pais> paises = new ArrayList<Pais>();

		String sql = "SELECT * FROM ISIS2304A091620.PAISES WHERE NOMBRE ='" + nombre + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{		
			int id = Integer.parseInt(rs.getString("ID"));
			String nombre2 = rs.getString("NOMBRE");
			paises.add(new Pais(id,nombre));
		}
		return paises;
	}
	
	/**
	 * Método que agrega el pais que entra como parámetro a la base de datos.
	 * @param pais - el pais a agregar. pais !=  null
	 * <b> post: </b> se ha agregado el pais a la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que el pais baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el pais a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addPais(Pais pais) throws SQLException, Exception 
	{
		String sql = "INSERT ISIS2304A091620.PAISES VALUES (";
		sql += pais.getId() + ",'";
		sql += pais.getNombre() + ")";

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
	 * Método que actualiza el pais que entra como parámetro en la base de datos.
	 * @param pais - el pais a actualizar. pais !=  null
	 * <b> post: </b> se ha actualizado el pais en la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el pais.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updatePais(Pais pais) throws SQLException, Exception 
	{
		String sql = "UPDATE ISIS2304A091620.PAISES SET ";
		sql += "id='" +pais.getId() + ",'";
		sql += "nombre='" +pais.getNombre() ;
		sql += " WHERE ID = " + pais.getId();
		
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
	 * Método que elimina el pais que entra como parámetro en la base de datos.
	 * @param pais - el pais a eliminar. pais !=  null
	 * <b> post: </b> se ha borrado el pais en la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el pais.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deletePais(Pais pais) throws SQLException, Exception 
	{
		String sql = "DELETE FROM ISIS2304A091620.PAISES";
		sql += " WHERE ID = " + pais.getId();

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
