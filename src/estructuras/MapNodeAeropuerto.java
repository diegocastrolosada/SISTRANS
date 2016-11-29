package estructuras;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.annotate.JsonProperty;


import vos.Aeropuerto;
import vos.Vuelo;
import estructuras.ListaDoble;

public class MapNodeAeropuerto 
{
    /**
     * Int que describe el identificador único del aeropuerto.
     */
    public String idAeropuerto;
    
    /**
     * Nombre del aeropuerto
     */
	private String nombre;
	
	/**
	 * Ciudad del aeropuerto
	 */
	private String ciudad;
	
	/**
	 * Distancia entre Aeropuertos
	 */
	private Double distanciaA;

	/**
	 * Arreglo de vuelos entre aeropuertos
	 */
    private String[] idVuelos;
    
    /**
     * Numero de escalas
     */
    
    private int numEscalas;
    
     /**
     * Hash de vuelos por idAeropuerto
     */
    public HashMap<String, ListaDoble<Vuelo>> hashVuelos;
    
    
    /**
     * Constructor principal de un nodo que pertecenece a un mapa.
     */
	public MapNodeAeropuerto(Aeropuerto a)
    {
		this.idAeropuerto = a.getCodigo();
		this.nombre = a.getNombre();
		this.ciudad = a.getCodigo();
		this.hashVuelos = new HashMap<String, ListaDoble<Vuelo>>();
        this.idVuelos = new String[100];
        
    }
    
	public int getNumEscalas()
	{
		return numEscalas;
	}
	public void setNumEscalas(int pNumEscalas) 
	{
		numEscalas = pNumEscalas;
	}
	
	public HashMap<String, ListaDoble<Vuelo>> getHashVuelos()
	{
		return hashVuelos;
	}
	public void setHashVuelos(HashMap<String,ListaDoble<Vuelo>> vuelos)
	{
		hashVuelos = vuelos;
	}	
	
	public void setDistanciaAeropuertos (double dis)
	{
		distanciaA = dis;
	}
	public double getDistanciaAeropuertos()
	{
		return distanciaA;
	}

	/**
     * Método toString
     */
    @Override
    public String toString() 
    {
    	return "ID: "+idAeropuerto;
    }
	public void setIdAeropuerto(String pIdAeropuerto) 
	{
		idAeropuerto = pIdAeropuerto;
	}
	public void setIdVuelos (int pos,String id)
	{
		System.out.println("pos del arreglo"+pos+"id a meter: "+id);
		idVuelos[pos] = id;
	}
	public String[] darIdVuelos()
	{
		return idVuelos;
	}

	
	
	
}
