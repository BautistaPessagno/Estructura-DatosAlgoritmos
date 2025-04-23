package parcial1.proximityIndex;

public class ProximityIndex {
    private String[] elements;
    private int size = 0;

    public void initialize(String[] elements) {
        if (elements == null) {
            throw new IllegalArgumentException("elements no puede ser null");
        }
        
        for(int rec= 0; rec < elements.length-1; rec++) {
        	if (elements[rec].compareTo(elements[rec+1]) >= 0)
                throw new IllegalArgumentException("hay repetidos o no está ordenado");
        }
        
        this.elements = elements;
        this.size = elements.length;

     }


    public String search(String element, int distance) {
        //TODO completar
        // ...
        return null;
    }

   
}
