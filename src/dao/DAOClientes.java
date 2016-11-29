package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Cliente;

public class DAOClientes {
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
	public DAOClientes() 
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
	public ArrayList<Cliente> darClientes() throws SQLException, Exception 
	{
		ArrayList<Cliente> clientes = new ArrayList<Cliente>();

		String sql = "SELECT * FROM ISIS2304A091620.CLIENTES";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int id = rs.getInt("ID");
			String tipoId = rs.getString("TIPO_ID");
			String nombre = rs.getString("NOMBRE");
			clientes.add(new Cliente(id, tipoId, nombre));
		}
		return clientes;
	}


	/**
	 * Método que busca el vuelo con el id que entra como parámetro.
	 * @param name - Nombre de el vuelo a buscar
	 * @return El vuelo encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Cliente buscarClientePorId(int id) throws SQLException, Exception
	{
		Cliente cliente = null;

		String sql = "SELECT * FROM ISIS2304A091620.CLIENTES WHERE ID ='" + id + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int id2 = rs.getInt("ID");
			String tipoId = rs.getString("TIPO_ID");
			String nombre = rs.getString("NOMBRE");
			cliente = new Cliente(id2, tipoId, nombre);
		}

		return cliente;
	}
	/**
	 * Método que agrega el vuelo que entra como parámetro a la base de datos.
	 * @param aeropuerto - el aeropuerto a agregar. aeropuerto !=  null
	 * <b> post: </b> se ha agregado el aeropuerto a la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que el vuelo baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el aeropuerto a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addCliente(Cliente cliente) throws SQLException, Exception 
	{
		String sql = "INSERT ISIS2304A091620.CLIENTES VALUES (";
		sql += cliente.getId() + ",'";
		sql += cliente.getTipoId() + "',";
		sql += cliente.getNombre() + ")";

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
	public void updateCliente(Cliente cliente) throws SQLException, Exception 
	{
		String sql = "UPDATE ISIS2304A091620.CLIENTES SET ";
		sql += "id='" +cliente.getId() + ",'";
		sql += "tipoId='" +cliente.getTipoId()  + "',";
		sql += "nombre='" +cliente.getNombre();
		sql += " WHERE ID = " + cliente.getId();
		

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
	 * Método que elimina el vuelo que entra como parámetro en la base de datos.
	 * @param vuelo - el vuelo a eliminar. vuelo !=  null
	 * <b> post: </b> se ha borrado el vuelo en la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el vuelo.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteCliente(Cliente cliente) throws SQLException, Exception 
	{
		String sql = "DELETE FROM ISIS2304A091620.CLIENTES";
		sql += " WHERE ID = " + cliente.getId();

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
