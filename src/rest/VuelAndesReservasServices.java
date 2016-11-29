package rest;

import java.sql.Date;
import java.util.ArrayList;

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
import vos.ListaReservas;
import vos.Reserva;

@Path("reservas")
public class VuelAndesReservasServices {

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
	public Response getReservas() {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		ListaReservas reservas;
		try {
			reservas = tm.darReservas();
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reservas).build();
	}


    /**
     * Método que expone servicio REST usando GET que busca el video con el nombre que entra como parámetro
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/name/"name para la busqueda"
     * @param name - Nombre del video a buscar que entra en la URL como parámetro 
     * @return Json con el/los videos encontrados con el nombre que entra como parámetro o json con 
     * el error que se produjo
     */
	@GET
	@Path("/idVuelo/{id}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Response getReservaPorVuelo(@javax.ws.rs.PathParam("id") int id) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		ListaReservas reservas;
		try {
			if (id <=-1 )
				throw new Exception("Id del vuelo no valido");
			reservas = tm.buscarReservaPorId(id);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reservas).build();
	}

    /**
     * Método que expone servicio REST usando PUT que agrega el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
     * @param video - video a agregar
     * @return Json con el video que agrego o Json con el error que se produjo
     */
	@PUT
	@Path("/reserva")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateReserva(Reserva reserva) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.updateReserva(reserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reserva).build();
	}
	
    /**
     * Método que expone servicio REST usando PUT que agrega los videos que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/videos
     * @param videos - videos a agregar. 
     * @return Json con el video que agrego o Json con el error que se produjo
     */
	@POST
	@Path("/reservas")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addReserva(ListaReservas reservas) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.addReservas(reservas);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reservas).build();
	}
	
    /**
     * Método que expone servicio REST usando POST que actualiza el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
     * @param video - video a actualizar. 
     * @return Json con el video que actualizo o Json con el error que se produjo
     */
	@POST
	@Path("/reserva")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addReserva(Reserva reserva) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.addReserva(reserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reserva).build();
	}
	

	@POST
	@Path("/reservasVuelos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addReservasVuelos(Reserva reserva) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.addReservasVuelos(reserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reserva).build();
	}
	
	
	@POST
	@Path("/reservasGrupal")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response addReservasGrupal(ArrayList<Reserva> reserva) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.addReservaGrupal(reserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reserva).build();
	}
	
	
    /**
     * Método que expone servicio REST usando DELETE que actualiza el video que recibe en Json
     * <b>URL: </b> http://"ip o nombre de host":8080/VideoAndes/rest/videos/video
     * @param video - video a aliminar. 
     * @return Json con el video que elimino o Json con el error que se produjo
     */
	@DELETE
	@Path("/reserva")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteReserva(Reserva reserva) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.deleteReserva(reserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reserva).build();
	}
	
	@DELETE
	@Path("/reservaHorario")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteReservaHorario(Reserva reserva) 
	{
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.deleteReservaHorario(reserva);
		} catch (Exception e) 
		{
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reserva).build();
	}
	
	@DELETE
	@Path("/reservaVuelos")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteReservaVuelos(Reserva reserva) {
		VuelAndesMaster tm = new VuelAndesMaster(getPath());
		try {
			tm.deleteReservaVuelos(reserva);
		} catch (Exception e) {
			return Response.status(500).entity(doErrorMessage(e)).build();
		}
		return Response.status(200).entity(reserva).build();
	}


}
