package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import vos.Aeronave;
import vos.Vuelo;

public class DAOAeronaves {
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
	public DAOAeronaves() 
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
	public ArrayList<Aeronave> darAeronaves() throws SQLException, Exception 
	{
		ArrayList<Aeronave> aeronaves = new ArrayList<Aeronave>();

		String sql = "SELECT * FROM ISIS2304A091620.AERONAVES";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int numSerie = rs.getInt("NUM_SERIE");
			String marca = rs.getString("MARCA");
			String modelo = rs.getString("MODELO");
			Date anioFabricacion = rs.getDate("ANIO_FABRICACION");
			String tipo = rs.getString("TIPO");
			String idAerolinea = rs.getString("ID_AEROLINEA");
			String posesion = rs.getString("POSESION"); 
			aeronaves.add(new Aeronave(numSerie, marca, modelo, anioFabricacion, tipo, idAerolinea, posesion));
		}
		return aeronaves;
	}


	/**
	 * Método que busca el vuelo con el id que entra como parámetro.
	 * @param name - Nombre de el vuelo a buscar
	 * @return El vuelo encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Aeronave buscarVueloPorNumSerie(int numSerie) throws SQLException, Exception
	{
		Aeronave aeronave = null;

		String sql = "SELECT * FROM ISIS2304A091620.AERONAVES WHERE NUM_SERIE ='" + numSerie + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int numSerie2 = rs.getInt("NUM_SERIE");
			String marca = rs.getString("MARCA");
			String modelo = rs.getString("MODELO");
			Date anioFabricacion = rs.getDate("ANIO_FABRICACION");
			String tipo = rs.getString("TIPO");
			String idAerolinea = rs.getString("ID_AEROLINEA");
			String posesion = rs.getString("POSESION"); 
			aeronave = new Aeronave(numSerie2, marca, modelo, anioFabricacion, tipo, idAerolinea, posesion);
		}

		return aeronave;
	}
	/**
	 * Método que agrega el vuelo que entra como parámetro a la base de datos.
	 * @param aeropuerto - el aeropuerto a agregar. aeropuerto !=  null
	 * <b> post: </b> se ha agregado el aeropuerto a la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que el vuelo baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el aeropuerto a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addAeronave(Aeronave aeronave) throws SQLException, Exception 
	{
		String sql = "INSERT ISIS2304A091620.AERONAVES VALUES (";
		sql += aeronave.getNumSerie() + ",'";
		sql += aeronave.getMarca() + "',";
		sql += aeronave.getModelo() + "',";
		sql += aeronave.getAnioFabricacion() + ",";
		sql += aeronave.getTipo() + ",";
		sql += aeronave.getAerolinea() +  ")";
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
	public void updateAeronave(Aeronave aeronave) throws SQLException, Exception 
	{
		String sql = "UPDATE ISIS2304A091620.AERONAVES SET (";
		sql += "numSerie='" +aeronave.getNumSerie() + ",'";
		sql += "marca='" +aeronave.getMarca() + "',";
		sql += "modelo='" +aeronave.getModelo() + "',";
		sql += "anioFabricacion='" +aeronave.getAnioFabricacion() + ",";
		sql += "tipo='" +aeronave.getTipo() + ",";
		sql += "aerolinea='" +aeronave.getAerolinea();
		sql += " WHERE NUM_SERIE = " + aeronave.getNumSerie();
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
	 * @param aeronave - el vuelo a eliminar. vuelo !=  null
	 * <b> post: </b> se ha borrado el vuelo en la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el vuelo.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void deleteAeronave(Aeronave aeronave) throws SQLException, Exception 
	{
		String sql = "DELETE FROM ISIS2304A091620.AERONAVES";
		sql += " WHERE NUM_SERIE = " + aeronave.getNumSerie();

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
	 * RFC6
	 */
	public ArrayList[] consultarAeronaves(int numSeriep) throws SQLException,Exception{
		ArrayList<Aeronave> aeronaves = new ArrayList<>();
		ArrayList<Vuelo> vuelos = new  ArrayList<>();
		ArrayList[] lista = new ArrayList[2];
	String sql = "SELECT * FROM ISIS2304A091620.AERONAVES a,ISIS2304A091620.VUELOS v WHERE a.NUM_SERIE=v.AERONAVE AND a.NUM_SERIE = '"+numSeriep+"'";
		System.out.println("SQL stmt:" + sql);
		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int numSerie = rs.getInt("NUM_SERIE");
			String marca = rs.getString("MARCA");
			String modelo = rs.getString("MODELO");
			Date anioFabricacion = rs.getDate("ANIO_FABRICACION");
			String tipo = rs.getString("TIPO");
			String idAerolinea = rs.getString("ID_AEROLINEA");
			String posesion = rs.getString("POSESION"); 
			String id = rs.getString("ID");
			Date horaSalida = rs.getDate("HORA_SALIDA");
			Date horaLlegada = rs.getDate("HORA_LLEGADA");
			double duracion = rs.getDouble("DURACION");
 			double distancia = rs.getDouble("DISTANCIA");
			int frecuencia = rs.getInt("FRECUENCIA");
			String tipo1 = rs.getString("TIPO");
			int aeronave = rs.getInt("AERONAVE");
			String aerolinea = rs.getString("AEROLINEA");
			String origen = rs.getString("ORIGEN");
			String destino = rs.getString("DESTINO");
			vuelos.add(new Vuelo(id, horaSalida, horaLlegada, duracion, distancia, frecuencia, tipo, aeronave, aerolinea, origen, destino));
			aeronaves.add(new Aeronave(numSerie, marca, modelo, anioFabricacion, tipo1, idAerolinea, posesion));
		}
		lista[0] = aeronaves;
		lista[1] = vuelos;
		return lista;
	}
}
