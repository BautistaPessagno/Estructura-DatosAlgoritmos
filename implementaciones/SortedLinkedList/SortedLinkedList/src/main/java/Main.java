import java.util.Iterator;

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

//  Prueba de SortedListWithHeaderAllowsRemoves
        System.out.println("-----------SorotedListWithHeaderAllowsRemove---------");

        SortedListService<Integer> a = new SortedListWithHeader<>();
        a.insert(50);a.insert(30);a.insert(40);a.insert(10);
        a.insert(20);a.insert(60);a.insert(70);a.insert(80);
        a.dump();

        System.out.println("Tamaño= "+a.size());
        System.out.println("Min= "+a.getMin());
        System.out.println("Max= "+a.getMax());

        System.out.println("Con Iterador");

        for(Iterator<Integer> it = a.iterator();it.hasNext();){
                Integer i = it.next();
                if(i.equals(80) || i.equals(10) || i.equals(40)){
                        System.out.println("Deleting "+i);
                        it.remove();
                }
                else
                        System.out.println("Intacto "+i);
        }

        a.dump();
        System.out.println("Tamaño= "+a.size());
        System.out.println("Min= "+a.getMin());
        System.out.println("Max= "+a.getMax());



    }

}