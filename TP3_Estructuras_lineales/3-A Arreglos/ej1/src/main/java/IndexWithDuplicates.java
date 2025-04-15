import java.util.Arrays;

public class IndexWithDuplicates implements IndexService {
    private int BLOCK = 20;
    private int[] indexes = new int[BLOCK];
    private int size = 0;

    // elements ser�n los valores del �ndice, los anteriores se descartan.
    // lanza excepction si elements is null y deja los valores anteriores.
    @Override
    public void initialize(int [] elements){
        if(elements == null){
            throw new RuntimeException("Elements is null");
        }
        size = 0;
        indexes = new int[BLOCK];
        for(int i = 0; i < elements.length; i++){   // entra n veces
            insert(elements[i]);                    //complejidad O(n)
        }
    // Compljidad total O(n^2)
    }

    public void addBlock(){
        indexes = Arrays.copyOf(indexes, indexes.length+BLOCK);
    }
    // busca una key en el �ndice, O(log2 N)
    @Override
    public boolean search(int key){
        int i = getClosestPosition(key);
        return indexes[i] == key;
    }
    // inserta el key en pos correcta. Crece autom�ticamente de a chunks

    public void insert(int key){
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

    // borra el key si lo hay, sino lo ignora.
    // decrece autom�ticamente de a chunks
    public void delete(int key){
        if(!search(key)){
            return;
        }
        int pos = getClosestPosition(key);
        for(int i = pos; i < size-1; i++){
            indexes[i] = indexes[i+1];
        }
        size--;
    }

    // devuelve la cantidad de apariciones de la clave especificada
    public int occurrences(int key){
        return getClosestPosition(key+1) - getClosestPosition(key-1);
    }

    public int getClosestPosition(int key){
        int l = 0, r = size;
        while(l < r){
            int m = l + (r - l) / 2;
            if(indexes[m] >= key){
                r = m;
            }
            else{
                l = m + 1;
            }
        }
        return l;
    }



    // devuelve un nuevo arreglo ordenado con los elementos que pertenecen al intervalo dado por
    // leftkey y rightkey.  Si el mismo es abierto/cerrado depende de las variables leftIncluded
    // y rightIncluded. True indica que es cerrado.
    // Si no hay matching devuelve arreglo de length 0
    @Override
    public int[] range(int leftKey, int rightKey, boolean leftIncluded, boolean rightIncluded){

        int l = leftIncluded? getClosestPosition(leftKey) : getClosestPosition(leftKey+ 1) ;
        int r = rightIncluded? (getClosestPosition(rightKey)+occurrences(rightKey)) : getClosestPosition(rightKey);
        int allocsize = r-l;

        int[] res = (l < r)?(new int[allocsize]) : (new int[0]);
        int newSize = 0;
        while(l < r){
            res[newSize++] = indexes[l++];
        }
        while(newSize < (allocsize)){
            res[newSize++] = indexes[l++];
        }
        System.out.print("[ ");
        for(int i = 0; i < res.length; i++) System.out.print(res[i] + " ");
        System.out.println("]");
        return res;
    }

    // imprime el contenido del �ndice ordenado por su key
    @Override
    public void sortedPrint(){
        System.out.print("[ ");
        for(int i = 0; i < size; i++){
            System.out.print(indexes[i] + " ");
        }
        System.out.println(" ]");
        System.out.println();
    }


    // devuelve el m�ximo elemento del �ndice. Lanza RuntimeException si no hay elementos
    @Override
    public int getMax(){
        return indexes[size-1];
    }

    // devuelve el m�nimo elemento del �ndice. Lanza RuntimeException si no hay elementos
    @Override
    public int getMin(){
        return indexes[0];
    }

}
