package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Vuelo;
import vos.VueloPasajero;

public class DAOVuelosPasajero {
	/**
	 * Arraylits de recursos que se usan para la ejecución de sentencias SQL
	 */
	private ArrayList<Object> recursos;

	/**
	 * Atributo que genera la conexión a la base de datos
	 */
	private Connection conn;

	/**
	 * Método constructor que crea DAOVuelos
	 * <b>post: </b> Crea la instancia del DAO e inicializa el Arraylist de recursos
	 */
	public DAOVuelosPasajero() 
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
	 * Método que, usando la conexión a la base de datos, saca todos los vuelos de la base de datos
	 * <b>SQL Statement:</b> SELECT * FROM VUELOS_PASAJERO;
	 * @return Arraylist con las vuelos de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<VueloPasajero> darVuelosPasajero() throws SQLException, Exception 
	{
		ArrayList<VueloPasajero> vuelosPasajero = new ArrayList<VueloPasajero>();
		DAOVuelos vuel = new DAOVuelos();
		
		String sql = "SELECT * FROM ISIS2304A091620.VUELOS_PASAJERO";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{	
			
			Vuelo vu = vuel.buscarVueloPorId(rs.getString("ID"));
			
			double precioEconomica = rs.getDouble("PRECIO_ECONOMICA");
			double precioEjecutiva = rs.getDouble("PRECIO_EJECUTIVA");
			int capacidad = rs.getInt("CAPACIDAD");
			
			VueloPasajero vp = new VueloPasajero(vu.getId(), vu.getHoraSalida(), vu.getHoraLlegada(), vu.getDuracion(), vu.getDistancia(), vu.getFrecuencia(), vu.getTipo(), vu.getAeronave(), vu.getAerolinea(), vu.getOrigen(), vu.getDestino());
			vp.setPrecioEconomica(precioEconomica);
			vp.setPrecioEjecutiva(precioEjecutiva);
			vp.setCapacidadActual(capacidad);
			
			vuelosPasajero.add(vp);
		}
		return vuelosPasajero;
	}


	/**
	 * Método que busca el vuelo con el id que entra como parámetro.
	 * @param id - id de el vuelo a buscar
	 * @return El vuelo encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public VueloPasajero buscarVueloPasajeroPorId(String id) throws SQLException, Exception
	{
		VueloPasajero vueloPasajero = null;

		String sql = "SELECT * FROM ISIS2304A091620.VUELOS_PASAJERO WHERE ID ='" + id + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			DAOVuelos vuel = new DAOVuelos();
			Vuelo vu = vuel.buscarVueloPorId(rs.getString("ID"));
			
			double precioEconomica = rs.getDouble("PRECIO_ECONOMICA");
			double precioEjecutiva = rs.getDouble("PRECIO_EJECUTIVA");
			int capacidad = rs.getInt("CAPACIDAD");
			
			VueloPasajero vp = new VueloPasajero(vu.getId(), vu.getHoraSalida(), vu.getHoraLlegada(), vu.getDuracion(), vu.getDistancia(), vu.getFrecuencia(), vu.getTipo(), vu.getAeronave(), vu.getAerolinea(), vu.getOrigen(), vu.getDestino());
			vp.setPrecioEconomica(precioEconomica);
			vp.setPrecioEjecutiva(precioEjecutiva);
			vp.setCapacidadActual(capacidad);
			
			vueloPasajero = vp;
		}
		return vueloPasajero;
	}
	/**
	 * Método que agrega el vuelo que entra como parámetro a la base de datos.
	 * @param vuelo - el vuelo a agregar. vuelo !=  null
	 * <b> post: </b> se ha agregado el vuelo a la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que el vuelo baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el aeropuerto a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addVueloPasajero(VueloPasajero vp) throws SQLException, Exception 
	{	
		DAOVuelos vuel = new DAOVuelos();
		vuel.addVuelo(vp);
		String sql2 = "INSERT ISIS2304A091620.VUELOS_PASAJERO VALUES (";
		sql2 += vp.getId() + ",'";
		sql2 += vp.getPrecioEconomica() + "','";
		sql2 += vp.getPrecioEjecutiva() + "','";
		sql2 += vp.getCapacidadActual() +  ")";
		System.out.println("SQL stmt:" + sql2);
		PreparedStatement prepStmt = conn.prepareStatement(sql2);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
		
		String sql = "COMMIT";
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt2 = conn.prepareStatement(sql);
		recursos.add(prepStmt2);
		prepStmt2.executeQuery();
	}

	/**
	 * Método que actualiza el vuelo que entra como parámetro en la base de datos.
	 * @param vuelo - el vuelo a actualizar. vuelo !=  null
	 * <b> post: </b> se ha actualizado el vuelo en la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el aeropuerto.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateVueloPasajero(VueloPasajero vp) throws SQLException, Exception 
	{	
		DAOVuelos vuel = new DAOVuelos();
		String sql = "UPDATE ISIS2304A091620.VUELOS_PASAJERO SET ";
		sql += "ID '" + vp.getId() + "','";
		sql += "PRECIO_ECONOMICA '" + vp.getPrecioEconomica() + "','";
		sql += "PRECIO_ECONOMICA '" + vp.getPrecioEjecutiva() + "','";
		sql += "CAPACIDAD '" + vp.getCapacidadActual() + "','";
		sql += "WHERE ID = '" + vp.getId() + "'";
		vuel.updateVuelo(vp);		
		
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
	public void deleteVueloPasajero(VueloPasajero vp) throws SQLException, Exception 
	{
		DAOVuelos vuel = new DAOVuelos();
		String sql = "DELETE FROM ISIS2304A091620.VUELOS_PASAJERO";
		sql += " WHERE ID = " + vp.getId();
		vuel.deleteVuelo(vp);
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
