package estructuras;

import java.util.*;

/**
 *  La clase <tt>MapDijkstraSP</tt> permite obtener el cámino más corto entre dos nodos
 *  de la clase {@link MapGraph} utilizando el algoritmo de Dijkstra.
 *  <p>
 *  En el peor de los casos el algoritmo tarde y tiempo de O(V*E) para encontrar el camino
 *  más corto entre dos nodos. Donde V es el numéro de vértices del grafo y E el número de
 *  arcos.
 *  <b>Para obtener mayor información:</b>
 *  Consultar la <a href="http://algs4.cs.princeton.edu/44sp">Sección 4.4</a> de
 *  <i>Algorithms, 4th Edition</i> de Robert Sedgewick y Kevin Wayne.
 *
 *  
 */
public class MapDijkstraSP {

	/**
	 * Referencia al MapGraph para el que se quiere encontrar el camino más corto entre dos nodos
	 */
	private MapGraph mapGraph;

	/**
	 * Id del nodo de destino
	 */
	private int idDestination;

	/**
	 * Arreglo que en la posición i tiene una referencia al último MapEdge del camino más corto del nodo fuente al nodo
	 * i
	 */
	private MapEdgeVuelo[] edgeTo;

	/**
	 * Arreglo que en la posición i tiene la distancia más corto del nodo fuente al nodo i o infinito en caso de que no
	 * haya un camino encontrado
	 */
	private double[] distTo;

	/**
	 * Cola de prioridad en la que se atienden los nodos encotrados por el algoritmo de Dijkstra
	 */
	private PriorityQueue<MapDijkstraNode> priorityQueue;

	/**
	 * Constructor de la clase que realiza el algoritmo Dijkstra luego de ser inicializado
	 * @param mapGraph Referencia al MapGraph sobre el que se quiere realizar el algoritmo
	 * @param idAeroOrigen Id de la fuente
	 * @param idAeroDestino Id del destino
	 */
	public MapDijkstraSP(MapGraph mapGraph, int idAeroOrigen, int idAeroDestino)
	{
		this.mapGraph = mapGraph;
		this.idDestination = idAeroDestino;

		distTo = new double[mapGraph.getNumberOfNodes()];
		edgeTo = new MapEdgeVuelo[mapGraph.getNumberOfNodes()];

		for(int v = 0; v<distTo.length; v++)
			distTo[v] = Double.POSITIVE_INFINITY;

		distTo[idAeroOrigen] = 0.0;

		priorityQueue = new PriorityQueue(mapGraph.getNumberOfNodes());
		MapDijkstraNode sourceNode = new MapDijkstraNode(idAeroOrigen, 0.0);
		priorityQueue.add(sourceNode);

		MapDijkstraNode currentNode = sourceNode;
		
		//TOD 1). Complete el método. Debe tomar el nodo actual de la cola y relajarlo
		// mientras no haya llegado al destino.
		while(!priorityQueue.isEmpty())
		{
			currentNode = priorityQueue.remove();
			relax(currentNode);
		}
	}



	/**
	 * Devuelve una LinkedList con los arcos que forman el camino del nodo fuente al nodo de destino
	 * @return
	 */
	public LinkedList<MapEdgeVuelo> pathTo() 
	{
		if (distTo[idDestination] == Double.POSITIVE_INFINITY)
			return null;
		LinkedList<MapEdgeVuelo> path = new LinkedList<MapEdgeVuelo>();

		//TOD 2). Complete el método. Debe agregar al camino el arco actual
		//y modificar el arco actual al arco anterior
		// mientras el arco actual no sea nulo.
		for(MapEdgeVuelo e = edgeTo[idDestination]; e!=null; e = edgeTo[e.getFrom()] )
		{
			path.push(e);
		}
		return path;
	}

	/**
	 * Recibe un nodo por parametro y explora sus arcos, relaja las distancias a estos y los agrega a la cola de
	 * prioridad
	 * @param currentNode
	 */
	private void relax(MapDijkstraNode currentNode)
	{
		try{
			for(MapEdgeVuelo edge : mapGraph.getNodeEdges(currentNode.nodeId)) {
				int edgeDestination = edge.getTo();

				if(distTo[edgeDestination] > distTo[currentNode.nodeId] + edge.distance)
				{
					MapDijkstraNode currentDestination = new MapDijkstraNode(edgeDestination, distTo[edgeDestination]);

					distTo[edgeDestination] = distTo[currentNode.nodeId] + edge.distance;
					edgeTo[edgeDestination] = edge;

					if (priorityQueue.contains(currentDestination)) {
						priorityQueue.remove(currentDestination);
						currentDestination.distance = distTo[currentNode.nodeId] + edge.distance;
						priorityQueue.add(currentDestination);
					}
					else
					{
						currentDestination.distance = distTo[currentNode.nodeId] + edge.distance;
						priorityQueue.add(currentDestination);
					}

				}

			}
		}catch(Exception e){
		}
	}

	/**
	 * Clase que representa un nodo que se va agregar a la cola de prioridad son su distancia actual a la fuente
	 */
	private class MapDijkstraNode implements Comparable<MapDijkstraNode>{

		public int nodeId;
		public double distance;

		public MapDijkstraNode (int edgeDestination, double distance) {
			this.nodeId = edgeDestination;
			this.distance = distance;
		}

		public boolean equals(MapDijkstraNode j) {
			return this.nodeId == j.nodeId;
		}

		public int compareTo(MapDijkstraNode j) {
			return (int)this.distance - (int)j.distance;
		}
	}

}


