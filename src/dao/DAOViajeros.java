package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Cliente;
import vos.Viajero;

public class DAOViajeros {
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
	public DAOViajeros() 
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
	public ArrayList<Viajero> darViajeros() throws SQLException, Exception 
	{
		ArrayList<Viajero> viajeros = new ArrayList<Viajero>();

		String sql = "SELECT * FROM ISIS2304A091620.VIAJEROS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			DAOClientes res = new DAOClientes();
			Cliente an = res.buscarClientePorId(rs.getInt("ID"));
			String frecuente = rs.getString("FRECUENTE");
			Viajero r= new Viajero(an.getId(),an.getTipoId(), an.getNombre());
			r.setFrecuente(frecuente);
			viajeros.add(r);
		}
		return viajeros;
	}


	/**
	 * Método que busca el vuelo con el id que entra como parámetro.
	 * @param name - Nombre de el vuelo a buscar
	 * @return El vuelo encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Viajero buscarViajeroPorId(int id) throws SQLException, Exception
	{
		Viajero anca = null;

		String sql = "SELECT * FROM ISIS2304A091620.VIAJEROS WHERE ID ='" + id + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			DAOClientes res = new DAOClientes();
			Cliente an = res.buscarClientePorId(rs.getInt("ID"));
			anca =  new Viajero(an.getId(),an.getTipoId(), an.getNombre());
			anca.setFrecuente(rs.getString("FRECUENTE"));
		}

		return anca;
	}
	/**
	 * Método que agrega el vuelo que entra como parámetro a la base de datos.
	 * @param aeropuerto - el aeropuerto a agregar. aeropuerto !=  null
	 * <b> post: </b> se ha agregado el aeropuerto a la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que el vuelo baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el aeropuerto a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addViajero(Viajero anc) throws SQLException, Exception 
	{
		DAOClientes aero = new DAOClientes();
		aero.addCliente(anc);
		String sql2 = "INSERT ISIS2304A091620.VIAJEROS VALUES ('";
		sql2 += anc.getId() + "','"; 
		sql2 += anc.getFrecuente() + "')";
		System.out.println("SQL stmt:" + sql2);
		PreparedStatement prepStmt = conn.prepareStatement(sql2);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		String sql = "COMMIT";
		System.out.println("SQL stmt:" + sql);

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
	public void updateViajero(Viajero anca) throws SQLException, Exception 
	{
		DAOClientes aero = new DAOClientes();
		String sql = "UPDATE ISIS2304A091620.VIAJEROS SET ";
		sql += "ID '" + anca.getId() + "',";
		sql += "FRECUENTE '" + anca.getFrecuente() +"'," ;
		sql += "WHERE ID = '" + anca.getId() + "'";
		aero.updateCliente(anca);
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
	public void deleteViajero(Viajero anca) throws SQLException, Exception 
	{
		DAOClientes aero = new DAOClientes();
		String sql = "DELETE FROM ISIS2304A091620.VIAJEROS";
		sql += " WHERE ID = " + anca.getId();
		aero.deleteCliente(anca);
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
	
	public void rf18(int id) throws SQLException{
		String sql = "UPDATE ISIS2304A091620.VIAJEROS SET FRECUENTE='SI' WHERE ID = "+id+" AND (SELECT SUM(VUELOS.DISTANCIA) FROM (SELECT * FROM ISIS2304A091620.VIAJEROS JOIN ISIS2304A091620.RESERVAS ON RESERVAS.CLIENTE=VIAJEROS.ID where VIAJEROS.ID="+id+")a JOIN VUELOS ON VUELOS.ID=a.VUELO GROUP BY a.cliente)>150000";
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
	}
}
