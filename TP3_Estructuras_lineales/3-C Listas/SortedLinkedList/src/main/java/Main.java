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

        System.out.println("---------SortedListWithHeader-------------");

        SortedListService<String> lh = new SortedListWithHeader<>();
        System.out.println(lh.insert("hola"));
        System.out.println(lh.insert("tal"));
        System.out.println(lh.insert("ah"));
        System.out.println(lh.insert("veo"));
        System.out.println(lh.insert("bio"));
        System.out.println(lh.insert("tito"));
        System.out.println(lh.insert("hola"));
        System.out.println(lh.insert("aca"));

// la lista deberia contener: aca  ah  bio hola tal  tito  veo
        for (String s : lh) {
            System.out.println(s);
        }

        System.out.println("\n");

        System.out.println(lh.remove("rrr"));
        System.out.println(lh.remove("tito"));
        System.out.println(lh.insert("bauti"));
        System.out.printf("\n");;

        for (String s : lh) {
            System.out.println(s);
        }


    }

}