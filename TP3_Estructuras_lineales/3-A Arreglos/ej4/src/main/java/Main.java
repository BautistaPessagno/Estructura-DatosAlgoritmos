public class Main {
    public static void main(String[] args) {
        IndexParametricService<String> myIndex= new IndexWithDuplicates<>(String.class);
        System.out.println (myIndex.occurrences( "avion") );  // se obtiene 0
        myIndex.delete( "barco");  // ignora
        System.out.println (myIndex.search( "arbol" ) );  // se obtiene false
        myIndex.insert( "barco" );  // almacena [80]
        myIndex.insert( "avion" );  // almacena [20, 80]
        myIndex.insert( "barco" );  // almacena [20, 80, 80]

        try{
            myIndex.initialize( null );
        }
        catch(Exception e) {
            System.out.println("Error: linea 15 (deberia haber error)");
        }

        // sigue con lo anterior
        System.out.println (myIndex.occurrences( "barco" ) );  // se obtiene 2

        try {
            myIndex.initialize( new String[] {"zen", "barco", "avion", "barco", "casa", "zen", "zen", "avion"} );
        }
        catch(Exception e) {
            System.out.println("Error: linea 25 (no deberia haber)");
        }
        // el índice posee [30, 30, 50, 50, 80, 100, 100, 100]
        System.out.println( myIndex.search( "avioneta" ));   // se obtiene false


        System.out.println( myIndex.search( "casa" ));   // se obtiene true

        System.out.println (myIndex.occurrences( "barco" ) );  // se obtiene 2

        myIndex.delete( "barco" );

        System.out.println (myIndex.occurrences( "barco" ) );  // se obtiene 1

        myIndex= new IndexWithDuplicates<>(String.class);

        myIndex.sortedPrint();

        try
        {
            System.out.println( myIndex.getMax() );
        }
        catch(Exception e)
        {
        }

        try
        {
            System.out.println( myIndex.getMin() );
        }
        catch(Exception e)
        {
        }

        try
        {
            myIndex.initialize( new String[] {"zen", "barco", "avion", "barco", "casa", "zen", "zen", "avion"} );
        }
        catch(Exception e)
        {
        }


        myIndex.sortedPrint();

        System.out.println();

        System.out.println( myIndex.getMax() );

        System.out.println( myIndex.getMin() );

        System.out.println("Pruebas de range");


        String[] rta= myIndex.range("barco", "zen", false, false); // ["casa"]

        rta= myIndex.range("barco", "zen", true, false); // [barco, barco, casa]

        rta= myIndex.range("barco", "zen", false, true); // [casa, zen, zen, zen]

        rta= myIndex.range("avion", "barco", true, false); // [avion, avion]

        rta= myIndex.range("azza", "zen", false, false); // [barco, barco, casa]

        rta= myIndex.range("azzz", "zen", true, false); // [barco, barco, casa]

        rta= myIndex.range("azzz", "zen", false, true); // [barco, barco, casa, zen, zen, zen]

        rta= myIndex.range("a", "barco", true, false); // [avion, avion]

        rta= myIndex.range("a", "barco", false, false); // [avion, avino]

        rta= myIndex.range("a", "aa", false, false); // []

        rta= myIndex.range("ab", "abb", false, false); // []

        rta= myIndex.range("zz", "zzz", true, true); // []

        rta= myIndex.range("casa", "casa", false, false); // []

        rta= myIndex.range("casa", "casaz", true, false); // [casa]

        rta= myIndex.range("casa", "casaz", false, false); // []



    }
}