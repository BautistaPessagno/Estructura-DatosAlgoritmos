public class SimpleLinkedList <T extends Comparable<T>>{
    private Node root = null;
    private Node last = null;

    public void dump() {
        Node current = root;

        while (current!=null ) {
            // avanzo
            System.out.println(current.data);
            current= current.next;
        }
    }

    public void insert(T data) {
        if(data == null) {
            throw new RuntimeException("Data is null");
        }
        if(root == null) {
            root = new Node(data, null);
            last = root;
        } else {
            last.next = new Node(data, null);
            last = last.next;
        }
    }

    private final class Node {
        private T data;
        private Node next;

        private Node(T data, Node next) {
            this.data = data;
            this.next = next;
        }
    }
}
