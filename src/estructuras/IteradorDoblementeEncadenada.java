package estructuras;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterador simple sobre una lista sencillamente encadenada
 * @param <T> tipo de elementos que almacena la lista sobre la que se itera
 */
public class IteradorDoblementeEncadenada<T> implements Iterator<T> {

	/**
	 * Nodo en el que se encuentra actualmente el iterador
	 */
	private NodoListaDoble<T> actual;
	
	/**
	 * Crea un nuevo iterador
	 * post:	el elemento actual se ha inicializado en el elemento por par�metro
	 * @param nActual el nodo en el que inicia el iterador.
	 */
	public IteradorDoblementeEncadenada(NodoListaDoble<T> nActual)
	{
		actual = nActual;
	}
	

	/**
	 * Indica si a�n hay elementos por iterar
	 * @return true en caso que a�n queden elementos por iterar o false en caso contrario
	 */
	public boolean hasNext() 
	{
		return actual != null;
	}

	
	/**
	 * Devuelve el elemento del nodo actual
	 * post:	Se ha avanzado el nodo actual al siguiente del actual
	 * @return 	elemento del nodo actual
	 * @throws NoSuchElementException Se lanza en caso que la lista no tenga m�s elementos
	 */
	public T next() throws NoSuchElementException
	{
		if( actual == null )
		{
			throw new NoSuchElementException("No hay m�s elementos en la lista");
		}
		T elem = actual.getElement();
		actual = actual.getNext();
		return elem;
	}


	@Override
	public void remove() 
	{
		throw new UnsupportedOperationException("El iterador no soporta la operaci�n solicitada");
		
	}

}
