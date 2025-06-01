public class Node<T extends Comparable<? super T>> implements NodeTreeInterface<T>{

    private T data;
    private Node left;
    private Node right;
    //Para AVL
    private int height;

    public Node(T data) {
        this.data = data;
        this.right = null;
        this.left = null;
    }

    @Override
    public T getData(){
        return data;
    }

    @Override
    public NodeTreeInterface<T> getLeft(){
        return left;
    }

    @Override
    public NodeTreeInterface<T> getRight(){
        return right;
    }

    public void insert(T myData) {
        if(myData == null)
            throw new IllegalArgumentException("Null data");
        if(myData.compareTo(data) <= 0) {
            if(left == null)
                left = new Node(myData);
            else
                left.insert(myData);
        } else {
            if(right == null)
                right = new Node(myData);
            else
                right.insert(myData);
        }
    }

    public String preorder(StringBuilder s) {
        s.append(data).append(" ");
        if(left != null)
            left.preorder(s);
        if(right != null)
            right.preorder(s);
        return s.toString();
    }

    public String postorder(StringBuilder s) {
        if(left != null)
            left.preorder(s);
        if(right != null)
            right.preorder(s);
        s.append(data).append(" ");
        return s.toString();
    }

    public String inorder(StringBuilder s) {
        if(left != null)
            left.preorder(s);
        s.append(data).append(" ");
        if(right != null)
            right.preorder(s);
        return s.toString();
    }

    public int getHeight() {
        if (left == null && right == null)
            return 0;
        else {
            int leftHeight = (left == null ? 0 : left.getHeight());
            int rightHeight = (right == null ? 0 : right.getHeight());
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public boolean contains(T myData){
        int c = data.compareTo(myData);
        if(c == 0)
            return true;
        if(left != null && c > 0)
            return left.contains(myData);
        if(right!=null && c < 0)
            return right.contains(myData);
        return false;
    }

    @SuppressWarnings("unchecked")
    public Node<T> delete(T myData){
        int c = data.compareTo(myData);
        if(c > 0){
            if(left != null)
                left = left.delete(myData);
        }
        else if(c < 0){
            if(right != null)
                right = right.delete(myData);
        }
        else {
            if(left == null)
                return (Node<T>) right;
            if(right == null)
                return (Node<T>) left;
            this.data = (T)findlowest(left);
            left = left.delete(this.data);
        }

        return this;
    }

    public T findlowest(Node<T> node){
        Node<T> current = node;
        while(current.right != null){
            current = current.right;
        }
        return current.data;
    }

}