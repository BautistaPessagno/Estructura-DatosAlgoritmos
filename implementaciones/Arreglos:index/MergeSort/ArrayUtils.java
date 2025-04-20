import java.util.Arrays;

public class ArrayUtils {

    public void mergeSort(int[] array, int l, int r) {
       if(l < r) {
           int mid = l+ (r-l) / 2;
           mergeSort(array, l, mid);
           mergeSort(array, mid+1, r);
           merge(array, l, mid, r);
       }
    }


    public void merge(int[] array, int l, int mid, int r) {
        int[] left = new int[mid - l + 1];
        int[] right = new int[r - mid];
        for(int i = 0; i < left.length; i++) {
            left[i] = array[l + i];
        }
        for(int i = 0; i < right.length; i++) {
            right[i] = array[mid + 1 + i];
        }

        int i = 0, j = 0;

        while(i < left.length && j < right.length) {
            if(left[i] <= right[j]) {
                array[l] = left[i];
                i++;
            } else {
                array[l] = right[j];
                j++;
            }
            l++;
        }
        while(i < left.length) {
            array[l] = left[i];
            i++;
            l++;
        }
        while(j < right.length) {
            array[l] = right[j];
            j++;
            l++;
        }
    }

    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println();
    }
}
