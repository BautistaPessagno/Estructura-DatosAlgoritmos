
public class ProximityIndex {
    private String[] elements;
    private int size = 0;

    public void initialize(String[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("elements no puede ser null");
        }
        
        for(int rec= 0; rec < elements.length-1; rec++) {
        	if (elements[rec].compareTo(elements[rec+1]) >= 0)
                throw new IllegalArgumentException("hay repetidos o no est� ordenado");
        }
        
        this.elements = elements;
        this.size = elements.length;

     }

    // busca una key en el índice, O(log2 N)
    public boolean search(String key){
        int i = getClosestPosition(key);
        return elements[i] == key;
    }

    public int getClosestPosition(String key){
        int l = 0, r = size;
        while(l < r){
            int m = l + (r - l) / 2;
            int cmp = elements[m].compareTo(key);
            if(cmp >= 0){
                r = m ;
            }
            else{
                l = m + 1;
            }
        }
        return l;
    }


    public String search(String element, int distance) {
        int i = getClosestPosition(element);
        int len = elements.length;
        if (elements[i] != element) {
            return null;
        }
        if (distance == 0) {
            return elements[i];
        }
        if (distance + i >= len) {
            return elements[(distance + i) % len];
        }
        if(distance + i < 0) {
            return elements[len + ((distance + i) % len)];
        }
        return elements[i];
    }

   
}
