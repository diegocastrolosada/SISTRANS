package tm;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Date;
import java.util.Properties;

import javax.jms.JMSException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import dao.*;
import dtm.VuelAndesDistributed;
import jms.NonReplyException;
import vos.*;
import vos2.ListaVuelosMsg;
import vos2.VueloMsg;
public class VuelAndesMaster {
	/**
	 * Atributo est谩tico que contiene el path relativo del archivo que tiene los datos de la conexi贸n
	 */
	private static final String CONNECTION_DATA_FILE_NAME_REMOTE = "/conexion.properties";

	/**
	 * Atributo est谩tico que contiene el path absoluto del archivo que tiene los datos de la conexi贸n
	 */
	private  String connectionDataPath;

	/**
	 * Atributo que guarda el usuario que se va a usar para conectarse a la base de datos.
	 */
	private String user;

	/**
	 * Atributo que guarda la clave que se va a usar para conectarse a la base de datos.
	 */
	private String password;

	/**
	 * Atributo que guarda el URL que se va a usar para conectarse a la base de datos.
	 */
	private String url;

	/**
	 * Atributo que guarda el driver que se va a usar para conectarse a la base de datos.
	 */
	private String driver;

	/**
	 * Conexi贸n a la base de datos
	 */
	private Connection conn;
	
	private VuelAndesDistributed dtm;
	/**
	 * M茅todo constructor de la clase VideoAndesMaster, esta clase modela y contiene cada una de las 
	 * transacciones y la logia de negocios que estas conllevan.
	 * <b>post: </b> Se crea el objeto VideoAndesMaster, se inicializa el path absoluto de el archivo de conexi贸n y se
	 * inicializa los atributos que se usan par la conexi贸n a la base de datos.
	 * @param contextPathP - path absoluto en el servidor del contexto del deploy actual
	 */
	public VuelAndesMaster(String contextPathP) {
		connectionDataPath = contextPathP + CONNECTION_DATA_FILE_NAME_REMOTE;
		initConnectionData();
	}
	/*
	 * M茅todo que  inicializa los atributos que se usan para la conexion a la base de datos.
	 * <b>post: </b> Se han inicializado los atributos que se usan par la conexi贸n a la base de datos.
	 */
	private void initConnectionData() {
		try {
			File arch = new File(this.connectionDataPath);
			Properties prop = new Properties();
			FileInputStream in = new FileInputStream(arch);
			prop.load(in);
			in.close();
			this.url = prop.getProperty("url");
			this.user = prop.getProperty("usuario");
			this.password = prop.getProperty("clave");
			this.driver = prop.getProperty("driver");
			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * M茅todo que  retorna la conexi贸n a la base de datos
	 * @return Connection - la conexi贸n a la base de datos
	 * @throws SQLException - Cualquier error que se genere durante la conexi贸n a la base de datos
	 */
	private Connection darConexion() throws SQLException {
		System.out.println("Connecting to: " + url + " With user: " + user);
		return DriverManager.getConnection(url, user, password);
	}
	////////////////////////////////////////
	///////Transacciones////////////////////
	////////////////////////////////////////

	//
	// Lista Transacciones Disponibles
	// Administrador
	// Aeropuertos
	// Aerolineas
	// Ciudades
	// Aeronaves
	// Sillas
	// Vuelos (Carga y Pasajero)
	// Remitente
	// Viajero
	// Aeronaves (Carga y Pasajero)
	//

	/**
	 *  Administradores
	 */

	//TODO Aca empieza Administradores


	/**
	 * M茅todo que modela la transacci贸n que retorna todos los administradores de la base de datos.
	 * @return ArrayList - objeto que modela  un arreglo de administradores. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaAdministradores darAdministradores() throws Exception {
		ArrayList<Administrador> administradores;
		DAOAdministradores daoAdministradores = new DAOAdministradores();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAdministradores.setConn(conn);
			administradores = daoAdministradores.darAdministradores();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAdministradores.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaAdministradores(administradores);
	}

	/**
	 * M茅todo que modela la transacci贸n que busca el/los administradores en la base de datos con el nombre entra como par谩metro.
	 * @param nombre - Nombre del administrador a buscar. nombre != null
	 * @return ArrayList - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaAdministradores buscarAdministradorPorNombre(String nombre) throws Exception {
		ArrayList<Administrador> administradores;
		DAOAdministradores daoAdministradores = new DAOAdministradores();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAdministradores.setConn(conn);
			administradores = daoAdministradores.buscarAdministradorPorNombre(nombre);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAdministradores.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaAdministradores(administradores);
	}

	/**
	 * M茅todo que modela la transacci贸n que busca el/los administradores en la base de datos con el nombre entra como par谩metro.
	 * @param id - id del administrador a buscar. id != null
	 * @return ArrayList - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaAdministradores buscarAdministradorPorId(int id) throws Exception {
		ArrayList<Administrador> administradores;
		DAOAdministradores daoAdministradores = new DAOAdministradores();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAdministradores.setConn(conn);
			administradores = daoAdministradores.buscarAdministradorPorId(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAdministradores.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaAdministradores(administradores);
	}

	/**
	 * M茅todo que modela la transacci贸n que agrega un solo administrador a la base de datos.
	 * <b> post: </b> se ha agregado el administrador que entra como par谩metro
	 * @param administrador - el administrador a agregar. video != null
	 * @throws Exception - cualquier error que se genera agregando el video
	 */
	public void addAdministrador(Administrador administrador) throws Exception {
		DAOAdministradores daoAdministradores = new DAOAdministradores();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAdministradores.setConn(conn);
			daoAdministradores.addAdministrador(administrador);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAdministradores.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que agrega los administradores que entran como par谩metro a la base de datos.
	 * <b> post: </b> se han agregado los administradores que entran como par谩metro
	 * @param administradores - objeto que modela una lista de administradores y se estos se pretenden agregar. administradores != null
	 * @throws Exception - cualquier error que se genera agregando los videos
	 */
	public void addAdministradores(ListaAdministradores administradores) throws Exception {
		DAOAdministradores daoAdministradores = new DAOAdministradores();
		try 
		{
			//////Transacci贸n - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoAdministradores.setConn(conn);
			for(Administrador administrador : administradores.getAdministradores())
				daoAdministradores.addAdministrador(administrador);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoAdministradores.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que actualiza el administrador que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha actualizado el administrador que entra como par谩metro
	 * @param administrador - administrador a actualizar. administrador != null
	 * @throws Exception - cualquier error que se genera actualizando los administradores
	 */
	public void updateAdministrador(Administrador administrador) throws Exception {
		DAOAdministradores daoAdministradores = new DAOAdministradores();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAdministradores.setConn(conn);
			daoAdministradores.updateAdministrador(administrador);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAdministradores.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que elimina el administrador que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha eliminado el administrador que entra como par谩metro
	 * @param administrador - administrador a eliminar. administrador != null
	 * @throws Exception - cualquier error que se genera actualizando los administradores
	 */
	public void deleteAdministrador(Administrador administrador) throws Exception {
		DAOAdministradores daoAdministradores = new DAOAdministradores();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAdministradores.setConn(conn);
			daoAdministradores.deleteAdministrador(administrador);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAdministradores.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 *  Aerolineas
	 */

	//TODO Aca empieza Aerolineas

	/**
	 * M茅todo que modela la transacci贸n que retorna todos las aerolineas de la base de datos.
	 * @return ArrayList - objeto que modela  un arreglo de Aerolineas. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaAerolineas darAerolineas() throws Exception {
		ArrayList<Aerolinea> aerolineas;
		DAOAerolineas daoAerolineas = new DAOAerolineas();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAerolineas.setConn(conn);
			aerolineas = daoAerolineas.darAerolineas();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAerolineas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaAerolineas(aerolineas);
	}

	/**
	 * M茅todo que modela la transacci贸n que busca la/las aerolineas en la base de datos con el nombre entra como par谩metro.
	 * @param nombre - Nombre de la aerolinea a buscar. nombre != null
	 * @return ArrayList - objeto que modela  un arreglo de aerolineas. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaAerolineas buscarAerolineaPorNombre(String nombre) throws Exception {
		ArrayList<Aerolinea> aerolineas;
		DAOAerolineas daoAerolineas = new DAOAerolineas();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAerolineas.setConn(conn);
			aerolineas = daoAerolineas.buscarAerolineaPorNombre(nombre);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAerolineas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaAerolineas(aerolineas);
	}

	/**
	 * M茅todo que modela la transacci贸n que busca el/las aerolineas en la base de datos con el nombre entra como par谩metro.
	 * @param id - id de la aerolinea a buscar. id != null
	 * @return ArrayList - objeto que modela  un arreglo de aerolineas. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaAerolineas buscarAerolineaPorIata(String iata) throws Exception {
		ArrayList<Aerolinea> aerolineas;
		DAOAerolineas daoAerolineas = new DAOAerolineas();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAerolineas.setConn(conn);
			aerolineas = daoAerolineas.buscarAerolineaPorIata(iata);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAerolineas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaAerolineas(aerolineas);
	}

	/**
	 * M茅todo que modela la transacci贸n que agrega un solo aerolinea a la base de datos.
	 * <b> post: </b> se ha agregado el aerolinea que entra como par谩metro
	 * @param aerolinea - el aerolinea a agregar. video != null
	 * @throws Exception - cualquier error que se genera agregando el video
	 */
	public void addAerolinea(Aerolinea aerolinea) throws Exception {
		DAOAerolineas daoAerolineas = new DAOAerolineas();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAerolineas.setConn(conn);
			daoAerolineas.addAerolinea(aerolinea);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAerolineas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que agrega las aerolineas que entran como par谩metro a la base de datos.
	 * <b> post: </b> se han agregado las aerolineas que entran como par谩metro
	 * @param administradores - objeto que modela una lista de aerolineas y se estos se pretenden agregar. aerolineas != null
	 * @throws Exception - cualquier error que se genera agregando las aerolineas
	 */
	public void addAerolineas(ListaAerolineas aerolineas) throws Exception {
		DAOAerolineas daoAerolineas = new DAOAerolineas();
		try 
		{
			//////Transacci贸n - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoAerolineas.setConn(conn);
			for(Aerolinea aerolinea : aerolineas.getAerolineas())
				daoAerolineas.addAerolinea(aerolinea);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoAerolineas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que actualiza el aerolinea que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha actualizado el aerolinea que entra como par谩metro
	 * @param aerolinea - aerolinea a actualizar. aerolinea != null
	 * @throws Exception - cualquier error que se genera actualizando las aerolineas
	 */
	public void updateAerolinea(Aerolinea aerolinea) throws Exception {
		DAOAerolineas daoAerolineas = new DAOAerolineas();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAerolineas.setConn(conn);
			daoAerolineas.updateAerolinea(aerolinea);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAerolineas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que elimina el aerolinea que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha eliminado el aerolinea que entra como par谩metro
	 * @param administrador - aerolinea a eliminar. aerolinea != null
	 * @throws Exception - cualquier error que se genera actualizando las aerolineas
	 */
	public void deleteAerolineas(Aerolinea aerolinea) throws Exception {
		DAOAerolineas daoAerolineas = new DAOAerolineas();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAerolineas.setConn(conn);
			daoAerolineas.deleteAerolinea(aerolinea);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAerolineas.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}


	}

	/**
	 *  Aeropuertos
	 */

	//TODO Aca empieza Aeropuertos

	/**
	 * M茅todo que modela la transacci贸n que retorna todos las aeropuertos de la base de datos.
	 * @return ArrayList - objeto que modela  un arreglo de aeropuertos. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaAeropuertos darAeropuertos() throws Exception {
		ArrayList<Aeropuerto> aeropuertos;
		DAOAeropuertos daoAeropuertos = new DAOAeropuertos();

		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeropuertos.setConn(conn);
			aeropuertos = daoAeropuertos.darAeropuertos();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeropuertos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaAeropuertos(aeropuertos);
	}

	/**
	 * M茅todo que modela la transacci贸n que busca la/las aeropuertos en la base de datos con el nombre entra como par谩metro.
	 * @param nombre - Nombre de la aeropuerto a buscar. nombre != null
	 * @return ArrayList - objeto que modela  un arreglo de aeropuertos. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaAeropuertos buscarAeropuertoPorNombre(String nombre) throws Exception {
		ArrayList<Aeropuerto> aeropuertos;
		DAOAeropuertos daoAeropuertos = new DAOAeropuertos();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeropuertos.setConn(conn);
			aeropuertos = daoAeropuertos.buscarAeropuertoPorNombre(nombre);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeropuertos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaAeropuertos(aeropuertos);
	}

	/**
	 * M茅todo que modela la transacci贸n que busca el/las aerolineas en la base de datos con el codigo entra como par谩metro.
	 * @param codigo - codigo de la aerolinea a buscar. codigo != null
	 * @return ArrayList - objeto que modela  un arreglo de aerolineas. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaAeropuertos buscarAeropuertoPorCodigo(String codigo) throws Exception {
		ArrayList<Aeropuerto> aeropuertos;
		DAOAeropuertos daoAeropuertos = new DAOAeropuertos();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeropuertos.setConn(conn);
			aeropuertos = daoAeropuertos.buscarAeropuertoPorCodigo(codigo);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeropuertos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaAeropuertos(aeropuertos);
	}

	/**
	 * M茅todo que modela la transacci贸n que agrega un solo aeropuerto a la base de datos.
	 * <b> post: </b> se ha agregado el aeropuerto que entra como par谩metro
	 * @param aeropuerto - el aeropuerto a agregar. aeropuerto != null
	 * @throws Exception - cualquier error que se genera agregando el video
	 */
	public void addAeropuerto(Aeropuerto aeropuerto) throws Exception {
		DAOAeropuertos daoAeropuertos = new DAOAeropuertos();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeropuertos.setConn(conn);
			daoAeropuertos.addAeropuerto(aeropuerto);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeropuertos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que agrega las aeropuertos que entran como par谩metro a la base de datos.
	 * <b> post: </b> se han agregado las aeropuertos que entran como par谩metro
	 * @param aeropuertos - objeto que modela una lista de aeropuertos y se estos se pretenden agregar. aeropuertos != null
	 * @throws Exception - cualquier error que se genera agregando las aeropuertos
	 */
	public void addAeropuertos(ListaAeropuertos aeropuertos) throws Exception {
		DAOAeropuertos daoAeropuertos = new DAOAeropuertos();
		try 
		{
			//////Transacci贸n - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoAeropuertos.setConn(conn);
			for(Aeropuerto aeropuerto : aeropuertos.getAeropuertos())
				daoAeropuertos.addAeropuerto(aeropuerto);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoAeropuertos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que actualiza el aeropuerto que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha actualizado el aeropuerto que entra como par谩metro
	 * @param aeropuerto - aeropuerto a actualizar. aeropuerto != null
	 * @throws Exception - cualquier error que se genera actualizando las aeropuertos
	 */
	public void updateAeropuerto(Aeropuerto aeropuerto) throws Exception {

		DAOAeropuertos daoAeropuertos = new DAOAeropuertos();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeropuertos.setConn(conn);
			daoAeropuertos.updateAeropuerto(aeropuerto);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeropuertos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que elimina el aeropuerto que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha eliminado el aeropuerto que entra como par谩metro
	 * @param aeropuerto - aeropuerto a eliminar. aeropuerto != null
	 * @throws Exception - cualquier error que se genera actualizando las aeropuertos
	 */
	public void deleteAeropuertos(Aeropuerto aeropuerto) throws Exception {

		DAOAeropuertos daoAeropuertos = new DAOAeropuertos();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeropuertos.setConn(conn);
			daoAeropuertos.deleteAeropuerto(aeropuerto);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeropuertos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}

	}

	/**
	 *  Ciudades
	 */

	// TODO Aca empieza Ciudades

	/**
	 * M茅todo que modela la transacci贸n que retorna todos los ciudades de la base de datos.
	 * @return ArrayList - objeto que modela  un arreglo de ciudades. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaCiudades darCiudades() throws Exception {
		ArrayList<Ciudad> ciudades;
		DAOCiudades daoCiudades = new DAOCiudades();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoCiudades.setConn(conn);
			ciudades = daoCiudades.darCiudades();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCiudades.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaCiudades(ciudades);
	}

	/**
	 * M茅todo que modela la transacci贸n que busca el/los ciudades en la base de datos con el nombre entra como par谩metro.
	 * @param nombre - Nombre la ciudad a buscar. nombre != null
	 * @return ArrayList - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaCiudades buscarCiudadPorId(int id) throws Exception {
		ArrayList<Ciudad> ciudades;
		DAOCiudades daoCiudades = new DAOCiudades();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoCiudades.setConn(conn);
			ciudades = daoCiudades.buscarCiudadPorId(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCiudades.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaCiudades(ciudades);
	}

	/**
	 * M茅todo que modela la transacci贸n que busca el/los ciudades en la base de datos con el nombre entra como par谩metro.
	 * @param id - id del ciudades a buscar. id != null
	 * @return ArrayList - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaCiudades buscarCiudadPorIdPais(int idPais) throws Exception {
		ArrayList<Ciudad> ciudades;
		DAOCiudades daoCiudades = new DAOCiudades();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoCiudades.setConn(conn);
			ciudades = daoCiudades.buscarCiudadPorIdPais(idPais);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCiudades.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaCiudades(ciudades);
	}

	/**
	 * M茅todo que modela la transacci贸n que agrega un solo ciudad a la base de datos.
	 * <b> post: </b> se ha agregado el ciudad que entra como par谩metro
	 * @param ciudad - el ciudad a agregar. video != null
	 * @throws Exception - cualquier error que se genera agregando el video
	 */
	public void addCiudad(Ciudad ciudad) throws Exception {
		DAOCiudades daoCiudades = new DAOCiudades();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoCiudades.setConn(conn);
			daoCiudades.addCiudad(ciudad);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCiudades.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que agrega los ciudades que entran como par谩metro a la base de datos.
	 * <b> post: </b> se han agregado los ciudades que entran como par谩metro
	 * @param ciudades - objeto que modela una lista de ciudades y se estos se pretenden agregar. ciudades != null
	 * @throws Exception - cualquier error que se genera agregando los videos
	 */
	public void addCiudades(ListaCiudades ciudades) throws Exception {
		DAOCiudades daoCiudades = new DAOCiudades();
		try 
		{
			//////Transacci贸n - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoCiudades.setConn(conn);
			for(Ciudad ciudad : ciudades.getCiudades())
				daoCiudades.addCiudad(ciudad);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoCiudades.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que actualiza el ciudad que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha actualizado el ciudad que entra como par谩metro
	 * @param ciudad - ciudad a actualizar. ciudad != null
	 * @throws Exception - cualquier error que se genera actualizando los ciudades
	 */
	public void updateCiudad(Ciudad ciudad) throws Exception {
		DAOCiudades daoCiudades = new DAOCiudades();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoCiudades.setConn(conn);
			daoCiudades.updateCiudad(ciudad);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCiudades.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que elimina el ciudad que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha eliminado el ciudad que entra como par谩metro
	 * @param ciudad - ciudad a eliminar. ciudad != null
	 * @throws Exception - cualquier error que se genera actualizando los ciudades
	 */
	public void deleteCiudades(Ciudad ciudades) throws Exception {
		DAOCiudades daoCiudades = new DAOCiudades();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoCiudades.setConn(conn);
			daoCiudades.deleteCiudad(ciudades);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoCiudades.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	/**
	 *  Aeronaves
	 */

	//TODO Aca empieza Aeronaves


	/**
	 * M茅todo que modela la transacci贸n que retorna todos los administradores de la base de datos.
	 * @return ArrayList - objeto que modela  un arreglo de administradores. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaAeronaves darAronaves() throws Exception {
		ArrayList<Aeronave> aeronaves;
		DAOAeronaves daoAeronaves = new DAOAeronaves();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeronaves.setConn(conn);
			aeronaves = daoAeronaves.darAeronaves();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeronaves.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaAeronaves(aeronaves);
	}

	/**
	 * M茅todo que modela la transacci贸n que busca el/los administradores en la base de datos con el nombre entra como par谩metro.
	 * @param nombre - Nombre del administrador a buscar. nombre != null
	 * @return ArrayList - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaAeronaves buscarAeronavesPorId(int numSerie) throws Exception {
		ArrayList<Aeronave> aeronaves = new ArrayList<>();
		DAOAeronaves daoAeronaves = new DAOAeronaves();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeronaves.setConn(conn);
			aeronaves.add(daoAeronaves.buscarVueloPorNumSerie(numSerie));
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeronaves.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaAeronaves(aeronaves);
	}


	/**
	 * M茅todo que modela la transacci贸n que agrega un solo administrador a la base de datos.
	 * <b> post: </b> se ha agregado el administrador que entra como par谩metro
	 * @param aeronave - el administrador a agregar. video != null
	 * @throws Exception - cualquier error que se genera agregando el video
	 */
	public void addAeronave(Aeronave aeronave) throws Exception {
		DAOAeronaves daoAeronaves = new DAOAeronaves();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeronaves.setConn(conn);
			daoAeronaves.addAeronave(aeronave);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeronaves.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que agrega los administradores que entran como par谩metro a la base de datos.
	 * <b> post: </b> se han agregado los administradores que entran como par谩metro
	 * @param aeronaves - objeto que modela una lista de administradores y se estos se pretenden agregar. administradores != null
	 * @throws Exception - cualquier error que se genera agregando los videos
	 */
	public void addAeronaves(ListaAeronaves aeronaves) throws Exception {
		DAOAeronaves daoAeronaves = new DAOAeronaves();
		try 
		{
			//////Transacci贸n - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoAeronaves.setConn(conn);
			for(Aeronave aeronave : aeronaves.getAeronaves())
				daoAeronaves.addAeronave(aeronave);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoAeronaves.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que actualiza el administrador que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha actualizado el administrador que entra como par谩metro
	 * @param administrador - administrador a actualizar. administrador != null
	 * @throws Exception - cualquier error que se genera actualizando los administradores
	 */
	public void updateAeronave(Aeronave aeronave) throws Exception {
		DAOAeronaves daoAeronaves = new DAOAeronaves();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeronaves.setConn(conn);
			daoAeronaves.updateAeronave(aeronave);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeronaves.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	/**
	 * M茅todo que modela la transacci贸n que actualiza el administrador que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha actualizado el administrador que entra como par谩metro
	 * @param administrador - administrador a actualizar. administrador != null
	 * @throws Exception - cualquier error que se genera actualizando los administradores
	 */
	public void updateAeronaveViaje(Vuelo vuelo) throws Exception {
		DAOVuelos daoVuelos = new DAOVuelos();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelos.setConn(conn);
			daoVuelos.updateAeronaveViaje(vuelo);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	/**
	 * M茅todo que modela la transacci贸n que elimina el administrador que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha eliminado el administrador que entra como par谩metro
	 * @param aeronave - administrador a eliminar. administrador != null
	 * @throws Exception - cualquier error que se genera actualizando los administradores
	 */
	public void deleteAeronave(Aeronave aeronave) throws Exception {
		DAOAeronaves daoAeronaves = new DAOAeronaves();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeronaves.setConn(conn);
			daoAeronaves.deleteAeronave(aeronave);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeronaves.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 *  Vuelos
	 */

	// TODO Aca empieza Vuelos

	/**
	 * M茅todo que modela la transacci贸n que retorna todos los vuelos de la base de datos.
	 * @return ArrayList - objeto que modela  un arreglo de vuelos. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaVuelos darVuelos() throws Exception {
		ArrayList<Vuelo> vuelos;
		DAOVuelos daoVuelos = new DAOVuelos();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelos.setConn(conn);
			vuelos = daoVuelos.darVuelos();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaVuelos(vuelos);
	}

	/**
	 * M茅todo que modela la transacci贸n que busca el/los vuelos en la base de datos con el tipo entra como par谩metro.
	 * @param tipo - tipo la silla a buscar. nombre != null
	 * @return ArrayList - objeto que modela  un arreglo de vuelos. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaVuelos buscarVueloPorId(String id) throws Exception {
		Vuelo vuelo;
		ArrayList<Vuelo>  vuelos = new ArrayList<>();
		DAOVuelos daoVuelos = new DAOVuelos();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelos.setConn(conn);
			vuelo = daoVuelos.buscarVueloPorId(id);
			vuelos.add(vuelo);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaVuelos(vuelos);
	}


	/**
	 * M茅todo que modela la transacci贸n que agrega un solo vuelos a la base de datos.
	 * <b> post: </b> se ha agregado el vuelos que entra como par谩metro
	 * @param vuelos - el vuelos a agregar. silla != null
	 * @throws Exception - cualquier error que se genera agregando el video
	 */
	public void addVuelo(Vuelo vuelo) throws Exception {
		DAOVuelos daoVuelos = new DAOVuelos();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelos.setConn(conn);
			daoVuelos.addVuelo(vuelo);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que agrega los vuelos que entran como par谩metro a la base de datos.
	 * <b> post: </b> se han agregado los vuelos que entran como par谩metro
	 * @param vuelos - objeto que modela una lista de vuelos y se estos se pretenden agregar. vuelos != null
	 * @throws Exception - cualquier error que se genera agregando los videos
	 */
	public void addVuelos(ListaVuelos vuelos) throws Exception {
		DAOVuelos daoVuelos = new DAOVuelos();
		try 
		{
			//////Transacci贸n - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoVuelos.setConn(conn);
			for(Vuelo vuelo : vuelos.getVuelos())
				daoVuelos.addVuelo(vuelo);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoVuelos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que actualiza el vuelo que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha actualizado el vuelo que entra como par谩metro
	 * @param vuelo - vuelo a actualizar. vuelo != null
	 * @throws Exception - cualquier error que se genera actualizando los vuelos
	 */
	public void updateVuelo(Vuelo vuelo) throws Exception {
		DAOVuelos daoVuelos = new DAOVuelos();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelos.setConn(conn);
			daoVuelos.updateVuelo(vuelo);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public double reporteViajeCarga(String id) throws Exception{
		DAOVuelos daoVuelos = new DAOVuelos();
		double rta =0;
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelos.setConn(conn);
			rta = daoVuelos.reporteViajeCarga(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return rta;
	}
	public double reporteViajePasajero(String id) throws Exception{
		DAOVuelos daoVuelos = new DAOVuelos();
		double rta = 0;
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelos.setConn(conn);
			rta = daoVuelos.reporteViajePasajero(id);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return rta;
	}
	/**
	 * M茅todo que modela la transacci贸n que elimina el vuelo que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha eliminado el vuelo que entra como par谩metro
	 * @param vuelo - vuelo a eliminar. vuelo != null
	 * @throws Exception - cualquier error que se genera actualizando los sillas
	 */
	public void deleteVuelo(Vuelo vuelo) throws Exception {
		DAOVuelos daoVuelos = new DAOVuelos();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelos.setConn(conn);
			daoVuelos.deleteVuelo(vuelo);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 *  Vuelos Carga
	 */

	// TODO Aca empieza Vuelos Carga

	/**
	 * M茅todo que modela la transacci贸n que retorna todos los vuelos de la base de datos.
	 * @return ArrayList - objeto que modela  un arreglo de vuelos. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaVuelosCarga darVuelosCarga() throws Exception {
		ArrayList<VueloCarga> vuelosCarga;
		DAOVuelosCarga daoVuelosCarga = new DAOVuelosCarga();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelosCarga.setConn(conn);
			vuelosCarga = daoVuelosCarga.darVuelosCarga();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelosCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaVuelosCarga(vuelosCarga);
	}

	/**
	 * M茅todo que modela la transacci贸n que busca el/los vuelos en la base de datos con el tipo entra como par谩metro.
	 * @param tipo - tipo la silla a buscar. nombre != null
	 * @return ArrayList - objeto que modela  un arreglo de vuelos. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaVuelosCarga buscarVueloCargaPorId(String id) throws Exception {
		VueloCarga vueloCarga;
		ArrayList<VueloCarga> vuelosCarga = new ArrayList<>();
		DAOVuelosCarga daoVuelosCarga = new DAOVuelosCarga();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelosCarga.setConn(conn);
			vueloCarga = daoVuelosCarga.buscarVueloCargaPorId(id);
			vuelosCarga.add(vueloCarga);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelosCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaVuelosCarga(vuelosCarga);
	}


	/**
	 * M茅todo que modela la transacci贸n que agrega un solo vuelos a la base de datos.
	 * <b> post: </b> se ha agregado el vuelos que entra como par谩metro
	 * @param vuelos - el vuelos a agregar. silla != null
	 * @throws Exception - cualquier error que se genera agregando el video
	 */
	public void addVueloCarga(VueloCarga vueloCarga) throws Exception {
		DAOVuelosCarga daoVuelosCarga = new DAOVuelosCarga();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelosCarga.setConn(conn);
			daoVuelosCarga.addVueloCarga(vueloCarga);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelosCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que agrega los vuelos que entran como par谩metro a la base de datos.
	 * <b> post: </b> se han agregado los vuelos que entran como par谩metro
	 * @param vuelos - objeto que modela una lista de vuelos y se estos se pretenden agregar. vuelos != null
	 * @throws Exception - cualquier error que se genera agregando los videos
	 */
	public void addVuelosCarga(ListaVuelosCarga vuelosCarga) throws Exception {
		DAOVuelosCarga daoVuelosCarga = new DAOVuelosCarga();
		try 
		{
			//////Transacci贸n - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoVuelosCarga.setConn(conn);
			for(VueloCarga vueloCarga : vuelosCarga.getVuelosCarga())
				daoVuelosCarga.addVueloCarga(vueloCarga);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoVuelosCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que actualiza el vuelo que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha actualizado el vuelo que entra como par谩metro
	 * @param vuelo - vuelo a actualizar. vuelo != null
	 * @throws Exception - cualquier error que se genera actualizando los vuelos
	 */
	public void updateVueloCarga(VueloCarga vueloCarga) throws Exception {
		DAOVuelosCarga daoVuelosCarga = new DAOVuelosCarga();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelosCarga.setConn(conn);
			daoVuelosCarga.updateVueloCarga(vueloCarga);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelosCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que elimina el vuelo que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha eliminado el vuelo que entra como par谩metro
	 * @param vuelo - vuelo a eliminar. vuelo != null
	 * @throws Exception - cualquier error que se genera actualizando los sillas
	 */
	public void deleteVueloCarga(VueloCarga vueloCarga) throws Exception {
		DAOVuelosCarga daoVuelosCarga = new DAOVuelosCarga();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelosCarga.setConn(conn);
			daoVuelosCarga.deleteVueloCarga(vueloCarga);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelosCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}


	/**
	 *  Vuelos Pasajero
	 */

	// TODO Aca empieza Vuelos Pasajero

	/**
	 * M茅todo que modela la transacci贸n que retorna todos los vuelos de la base de datos.
	 * @return ArrayList - objeto que modela  un arreglo de vuelos. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaVuelosPasajero darVuelosPasajero() throws Exception {
		ArrayList<VueloPasajero> vuelosPasajero;
		DAOVuelosPasajero daoVuelosPasajero = new DAOVuelosPasajero();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelosPasajero.setConn(conn);
			vuelosPasajero = daoVuelosPasajero.darVuelosPasajero();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelosPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaVuelosPasajero(vuelosPasajero);
	}

	/**
	 * M茅todo que modela la transacci贸n que busca el/los vuelos en la base de datos con el tipo entra como par谩metro.
	 * @param tipo - tipo la silla a buscar. nombre != null
	 * @return ArrayList - objeto que modela  un arreglo de vuelos. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaVuelosPasajero buscarVueloPasajeroPorId(String id) throws Exception {
		ArrayList<VueloPasajero> vuelosPasajero = new ArrayList<>();
		VueloPasajero vuelo;
		DAOVuelosPasajero daoVuelosPasajero = new DAOVuelosPasajero();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelosPasajero.setConn(conn);
			vuelo= daoVuelosPasajero.buscarVueloPasajeroPorId(id);
			vuelosPasajero.add(vuelo);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelosPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaVuelosPasajero(vuelosPasajero);
	}


	/**
	 * M茅todo que modela la transacci贸n que agrega un solo vuelos a la base de datos.
	 * <b> post: </b> se ha agregado el vuelos que entra como par谩metro
	 * @param vuelos - el vuelos a agregar. silla != null
	 * @throws Exception - cualquier error que se genera agregando el video
	 */
	public void addVueloPasajero(VueloPasajero vueloPasajero) throws Exception {
		DAOVuelosPasajero daoVuelosPasajero = new DAOVuelosPasajero();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelosPasajero.setConn(conn);
			daoVuelosPasajero.addVueloPasajero(vueloPasajero);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelosPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que agrega los vuelos que entran como par谩metro a la base de datos.
	 * <b> post: </b> se han agregado los vuelos que entran como par谩metro
	 * @param vuelos - objeto que modela una lista de vuelos y se estos se pretenden agregar. vuelos != null
	 * @throws Exception - cualquier error que se genera agregando los videos
	 */
	public void addVuelosPasajero(ListaVuelosPasajero vuelosPasajero) throws Exception {
		DAOVuelosPasajero daoVuelosPasajero = new DAOVuelosPasajero();
		try 
		{
			//////Transacci贸n - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoVuelosPasajero.setConn(conn);
			for(VueloPasajero vueloPasajero : vuelosPasajero.getVuelosPasajero())
				daoVuelosPasajero.addVueloPasajero(vueloPasajero);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoVuelosPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que actualiza el vuelo que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha actualizado el vuelo que entra como par谩metro
	 * @param vuelo - vuelo a actualizar. vuelo != null
	 * @throws Exception - cualquier error que se genera actualizando los vuelos
	 */
	public void updateVueloPasajero(VueloPasajero vueloPasajero) throws Exception {
		DAOVuelosPasajero daoVuelosPasajero = new DAOVuelosPasajero();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelosPasajero.setConn(conn);
			daoVuelosPasajero.updateVueloPasajero(vueloPasajero);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelosPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que elimina el vuelo que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha eliminado el vuelo que entra como par谩metro
	 * @param vuelo - vuelo a eliminar. vuelo != null
	 * @throws Exception - cualquier error que se genera actualizando los sillas
	 */
	public void deleteVueloPasajero(VueloPasajero vueloPasajero) throws Exception {
		DAOVuelosPasajero daoVuelosPasajero = new DAOVuelosPasajero();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelosPasajero.setConn(conn);
			daoVuelosPasajero.deleteVueloPasajero(vueloPasajero);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelosPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}



	/**
	 *  Remitente
	 */

	// TODO Aca empieza Remitente


	/**
	 * M茅todo que modela la transacci贸n que retorna todos los videos de la base de datos.
	 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaRemitentes darRemitentes() throws Exception {
		ArrayList<Remitente> remitentes;
		DAORemitente daoRemitentes = new DAORemitente();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoRemitentes.setConn(conn);
			remitentes = daoRemitentes.darRemitentes();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoRemitentes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaRemitentes(remitentes);
	}

	public ListaRemitentes buscarRemitentePorId(int id) throws Exception {
		ArrayList<Remitente> remitentes = new ArrayList<>();
		Remitente remitente;
		DAORemitente daoRemitente = new DAORemitente();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoRemitente.setConn(conn);
			remitente = daoRemitente.buscarRemitentePorId(id);
			remitentes.add(remitente);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoRemitente.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaRemitentes(remitentes);
	}


	/**
	 * M茅todo que modela la transacci贸n que agrega un solo video a la base de datos.
	 * <b> post: </b> se ha agregado el video que entra como par谩metro
	 * @param video - el video a agregar. video != null
	 * @throws Exception - cualquier error que se genera agregando el video
	 */
	public void addRemitente(Remitente remitente) throws Exception {
		DAORemitente daoRemitentes = new DAORemitente();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoRemitentes.setConn(conn);
			daoRemitentes.addRemitente(remitente);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoRemitentes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que agrega los videos que entran como par谩metro a la base de datos.
	 * <b> post: </b> se han agregado los videos que entran como par谩metro
	 * @param videos - objeto que modela una lista de videos y se estos se pretenden agregar. videos != null
	 * @throws Exception - cualquier error que se genera agregando los videos
	 */
	public void addRemitentes(ListaRemitentes remitentes) throws Exception {
		DAORemitente daoRemitentes = new DAORemitente();
		try 
		{
			//////Transacci贸n - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoRemitentes.setConn(conn);
			for(Remitente remitente : remitentes.getRemitentes())
				daoRemitentes.addRemitente(remitente);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoRemitentes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que actualiza el video que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha actualizado el video que entra como par谩metro
	 * @param video - Video a actualizar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void updateRemitente(Remitente remitente) throws Exception {
		DAORemitente daoRemitentes = new DAORemitente();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoRemitentes.setConn(conn);
			daoRemitentes.updateRemitente(remitente);
			;

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoRemitentes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que elimina el video que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha eliminado el video que entra como par谩metro
	 * @param video - Video a eliminar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void deleteRemitente(Remitente remitente) throws Exception {
		DAORemitente daoRemitentes = new DAORemitente();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoRemitentes.setConn(conn);
			daoRemitentes.deleteRemitente(remitente);;

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoRemitentes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}





	/**
	 *  Viajeros
	 */

	// TODO Aca empieza Viajeros



	/**
	 * M茅todo que modela la transacci贸n que retorna todos los videos de la base de datos.
	 * @return ListaVideos - objeto que modela  un arreglo de videos. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaViajeros darViajeros() throws Exception {
		ArrayList<Viajero> viajeros;
		DAOViajeros daoViajeros = new DAOViajeros();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoViajeros.setConn(conn);
			viajeros = daoViajeros.darViajeros();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoViajeros.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaViajeros(viajeros);
	}

	public ListaViajeros buscarViajerosPorId(int id) throws Exception {
		ArrayList<Viajero> viajeros = new ArrayList<>();
		Viajero viajero;
		DAOViajeros daoViajeros = new DAOViajeros();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoViajeros.setConn(conn);
			viajero = daoViajeros.buscarViajeroPorId(id);
			viajeros.add(viajero);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoViajeros.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaViajeros(viajeros);
	}

	/**
	 * M茅todo que modela la transacci贸n que agrega un solo video a la base de datos.
	 * <b> post: </b> se ha agregado el video que entra como par谩metro
	 * @param video - el video a agregar. video != null
	 * @throws Exception - cualquier error que se genera agregando el video
	 */
	public void addViajero(Viajero viajero) throws Exception {
		DAOViajeros daoViajeros = new DAOViajeros();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoViajeros.setConn(conn);
			daoViajeros.addViajero(viajero);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoViajeros.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que agrega los videos que entran como par谩metro a la base de datos.
	 * <b> post: </b> se han agregado los videos que entran como par谩metro
	 * @param videos - objeto que modela una lista de videos y se estos se pretenden agregar. videos != null
	 * @throws Exception - cualquier error que se genera agregando los videos
	 */
	public void addViajeros(ListaViajeros viajeros) throws Exception {
		DAOViajeros daoViajeros = new DAOViajeros();
		try 
		{
			//////Transacci贸n - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoViajeros.setConn(conn);
			for(Viajero viajero : viajeros.getViajeros())
				daoViajeros.addViajero(viajero);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoViajeros.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que actualiza el video que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha actualizado el video que entra como par谩metro
	 * @param video - Video a actualizar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void updateViajero(Viajero viajero) throws Exception {
		DAOViajeros daoViajeros = new DAOViajeros();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoViajeros.setConn(conn);
			daoViajeros.updateViajero(viajero);
			;

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoViajeros.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que elimina el video que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha eliminado el video que entra como par谩metro
	 * @param video - Video a eliminar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void deleteViajero(Viajero viajero) throws Exception {
		DAOViajeros daoViajeros = new DAOViajeros();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoViajeros.setConn(conn);
			daoViajeros.deleteViajero(viajero);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoViajeros.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 *  Aeronaves Carga
	 */

	// TODO Aca empieza Aeronaves

	/**
	 * M茅todo que modela la transacci贸n que retorna todos los Aeronaves de la base de datos.
	 * @return ArrayList - objeto que modela  un arreglo de Aeronaves. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaAeronavesCarga darAeronavesCarga() throws Exception {
		ArrayList<AeronaveCarga> aeronaves;
		DAOAeronaveCarga daoAeronavesCarga = new DAOAeronaveCarga();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeronavesCarga.setConn(conn);
			aeronaves = daoAeronavesCarga.darAeronavesCarga();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeronavesCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaAeronavesCarga(aeronaves);
	}

	/**
	 * M茅todo que modela la transacci贸n que busca el/los Aeronaves en la base de datos con el tipo entra como par谩metro.
	 * @param tipo - tipo la silla a buscar. nombre != null
	 * @return ArrayList - objeto que modela  un arreglo de Aeronaves. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaAeronavesCarga buscarAeronaveCargaPorNumSer(int numSer) throws Exception {
		ArrayList<AeronaveCarga> aeronavesCarga = new ArrayList<>();
		AeronaveCarga aeronaveCarga;
		DAOAeronaveCarga daoAeronavesCarga = new DAOAeronaveCarga();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeronavesCarga.setConn(conn);
			aeronaveCarga = daoAeronavesCarga.buscarAeronaveCargaPorNumSerie(numSer);
			aeronavesCarga.add(aeronaveCarga);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeronavesCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaAeronavesCarga(aeronavesCarga);
	}


	/**
	 * M茅todo que modela la transacci贸n que agrega un solo Aeronaves a la base de datos.
	 * <b> post: </b> se ha agregado el Aeronaves que entra como par谩metro
	 * @param Aeronaves - el Aeronaves a agregar. aeronave != null
	 * @throws Exception - cualquier error que se genera agregando el video
	 */
	public void addAeronaveCarga(AeronaveCarga aeronaveCarga) throws Exception {
		DAOAeronaveCarga daoAeronavesCarga = new DAOAeronaveCarga();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeronavesCarga.setConn(conn);
			daoAeronavesCarga.addAeronaveCarga(aeronaveCarga);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeronavesCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que agrega los Aeronaves que entran como par谩metro a la base de datos.
	 * <b> post: </b> se han agregado los Aeronaves que entran como par谩metro
	 * @param Aeronaves - objeto que modela una lista de Aeronaves y se estos se pretenden agregar. vuelos != null
	 * @throws Exception - cualquier error que se genera agregando los aeronaves
	 */
	public void addAeronavesCarga(ListaAeronavesCarga aeronavesCarga) throws Exception {
		DAOAeronaveCarga daoAeronavesCarga = new DAOAeronaveCarga();
		try 
		{
			//////Transacci贸n - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoAeronavesCarga.setConn(conn);
			for(AeronaveCarga aeronaveCarga : aeronavesCarga.getAeronavesCarga())
				daoAeronavesCarga.addAeronaveCarga(aeronaveCarga);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoAeronavesCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que actualiza el aeronave que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha actualizado el aeronave que entra como par谩metro
	 * @param aeronave - aeronave a actualizar. aeronave != null
	 * @throws Exception - cualquier error que se genera actualizando las aeronaves
	 */
	public void updateAeronaveCarga(AeronaveCarga aeronaveCarga) throws Exception {
		DAOAeronaveCarga daoAeronavesCarga = new DAOAeronaveCarga();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeronavesCarga.setConn(conn);
			daoAeronavesCarga.updateAeronaveCarga(aeronaveCarga);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeronavesCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que elimina el aeronave que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha eliminado el aeronave que entra como par谩metro
	 * @param aeronave - aeronave a eliminar. aeronave != null
	 * @throws Exception - cualquier error que se genera actualizando los sillas
	 */
	public void deleteAeronaveCarga(AeronaveCarga aeronaveCarga) throws Exception {
		DAOAeronaveCarga daoAeronavesCarga = new DAOAeronaveCarga();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeronavesCarga.setConn(conn);
			daoAeronavesCarga.deleteAeronaveCarga(aeronaveCarga);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeronavesCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}


	/**
	 *  Aeronaves Pasajero
	 */

	// TODO Aca empieza Aeronaves Pasajero

	/**
	 * M茅todo que modela la transacci贸n que retorna todos los Aeronaves de la base de datos.
	 * @return ArrayList - objeto que modela  un arreglo de Aeronaves. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaAeronavesPasajero darAeronavesPasajero() throws Exception {
		ArrayList<AeronavePasajero> aeronavesPasajero;
		DAOAeronavesPasajero daoAeronavesPasajero = new DAOAeronavesPasajero();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeronavesPasajero.setConn(conn);
			aeronavesPasajero = daoAeronavesPasajero.darAeronavesPasajero();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeronavesPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaAeronavesPasajero(aeronavesPasajero);
	}

	/**
	 * M茅todo que modela la transacci贸n que busca el/los Aeronaves en la base de datos con el tipo entra como par谩metro.
	 * @param tipo - tipo la silla a buscar. nombre != null
	 * @return ArrayList - objeto que modela  un arreglo de Aeronaves. este arreglo contiene el resultado de la b煤squeda
	 * @throws Exception -  cualquier error que se genere durante la transacci贸n
	 */
	public ListaAeronavesPasajero buscarAeronavePasajeroPorNumSer(int NumSer) throws Exception {
		ArrayList<AeronavePasajero> aeronavesPasajero = new ArrayList<>();
		AeronavePasajero aeronavePasajero;
		DAOAeronavesPasajero daoAeronavesPasajero = new DAOAeronavesPasajero();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeronavesPasajero.setConn(conn);
			aeronavePasajero = daoAeronavesPasajero.buscarAeronavePasajeroPorNumSerie(NumSer);
			aeronavesPasajero.add(aeronavePasajero);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeronavesPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaAeronavesPasajero(aeronavesPasajero);
	}


	/**
	 * M茅todo que modela la transacci贸n que agrega un solo Aeronaves a la base de datos.
	 * <b> post: </b> se ha agregado el Aeronaves que entra como par谩metro
	 * @param Aeronaves - el Aeronaves a agregar. aeronave != null
	 * @throws Exception - cualquier error que se genera agregando el video
	 */
	public void addAeronavePasajero(AeronavePasajero aeronavePasajero) throws Exception {
		DAOAeronavesPasajero daoAeronavesPasajero = new DAOAeronavesPasajero();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeronavesPasajero.setConn(conn);
			daoAeronavesPasajero.addAeronavePasajero(aeronavePasajero);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeronavesPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que agrega los Aeronaves que entran como par谩metro a la base de datos.
	 * <b> post: </b> se han agregado los Aeronaves que entran como par谩metro
	 * @param Aeronaves - objeto que modela una lista de Aeronaves y se estos se pretenden agregar. Aeronaves != null
	 * @throws Exception - cualquier error que se genera agregando los videos
	 */
	public void addAeronavesPasajero(ListaAeronavesPasajero aeronavesPasajero) throws Exception {
		DAOAeronavesPasajero daoAeronavesPasajero = new DAOAeronavesPasajero();
		try 
		{
			//////Transacci贸n - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoAeronavesPasajero.setConn(conn);
			for(AeronavePasajero aeronavePasajero : aeronavesPasajero.getAeronavesPasajero())
				daoAeronavesPasajero.addAeronavePasajero(aeronavePasajero);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoAeronavesPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que actualiza el aeronave que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha actualizado el vuelo que entra como par谩metro
	 * @param aeronave - aeronave a actualizar. aeronave != null
	 * @throws Exception - cualquier error que se genera actualizando los Aeronaves
	 */
	public void updateAeronavePasajero(AeronavePasajero aeronavePasajero) throws Exception {
		DAOAeronavesPasajero daoAeronavesPasajero = new DAOAeronavesPasajero();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeronavesPasajero.setConn(conn);
			daoAeronavesPasajero.updateAeronavePasajero(aeronavePasajero);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeronavesPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que elimina el aeronave que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha eliminado el aeronave que entra como par谩metro
	 * @param aeronave - vuelo a eliminar. aeronave != null
	 * @throws Exception - cualquier error que se genera actualizando los aeronave
	 */
	public void deleteAeronavePasajero(AeronavePasajero aeronavePasajero) throws Exception {
		DAOAeronavesPasajero daoAeronavesPasajero = new DAOAeronavesPasajero();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeronavesPasajero.setConn(conn);
			daoAeronavesPasajero.deleteAeronavePasajero(aeronavePasajero);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeronavesPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 *  Reservas Carga
	 */

	//TODO Aca empieza Reservas Carga


	public ListaReservasCarga darReservasCarga() throws Exception {
		ArrayList<ReservaCarga> reservasCarga;
		DAOReservasCarga daoReservasCarga = new DAOReservasCarga();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoReservasCarga.setConn(conn);
			reservasCarga = daoReservasCarga.darReservasCarga();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReservasCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaReservasCarga(reservasCarga);
	}

	/**
	 * M閠odo que modela la transacci髇 que busca el/los reservas en la base de datos con el nombre entra como par醡etro.
	 * @param name - Nombre del reserva a buscar. name != null
	 * @return ListaReservas - objeto que modela  un arreglo de reservas. este arreglo contiene el resultado de la b鷖queda
	 * @throws Exception -  cualquier error que se genere durante la transacci髇
	 */
	public ListaReservasCarga buscarReservaCargaPorId(int id) throws Exception {
		ArrayList<ReservaCarga> reservasCarga = new ArrayList<>();
		ReservaCarga reservaCarga;
		DAOReservasCarga daoReservasCarga = new DAOReservasCarga();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoReservasCarga.setConn(conn);
			reservaCarga = daoReservasCarga.buscarReservaCargaPorId(id);
			reservasCarga.add(reservaCarga);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReservasCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaReservasCarga(reservasCarga);
	}

	/**
	 * M閠odo que modela la transacci髇 que agrega un solo reserva a la base de datos.
	 * <b> post: </b> se ha agregado el reserva que entra como par醡etro
	 * @param reserva - el reserva a agregar. reserva != null
	 * @throws Exception - cualquier error que se genera agregando el reserva
	 */
	public void addReservaCarga(ReservaCarga reservaCarga) throws Exception {
		DAOReservasCarga daoReservasCarga = new DAOReservasCarga();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoReservasCarga.setConn(conn);
			daoReservasCarga.addReservaCarga(reservaCarga);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReservasCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M閠odo que modela la transacci髇 que agrega los reservas que entran como par醡etro a la base de datos.
	 * <b> post: </b> se han agregado los reservas que entran como par醡etro
	 * @param reservas - objeto que modela una lista de reservas y se estos se pretenden agregar. reservas != null
	 * @throws Exception - cualquier error que se genera agregando los reservas
	 */
	public void addReservasCarga(ListaReservasCarga reservasCarga) throws Exception {
		DAOReservasCarga daoReservasCarga = new DAOReservasCarga();
		try 
		{
			//////Transacci髇 - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoReservasCarga.setConn(conn);
			for(ReservaCarga reservaCarga : reservasCarga.getReservasCarga())
				daoReservasCarga.addReservaCarga(reservaCarga);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoReservasCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M閠odo que modela la transacci髇 que actualiza el reserva que entra como par醡etro a la base de datos.
	 * <b> post: </b> se ha actualizado el reserva que entra como par醡etro
	 * @param reserva - reserva a actualizar. reserva != null
	 * @throws Exception - cualquier error que se genera actualizando los reservas
	 */
	public void updateReservaCarga(ReservaCarga reservaCarga) throws Exception {
		DAOReservasCarga daoReservasCarga = new DAOReservasCarga();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoReservasCarga.setConn(conn);
			daoReservasCarga.updateReservaCarga(reservaCarga);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReservasCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M閠odo que modela la transacci髇 que elimina el reserva que entra como par醡etro a la base de datos.
	 * <b> post: </b> se ha eliminado el reserva que entra como par醡etro
	 * @param reserva - reserva a eliminar. reserva != null
	 * @throws Exception - cualquier error que se genera actualizando los reservas
	 */
	public void deleteReservaCarga(ReservaCarga reservaCarga) throws Exception {
		DAOReservasCarga daoReservasCarga = new DAOReservasCarga();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoReservasCarga.setConn(conn);
			daoReservasCarga.deleteReservaCarga(reservaCarga);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReservasCarga.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}




	/**
	 *  Reservas Pasajero
	 */

	//TODO Aca empieza Reservas Pasajero


	public ListaReservas darReservas() throws Exception {
		ArrayList<Reserva> reservasPasajero;
		DAOReservas daoReservasPasajero = new DAOReservas();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoReservasPasajero.setConn(conn);
			reservasPasajero = daoReservasPasajero.darReservas();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReservasPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaReservas(reservasPasajero);
	}

	/**
	 * M閠odo que modela la transacci髇 que busca el/los reservas en la base de datos con el nombre entra como par醡etro.
	 * @param name - Nombre del reserva a buscar. name != null
	 * @return ListaReservas - objeto que modela  un arreglo de reservas. este arreglo contiene el resultado de la b鷖queda
	 * @throws Exception -  cualquier error que se genere durante la transacci髇
	 */
	public ListaReservas buscarReservaPorId(int id) throws Exception {
		ArrayList<Reserva> reservasPasajero = new ArrayList<>();
		Reserva reservaPasajero;
		DAOReservas daoReservasPasajero = new DAOReservas();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoReservasPasajero.setConn(conn);
			reservaPasajero = daoReservasPasajero.buscarReservaPorId(id);
			reservasPasajero.add(reservaPasajero);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReservasPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaReservas(reservasPasajero);
	}

	/**
	 * M閠odo que modela la transacci髇 que agrega un solo reserva a la base de datos.
	 * <b> post: </b> se ha agregado el reserva que entra como par醡etro
	 * @param reserva - el reserva a agregar. reserva != null
	 * @throws Exception - cualquier error que se genera agregando el reserva
	 */
	public void addReserva(Reserva reservaPasajero) throws Exception {
		DAOReservas daoReservasPasajero = new DAOReservas();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoReservasPasajero.setConn(conn);
			daoReservasPasajero.addReserva(reservaPasajero);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReservasPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M閠odo que modela la transacci髇 que agrega los reservas que entran como par醡etro a la base de datos.
	 * <b> post: </b> se han agregado los reservas que entran como par醡etro
	 * @param reservas - objeto que modela una lista de reservas y se estos se pretenden agregar. reservas != null
	 * @throws Exception - cualquier error que se genera agregando los reservas
	 */
	public void addReservas(ListaReservas reservasPasajero) throws Exception {
		DAOReservas daoReservasPasajero = new DAOReservas();
		try 
		{
			//////Transacci髇 - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoReservasPasajero.setConn(conn);
			for(Reserva reservaPasajero : reservasPasajero.getReservas())
				daoReservasPasajero.addReserva(reservaPasajero);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoReservasPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	public void addReservasVuelos(Reserva reservaPasajero) throws Exception {
		DAOReservas daoReservasPasajero = new DAOReservas();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoReservasPasajero.setConn(conn);
			daoReservasPasajero.addReservaVuelos(reservaPasajero);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReservasPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	

	public void addReservaGrupal(ArrayList<Reserva> reservaPasajero) throws Exception {
		DAOReservas daoReservasPasajero = new DAOReservas();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoReservasPasajero.setConn(conn);
			daoReservasPasajero.addReservaGrupal(reservaPasajero);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReservasPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}


	/**
	 * M閠odo que modela la transacci髇 que actualiza el reserva que entra como par醡etro a la base de datos.
	 * <b> post: </b> se ha actualizado el reserva que entra como par醡etro
	 * @param reserva - reserva a actualizar. reserva != null
	 * @throws Exception - cualquier error que se genera actualizando los reservas
	 */
	public void updateReserva(Reserva reservaPasajero) throws Exception {
		DAOReservas daoReservasPasajero = new DAOReservas();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoReservasPasajero.setConn(conn);
			daoReservasPasajero.updateReserva(reservaPasajero); 

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReservasPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M閠odo que modela la transacci髇 que elimina el reserva que entra como par醡etro a la base de datos.
	 * <b> post: </b> se ha eliminado el reserva que entra como par醡etro
	 * @param reserva - reserva a eliminar. reserva != null
	 * @throws Exception - cualquier error que se genera actualizando los reservas
	 */
	public void deleteReserva(Reserva reservaPasajero) throws Exception {
		DAOReservas daoReservasPasajero = new DAOReservas();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoReservasPasajero.setConn(conn);
			daoReservasPasajero.deleteReserva(reservaPasajero);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReservasPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}


	public void deleteReservaHorario(Reserva reservaPasajero) throws Exception {
		DAOReservas daoReservasPasajero = new DAOReservas();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoReservasPasajero.setConn(conn);
			daoReservasPasajero.deleteReservaId(reservaPasajero);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReservasPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public void deleteReservaVuelos(Reserva reservaPasajero) throws Exception {
		DAOReservas daoReservasPasajero = new DAOReservas();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoReservasPasajero.setConn(conn);
			daoReservasPasajero.deleteReservaVuelos(reservaPasajero);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReservasPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	
	public ListaReservasPasajero darReservasPasajero() throws Exception {
		ArrayList<ReservaPasajero> reservasPasajero;
		DAOReservasPasajero daoReservasPasajero = new DAOReservasPasajero();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoReservasPasajero.setConn(conn);
			reservasPasajero = daoReservasPasajero.darReservasPasajero();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReservasPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaReservasPasajero(reservasPasajero);
	}

	/**
	 * M閠odo que modela la transacci髇 que busca el/los reservas en la base de datos con el nombre entra como par醡etro.
	 * @param name - Nombre del reserva a buscar. name != null
	 * @return ListaReservas - objeto que modela  un arreglo de reservas. este arreglo contiene el resultado de la b鷖queda
	 * @throws Exception -  cualquier error que se genere durante la transacci髇
	 */
	public ListaReservasPasajero buscarReservaPasajeroPorId(int id) throws Exception {
		ArrayList<ReservaPasajero> reservasPasajero = new ArrayList<>();
		ReservaPasajero reservaPasajero;
		DAOReservasPasajero daoReservasPasajero = new DAOReservasPasajero();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoReservasPasajero.setConn(conn);
			reservaPasajero = daoReservasPasajero.buscarReservaPasajeroPorId(id);
			reservasPasajero.add(reservaPasajero);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReservasPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaReservasPasajero(reservasPasajero);
	}

	/**
	 * M閠odo que modela la transacci髇 que agrega un solo reserva a la base de datos.
	 * <b> post: </b> se ha agregado el reserva que entra como par醡etro
	 * @param reserva - el reserva a agregar. reserva != null
	 * @throws Exception - cualquier error que se genera agregando el reserva
	 */
	public void addReservaPasajero(ReservaPasajero reservaPasajero) throws Exception {
		DAOReservasPasajero daoReservasPasajero = new DAOReservasPasajero();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoReservasPasajero.setConn(conn);
			daoReservasPasajero.addReservaPasajero(reservaPasajero);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReservasPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M閠odo que modela la transacci髇 que agrega los reservas que entran como par醡etro a la base de datos.
	 * <b> post: </b> se han agregado los reservas que entran como par醡etro
	 * @param reservas - objeto que modela una lista de reservas y se estos se pretenden agregar. reservas != null
	 * @throws Exception - cualquier error que se genera agregando los reservas
	 */
	public void addReservasPasajero(ListaReservasPasajero reservasPasajero) throws Exception {
		DAOReservasPasajero daoReservasPasajero = new DAOReservasPasajero();
		try 
		{
			//////Transacci髇 - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoReservasPasajero.setConn(conn);
			for(ReservaPasajero reservaPasajero : reservasPasajero.getReservasPasajero())
				daoReservasPasajero.addReservaPasajero(reservaPasajero);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoReservasPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M閠odo que modela la transacci髇 que actualiza el reserva que entra como par醡etro a la base de datos.
	 * <b> post: </b> se ha actualizado el reserva que entra como par醡etro
	 * @param reserva - reserva a actualizar. reserva != null
	 * @throws Exception - cualquier error que se genera actualizando los reservas
	 */
	public void updateReservaPasajero(ReservaPasajero reservaPasajero) throws Exception {
		DAOReservasPasajero daoReservasPasajero = new DAOReservasPasajero();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoReservasPasajero.setConn(conn);
			daoReservasPasajero.updateReservaPasajero(reservaPasajero); 

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReservasPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M閠odo que modela la transacci髇 que elimina el reserva que entra como par醡etro a la base de datos.
	 * <b> post: </b> se ha eliminado el reserva que entra como par醡etro
	 * @param reserva - reserva a eliminar. reserva != null
	 * @throws Exception - cualquier error que se genera actualizando los reservas
	 */
	public void deleteReservaPasajero(ReservaPasajero reservaPasajero) throws Exception {
		DAOReservasPasajero daoReservasPasajero = new DAOReservasPasajero();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoReservasPasajero.setConn(conn);
			daoReservasPasajero.deleteReservaPasajero(reservaPasajero);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoReservasPasajero.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/*
	 *  Clientes
	 */

	//TODO Aca empieza Clientes

	public ListaClientes darClientes() throws Exception {
		ArrayList<Cliente> clientes;
		DAOClientes daoClientes = new DAOClientes();

		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoClientes.setConn(conn);
			clientes = daoClientes.darClientes();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoClientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaClientes(clientes);
	}

	/**
	 * M閠odo que modela la transacci髇 que busca el/los reservas en la base de datos con el nombre entra como par醡etro.
	 * @param name - Nombre del reserva a buscar. name != null
	 * @return ListaReservas - objeto que modela  un arreglo de reservas. este arreglo contiene el resultado de la b鷖queda
	 * @throws Exception -  cualquier error que se genere durante la transacci髇
	 */
	public ListaClientes buscarClientePorId(int id) throws Exception {
		ArrayList<Cliente> clientes = new ArrayList<>();
		Cliente cliente;
		DAOClientes daoClientes = new DAOClientes();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoClientes.setConn(conn);
			cliente = daoClientes.buscarClientePorId(id);
			clientes.add(cliente);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoClientes.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaClientes(clientes);
	}
	public void addCliente(Cliente viajero) throws Exception {
		DAOClientes daoViajeros = new DAOClientes();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoViajeros.setConn(conn);
			daoViajeros.addCliente(viajero);
			conn.commit();

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoViajeros.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que agrega los videos que entran como par谩metro a la base de datos.
	 * <b> post: </b> se han agregado los videos que entran como par谩metro
	 * @param videos - objeto que modela una lista de videos y se estos se pretenden agregar. videos != null
	 * @throws Exception - cualquier error que se genera agregando los videos
	 */
	public void addClientes(ListaClientes viajeros) throws Exception {
		DAOClientes daoViajeros = new DAOClientes();
		try 
		{
			//////Transacci贸n - ACID Example
			this.conn = darConexion();
			conn.setAutoCommit(false);
			daoViajeros.setConn(conn);
			for(Cliente viajero : viajeros.getClientes())
				daoViajeros.addCliente(viajero);
			conn.commit();
		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			conn.rollback();
			throw e;
		} finally {
			try {
				daoViajeros.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que actualiza el video que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha actualizado el video que entra como par谩metro
	 * @param video - Video a actualizar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void updateCliente(Cliente viajero) throws Exception {
		DAOClientes daoViajeros = new DAOClientes();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoViajeros.setConn(conn);
			daoViajeros.updateCliente(viajero);
			;

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoViajeros.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}

	/**
	 * M茅todo que modela la transacci贸n que elimina el video que entra como par谩metro a la base de datos.
	 * <b> post: </b> se ha eliminado el video que entra como par谩metro
	 * @param video - Video a eliminar. video != null
	 * @throws Exception - cualquier error que se genera actualizando los videos
	 */
	public void deleteCliente(Cliente viajero) throws Exception {
		DAOClientes daoViajeros = new DAOClientes();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoViajeros.setConn(conn);
			daoViajeros.deleteCliente(viajero);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoViajeros.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
	public ArrayList[] consultarAeronaves(int num)throws Exception{
		ArrayList[] consulta;
		DAOAeronaves daoAeronaves = new DAOAeronaves();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoAeronaves.setConn(conn);
			consulta = daoAeronaves.consultarAeronaves(num);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoAeronaves.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return consulta;
	}
	public ListaVuelos darVuelosCliente(int cliente) throws Exception {
		ArrayList<Vuelo> vuelos;
		DAOVuelos daoVuelos = new DAOVuelos();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelos.setConn(conn);
			vuelos = daoVuelos.vuelosCliente(cliente);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaVuelos(vuelos);
	}
	
	public ListaVuelos darVuelosRFC7(String idAeropuerto,String aerolinea,int aeronave) throws Exception 
	{
		ArrayList<Vuelo> vuelos;
		DAOVuelos daoVuelos = new DAOVuelos();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelos.setConn(conn);
			vuelos = daoVuelos.RFC7(idAeropuerto,aerolinea,aeronave);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaVuelos(vuelos);
	}
	
	public ListaVuelos darVuelosRFC8(String idAeropuerto, int aeronave) throws Exception 
	{
		ArrayList<Vuelo> vuelos;
		DAOVuelos daoVuelos = new DAOVuelos();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoVuelos.setConn(conn);
			vuelos = daoVuelos.RFC8(idAeropuerto, aeronave);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaVuelos(vuelos);
	}
	public ListaVuelos darVuelosRFC9(int cliente, String tipo) throws Exception {
		ArrayList<Vuelo> vuelos;
		DAOVuelos daoVuelos = new DAOVuelos();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoVuelos.setConn(conn);
			vuelos = daoVuelos.RFC9(cliente, tipo);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaVuelos(vuelos);
	}
	public ListaVuelos darVuelosRFC10(String idC1, String idC2) throws Exception {
		ArrayList<Vuelo> vuelos;
		DAOVuelos daoVuelos = new DAOVuelos();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoVuelos.setConn(conn);
			vuelos = daoVuelos.RFC10(idC1, idC2);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaVuelos(vuelos);
	}
	
	public ListaVuelos darVuelosRFC11(String idAeropuerto) throws Exception
	{
		ListaVuelos r = dtm.getLocalVuelos();
		try
		{
			ListaVuelos resp = dtm.getRemoteVuelos();
			r.getVuelos().addAll(resp.getVuelos());
			
		}
		catch(NonReplyException e)
		{
			
		}
		return r;
	}
	public ListaVuelosMsg darVuelosRFC11Local(String idAeropuerto) throws Exception 
	{
		ArrayList<VueloMsg> vuelos;
		DAOVuelos daoVuelos = new DAOVuelos();
		try 
		{
			//////Transacci贸n
			this.conn = darConexion();
			daoVuelos.setConn(conn);
			vuelos = daoVuelos.RFC11(idAeropuerto);

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoVuelos.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
		return new ListaVuelosMsg(vuelos);
	}
	
	
	public void promoverUsuario(int id) throws SQLException{
		DAOViajeros daoViajeros = new DAOViajeros();
		try 
		{
			//////Transacci髇
			this.conn = darConexion();
			daoViajeros.setConn(conn);
			daoViajeros.rf18(id); 

		} catch (SQLException e) {
			System.err.println("SQLException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} catch (Exception e) {
			System.err.println("GeneralException:" + e.getMessage());
			e.printStackTrace();
			throw e;
		} finally {
			try {
				daoViajeros.cerrarRecursos();
				if(this.conn!=null)
					this.conn.close();
			} catch (SQLException exception) {
				System.err.println("SQLException closing resources:" + exception.getMessage());
				exception.printStackTrace();
				throw exception;
			}
		}
	}
}
