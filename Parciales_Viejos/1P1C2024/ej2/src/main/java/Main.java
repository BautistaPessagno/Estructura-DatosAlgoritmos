public class Main {
    public static void main(String[] args) {
        IndexWithDuplicates index1 = new IndexWithDuplicates();
        index1.initialize(new int[] {1, 3, 5, 7});
        IndexWithDuplicates index2 = new IndexWithDuplicates();
        index2.initialize(new int[] {2, 4, 6, 8});
        index1.merge(index2);
        index1.dump();
        // Resultado esperado: [1, 2, 3, 4, 5, 6, 7, 8]

        System.out.println("-----------------");
        index1 = new IndexWithDuplicates();
        index1.initialize(new int[] {1, 1, 3, 5, 7});
        index2 = new IndexWithDuplicates();
        index2.initialize(new int[] {2, 4, 4, 6, 8});
        index1.merge(index2);
        index1.dump();
        // Resultado esperado: [1, 1, 2, 3, 4, 4, 5, 6, 7, 8]

        System.out.println("-----------------");
        index1 = new IndexWithDuplicates();
        index1.initialize(new int[] {1, 3, 5});
        index2 = new IndexWithDuplicates();
        index2.initialize(new int[] {2, 4, 6, 8, 10});
        index1.merge(index2);
        index1.dump();
        // Resultado esperado: [1, 2, 3, 4, 5, 6, 8, 10]


    }
}