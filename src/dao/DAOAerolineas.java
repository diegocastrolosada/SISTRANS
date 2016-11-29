package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Aerolinea;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 */

public class DAOAerolineas 
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
	 * Método constructor que crea DAOAerolineas
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOAerolineas() 
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
	 * Método que, usando la conexión a la base de datos, saca todos las aerolineas de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM AEROLINEAS;
	 * @return Arraylist con las aerolineas de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Aerolinea> darAerolineas() throws SQLException, Exception 
	{
		ArrayList<Aerolinea> aerolineas = new ArrayList<Aerolinea>();

		String sql = "SELECT * FROM ISIS2304A091620.AEROLINEAS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String iata = rs.getString("IATA");
			String nombre = rs.getString("NOMBRE");
			String codigo = rs.getString("CODIGO");
			String pais = rs.getString("PAIS");
			aerolineas.add(new Aerolinea(iata, nombre, codigo, pais));
		}
		return aerolineas;
	}


	/**
	 * Método que busca la/las aerolineas con el nombre que entra como parámetro.
	 * @param name - Nombre de la/las aerolineas a buscar
	 * @return ArrayList con las aerolineas encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Aerolinea> buscarAerolineaPorNombre(String nombre) throws SQLException, Exception
	{
		ArrayList<Aerolinea> aerolineas = new ArrayList<Aerolinea>();

		String sql = "SELECT * FROM ISIS2304A091620.AEROLINEAS WHERE NOMBRE ='" + nombre + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String iata = rs.getString("IATA");
			String nombre2 = rs.getString("NOMBRE");
			String codigo = rs.getString("CODIGO");
			String pais = rs.getString("PAIS");
			aerolineas.add(new Aerolinea(iata, nombre, codigo, pais));
		}

		return aerolineas;
	}
	
	/**
	 * Método que busca la/las aerolineas con el iata que entra como parámetro.
	 * @param iata - Iata de la/las aerolineas a buscar
	 * @return ArrayList con las aerolineas encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Aerolinea> buscarAerolineaPorIata(String iata) throws SQLException, Exception
	{
		ArrayList<Aerolinea> aerolineas = new ArrayList<Aerolinea>();

		String sql = "SELECT * FROM ISIS2304A091620.AEROLINEAS WHERE IATA ='" + iata + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String iata2 = rs.getString("IATA");
			String nombre = rs.getString("NOMBRE");
			String codigo = rs.getString("CODIGO");
			String pais = rs.getString("PAIS");
			aerolineas.add(new Aerolinea(iata, nombre, codigo, pais));
		}

		return aerolineas;
	}

	
	/**
	 * Método que agrega la aerolinea que entra como parámetro a la base de datos.
	 * @param aerolinea - la aerolinea a agregar. aerolinea !=  null
	 * <b> post: </b> se ha agregado la aerolinea a la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que la aerolinea baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar la aerolinea a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addAerolinea(Aerolinea aerolinea) throws SQLException, Exception 
	{
		String sql = "INSERT ISIS2304A091620.AEROLINEAS(IATA,NOMBRE,CODIGO,PAIS) VALUES ('";
		sql += aerolinea.getIata() + "','";
		sql += aerolinea.getNombre() + "','";
		sql += aerolinea.getCodigo() + "','";
		sql += aerolinea.getPais() + "')";
	
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
	 * Método que actualiza la aerolinea que entra como parámetro en la base de datos.
	 * @param aerolinea - la aerolinea a actualizar. aerolinea !=  null
	 * <b> post: </b> se ha actualizado la aerolinea en la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar la aerolinea.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateAerolinea(Aerolinea aerolinea) throws SQLException, Exception 
	{
		String sql = "UPDATE ISIS2304A091620.AEROLINEAS SET '";
		sql += "iata='" +aerolinea.getIata() + "','";
		sql += "nombre='" +aerolinea.getNombre() + "','";
		sql += "codigo='" +aerolinea.getCodigo() + "',";
		sql += "pais='" +aerolinea.getPais() ;
		sql += "' WHERE IATA = " + aerolinea.getIata();

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
	 * Método que elimina la aerolinea que entra como parámetro en la base de datos.
	 * @param aerolinea - la aerolinea a eliminar. aerolinea !=  null
	 * <b> post: </b> se ha borrado la aerolinea en la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar la aerolinea.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteAerolinea(Aerolinea aerolinea) throws SQLException, Exception 
	{
		String sql = "DELETE FROM ISIS2304A091620.AEROLINEAS";
		sql += " WHERE IATA = " + aerolinea.getIata();

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
