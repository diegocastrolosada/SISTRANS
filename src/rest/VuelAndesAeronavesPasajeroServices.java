package rest;

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
import vos.AeronavePasajero;
import vos.ListaAeronavesPasajero;

@Path("aeronavesPasajero")
public class VuelAndesAeronavesPasajeroServices {

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
	public Response getAeronavesPasajero() {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		ListaAeronavesPasajero aeronaves;
		try {
			aeronaves = tm.darAeronavesPasajero();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(aeronaves).build();
	}


    /**
     * Método que expone servicio REST usando GET que busca el video con el nombre que entra como parámetro
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/name/"name para la busqueda"
     * @param name - Nombre del video a buscar que entra en la URL como parámetro 
     * @return Json con el/los videos encontrados con el nombre que entra como parámetro o json con 
     * el error que se produjo
     */
	@GET
	@Path("/numSerie/{numSerie}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getAeronavePasajeroPorNumSerie(@javax.ws.rs.PathParam("numSerie") int numSerie) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		ListaAeronavesPasajero aeronaves;
		try {
			if (numSerie <= -1)
				throw new Exception("#Serie de la aeronave no valido");
			aeronaves = tm.buscarAeronavePasajeroPorNumSer(numSerie);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(aeronaves).build();
	}


    /**
     * Método que expone servicio REST usando PUT que agrega el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
     * @param video - video a agregar
     * @return Json con el video que agrego o Json con el error que se produjo
     */
	@PUT
	@Path("/aeronavePasajero")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAeronavePasajero(AeronavePasajero aeronavePasajero) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.updateAeronavePasajero(aeronavePasajero);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(aeronavePasajero).build();
	}
	
    /**
     * Método que expone servicio REST usando PUT que agrega los videos que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/videos
     * @param videos - videos a agregar. 
     * @return Json con el video que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/aeronavesPasajero")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAeronavesPasajero(ListaAeronavesPasajero aeronavesPasajero) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.addAeronavesPasajero(aeronavesPasajero);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(aeronavesPasajero).build();
	}
	
    /**
     * Método que expone servicio REST usando POST que actualiza el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
     * @param video - video a actualizar. 
     * @return Json con el video que actualizo o Json con el error que se produjo
     */
	@POST
	@Path("/aeronavePasajero")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAeronavePasajero(AeronavePasajero aeronavePasajero) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.addAeronavePasajero(aeronavePasajero);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(aeronavePasajero).build();
	}
	
    /**
     * Método que expone servicio REST usando DELETE que actualiza el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
     * @param video - video a aliminar. 
     * @return Json con el video que elimino o Json con el error que se produjo
     */
	@DELETE
	@Path("/aeronavePasajero")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAeronavePasajero(AeronavePasajero aeronavePasajero) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.deleteAeronavePasajero(aeronavePasajero);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(aeronavePasajero).build();
	}


}
