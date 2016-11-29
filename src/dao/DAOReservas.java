package dao;

import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;

import estructuras.MapDijkstraSP;
import estructuras.MapEdgeVuelo;
import estructuras.MapGraph;
import vos.Aeropuerto;
import vos.Reserva;
import vos.Vuelo;

public class DAOReservas {
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
	public DAOReservas() 
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
	public ArrayList<Reserva> darReservas() throws SQLException, Exception 
	{
		ArrayList<Reserva> reserva = new ArrayList<Reserva>();

		String sql = "SELECT * FROM ISIS2304A091620.RESERVAS";

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int id = rs.getInt("ID");
			String vuelo = rs.getString("VUELO");
			int cliente = rs.getInt("CLIENTE");
			System.out.println(cliente);
			reserva.add(new Reserva(id, vuelo, cliente));
		}
		return reserva;
	}


	/**
	 * Método que busca el vuelo con el id que entra como parámetro.
	 * @param name - Nombre de el vuelo a buscar
	 * @return El vuelo encontrado
	 * @throws SQLException - Cualquier error que la base de datos arroje.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public Reserva buscarReservaPorId(int id) throws SQLException, Exception
	{
		Reserva reserva = null;

		String sql = "SELECT * FROM ISIS2304A091620.RESERVAS WHERE ID ='" + id + "'";

		System.out.println("SQL stmt:" + sql);

		PreparedStatement prepStmt = conn.prepareStatement(sql);
		recursos.add(prepStmt);
		ResultSet rs = prepStmt.executeQuery();

		while (rs.next()) 
		{
			int id2 = rs.getInt("ID");
			String vuelo = rs.getString("VUELO");
			int cliente = rs.getInt("CLIENTE");
			reserva = new Reserva(id2, vuelo,cliente);
		}

		return reserva;
	}
	
	/**
	 * Método que agrega el vuelo que entra como parámetro a la base de datos.
	 * @param aeropuerto - el aeropuerto a agregar. aeropuerto !=  null
	 * <b> post: </b> se ha agregado el aeropuerto a la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que el vuelo baje  a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo agregar el aeropuerto a la base de datos
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void addReserva(Reserva reserva) throws SQLException, Exception 
	{
		String sql = "INSERT INTO ISIS2304A091620.RESERVAS VALUES (";
		sql += reserva.getId() + ",'";
		sql += reserva.getVuelo() + "',";
		sql += reserva.getCliente()+")";

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
	
	public void addReservaVuelos (Reserva reserva) throws SQLException, Exception
	{		
			// Toma la informacion del vuelo  de la reserva
			String idVuelo = reserva.getVuelo();
			DAOVuelos daoVuelos = new DAOVuelos();
			Vuelo vuelo = daoVuelos.buscarVueloPorId(idVuelo);
			String tipoVuelo = vuelo.getTipo();
			
			// Toma la informacion de Aeropuerto
			String idAeroOrigen = vuelo.getOrigen();
			int iIdO = idAeroOrigen.hashCode();
			String idAeroDestino = vuelo.getDestino();
			int iIdD = idAeroDestino.hashCode();
			
			DAOAeropuertos daoAeropuertos = new DAOAeropuertos();
			ArrayList<Aeropuerto> aeroT = daoAeropuertos.darAeropuertos();
		 
			// Calcula la ruta optima
			MapGraph grafo = new MapGraph();
			for (int i = 0; i < aeroT.size(); i++)
			{
				Aeropuerto act = aeroT.get(i);
				grafo.addNode(act);
				grafo.addEdge(vuelo, null);
			}
			
			MapDijkstraSP grafoD = new MapDijkstraSP(grafo, iIdO, iIdD);
			LinkedList<MapEdgeVuelo> listaArcos = grafoD.pathTo();
			
			for(int i = 0; i < listaArcos.size(); i++)
			{
				MapEdgeVuelo arcoVueloAct = listaArcos.get(i);
				String idVueloN = arcoVueloAct.getStringFrom();
				
				//Realiza la reserva del vuelo generando un nuevo id para la nueva reserva
				DAOReservas daoReservas = new DAOReservas();
				ArrayList<Reserva> reservas = daoReservas.darReservas();
				Reserva ultima = reservas.get(reservas.size());
				int id = ultima.getId()+1;
				Reserva nueva = new Reserva(id, idVueloN,reserva.getId());
				addReserva(nueva);
			}
		}
	
	public void addReservaGrupal(ArrayList<Reserva> reservas) throws SQLException, Exception
	{
		for (int i = 0; i < reservas.size(); i++)
		{
			Reserva act = reservas.get(i);
			addReserva(act);
			PreparedStatement prepStmt = conn.prepareStatement("SAVEPOINT");
			recursos.add(prepStmt);
			prepStmt.executeQuery();
		}
		
	}

	/**
	 * Método que actualiza el aeropuerto que entra como parámetro en la base de datos.
	 * @param aeropuerto - el aeropuerto a actualizar. aeropuerto !=  null
	 * <b> post: </b> se ha actualizado el aeropuerto en la base de datos en la transaction actual. pendiente que el master
	 * haga commit para que los cambios bajen a la base de datos.
	 * @throws SQLException - Cualquier error que la base de datos arroje. No pudo actualizar el aeropuerto.
	 * @throws Exception - Cualquier error que no corresponda a la base de datos
	 */
	public void updateReserva(Reserva reserva) throws SQLException, Exception 
	{
		String sql = "UPDATE ISIS2304A091620.RESERVAS SET ";
		sql += "id='" +reserva.getId() + ",";
		sql += "vuelo='" +reserva.getVuelo() ;
		sql += " WHERE ID = " + reserva.getId();
		

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
	public void deleteReserva(Reserva reserva) throws SQLException, Exception 
	{
		String sql = "DELETE FROM ISIS2304A091620.RESERVAS";
		sql += " WHERE ID = " + reserva.getId();

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
	
	public void deleteReservaId(Reserva reserva) throws SQLException, Exception 
	{
		DAOVuelos daoVuelos = new DAOVuelos();
		String vueloActId = reserva.getVuelo();
		System.out.println(vueloActId);
		Vuelo vueloAct = daoVuelos.buscarVueloPorId(vueloActId);
		Date horaAct = vueloAct.getHoraSalida();
		long miliAct = horaAct.getTime();
		Date actual = new Date();
		long miliIng = actual.getTime();
		long diferencia = miliAct-miliIng;
		long dia = 86400000;
		
		if (diferencia <= dia)
		{
			deleteReserva(reserva);
		}
		else
		{
			throw new Exception("Reserva no cancelada, no cumple el plazo de 24 horas");
		}	
	}
	
	public void deleteReservaVuelos(Reserva reserva) throws SQLException, Exception 
	{
		DAOReservas daoReservas = new DAOReservas();
		ArrayList<Reserva> reservas = daoReservas.darReservas();

		for (int i = 0; i < reservas.size(); i++)
		{
			Reserva act = reservas.get(i);
			int id1 = reserva.getId();
			int id2 = act.getId();

			if (id1 == id2)
			{
				Date D = new Date();
				deleteReservaId(reserva);
			}
			else
			{
				//No hace nada
			}
		}
	}
}
