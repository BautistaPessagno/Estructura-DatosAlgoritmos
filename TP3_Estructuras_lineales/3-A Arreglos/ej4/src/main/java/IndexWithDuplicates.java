import java.lang.reflect.Array;

public class IndexWithDuplicates<T extends Comparable<? super T>> implements IndexParametricService<T> {
    private int BLOCK = 20;
    private T[] indexes;
    private int size;
    private final Class<T> theClass;

    public IndexWithDuplicates(Class<T> theClass) {
        this.indexes = (T[]) Array.newInstance(theClass, BLOCK);
        this.size = 0;
        this.theClass = theClass;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void initialize(T [] elements){
        int dim = elements.length;
        if(elements == null){
            throw new RuntimeException("Elements is null");
        }
        this.indexes = (T[]) Array.newInstance(theClass, dim);
        this.size = 0;
        for(int i = 0; i < dim; i++){   // entra n veces
            insert(elements[i]);                    //complejidad O(n)
        }
    }

    @Override
    // busca una key en el índice, O(log2 N)
    public boolean search(T key){
        int i = getClosestPosition(key);
        return indexes[i] == key;
    }

    public int getClosestPosition(T key){
        int l = 0;
        int r = size-1;
        while(l <= r){
            int m = l + (r-l)/2;
            if(indexes[m] == key){return m;
            }
            if(indexes[m].compareTo(key) < 0){
                l = m+1;
            }else{
                r = m-1;
            }
        }
        return l;
    }

    // inserta el key en pos correcta. Crece automáticamente de a chunks.
    // si el valor proporcionado es null, ignora el pedido.
    @Override
    public void insert(T key){
        if (size >= indexes.length) {
            addBlock();
        }
        int pos = getClosestPosition(key);  // O(log2 N)
        for(int i = size; i > pos; i--) {
            indexes[i] = indexes[i-1];      // O(n) -> le gana a O(log2 N)
        }
        size++;
        indexes[pos] = key;
        System.out.print("[ ");
        for(int i = 0; i<size; i++) System.out.print(indexes[i] + " ");
        System.out.println("]");
    }

    @SuppressWarnings("unchecked")
    public void addBlock(){
        T[] newIndexes = (T[]) Array.newInstance(theClass, indexes.length + BLOCK);
        System.arraycopy(indexes, 0, newIndexes, 0, size);
        this.indexes = newIndexes;
    }

    // borra el key si lo hay, sino lo ignora.
    // decrece automáticamente de a chunks
    @Override
    public void delete(T key){
        if(!search(key)){
            return;
        }
        int pos = getClosestPosition(key);
        for(int i = pos; i < size-1; i++){
            indexes[i] = indexes[i+1];
        }
        size--;
    }

    // devuelve la cantidad de apariciones de la clave especificada.
    @Override
    public int occurrences(T key){
        int pos = getClosestPosition(key), count = 0;
        boolean dif = false;
        while(!dif && pos < size){
            if(indexes[pos].compareTo(key) == 0){
                count++;
                pos++;
            }else{
                dif = true;
            }
        }
        return count;
    }

    // devuelve un nuevo arreglo ordenado con los elementos que pertenecen
    // al intervalo dado por leftkey y rightkey.  Si el mismo es abierto/cerrado depende
    // de las variables leftIncluded  y rightIncluded. True indica que es cerrado. El valor
    // devuelto será un arrego de length 0 si no hay elementos que satisfagan al condicion
    @SuppressWarnings("unchecked")
    @Override
    public T[] range(T leftKey, T rightKey, boolean leftIncluded, boolean rightIncluded){
        if(!search(rightKey) || !search(leftKey) || leftKey.compareTo(rightKey) >= 0){
            return (T[]) Array.newInstance(theClass, 0);
        }
        int l = getClosestPosition(leftKey);
        int r = getClosestPosition(rightKey);

        if(!leftIncluded){
            l++;
        }
        if(!rightIncluded){
            r--;
        }
        T[] res = (T[]) Array.newInstance(theClass, r-l+1);
        int newSize = 0;
        while(l <= r){
            res[newSize++] = indexes[l++];
        }
        System.out.print("[ ");
        for(int i = 0; i < newSize; i++) System.out.print(res[i] + " ");
        System.out.println("]");
        return res;
    }


    // imprime el contenido del índice ordenado por su key
    @Override
    public void sortedPrint(){
        System.out.print("[ ");
        for(int i = 0; i < size; i++){
            System.out.print(indexes[i] + " ");
        }
        System.out.println(" ]");
        System.out.println();
    }


    // devuelve el máximo elemento del índice o null si no hay elementos
    @Override
    public T getMax(){
        return indexes[size-1];
    }

    // devuelve el mínimo elemento del índice o null si no hay elementos
    @Override
    public T getMin(){
        return indexes[0];
    }
}
