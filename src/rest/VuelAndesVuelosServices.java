package rest;

import java.sql.Date;

import javax.servlet.ServletContext;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import tm.VuelAndesMaster;
import vos.ListaVuelos;
import vos.Vuelo;

@Path("vuelos")
public class VuelAndesVuelosServices {

	// Servicios REST tipo GET:


	/**
	 * Atributo que usa la anotación @Context para tener el ServletContext de la conexión actual.
	 */
	@Context
	private ServletContext context;

	/**
	 * Método que retorna el path de la carpeta WEB-INF/ConnectionData en el deploy actual dentro del servidor.
	 * @return path de la carpeta WEB-INF/ConnectionData en el deploy actual.
	 */
	private String getPath() {
		return context.getRealPath("WEB-INF/ConnectionData");
	}
	
	
	private String doErrorMessage(Exception e){
		return "{ \"ERROR\": \""+ e.getMessage() + "\"}" ;
	}
	

	/**
	 * Método que expone servicio REST usando GET que da todos los videos de la base de datos.
	 * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos
	 * @return Json con todos los videos de la base de datos O json con 
     * el error que se produjo
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getVuelosCarga() {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		ListaVuelos vuelos;
		try {
			vuelos = tm.darVuelos();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(vuelos).build();
	}


    /**
     * Método que expone servicio REST usando GET que busca el video con el nombre que entra como parámetro
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/name/"name para la busqueda"
     * @param name - Nombre del video a buscar que entra en la URL como parámetro 
     * @return Json con el/los videos encontrados con el nombre que entra como parámetro o json con 
     * el error que se produjo
     */
	@GET
	@Path("/id/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getVueloCargaId(@javax.ws.rs.PathParam("id") String id) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		ListaVuelos vuelos;
		try {
			if (id == null || id.length() == 0)
				throw new Exception("Id del vuelo no valido");
			vuelos = tm.buscarVueloPorId(id);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(vuelos).build();
	}
	@GET
	@Path("/reporteViajeCarga/id/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getReporteViajeCarga(@javax.ws.rs.PathParam("id") String id) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		double rta;
		try {
			if (id == null || id.length() == 0)
				throw new Exception("Id del vuelo no valido");
			    rta = tm.reporteViajeCarga(id);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(rta).build();
	}
	@GET
	@Path("/reporteViajePasajero/id/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getReporteViajePasajero(@javax.ws.rs.PathParam("id") String id) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		double rta;
		try {
			if (id == null || id.length() == 0)
				throw new Exception("Id del vuelo no valido");
			    rta = tm.reporteViajePasajero(id);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(rta).build();
	}
    /**
     * Método que expone servicio REST usando PUT que agrega el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
     * @param video - video a agregar
     * @return Json con el video que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/vuelo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addVueloCarga(Vuelo vuelo) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.addVuelo(vuelo);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(vuelo).build();
	}
	
    /**
     * Método que expone servicio REST usando PUT que agrega los videos que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/videos
     * @param videos - videos a agregar. 
     * @return Json con el video que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/vuelos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addVuelo(ListaVuelos vuelos) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.addVuelos(vuelos);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(vuelos).build();
	}
	
	
	
    /**
     * Método que expone servicio REST usando POST que actualiza el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
     * @param video - video a actualizar. 
     * @return Json con el video que actualizo o Json con el error que se produjo
     */
	@PUT
	@Path("/vuelo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateVuelo(Vuelo vuelo) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.updateVuelo(vuelo);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(vuelo).build();
	}
	@PUT
	@Path("/aeronaveVuelo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAeronaveVuelo(Vuelo vuelo) throws Exception {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			     tm.updateAeronaveViaje(vuelo);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(vuelo).build();
	}
    /**
     * Método que expone servicio REST usando DELETE que actualiza el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
     * @param video - video a aliminar. 
     * @return Json con el video que elimino o Json con el error que se produjo
     */
	@DELETE
	@Path("/vuelo")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteVuelo(Vuelo vuelo) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.deleteVuelo(vuelo);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(vuelo).build();
	}
	
	@GET
	@Path("/cliente/{cliente}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getVuelosCliente(@javax.ws.rs.PathParam("cliente") int cliente) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		ListaVuelos vuelos;
		try {
			if (cliente==0)
				throw new Exception("Cliente no v�lido");
			vuelos = tm.darVuelosCliente(cliente);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(vuelos).build();
	}
	
	@GET
	@Path("/aeropuerto/{idAeropuerto}/aerolinea/{aerolinea}/aeronave/{aeronave}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getVuelosRFC7(@javax.ws.rs.PathParam("idAeropuerto") String idAeropuerto,@javax.ws.rs.PathParam("aerolinea") String aerolinea,@javax.ws.rs.PathParam("aeronave") int aeronave) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		ListaVuelos vuelos;
		try {
			if (idAeropuerto==null)
				throw new Exception("Aeropuerto no v�lido");
			vuelos = tm.darVuelosRFC7(idAeropuerto,aerolinea,aeronave);
		} catch (Exception e) 
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(vuelos).build();
	}
	
	@GET
	@Path("/aeropuerto/{idAeropuerto}/aeronave/{aeronave}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getVuelosRFC8(@javax.ws.rs.PathParam("idAeropuerto") String idAeropuerto,@javax.ws.rs.PathParam("aeronave") int aeronave) 
	{
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		ListaVuelos vuelos;
		try {
			if (idAeropuerto==null)
				throw new Exception("Parametros de URL no validos");
			vuelos = tm.darVuelosRFC8(idAeropuerto,aeronave);
		} catch (Exception e) 
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(vuelos).build();
	}
	
	@GET
	@Path("/cliente/{idcliente}/categoria/{categoria}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getVuelosRFC9(@javax.ws.rs.PathParam("idcliente") int cliente,@javax.ws.rs.PathParam("categoria") String categoria) 
	{
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		ListaVuelos vuelos;
		try {
			if (cliente==0)
				throw new Exception("Parametros de URL no validos");
			vuelos = tm.darVuelosRFC9(cliente,categoria);
		} catch (Exception e) 
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(vuelos).build();
	}
	
	@GET
	@Path("/ciudad1/{idC1}/ciudad2/{idC2}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getVuelosRFC10(@javax.ws.rs.PathParam("idC1") String idC1, @javax.ws.rs.PathParam("idC2") String idC2) 
	{
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		ListaVuelos vuelos;
		try {
			if (idC1 == null || idC2 == null)
				throw new Exception("Parametros de URL no validos");
			vuelos = tm.darVuelosRFC10(idC1,idC2);
		} catch (Exception e) 
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(vuelos).build();
	}
	@GET
	@Path("/aeropuerto/{idAeropuerto}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getVuelosRFC11(@javax.ws.rs.PathParam("idAeropuerto") String idAeropuerto) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		ListaVuelos vuelos;
		try {
			if (idAeropuerto==null)
				throw new Exception("Aeropuerto no v�lido");
			vuelos = tm.darVuelosRFC11(idAeropuerto);
		} catch (Exception e) 
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(vuelos).build();
	}
}

