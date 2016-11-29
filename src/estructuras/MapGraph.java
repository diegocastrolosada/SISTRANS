package estructuras;

import java.util.Map;
import java.util.Set;

import vos.Aeropuerto;
import vos.Vuelo;

import java.util.List;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ArrayList;


public class MapGraph implements Iterable<MapNodeAeropuerto>
{
	/**
	 *  Diccionario que permite establecer una relacion entre
	 *  el id de un nodo y el nodo en si
	 **/
	private Map<String, MapNodeAeropuerto> nodes;

	/**
	 * Diccionario que representa la lista de adyacencia principal del
	 * grafo que abstrae el mapa de vuelos.
	 **/
	private Map<String, Set<MapEdgeVuelo>> edges;

	/**
	 * Arreglo con las llaves del grafo
	 */
	public List<String> keys;

	/**
	 * Constructor principal del grafo.
	 **/
	public MapGraph()
	{
		this.nodes = new HashMap<String, MapNodeAeropuerto>();
		this.edges = new HashMap<String, Set<MapEdgeVuelo>>();
		this.keys = new ArrayList<String>();
	}

	/**
	 * Agrega el identificador y la informacion que describe a un nodo del
	 * mapa.
	 *
	 * @param id Numero de identificacion unica del nodo en el mapa de vuelos. id >= 0
	 * @param latitude Numero que establece la latitud geografica del nodo en el mapa real.
	 * @param longitude Numero que establece la longitud geografica del nodo en el mapa real.
	 */
	public void addNode(Aeropuerto pAeropuerto)
	{
		MapNodeAeropuerto nodo = new MapNodeAeropuerto(pAeropuerto);
		String id = pAeropuerto.getCodigo();
		nodes.put(id, nodo);
		keys.add(id);
	}
	
	

	/**
	 * A√±ade un arco entre dos nodos, cuya distancia se encuentra expresada en metros.
	 *
	 * @param from Nodo que establece el inicio del segmento.
	 * @param to Nodo final del segmento.
	 * @param distance Distancia geogr√°fica real entre los dos nodos solicitados.
	 */
	public void addEdge(Vuelo vueloB, LinkedList<MapEdgeVuelo> path)
	{
		MapEdgeVuelo edge = new MapEdgeVuelo(vueloB, path);
		String from = vueloB.getOrigen();
		if (edges.get(from) == null){
			Set<MapEdgeVuelo> edgesFrom = new HashSet<MapEdgeVuelo>();
			edgesFrom.add(edge);
			edges.put(from, edgesFrom);
		}
		else{
			edges.get(from).add(edge);
		}
	}


	/**
	 * Bas√°ndose en el identificador de un nodo, obtiene la informaci√≥n respectiva del nodo
	 * solicitado.
	 *
	 * @param id N√∫mero de identificaci√≥n √∫nico de un nodo en el mapa. id >= 0
	 * @return La informaci√≥n completa de descripci√≥n del nodo solicitado.
	 * @see MapNodeAeropuerto
	 */
	public MapNodeAeropuerto getMapNodeAeropuerto(int id)
	{
		return nodes.get(id);
	}

	/**
	 * Bas√°ndose en el identificador de un nodo, obtiene el conjunto de arcos que tienen como
	 * origen, el nodo solicitado.
	 *
	 * @param id N√∫mero de identificaci√≥n √∫nico de un nodo en el mapa. id >= 0
	 * @return El conjunto de arcos que tienen como origen, el nodo identificado con n√∫mero id.
	 * @see Set
	 * @see MapEdgeVuelo
	 */
	public Set<MapEdgeVuelo> getNodeEdges(int id)
	{
		return edges.get(id);
	}

	/**
	 * Función para determinar si el grafo está vacio.
	 * @return true - si está vacio
	 * @return false - si tiene algún nodo
	 */
	public boolean isEmpty(){
		return nodes.isEmpty();
	}


	@Override
	public Iterator<MapNodeAeropuerto> iterator() {
		// TODO Auto-generated method stub
		return new Iterator<MapNodeAeropuerto>() {

			Integer pos = -1;
			String key;

			@Override
			public boolean hasNext() {
				// TODO Auto-generated method stub
				return ((pos+1) < keys.size());
			}

			@Override
			public MapNodeAeropuerto next() {
				if (hasNext()){
					pos++;
					key = keys.get(pos);
					return nodes.get(key);
				}
				return null;
			}

			@Override
			public void remove() {
				// TODO Auto-generated method stub
				
			}
		};
	}
	

    public Integer getNumberOfNodes() {
        return nodes.size();
    }

	

}
