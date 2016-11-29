package estructuras;

import java.io.Serializable;

/**
 * Nodo de una lista sencillamente encadenada
 * @param <T> tipo de elementos que almacena el nodo
 */
public class NodoListaDoble <T> implements Serializable
{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 60L;

	/**
	 * Elemento almacenado por el nodo
	 */
	private T elem;
	
	/**
	 * El nodo anterior
	 */
	private NodoListaDoble<T> anterior;

	/**
	 * El nodo siguiente
	 */
	private NodoListaDoble<T> siguiente;
	
	/**
	 * Crea un nuevo nodo con el elemento que llega por par�metro
	 * post: el nodo siguiente se ha inicializado en null
	 * @param nElem el elemento que debe almacenar el nodo
	 */
	public NodoListaDoble(T nElem)
	{
		elem = nElem;
		siguiente = null;
	}
	
	/**
	 * Devuelve el nodo siguiente
	 * @return siguiente
	 */
	public NodoListaDoble<T> getNext()
	{
		return siguiente;
	}
	
	/**
	 * Devuelve el nodo anterior
	 * @return anterior
	 */
	public NodoListaDoble<T> getPrev()
	{
		return anterior;
	}
	
	/**
	 * Cambia el nodo siguiente por el nodo que llega por par�metro
	 * post: 	Se ha cambiado siguiente por el nodo que llega por par�metro
	 * @param nSiguiente el nuevo siguiente.
	 */
	public void setNext(NodoListaDoble<T> nSiguiente)
	{
		siguiente = nSiguiente;

	}
	
	/**
	 * Cambia el nodo anterior por el nodo que llega por par�metro
	 * post: 	Se ha cambiado anterior por el nodo que llega por par�metro
	 * @param nAnterior el nuevo anterior.
	 */
	public void setPrev(NodoListaDoble<T> nAnterior)
	{
		anterior = nAnterior;
	}
	
	/**
	 * Deuvleve el elemento almacenado por el nodo
	 * @return elem
	 */
	public T getElement()
	{
		return elem;
	}

	/**
	 * pone un elemento
	 * @param t
	 */
	public void setElement(T t)
	{
		elem = t;
	}

}
