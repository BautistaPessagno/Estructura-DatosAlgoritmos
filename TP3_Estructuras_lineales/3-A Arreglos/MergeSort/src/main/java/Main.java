public class Main {
    public static void main(String[] args) {
        ArrayUtils sorter = new ArrayUtils();

        // Test cases with different array sizes
        int[] test1 = {5, 2};
        int[] test2 = {9, 3, 7};
        int[] test3 = {64, 34, 25, 12, 22, 11, 90};

        System.out.println("Testing array with 2 elements:");
        ArrayUtils.printArray(test1);
        sorter.mergeSort(test1, 0, test1.length-1);
        System.out.println("Sorted array:");
        ArrayUtils.printArray(test1);

        System.out.println("\nTesting array with 3 elements:");
        ArrayUtils.printArray(test2);
        sorter.mergeSort(test2, 0, test2.length-1);
        System.out.println("Sorted array:");
        ArrayUtils.printArray(test2);

        System.out.println("\nTesting array with multiple elements:");
        ArrayUtils.printArray(test3);
        sorter.mergeSort(test3, 0, test3.length-1);
        System.out.println("Sorted array:");
        ArrayUtils.printArray(test3);
        System.out.println("\nTesting array with multiple elements:");
        ArrayUtils.printArray(test3);
        sorter.mergeSort(test3, 0, test3.length-1);
        System.out.println("Sorted array:");
        ArrayUtils.printArray(test3);

        int[] test4 = {38, 27, 43, 3, 9, 82, 10};
        System.out.println("\nTesting another array with multiple elements:");
        ArrayUtils.printArray(test4);
        sorter.mergeSort(test4, 0, test4.length-1);
        System.out.println("Sorted array:");
        ArrayUtils.printArray(test4);

        int[] test5 = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        System.out.println("\nTesting a sorted array:");
        ArrayUtils.printArray(test5);
        sorter.mergeSort(test5, 0, test5.length-1);
        System.out.println("Sorted array:");
        ArrayUtils.printArray(test5);

        int[] test6 = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        System.out.println("\nTesting a reverse sorted array:");
        ArrayUtils.printArray(test6);
        sorter.mergeSort(test6, 0, test6.length-1);
        System.out.println("Sorted array:");
        ArrayUtils.printArray(test6);
    }
}