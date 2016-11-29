package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Silla;

/**
 * Clase DAO que se conecta la base de datos usando JDBC para resolver los requerimientos de la aplicación
 */
public class DAOSillas 
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
	 * Método constructor que crea DAOSillas
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOSillas() 
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
	 * Método que, usando la conexión a la base de datos, saca todos las sillas de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM SILLAS;
	 * @return Arraylist con las sillas de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Silla> darSillas() throws SQLException, Exception 
	{
		ArrayList<Silla> sillas = new ArrayList<Silla>();

		String sql = "SELECT * FROM ISIS2304A091620.SILLAS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String tipo = rs.getString("TIPO");
			int numero = Integer.parseInt(rs.getString("NUMERO"));
			sillas.add(new Silla(tipo, numero));
		}
		return sillas;
	}


	/**
	 * Método que busca la/las sillas con el tipo que entra como parámetro.
	 * @param tipo - Tipo de la/las sillas a buscar
	 * @return ArrayList con las sillas encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Silla> buscarSillaPorTipo(String tipo) throws SQLException, Exception
	{
		ArrayList<Silla> sillas = new ArrayList<Silla>();

		String sql = "SELECT * FROM ISIS2304A091620.SILLAS WHERE TIPO ='" + tipo + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String tipo2 = rs.getString("TIPO");
			int numero = Integer.parseInt(rs.getString("NUMERO"));
			sillas.add(new Silla(tipo, numero));
		}

		return sillas;
	}
	
	/**
	 * Método que busca la/las sillas con el numero que entra como parámetro.
	 * @param numero - Numero de la/las sillas a buscar
	 * @return ArrayList con las sillas encontrados
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<Silla> buscarSillaPorNumero(int numero) throws SQLException, Exception
	{
		ArrayList<Silla> sillas = new ArrayList<Silla>();

		String sql = "SELECT * FROM ISIS2304A091620.SILLAS WHERE NUMERO ='" + numero + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String tipo = rs.getString("TIPO");
			int numero2 = Integer.parseInt(rs.getString("NUMERO"));
			sillas.add(new Silla(tipo, numero));
		}

		return sillas;
	}

	
	/**
	 * Método que agrega la silla que entra como parámetro a la base de datos.
	 * @param silla - la silla a agregar. silla !=  null
	 * <b> post: </b> se ha agregado la silla a la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que la silla baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar la silla a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addSilla(Silla silla) throws SQLException, Exception 
	{
		String sql = "INSERT ISIS2304A091620.SILLAS VALUES (";
		sql += silla.getTipo() + ",'";
		sql += silla.getNumero() + ")";

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
	 * Método que actualiza la silla que entra como parámetro en la base de datos.
	 * @param silla - la silla a actualizar. silla !=  null
	 * <b> post: </b> se ha actualizado la silla en la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar la silla.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateSilla(Silla silla) throws SQLException, Exception 
	{
		String sql = "UPDATE ISIS2304A091620.SILLAS SET ";
		sql += "tipo='" +silla.getTipo() + ",'";
		sql += "numero='" +silla.getNumero();
		sql += " WHERE NUMERO = " + silla.getNumero();

		
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
	 * Método que elimina la silla que entra como parámetro en la base de datos.
	 * @param silla - la silla a eliminar. silla !=  null
	 * <b> post: </b> se ha borrado la silla en la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar la silla.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteSilla(Silla silla) throws SQLException, Exception 
	{
		String sql = "DELETE FROM ISIS2304A091620.SILLAS";
		sql += " WHERE NUMERO = " + silla.getNumero();

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
