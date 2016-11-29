package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Vuelo;
import vos.VueloCarga;

public class DAOVuelosCarga {
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
	public DAOVuelosCarga() 
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
	 * <b>SQL Statement:</b> SELECT * FROM VUELOS_CARGA;
	 * @return Arraylist con las vuelos de la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public ArrayList<VueloCarga> darVuelosCarga() throws SQLException, Exception 
	{
		ArrayList<VueloCarga> vuelosCarga = new ArrayList<VueloCarga>();

		String sql = "SELECT * FROM ISIS2304A091620.VUELOS_CARGA";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{	
			DAOVuelos vuel = new DAOVuelos();
			Vuelo vu = vuel.buscarVueloPorId(rs.getString("ID"));
			
			double precioDensidad = rs.getDouble("PRECIO_DENSIDAD");
			double capacidad = rs.getDouble("CAPACIDAD");
			
			VueloCarga vc = new VueloCarga(vu.getId(), vu.getHoraSalida(), vu.getHoraLlegada(), vu.getDuracion(), vu.getDistancia(), vu.getFrecuencia(), vu.getTipo(), vu.getAeronave(), vu.getAerolinea(), vu.getOrigen(), vu.getDestino());
			vc.setPrecioDensidad(precioDensidad);
			vc.setCapacidadActual(capacidad);
			
			vuelosCarga.add(vc);
		}
		return vuelosCarga;
	}


	/**
	 * Método que busca el vuelo con el id que entra como parámetro.
	 * @param id - id de el vuelo a buscar
	 * @return El vuelo encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public VueloCarga buscarVueloCargaPorId(String id) throws SQLException, Exception
	{
		VueloCarga vueloCarga = null;

		String sql = "SELECT * FROM ISIS2304A091620.VUELOS_CARGA WHERE ID ='" + id + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			DAOVuelos vuel = new DAOVuelos();
			Vuelo vu = vuel.buscarVueloPorId(rs.getString("ID"));
			
			double precioDensidad = rs.getDouble("PRECIO_DENSIDAD");
			double capacidad = rs.getDouble("CAPACIDAD");
			
			VueloCarga vc = new VueloCarga(vu.getId(), vu.getHoraSalida(), vu.getHoraLlegada(), vu.getDuracion(), vu.getDistancia(), vu.getFrecuencia(), vu.getTipo(), vu.getAeronave(), vu.getAerolinea(), vu.getOrigen(), vu.getDestino());
			vc.setPrecioDensidad(precioDensidad);
			vc.setCapacidadActual(capacidad);
			
			vueloCarga = vc;
		}

		return vueloCarga;
	}
	/**
	 * Método que agrega el vuelo que entra como parámetro a la base de datos.
	 * @param vuelo - el vuelo a agregar. vuelo !=  null
	 * <b> post: </b> se ha agregado el vuelo a la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que el vuelo baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el aeropuerto a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addVueloCarga(VueloCarga vc) throws SQLException, Exception 
	{	
		DAOVuelos vuel = new DAOVuelos();
		vuel.addVuelo(vc);
		String sql2 = "INSERT ISIS2304A091620.VUELOS_CARGA VALUES (";
		sql2 += vc.getId() + ",'";
		sql2 += vc.getPrecioDensidad() + "','";
		sql2 += vc.getCapacidadActual() +  ")";
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
	public void updateVueloCarga(VueloCarga vc) throws SQLException, Exception 
	{	
		DAOVuelos vuel = new DAOVuelos();
		String sql = "UPDATE ISIS2304A091620.VUELOS_CARGA SET ";
		sql += "ID '" + vc.getId() + "','";
		sql += "PRECIO_DENSIDAD '" + vc.getPrecioDensidad() + "','";
		sql += "CAPACIDAD '" + vc.getCapacidadActual() + "','";
		sql += "WHERE ID = '" + vc.getId() + "'";
		vuel.updateVuelo(vc);		
		
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
	public void deleteVueloCarga(VueloCarga vc) throws SQLException, Exception 
	{
		DAOVuelos vuel = new DAOVuelos();
		String sql = "DELETE FROM ISIS2304A091620.VUELOS_CARGA";
		sql += " WHERE ID = " + vc.getId();
		vuel.deleteVuelo(vc);
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