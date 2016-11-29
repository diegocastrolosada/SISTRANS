package estructuras;

import java.util.LinkedList;

import vos.Vuelo;

public class MapEdgeVuelo {
	
	/**
     * Identificador único del nodo de origen (aeropuerto de origen) del vuelo.
     */
    public final String from;

    /**
     * Identificador único del nodo final (aeropuerto de destino) del vuelo.
     */
    public final String to;

    /**
     * Distancia geográfica entre el nodo de origen y el nodo final.
     */
    public final double distance;
    
    /**
     * Identificador único de la aerolínea que opera el vuelo entre los dos nodos.
     */
    public final double tiempo;
    
    /**
     * Camino de menor tiempo entre dos parques usando el grafo esquinas.
     */
    public final LinkedList<MapEdgeVuelo> path;
    
 
    
    /**
     * Constructor de un vuelo entre dos aeropuertos.
     * @param from Identificador único del nodo de origen (aeropuerto de origen) del vuelo. from >= 0.
     * @param to Identificador único del nodo final (aeropuerto de destino) del vuelo. to >= 0.
     * @param distance Distancia geográfica entre el nodo de origen y final del trayecto
     * @param tiempo 
     * @param path 
     * @param cost Precio del vuelo correspondiente a este arco. cost 80 >= 10000.
     */
    public MapEdgeVuelo(Vuelo vueloB, LinkedList<MapEdgeVuelo> path)
    {
        this.from = vueloB.getOrigen();
        this.to = vueloB.getDestino();
        this.distance = vueloB.getDistancia();
        this.tiempo = vueloB.getDuracion();
        this.path = path;
    }
    
    public int getFrom() {
		return from.hashCode();
	}
    
    public String getStringFrom()
    {
    	return from;
    }

	public int getTo() {
		return to.hashCode();
	}
	
	public String getStringTo()
	{
		return to;
	}

	public double getDistance() {
		return distance;
	}

	public LinkedList<MapEdgeVuelo> getPath()
	{
		return path;
	}

	/**
     * Método toString
     */
    public String toString() 
    {
    	return "Ruta desde: "+from+" hasta: "+to+", Distancia: "+distance;
    }
}
