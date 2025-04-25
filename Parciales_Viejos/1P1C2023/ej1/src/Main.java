public class Main {
    public static void main(String[] args) {
        int[][] v = new int [][]
                        {{2, 8, 32, 30},
                        {12, 6, 18, 19},
                        {1, 2, 4, 8},
                        {1, 31, 1, 16}};
        MinPathFinder minPathFinder = new MinPathFinder();
        int ans = minPathFinder.getMinPath(v);
        System.out.println(ans); // deberia imprimir 38

        v = new int [][]
                {{2, 8, 32, 30},
                        {12, 6, 18, 19},
                        {1, 2, 4, 8}};
        minPathFinder = new MinPathFinder();
        ans = minPathFinder.getMinPath(v);
        System.out.println(ans); // 29

        v = new int [][]
                        {{1, 3, 1},
                        {1, 5, 1},
                        {4, 2, 1}};
        minPathFinder = new MinPathFinder();
        ans = minPathFinder.getMinPath(v);
        System.out.println(ans); // deberia imprimir 7
    }
}