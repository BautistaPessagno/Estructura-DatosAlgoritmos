
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SortedLinkedListWithHeaderAllowsRemoves<T extends Comparable<? super T>> extends SortedListWithHeader<T> {

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node prev;
            private Node current;

            @Override
            public boolean hasNext() {
                return current.next != null;
            }

            @Override
            public T next() {
                if(!hasNext()) {
                    throw new NoSuchElementException();
                }
                prev = current;
                T ans = current.data;
                current = current.next;
                return ans;
            }

            @Override
            public void remove(){
                current = current.next;
                prev = current.next;
            }
        };
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


