public class Main {

    public static int maxSubarraySum(int[] arr) {
        int n = arr.length;  // temporal -> +0  espacial -> +1
        int maxSum = Integer.MIN_VALUE;  // temporal -> creo que O(1)  espacial -> +1
        int currentSum = 0;
        for (int i = 0; i < n; i++) {
            currentSum = Math.max(arr[i], currentSum + arr[i]);
            maxSum = Math.max(maxSum, currentSum);
        }
        // al for va a entrar n veces, por lo tanto
        // va a realizar 1 comparacion, una incrementacion n veces y despues dos comparaciones mas + una suma

        // por lo tanto el tiempo de ejecucion es O(n)

        return maxSum;
    }


    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}

