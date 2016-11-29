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
import vos.AeronaveCarga;
import vos.AeronavePasajero;
import vos.ListaAeronavesCarga;
import vos.ListaAeronavesPasajero;

@Path("aeronavesCarga")
public class VuelAndesAeronavesCarga {

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
	public Response getAeronavesCarga() {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		ListaAeronavesCarga aeronaves;
		try {
			aeronaves = tm.darAeronavesCarga();
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
	public Response getAeronaveCargaPorNumSerie(@javax.ws.rs.PathParam("numSerie") int numSerie) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		ListaAeronavesCarga aeronaves;
		try {
			if (numSerie <= -1)
				throw new Exception("#Serie de la aeronave no valido");
			aeronaves = tm.buscarAeronaveCargaPorNumSer(numSerie);
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
	@Path("/aeronaveCarga")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateAeronaveCarga(AeronaveCarga aeronaveCarga) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.updateAeronaveCarga(aeronaveCarga);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(aeronaveCarga).build();
	}
	
    /**
     * Método que expone servicio REST usando PUT que agrega los videos que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/videos
     * @param videos - videos a agregar. 
     * @return Json con el video que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/aeronavesCarga")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAeronavesCarga(ListaAeronavesCarga aeronavesCarga) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.addAeronavesCarga(aeronavesCarga);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(aeronavesCarga).build();
	}
	
    /**
     * Método que expone servicio REST usando POST que actualiza el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
     * @param video - video a actualizar. 
     * @return Json con el video que actualizo o Json con el error que se produjo
     */
	@POST
	@Path("/aeronaveCarga")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addAeronaveCarga(AeronaveCarga aeronaveCarga) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.addAeronaveCarga(aeronaveCarga);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(aeronaveCarga).build();
	}
	
    /**
     * Método que expone servicio REST usando DELETE que actualiza el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
     * @param video - video a aliminar. 
     * @return Json con el video que elimino o Json con el error que se produjo
     */
	@DELETE
	@Path("/aeronaveCarga")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteAeronaveCarga(AeronaveCarga aeronaveCarga) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.deleteAeronaveCarga(aeronaveCarga);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(aeronaveCarga).build();
	}


}

