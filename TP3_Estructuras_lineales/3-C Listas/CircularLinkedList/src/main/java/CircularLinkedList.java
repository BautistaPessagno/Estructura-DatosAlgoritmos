public class CircularLinkedList <T extends Comparable<? super T>>{

        private int size = 0;
        private Node last = null;

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
        }

        public T getLast(){
            if(last == null)
                return null;
            return last.data;
        }



        public Node getHeader() {
            return last;
        }

        public void decSize(){
            size--;
        }

        // enqueue resuelto todo en la clase SortedLinkedList, iterativo
        public void enqueue(T data) {
           if(data == null)
                throw new IllegalArgumentException("data cannot be null");
            if(last == null){
                last = new Node(data);
                last.next = last;
                size++;
                return;
            }
            Node first = last.next;
            Node newNode = new Node(data);
            last.next = newNode;
            newNode.next = first;
            last = newNode;
        }






        public T dequeue() {
            T ans = null;
            if(last == null)
                return null;
            if(last.next == last) {
                ans = last.data;
                last = null;
            }
            else {
                ans = last.next.data;
                Node first = last.next;
                last.next = first.next;
            }
            size--;
            return ans;
        }

        public T peek() {
            if(last == null)
                return null;
            return last.next.data;
        }



        public boolean isEmpty() {
            return last == null;
        }


        public int size() {
            return size;
        }


        public void dump() {
            if(isEmpty()){
                System.out.println("Empty");
                return;
            }
            Node current = last.next;

           do {
                System.out.println(current.data);
                current= current.next;
           }while(current != last.next);
        }

}
