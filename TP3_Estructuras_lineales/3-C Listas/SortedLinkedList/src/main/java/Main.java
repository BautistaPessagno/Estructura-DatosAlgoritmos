public class Main {
    public static void main(String[] args) {

SortedLinkedList<String> l = new SortedLinkedList<>();
        System.out.println("lista " +  (l.isEmpty()? "":"NO") + " vacia");
        System.out.println(l.size() );
        System.out.println(l.getMin() );
        System.out.println(l.getMax() );
        System.out.println();

        System.out.println(l.insert("hola"));
        l.dump();
        System.out.println();

        System.out.println("lista " +  (l.isEmpty()? "":"NO") + " vacia");
        System.out.println();

        System.out.println(l.insert("tal"));
        l.dump();
        System.out.println();

        System.out.println(l.insert("ah"));
        l.dump();
        System.out.println();

        System.out.println(l.insert("veo"));
        l.dump();
        System.out.println();

        System.out.println(l.insert("bio"));
        l.dump();
        System.out.println();

        System.out.println(l.insert("tito"));
        l.dump();
        System.out.println();


        System.out.println(l.insert("hola"));
        l.dump();
        System.out.println();


        System.out.println(l.insert("aca"));
        l.dump();
        System.out.println();

        System.out.println(l.size() );
        System.out.println(l.getMin() );
        System.out.println(l.getMax() );

//PRUEBA DE SortedListWithHeader

//        SortedLinkedList<String> l = new SortedLinkedListWithHeader<>();
//        System.out.println(l.insert("hola"));
//        System.out.println(l.insert("tal"));
//        System.out.println(l.insert("ah"));
//        System.out.println(l.insert("veo"));
//        System.out.println(l.insert("bio"));
//        System.out.println(l.insert("tito"));
//        System.out.println(l.insert("hola"));
//        System.out.println(l.insert("aca"));
//
//// la lista deberia contener: aca  ah  bio hola tal  tito  veo
//        for (String s : l) {
//            System.out.println(s);
//        }
//
//        System.out.println("\n");
//
//        System.out.println(l.remove("rrr"));
//        System.out.println(l.remove("tito"));
//        System.out.println(l.insert("bauti"));
//        System.out.printf("\n");;
//
//        for (String s : l) {
//            System.out.println(s);
//        }
//
//
    }

}