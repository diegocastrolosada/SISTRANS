package estructuras;

import java.util.ListIterator;
import java.util.NoSuchElementException;

/**
 * Crea un iterador de lista sobre los elementos de una lista doblemente encadenada
 * @param <T> tipo de elementos que almacena la lista encadenada
 */
public class IteradorListaDoblementeEncadenada<T> implements ListIterator<T> {

	/**
	 * Primer elemento de la lista encadenada
	 */
	private NodoListaDoble<T> primero;

	/**
	 * Siguiente elemento en el iterador
	 */
	private NodoListaDoble<T> siguiente;

	/**
	 * Anterior elemento en el iterador
	 */
	private NodoListaDoble<T> anterior;

	/**
	 * Indice del siguiente elemento en el iterador
	 */
	private int indiceSiguiente;

	/**
	 * Construye un nuevo iterador sobre una lista encadenada
	 * post: 	el elemento anterior se ha incializado en null
	 * 			el elemento siguiente se ha incializado en el primer elemento de la lista
	 * 			el primer elemento de la lista se ha inicializado en el valor por par�metro
	 * 			el indice del siguiente elemento se ha inicializado en cero
	 * @param nPrimero el primer elemento de la lista
	 */
	public IteradorListaDoblementeEncadenada(NodoListaDoble<T> nPrimero)
	{
		primero = nPrimero;
		siguiente = nPrimero;
		anterior = null;
		indiceSiguiente = 0;
	}

	/**
	 * Construye un nuevo iterador sobre una lista encadenada
	 * post: 	el elemento anterior se ha incializado en el valor previo a la posici�n por par�metro
	 * 			el elemento siguiente se ha incializado en el valor en la posici�n por par�metro
	 * 			el primer elemento de la lista se ha inicializado en el valor por par�metro
	 * 			el indice del siguiente elemento se ha inicializado en la posici�n por par�metro
	 * 			se ha avanzado el iterador hasta la posici�n por par�metro
	 * @param nPrimero el primer elemento de la lista
	 * @param pos la posici�n en la que se desea inicie el iterador
	 */
	public IteradorListaDoblementeEncadenada(NodoListaDoble<T> nPrimero, int pos)
	{
		primero = nPrimero;
		siguiente = primero;
		anterior = null;
		indiceSiguiente = 0;

		while(indiceSiguiente < pos)
		{
			next();
		}
	}	

	public void add(T t) 
	{
		throw new UnsupportedOperationException("El iterador no soporta la operaci�n solicitada");

	}


	/**
	 * Indica si hay nodo siguiente en la lista iterada
	 * @return true en caso de que haya un elemento siguiente o false en caso contrario
	 */
	public boolean hasNext() {
		return siguiente != null;
	}


	/**
	 * Indica si hay nodo anterior en la lista iterada
	 * @return true en caso de que haya un elemento anterior o false en caso contrario
	 */
	public boolean hasPrevious() {
		return anterior != null;
	}


	/**
	 * Devuelve el sigueinte elemento de la lista
	 * post:	Se actualiza anterior a siguiente
	 * 			Se actualiza siguiente al siguiente del siguiente
	 * 			Se aumenta el indice del siguiente elemento en 1
	 * @return	El elemento <T> del siguiente nodo
	 * @throws NoSuchElementException Se lanza en caso que no exista elemento siguiente
	 */
	public T next() throws NoSuchElementException
	{
		if(siguiente == null)
		{
			throw new NoSuchElementException("No hay elemento siguiente");
		}
		T respuesta = siguiente.getElement();
		anterior = siguiente;
		siguiente = siguiente.getNext();
		indiceSiguiente++;
		return respuesta;
	}


	/**
	 * Devuelve el indice del siguiente nodo
	 * @return indiceSiguiente
	 */
	public int nextIndex() 
	{
		return indiceSiguiente;
	}


	/**
	 * Devuelve el anterior elemento de la lista
	 * post:	Se actualiza siguiente a anterior
	 * 			Se actualiza anterior al anterior del anterior
	 * 			Se disminuye el indice del siguiente elemento en 1
	 * @return	El elemento <T> del anterior nodo
	 * @throws NoSuchElementException Se lanza en caso que no exista elemento anterior
	 */
	public T previous() throws NoSuchElementException
	{
		if(anterior == null)
		{
			throw new NoSuchElementException("No hay elemento anterior.");
		}
		T respuesta = anterior.getElement();
		siguiente = anterior;
		anterior = localizarAnterior(anterior);
		indiceSiguiente--;
		return respuesta;
	}

	/**
	 * Ubica el nodo anterior a un nodo que llega por par�metro
	 * @param nodo el nodo del que se desea conocer su nodo anterior. nodo != null && el nodo existe en la lista
	 * @return el nodo anterior al nodo que llega por par�metro o null en caso que el nodo no tenga anterior
	 */
	private NodoListaDoble<T> localizarAnterior(NodoListaDoble<T> nodo) 
	{

		return nodo.getPrev();
	}


	/**
	 * Devuelve el indice del nodo anteiror en la lista
	 * @return indiceSiguiente -1
	 */
	public int previousIndex() 
	{
		return indiceSiguiente-1;
	}

	public void remove() 
	{
		throw new UnsupportedOperationException("El iterador no soporta la operaci�n solicitada");
	}


	public void set(T t) 
	{
		throw new UnsupportedOperationException("El iterador no soporta la operaci�n solicitada");

	}

}
