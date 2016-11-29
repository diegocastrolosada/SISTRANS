package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Aeropuerto;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 */
public class DAOAeropuertos 
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
	public DAOAeropuertos() 
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
	 * Método que, usando la conexión a la base de datos, saca todos los aeropuertos de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM AEROPUERTOS;
	 * @return Arraylist con las aeropuertos de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Aeropuerto> darAeropuertos() throws SQLException, Exception 
	{
		ArrayList<Aeropuerto> aeropuertos = new ArrayList<Aeropuerto>();

		String sql = "SELECT * FROM ISIS2304A091620.AEROPUERTOS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String nombre = rs.getString("NOMBRE");
			String codigo = rs.getString("CODIGO");
			String ciudad = rs.getString("ID_CIUDAD");
			aeropuertos.add(new Aeropuerto(nombre, codigo, ciudad));

		}
		return aeropuertos;
	}


	/**
	 * Método que busca el/los aeropuertos con el nombre que entra como parámetro.
	 * @param name - Nombre de el/los aeropuertos a buscar
	 * @return ArrayList con los aeropuertos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Aeropuerto> buscarAeropuertoPorNombre(String nombre) throws SQLException, Exception
	{
		ArrayList<Aeropuerto> aeropuertos = new ArrayList<Aeropuerto>();

		String sql = "SELECT * FROM ISIS2304A091620.AEROPUERTOS WHERE NOMBRE ='" + nombre + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String nombre2 = rs.getString("NOMBRE");
			String codigo = rs.getString("CODIGO");
			String ciudad = rs.getString("ID_CIUDAD");
			aeropuertos.add(new Aeropuerto(nombre, codigo,  ciudad));
		}

		return aeropuertos;
	}
	
	/**
	 * Método que busca el/los aeropuertos con el codigo que entra como parámetro.
	 * @param codigo - Codigo de el/los aeropuertos a buscar
	 * @return ArrayList con los aeropuertos encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Aeropuerto> buscarAeropuertoPorCodigo(String codigo) throws SQLException, Exception
	{
		ArrayList<Aeropuerto> aeropuertos = new ArrayList<Aeropuerto>();

		String sql = "SELECT * FROM ISIS2304A091620.AEROPUERTOS WHERE CODIGO ='" + codigo + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String nombre = rs.getString("NOMBRE");
			String codigo2 = rs.getString("CODIGO");
			String ciudad = rs.getString("ID_CIUDAD");
			aeropuertos.add(new Aeropuerto(nombre, codigo2 ,ciudad));
		}

		return aeropuertos;
	}

	
	/**
	 * Método que agrega el aeropuerto que entra como parámetro a la base de datos.
	 * @param aeropuerto - el aeropuerto a agregar. aeropuerto !=  null
	 * <b> post: </b> se ha agregado el aeropuerto a la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que el aeropuerto baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el aeropuerto a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addAeropuerto(Aeropuerto aeropuerto) throws SQLException, Exception 
	{
		String sql = "INSERT ISIS2304A091620.AEROPUERTOS VALUES (";
		sql += aeropuerto.getNombre() + ",'";
		sql += aeropuerto.getCodigo() + "',";
		sql += aeropuerto.getCiudad() + ")";

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
	 * Método que actualiza el aeropuerto que entra como parámetro en la base de datos.
	 * @param aeropuerto - el aeropuerto a actualizar. aeropuerto !=  null
	 * <b> post: </b> se ha actualizado el aeropuerto en la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el aeropuerto.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateAeropuerto(Aeropuerto aeropuerto) throws SQLException, Exception 
	{
		String sql = "UPDATE ISIS2304A091620.AEROPUERTOS SET ";
		sql += "nombre='" +aeropuerto.getNombre() + ",'";
		sql += "codigo='" +aeropuerto.getCodigo() + "',";
		sql += "ciudad='" +aeropuerto.getCiudad();
		sql += " WHERE CODIGO = " + aeropuerto.getCodigo();

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
	 * Método que elimina el aeropuerto que entra como parámetro en la base de datos.
	 * @param aeropuerto - el aeropuerto a eliminar. aeropuerto !=  null
	 * <b> post: </b> se ha borrado el aeropuerto en la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el aeropuerto.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteAeropuerto(Aeropuerto aeropuerto) throws SQLException, Exception 
	{
		String sql = "DELETE FROM ISIS2304A091620.AEROPUERTOS";
		sql += " WHERE CODIGO = " + aeropuerto.getCodigo();

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
