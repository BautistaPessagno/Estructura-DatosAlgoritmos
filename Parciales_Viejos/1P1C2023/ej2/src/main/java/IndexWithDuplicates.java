import java.util.Arrays;

public class IndexWithDuplicates<E extends Comparable<E> > {

    private final int chunk_size = 5;
    private E [] m_idx;
    private int m_size;
    @SuppressWarnings("unchecked")
    public IndexWithDuplicates(){
        m_idx = (E[]) new Comparable [chunk_size];
    }

    public void initialize(E[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("elements cannot be null");
        }
        for ( E e : elements )
            insert(e);
    }

    private void grow(){
        if (m_size < m_idx.length)
            return;
        m_idx = Arrays.copyOf(m_idx, m_idx.length + chunk_size );
    }



    public void insert(E key) {
        grow();

        int position = 0;
        for ( position = 0; position < m_size && m_idx[position].compareTo( key ) < 0; ++position);

        for (int i = m_size; i > position; --i)
            m_idx[i] = m_idx[i - 1];
        m_idx[position] = key;
        ++m_size;
    }

    public int getClosestPosition(E key){ //-> complejidad O(log) n)
        int l = 0, r = m_size;
        while(l < r){
            int m = l + (r - l) / 2;
            if(m_idx[m].compareTo(key) >= 0){
                r = m;
            }
            else{
                l = m + 1;
            }
        }
        return l;
    }

    public int occurrences(E key){
        int i = getClosestPosition(key);
        int count = 0;
        while( i < m_size && m_idx[i].compareTo(key) == 0){
            count++;
            i++;
        }
        return count;
    }

    void repeatedValues( E[] values, SimpleLinkedList<E> repeatedLst, SimpleLinkedList<E> singleLst, SimpleLinkedList<E> notIndexedLst ) {
        for(E value: values){
            int count = occurrences(value);
            if(count > 1){
                repeatedLst.insert(value);
            }
            else if(count == 1){
                singleLst.insert(value);
            }
            else{
                notIndexedLst.insert(value);
            }
        }
    }





}
