import java.util.Iterator;
import java.util.Spliterator;
import java.util.function.Consumer;

// lista simplemente encadenada, no acepta repetidos (false e ignora) ni nulls (exception)
public class SortedLinkedList<T extends Comparable<? super T>> implements SortedListService<T>{

	private Node root;

	public Node getRoot() {
		return root;
	}

	public void setRoot(Node root) {
		this.root = root;
	}

	// insert resuelto todo en la clase SortedLinkedList, iterativo
	@Override
	public boolean insert(T data) {
		
		if (data == null) 
			throw new IllegalArgumentException("data cannot be null");

		Node prev= null;
		Node current = root;

		while (current!=null && current.data.compareTo(data) < 0) {
			// avanzo
			prev= current;
			current= current.next;
		}

		// repetido?
		if (current!=null && current.data.compareTo(data) == 0) {
			System.err.println(String.format("Insertion failed. %s repeated", data));
			return false;
		}

		Node aux= new Node(data, current);
		// es el lugar para colocarlo
		if (current == root) {
			// el primero es un caso especial: cambia root
			root= aux;
		}
		else {
			// nodo interno
			prev.next= aux;
		}
		
		return true;
	}

	
	// insert resuelto todo en la clase SortedLinkedList, recursivo
	public boolean insert2(T data) {
		if (data == null) 
			throw new IllegalArgumentException("data cannot be null");
		
		boolean[] rta = new boolean[1];
		root= insertRec(data, root, rta);
		return rta[0];
	}

	private Node insertRec(T data, Node current, boolean[] rta) {
		if(current !=null && current.data.compareTo(data) == 0) {
			rta[0]= false;
			return current;
		}
		if(current!=null && current.data.compareTo(data) < 0) {
			current.next= insertRec(data, current.next, rta);
			return current;
		}
		Node aux= new Node(data, current);
		rta[0]= true;
		return aux;
	}
	
	// insert resuelto delegando al nodo
	public boolean insert3(T data) {
		if(data == null)
			throw new IllegalArgumentException("data cannot be null");
		if(root == null) {
			root = new Node(data);
			return true;
		}
		boolean[] rta = new boolean[1];
		root = root.insert(data,rta);
		// COMPLETAR 
		return rta[0];
	}
	
	@Override
	public boolean find(T data) {
		return getPos(data) != -1;
	}
	
	// delete resuelto todo en la clase SortedLinkedList, iterativo
	@Override
	public boolean remove(T data) {

		if (root == null) {
			return false;
		}
		if(root.data.compareTo(data)==0) {
			root = root.next;
			return true;
		}
		Node prev= root;
		Node current = root.next;
		while(current != null){
			if(current.data.compareTo(data) == 0){
				prev.next = current.next;
				return true;
			}
			prev= current;
			current = current.next;
		}
		return false;
	}
	
	// delete resuelto todo en la clase SortedLinkedList, recursivo
	public boolean remove2(T data) {
		if(data == null)
			throw new IllegalArgumentException("data cannot be null");
		boolean[] rta = new boolean[1];
		root = removeRec(data, root, rta);
		return rta[0];
	}

	public Node removeRec(T data, Node current, boolean[] rta) {
		if(current==null || current.data.compareTo(data) > 0) {
			rta[0]= false;
			return null;
		}
		else if(current.data.compareTo(data) == 0) {
			rta[0]= true;
			return current.next;
		}
		current.next = removeRec(data, current.next, rta);
		return current;
	}

	// delete resuelto delegando al nodo
//	@Override
	public boolean remove3(T data) {
		if(data == null)
			throw new IllegalArgumentException("data cannot be null");
		if(root == null) {
			return false;
		}
		boolean[] rta = new boolean[1];
		root = root.delete(data,rta);
		// COMPLETAR
		return rta[0];
	}
	
	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public int size() {
		int rta= 0;
		
		Node current = root;

		while (current!=null ) {
			// avanzo
			rta++;
			current= current.next;
		}
		return rta;
	}

	
	@Override
	public void dump() {
		Node current = root;

		while (current!=null ) {
			// avanzo
			System.out.println(current.data);
			current= current.next;
		}
	}
	
	
	@Override
	public boolean equals(Object other) {
		if (other == null || !  (other instanceof SortedLinkedList) )
			return false;
		
		@SuppressWarnings("unchecked")
		SortedLinkedList<T> auxi = (SortedLinkedList<T>) other;
		
		Node current = root;
		Node currentOther= auxi.root;
		while (current!=null && currentOther != null ) {
			if (current.data.compareTo(currentOther.data) != 0)
				return false;
			
			// por ahora si, avanzo ambas
			current= current.next;
			currentOther= currentOther.next;
		}
		
		return current == null && currentOther == null;
		
	}
	
	// -1 si no lo encontro
	protected int getPos(T data) {
		Node current = root;
		int pos= 0;
		
		while (current!=null ) {
			if (current.data.compareTo(data) == 0)
				return pos;
			
			// avanzo
			current= current.next;
			pos++;
		}
		return -1;
	}
	
	@Override
	public T getMin() {
		if (root == null)
			return null;
		
		return root.data;
	}


	@Override
	public T getMax() {
		
		if (root == null)
			return null;
		
		Node current = root;
		
		
		while (current.next !=null ) {
			// avanzo
			current= current.next;
		}
		
		return current.data;
	}

	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {
			Node current= root;

			@Override
			public boolean hasNext() {
				return current != null;
			}

			@Override
			public T next() {
				T rta= current.data;
				current= current.next;
				return rta;
			}
		};
	}

	private final class Node {
		private T data;
		private Node next;
	
		private Node(T data, Node next) {
			this.data= data;
			this.next= next;
		}

		private Node(T data) {
			this.data= data;
			this.next= null;
		}

		public Node insert(T data, boolean[] rta){
			if(this.data.compareTo(data) == 0) {
				rta[0]= false;
				return this;
			}

			if (this.data.compareTo(data) < 0 && this.next != null) {
				this.next = this.next.insert(data, rta);
				return this;
			}

			Node aux= new Node(data, this.next);
			rta[0]= true;
			return aux;
		}

		public Node delete(T data, boolean[] rta){
			if(this.data.compareTo(data) == 0) {
				this.data= this.next.data;
				rta[0]= true;
				return this;
			}

			if (this.data.compareTo(data) < 0 && this.next != null) {
				this.next = this.next.delete(data, rta);
				return this;
			}
			rta[0]= false;
			return this;
		}
		
	}



}
