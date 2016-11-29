package dtm;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;

import javax.jms.JMSException;
import javax.jms.Queue;
import javax.jms.QueueConnection;
import javax.jms.QueueConnectionFactory;
import javax.jms.QueueReceiver;
import javax.jms.QueueSession;
import javax.jms.Session;
import javax.jms.TopicConnectionFactory;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;

import com.rabbitmq.jms.admin.RMQConnectionFactory;
import com.rabbitmq.jms.admin.RMQDestination;

import jms.AllVideosMDB;
import jms.NonReplyException;
import tm.VuelAndesMaster;
import vos.*;

public class VuelAndesDistributed 
{
	private final static String QUEUE_NAME = "java:global/RMQAppQueue";
	private final static String MQ_CONNECTION_NAME = "java:global/RMQClient";
	
	private static VuelAndesDistributed instance;
	
	private VuelAndesMaster tm;
	
//	private QueueConnectionFactory queueFactory;
//	
//	private TopicConnectionFactory factory;
//	
//	private AllVideosMDB allVideosMQ;
	
	private static String path;


	private VuelAndesDistributed() throws NamingException, JMSException
	{
		InitialContext ctx = new InitialContext();
//		factory = (RMQConnectionFactory) ctx.lookup(MQ_CONNECTION_NAME);
//		allVideosMQ = new AllVideosMDB(factory, ctx);
//		
//		allVideosMQ.start();
		
	}
	
	public void stop() throws JMSException
	{
//		allVideosMQ.close();
	}
	
	/**
	 * Método que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	public static void setPath(String p) {
		path = p;
	}
	
	public void setUpTransactionManager(VuelAndesMaster tm)
	{
	   this.tm = tm;
	}
	
	private static VuelAndesDistributed getInst()
	{
		return instance;
	}
	
	public static VuelAndesDistributed getInstance(VuelAndesMaster tm)
	{
		if(instance == null)
		{
			try {
				instance = new VuelAndesDistributed();
			} catch (NamingException e) {
				e.printStackTrace();
			} catch (JMSException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		instance.setUpTransactionManager(tm);
		return instance;
	}
	
	public static VuelAndesDistributed getInstance()
	{
		if(instance == null)
		{
			VuelAndesMaster tm = new VuelAndesMaster(path);
			return getInstance(tm);
		}
		if(instance.tm != null)
		{
			return instance;
		}
		VuelAndesMaster tm = new VuelAndesMaster(path);
		return getInstance(tm);
	}
	
	// Getters de las listas
	public ListaAdministradores getLocalAdministradores() throws Exception
	{
		return tm.darAdministradores();
	}
	public ListaAerolineas getLocalAerolineas() throws Exception
	{
		return tm.darAerolineas();
	}
	public ListaAeronaves getLocalAeronaves() throws Exception
	{
		return tm.darAronaves();
	}
	public ListaAeropuertos getLocalAeropuertos() throws Exception
	{
		return tm.darAeropuertos();
	}

	public ListaCiudades getLocalCiudades() throws Exception
	{
		return tm.darCiudades();
	}

	public ListaClientes getLocalClientes() throws Exception
	{
		return tm.darClientes();
	}

	public ListaRemitentes getLocalRemitentes() throws Exception
	{
        return	tm.darRemitentes();
	}

	public ListaReservas getLocalReservas() throws Exception
	{
		return tm.darReservas();
	}
	public ListaReservasCarga getLocalReservasCarga() throws Exception
	{
		return tm.darReservasCarga();
	}
	public ListaReservasPasajero getLocalReservasPasajero() throws Exception
	{
		return tm.darReservasPasajero();
	}
	public ListaViajeros getLocalViajeros() throws Exception
	{
		return tm.darViajeros();
	}
	public ListaVuelos getLocalVuelos() throws Exception
	{
		return tm.darVuelos();
	}
	public ListaVuelosCarga getLocalVuelosCarga() throws Exception
	{
		return tm.darVuelosCarga();
	}
	public ListaVuelosPasajero getLocalVuelosPasajero() throws Exception
	{
		return tm.darVuelosPasajero();
	}
//	
//	public ListaVideos getRemoteVideos() throws JsonGenerationException, JsonMappingException, JMSException, IOException, NonReplyException, InterruptedException, NoSuchAlgorithmException
//	{
//		return allVideosMQ.getRemoteVideos();
//	}
}