import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedListWithHeader<T extends Comparable<? super T>> implements SortedListService<T>{

    private Node root;
    private int size = 0;
    private Node header = null;

    public Node getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    public Node getHeader() {
        return header;
    }

    public void decSize(){
        size--;
    }

    // insert resuelto todo en la clase SortedLinkedList, iterativo
    @Override
    public boolean insert(T data) {

        if (data == null)
            throw new IllegalArgumentException("data cannot be null");

        Node current = root;
        while (current!=null && current.data.compareTo(data) < 0) {
            // avanzo
            current= current.next;
        }

        // repetido?
        if (current!=null && current.data.compareTo(data) == 0) {
            System.err.println(String.format("Insertion failed. %s repeated", data));
            return false;
        }

        Node aux= new Node(data, current);
        if(aux.next==null) {
            header = aux;
        }
        // es el lugar para colocarlo
        if (current == root) {
            // el primero es un caso especial: cambia root
            root= aux;
        }
        else {
            // nodo interno
            Node prev = root;
            while (prev.next != current) {
                // avanzo
                prev= prev.next;
            }
            prev.next= aux;
        }
        size++;
        return true;
    }

    // insert resuelto todo en la clase SortedLinkedList, recursivo
    public boolean insert2(T data) {
        if (data == null)
            throw new IllegalArgumentException("data cannot be null");

        boolean[] rta = new boolean[1];
        root= insertRec(data, root, rta);
        if(rta[0]) {
            size++;
        }
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
        header = (current==null)?aux:header;
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
        boolean[] rta = new boolean[2];
        root = root.insert(data,rta);
        // COMPLETAR
        header = rta[1]?header.next:header;
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
        Node current = root;
        while(current != null){
            if(current.data.compareTo(data) == 0){
                if(current == root) {
                    root = current.next;
                    size--;
                    return true;
                }
                Node prev = root;
                while (prev.next != current) {
                    prev= prev.next;
                }
                if(current.next == null) {
                    header = prev;
                }
                prev.next = current.next;
                size--;
                return true;
            }
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
        if(rta[0]) {
            size--;
        }
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
        // completar
        return true;
    }

    @Override
    public boolean isEmpty() {
        return root == null;
    }

    @Override
    public int size() {
        return size;
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
        if (other == null || !(other instanceof SortedListWithHeader)) {
            return false;
        }
        SortedListWithHeader<T> auxi = (SortedListWithHeader<T>) other;

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
        return header.data;
    }

    @Override
    public Iterator<T> iterator() {
        return new SortedLinkedListIterator();
    }

    private class SortedLinkedListIterator implements Iterator<T> {
        private Node iter;
        private boolean canRemove;
        private Node prev;
        private Node toDel;

        SortedLinkedListIterator() {
            iter = root;
        }

        public boolean hasNext() {
            return iter != null;
        }

        public T next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            T aux = iter.data;
            prev = toDel;
            toDel = iter;
            iter = iter.next;
            canRemove = true;
            return aux;

        }

        public void remove(){
            if (!canRemove)
                throw new IllegalStateException();
            canRemove=false;
            if (toDel.equals(root))
                root = iter;
            else prev.next = iter;

        }

    }

    final class Node {
        T data;
        Node next;

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
            rta[1] = (this==null);
            rta[0]= true;
            return aux;
        }

    }
}
