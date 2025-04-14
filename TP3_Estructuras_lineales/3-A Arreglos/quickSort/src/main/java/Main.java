public class Main {
    public static void main(String[] args) {
        int[] arr = new int[]{34, 10, 8, 60, 21, 17, 28, 30, 2, 70, 50, 15, 62, 40};
        ArraysUtilities.quicksort(arr);
        for (int i : arr) {
            System.out.print(i + " ");
            // should print 2 8 10 15 17 21 28 30 34 40 50 60 62 70
        }
    }
}