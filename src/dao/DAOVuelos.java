package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import vos.Vuelo;
import vos2.VueloMsg;

public class DAOVuelos {
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
	public DAOVuelos() 
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
	public ArrayList<Vuelo> darVuelos() throws SQLException, Exception 
	{
		ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();

		String sql = "SELECT * FROM ISIS2304A091620.VUELOS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String id = rs.getString("ID");
			Date horaSalida = rs.getDate("HORA_SALIDA");
			Date horaLlegada = rs.getDate("HORA_LLEGADA");
			double duracion = rs.getDouble("DURACION");
 			double distancia = rs.getDouble("DISTANCIA");
			int frecuencia = rs.getInt("FRECUENCIA");
			String tipo = rs.getString("TIPO");
			int aeronave = rs.getInt("AERONAVE");
			String aerolinea = rs.getString("AEROLINEA");
			String origen = rs.getString("ORIGEN");
			String destino = rs.getString("DESTINO");
			vuelos.add(new Vuelo(id, horaSalida, horaLlegada, duracion, distancia, frecuencia, tipo, aeronave, aerolinea, origen, destino));
		}
		return vuelos;
	}


	/**
	 * Método que busca el vuelo con el id que entra como parámetro.
	 * @param name - Nombre de el vuelo a buscar
	 * @return El vuelo encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Vuelo buscarVueloPorId(String id) throws SQLException, Exception
	{
		Vuelo vuelos = null;

		String sql2 = "SELECT * FROM ISIS2304A091620.VUELOS WHERE ID ='" + id + "'";

		System.out.println("SQL stmt:" + sql2);

		PreparedStatement prepStmt = conn.prepareStatement(sql2);
		System.out.println(prepStmt);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String id2 = rs.getString("ID");
			Date horaSalida = rs.getDate("HORA_SALIDA");
			Date horaLlegada = rs.getDate("HORA_LLEGADA");
			double duracion = rs.getDouble("DURACION");
 			double distancia = rs.getDouble("DISTANCIA");
			int frecuencia = rs.getInt("FRECUENCIA");
			String tipo = rs.getString("TIPO");
			int aeronave = rs.getInt("AERONAVE");
			String aerolinea = rs.getString("AEROLINEA");
			String origen = rs.getString("ORIGEN");
			String destino = rs.getString("DESTINO");
			vuelos = new Vuelo(id2, horaSalida, horaLlegada, duracion, distancia, frecuencia, tipo, aeronave, aerolinea, origen, destino);
		}

		return vuelos;
	}
	/**
	 * Método que agrega el vuelo que entra como parámetro a la base de datos.
	 * @param aeropuerto - el aeropuerto a agregar. aeropuerto !=  null
	 * <b> post: </b> se ha agregado el aeropuerto a la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que el vuelo baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el aeropuerto a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addVuelo(Vuelo vuelo) throws SQLException, Exception 
	{
		String sql = "INSERT INTO ISIS2304A091620.VUELOS VALUES ('";
		sql += vuelo.getId() + "',";
		sql += vuelo.getHoraSalida() + ",";
		sql += vuelo.getHoraLlegada() + ",";
		sql += vuelo.getDuracion() + ",";
		sql += vuelo.getDistancia() + ",";
		sql += vuelo.getFrecuencia() + ",'";
		sql += vuelo.getTipo() + "',";
		sql += vuelo.getAeronave() + ",'";
		sql += vuelo.getAerolinea() + "','";
		sql += vuelo.getOrigen() + "','";
		sql += vuelo.getDestino() + "')";

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
	public void updateVuelo(Vuelo vuelo) throws SQLException, Exception 
	{
		String sql = "UPDATE ISIS2304A091620.VUELOS SET ";
		sql += "id='" +vuelo.getId() + "',";
		sql += "HORA_SALIDA=" +vuelo.getHoraSalida() + ",";
		sql += "HORA_LLEGADA=" +vuelo.getHoraLlegada() + ",";
		sql += "duracion=" +vuelo.getDuracion() + ",";
		sql += "distancia=" +vuelo.getDistancia() + ",";
		sql += "frecuencia=" +vuelo.getFrecuencia() + ",";
		sql += "tipo='" +vuelo.getTipo() + "',";
		sql += "aeronave='" +vuelo.getAeronave() + "',";
		sql += "aerolinea='" +vuelo.getAerolinea() + "',";
		sql += "origen='" +vuelo.getOrigen() + "',";
		sql += "destino='" +vuelo.getDestino() + "'";
		sql += " WHERE ID = '" + vuelo.getId()+"'";

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
	public void deleteVuelo(Vuelo vuelo) throws SQLException, Exception 
	{
		String sql = "DELETE FROM ISIS2304A091620.VUELOS";
		sql += " WHERE ID = " + vuelo.getId();

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

	public void updateAeronaveViaje(Vuelo vuelo) throws SQLException, Exception
	{
		String sql = "UPDATE ISIS2304A091620.VUELOS SET ";
		sql += "aeronave='" + vuelo.getAeronave() + "'";
		sql += " WHERE ID = '" + vuelo.getId()+ "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		prepStmt.executeQuery();
	}
	
	public double reporteViajeCarga(String id) throws SQLException, Exception
	{
		double rta = 0;
		String sql = "SELECT SUM(PRECIO) AS REPORTE FROM ISIS2304A091620.RESERVAS_CARGA WHERE VUELO ='"+id+"' GROUP BY VUELO";
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		while(rs.next())
		{
			rta = rs.getDouble("REPORTE");
		}
		return rta;
	}
	public double reporteViajePasajero(String id) throws SQLException, Exception
	{
		double rta = 0;
		String sql = "SELECT SUM(PRECIO) AS REPORTE FROM ISIS2304A091620.RESERVAS_VIAJERO WHERE VUELO ='"+id+"' GROUP BY VUELO";
		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		while(rs.next())
		{
			rta = rs.getDouble("REPORTE");
		}
		return rta;
	}
	
	public ArrayList<Vuelo> vuelosCliente(int cliente) throws SQLException, Exception
	{
		
		ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();

		String sql = "SELECT v.* FROM ISIS2304A091620.VUELOS v,ISIS2304A091620.RESERVAS r WHERE v.ID = r.VUELO AND r.CLIENTE = "+cliente;

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String id = rs.getString("ID");
			Date horaSalida = rs.getDate("HORA_SALIDA");
			Date horaLlegada = rs.getDate("HORA_LLEGADA");
			double duracion = rs.getDouble("DURACION");
 			double distancia = rs.getDouble("DISTANCIA");
			int frecuencia = rs.getInt("FRECUENCIA");
			String tipo = rs.getString("TIPO");
			int aeronave = rs.getInt("AERONAVE");
			String aerolinea = rs.getString("AEROLINEA");
			String origen = rs.getString("ORIGEN");
			String destino = rs.getString("DESTINO");
			vuelos.add(new Vuelo(id, horaSalida, horaLlegada, duracion, distancia, frecuencia, tipo, aeronave, aerolinea, origen, destino));
		}
		return vuelos;
	}
	
	public ArrayList<Vuelo> RFC7(String idAeropuerto,String aerolinea,int aeronave)throws SQLException,Exception
	{
		ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();
		String sql0 = "CREATE INDEX rfc7 ON ISIS2304A091620.VUELOS(ORIGEN,DESTINO)";
		String sql1 = "CREATE INDEX rfc71 ON ISIS2304A091620.VUELOS(AEROLINEA)";
		String sql = "SELECT * FROM ISIS2304A091620.AEROPUERTOS a INNER JOIN ISIS2304A091620.VUELOS b ON a.CODIGO = b.ORIGEN OR a.CODIGO = b.DESTINO WHERE a.CODIGO = "+idAeropuerto+" and b.aerolinea= "+aerolinea+" and b.aeronave="+aeronave+" and b.hora_salida BETWEEN '01/01/2017'	AND '01/02/2017'";

		/** Agrega los vuelos origen y destino del aeropuerto a la lista*/
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		String sql2 = "DROP INDEX rfc7";
		String sql3 = "DROP INDEX rfc71";
		while (rs.next()) 
		{
			String id = rs.getString("ID");
			Date horaSalida = rs.getDate("HORA_SALIDA");
			Date horaLlegada = rs.getDate("HORA_LLEGADA");
			double duracion = rs.getDouble("DURACION");
			double distancia = rs.getDouble("DISTANCIA");
			int frecuencia = rs.getInt("FRECUENCIA");
			String tipo = rs.getString("TIPO");
			int aeronave2 = rs.getInt("AERONAVE");
			String aerolinea2 = rs.getString("AEROLINEA");
			String origen = rs.getString("ORIGEN");
			String destino = rs.getString("DESTINO");
			vuelos.add(new Vuelo(id, horaSalida, horaLlegada, duracion, distancia, frecuencia, tipo, aeronave2, aerolinea2, origen, destino));
		}
		
		return vuelos;
	}
	
	public ArrayList<Vuelo> RFC8(String idAeropuerto, int aeronave)throws SQLException,Exception
	{
		ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();
		String sql0 = "CREATE INDEX rfc8 ON ISIS2304A091620.VUELOS(ORIGEN,DESTINO)";
		String sql1 = "CREATE INDEX rfc81 ON ISIS2304A091620.VUELOS(HORA_SALIDA,HORA_LLEGADA)";
		String sql = "SELECT * FROM ISIS2304A091620.AEROPUERTOS a INNER JOIN ISIS2304A091620.VUELOS b ON a.CODIGO = b.ORIGEN OR a.CODIGO = b.DESTINO WHERE a.CODIGO = "+idAeropuerto+"and b.aeronave="+aeronave+" and b.hora_salida BETWEEN '01/01/2017' AND '01/02/2017'";
		
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		
		String sql2 = "DROP INDEX rfc8";
		String sql3 = "DRO INDEX rfc81";
		while (rs.next()) 
		{
			String id = rs.getString("ID");
			Date horaSalida = rs.getDate("HORA_SALIDA");
			Date horaLlegada = rs.getDate("HORA_LLEGADA");
			double duracion = rs.getDouble("DURACION");
			double distancia = rs.getDouble("DISTANCIA");
			int frecuencia = rs.getInt("FRECUENCIA");
			String tipo = rs.getString("TIPO");
			int aeronave2 = rs.getInt("AERONAVE");
			String aerolinea2 = rs.getString("AEROLINEA");
			String origen = rs.getString("ORIGEN");
			String destino = rs.getString("DESTINO");
			vuelos.add(new Vuelo(id, horaSalida, horaLlegada, duracion, distancia, frecuencia, tipo, aeronave2, aerolinea2, origen, destino));
		}
		
		return vuelos;
	}
	public ArrayList<Vuelo> RFC9(int cliente, String tipo)throws SQLException,Exception
	{
		ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();
		String sql0 = "INSERT BITMAP INDEX rfc9 ON ISIS2304A091620.SILLAS(TIPO)";
		String sql = "SELECT v.* FROM VUELOS v, (SELECT * FROM SILLAS s JOIN (SELECT RESERVAS.VUELO,RESERVAS_VIAJERO.SILLA,RESERVAS.CLIENTE FROM RESERVAS JOIN RESERVAS_VIAJERO on RESERVAS_VIAJERO.ID = RESERVAS.ID)r on s.numero = r.SILLA)a WHERE a.cliente = "+cliente+" and v.ID = a.VUELO and a.TIPO = "+tipo;
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String id = rs.getString("ID");
			Date horaSalida = rs.getDate("HORA_SALIDA");
			Date horaLlegada = rs.getDate("HORA_LLEGADA");
			double duracion = rs.getDouble("DURACION");
			double distancia = rs.getDouble("DISTANCIA");
			int frecuencia = rs.getInt("FRECUENCIA");
			String tipo2 = rs.getString("TIPO");
			int aeronave2 = rs.getInt("AERONAVE");
			String aerolinea2 = rs.getString("AEROLINEA");
			String origen = rs.getString("ORIGEN");
			String destino = rs.getString("DESTINO");
			vuelos.add(new Vuelo(id, horaSalida, horaLlegada, duracion, distancia, frecuencia, tipo2, aeronave2, aerolinea2, origen, destino));
		}
		
		return vuelos;
	}
	public ArrayList<Vuelo> RFC10(String idAeropuertoO, String idAeropuertoD)throws SQLException,Exception
	{
		ArrayList<Vuelo> vuelos = new ArrayList<Vuelo>();

		
		String sql =
		"SELECT v.*, Nvl(C.pesotransportado, 0) AS PESO_TRANSPORTADO FROM   ISIS2304A091620.vuelos v LEFT JOIN (SELECT vuelo,  Sum(peso_carga) AS PesoTransportado  FROM   ISIS2304A091620.reservas_carga  GROUP  BY vuelo)c  ON v.id = c.vuelo WHERE  ( V.origen = "+idAeropuertoO+" AND V.destino = "+idAeropuertoD+" ) OR ( V.origen = "+idAeropuertoD+" AND V.destino = "+idAeropuertoO+" )"; 

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			String id = rs.getString("ID");
			Date horaSalida = rs.getDate("HORA_SALIDA");
			Date horaLlegada = rs.getDate("HORA_LLEGADA");
			double duracion = rs.getDouble("DURACION");
			double distancia = rs.getDouble("DISTANCIA");
			int frecuencia = rs.getInt("FRECUENCIA");
			String tipo = rs.getString("TIPO");
			int aeronave = rs.getInt("AERONAVE");
			String aerolinea = rs.getString("AEROLINEA");
			String origen = rs.getString("ORIGEN");
			String destino = rs.getString("DESTINO");
			vuelos.add(new Vuelo(id, horaSalida, horaLlegada, duracion, distancia, frecuencia, tipo, aeronave, aerolinea, origen, destino));
		}
		
		return vuelos;
	}
	
	
	public ArrayList<VueloMsg> RFC11(String idAeropuerto)throws SQLException,Exception
	{
		ArrayList<VueloMsg> vuelos = new ArrayList<VueloMsg>();
		String sql = "SELECT * FROM ISIS2304A091620.AEROPUERTOS a INNER JOIN ISIS2304A091620.VUELOS b ON a.CODIGO = b.ORIGEN OR a.CODIGO = b.DESTINO WHERE a.CODIGO = "+idAeropuerto;
				
	    /** Agrega los vuelos origen y destino del aeropuerto a la lista*/
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();
		while (rs.next()) 
		{
			String id = rs.getString("ID");
			double duracion = rs.getDouble("DURACION");
			double distancia = rs.getDouble("DISTANCIA");
			int frecuencia = rs.getInt("FRECUENCIA");
			String tipo = rs.getString("TIPO");
			int aeronave2 = rs.getInt("AERONAVE");
			String aerolinea2 = rs.getString("AEROLINEA");
			String origen = rs.getString("ORIGEN");
			String destino = rs.getString("DESTINO");
			vuelos.add(new VueloMsg(id,duracion, distancia, frecuencia, tipo, aeronave2, aerolinea2, origen, destino));
		}
		
		return vuelos;
	}
	
	
}
