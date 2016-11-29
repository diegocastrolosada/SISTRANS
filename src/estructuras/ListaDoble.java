package estructuras;

import java.io.Serializable;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;


/**
 * Clase que representa un lista sencillamente encadenada. La lista no acepta elementos nulos o repetidos
 * @param <T> tipo de elementos almacenados en la lista
 */
public class ListaDoble<T> implements Serializable 
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 70L;
	
	/**
	 * Primer elemento de la lista
	 */
	private NodoListaDoble<T> primero;
	
	private NodoListaDoble<T> ultimo;

	/**
	 * Crea una lista sencillamente encadenada
	 * post:	El primer elemento se ha inicializado en null
	 */
	public ListaDoble()
	{
		primero = null;

	}

	/**
	 * Devuelve el primer nodo
	 * @return
	 */
	public NodoListaDoble<T> getFirst()
	{
		return primero;
	}


	public NodoListaDoble<T> getLast() 
	{
		return ultimo;
	}

	/**
	 * Agrega el elemento t en la primera posici�n de la lista
	 * post:  Los elementos posteriores a pos se han movido una posici�n a la derecha
	 * @param pos la posici�n donde se desea agregar elemento
	 * @param t el elemento que se desea agregar
	 * @throws NullPointerException En caso que t sea nulo
	 * @throws IndexOutOfBoundsException si la posici�n est� fuera de la lista (pos < 0 || pos > size())
	 * @throws IllegalArgumentException si ya existe el elemento t en la lista
	 */
	public void addFirst(T t) throws NullPointerException, IndexOutOfBoundsException, IllegalArgumentException
	{
		if(t==null)
		{
			throw new NullPointerException("La lista no acepta elementos nulos");
		}

		NodoListaDoble<T> n =  new NodoListaDoble<T>(t);
		n.setNext(primero);
		if(primero != null)
		{
			primero.setPrev(n);
		}
		primero = n;

	}

	/**
	 * Agrega el elemento por par�metro al final de la lista
	 * @param t el elemento que se desea agregar.
	 * @return true en caso de que la lista se haya modificado (se agreg� el elemento) o false en caso contrario
	 * @throws NullPointerException Se lanza en caso que T sea nulo
	 * @throws IllegalArgumentException Se lanza en caso que ya exista un elemento con el identificador de t en la lista
	 */
	public boolean addLast(T t) throws NullPointerException, IllegalArgumentException
	{

		boolean resultado = false;

		if(t==null)
		{
			throw new NullPointerException("La lista no acepta elementos nulos");
		}

		NodoListaDoble<T> n = new NodoListaDoble<T>(t);

		if(primero == null)
		{
			primero = n;
			resultado = true;
		}

		else
		{
			NodoListaDoble<T> actual = primero;
			while(actual.getNext() != null)
			{	
				actual = actual.getNext();	
			}

			actual.setNext(n);
			n.setPrev(actual);
			ultimo = n;
			resultado = true;
		}

		return resultado;
	}



	/**
	 * Elimina el primer elemento de la lista.
	 * post: todos los elementos despu�s de pos se han corrido una posici�n a la izquierda
	 * @return el elemento eliminado
	 * @throws IndexOutOfBoundsException si la posici�n est� fuera de la lista (pos < 0 || pos > size())
	 */
	public T removeFirst() 
	{

		NodoListaDoble<T> actual = primero;

		T elem = primero.getElement();
		primero = actual.getNext();
		return elem;
	}

	/**
	 * Elimina el elemento de la posici�n o de la lista.
	 * post: todos los elementos despu�s de pos se han corrido una posici�n a la izquierda
	 * @return el elemento eliminado
	 * @throws IndexOutOfBoundsException si la posici�n est� fuera de la lista (pos < 0 || pos > size())
	 */
	public T removeLast() 
	{
		NodoListaDoble<T> actual = primero;

		while(actual.getNext().getElement() != null)
		{	
			actual = actual.getNext();
		}

		NodoListaDoble<T> aBorrar = actual;
		T elem = aBorrar.getElement();

		NodoListaDoble<T> sig = actual.getNext();
		NodoListaDoble<T> ant = actual.getPrev();
		ant.setNext(sig);
		if(sig!=null)
		{
			sig.setPrev(ant);
		}
		return elem;
	}



	/**
	 * Eliminad todos los elementos de la lista
	 * post: se han borrado todos los elementos de la lista
	 */
	public void clear() 
	{
		primero = null;
	}

	/**
	 * Indica si la lista esta vac�a
	 * @return true en caso que la lista no tenga elementos o false en caso contrario
	 */
	public boolean isEmpty() 
	{
		return primero == null;
	}

	/**
	 * Devuelve un iterador sobre la lista
	 * @return un nuevo iterador que inicia al principio de la lista
	 */
	public Iterator<T> iterator() 
	{
		return new IteradorDoblementeEncadenada(primero);
	}

	/**
	 * Devuelve un iterador de lista sobre la lista
	 * @return nuevo iterador que inicia en la posici�n indicada de la lista
	 */
	public ListIterator<T> listIterator(int pos) 
	{
		return new IteradorListaDoblementeEncadenada(primero, pos);
	}

	/**
	 * Indica la cantidad de elementos en la lista
	 * @return n�mero de elementos en la lista
	 */
	public int size() 
	{
		//TOD complete seg�n la documentaci�n
		// Recorra la lista contando cuantos elementos hay
		int contador = 0;
		NodoListaDoble<T> actual = primero;

		while(actual != null)
		{
			contador++;
			actual = actual.getNext();
		}

		return contador;
	}
	
	/**
	 * Invierte la lista
	 */
	//TOD LABO-REVISAR
	public ListaDoble<T> invertir()
	{
		NodoListaDoble<T> actual = primero;

		while(actual!= null)
		{
			NodoListaDoble<T> sig = actual.getNext( );
			NodoListaDoble<T> ant = actual.getPrev( );
			actual.setPrev(sig);
			actual.setNext(ant);
			primero = actual;
			actual = actual.getPrev();
		}
		
		return this;
	}


	/**
	 * Devuelve un nuevo arreglo de objetos con los elementos T de la lista
	 * @return arreglo con los elementos de la lista
	 */
	public Object[] toArray() {

		//TOD complete seg�n la documentaci�n
		Object[] arreglo = new Object[size()];

		NodoListaDoble<T> actual = primero;
		int i = 0;

		while(actual!=null)
		{
			T elem = actual.getElement();
			arreglo[i] = elem;
			i++;
			actual = actual.getNext();
		}
		return arreglo;

	}

	/**
	 * Retorna un arreglo de T con los elementos T de la lista. Si los elementos de la lista caben en el par�metro se devuelve all�
	 *  de lo contrario se devuelven en un nuevo arreglo. En caso que el par�metro tenga m�s espacio que elementos en la lista el elemento
	 *  inmediatamente posterior al �ltimo elemento de la lista en el arreglo debe ser null
	 *  @param array arreglo donde se guardaran los elementos de la lista
	 *  @return un arreglo con los elementos T de la lista
	 *  @throws NullPointerException Si el arreglo por par�metro es nulo
	 */
	public <T> T[] toArray(T[] array) throws NullPointerException
	{
		if( array == null )
		{
			throw new NullPointerException("El arreglo no puede ser nulo");
		}
		if( array.length < size())
		{
			return (T[])toArray();
		}
		else
		{
			NodoListaDoble actual = primero;
			int  i = 0;
			while(actual != null)
			{
				array[i] = (T)actual.getElement();
				actual = actual.getNext();
				i++;
			}
			if(array.length > size())
			{
				array[i] = null;
			}
			return array;
		}


	}

}
