public class Main {
    public static void main(String[] args) {
        Qgrams g = new Qgrams(2);
        g.printTokens("salesal");
        System.out.println();
        g.printTokens("alale");
        System.out.println();

        double sim = g.similarity("salesal", "alale");
        System.out.println(sim);

        // #salesal# largo 8
        // #alale# largo 7
        //{#s, sa, al, le, es, sa, al, l#} largo 8
        // {#a, al, la, al, le, e#} largo 6
        // comun = {al, le} largo 2
        // no comun = 8-2 + 6-2 = 10
        // (8+6-(8+6-4))/14 = 4/14 = 2/7 = 0.2857142857142857

    }
}