public class Levenshtein {

    static int distance(String a, String b) {
        a = a.toLowerCase();
        b = b.toLowerCase();
        String min = (a.length() < b.length())?a:b;
        String max = (a.length() > b.length())?a:b;
        int[][] dp = new int[2][min.length() + 1];
        for (int i = 0; i <= max.length(); i++) {
            for (int j = 1; j <= min.length(); j++) {
                if(j == 0){
                    dp[1][j] = i;
                }
                if (i == 0) {
                    dp[1][j] = j;
                } else if (j == 0) {
                    dp[1][j] = i;
                } else if (max.charAt(i - 1) == min.charAt(j - 1)) {
                    dp[1][j] = dp[0][j - 1];
                } else {
                    dp[1][j] = 1 + Math.min(dp[0][j - 1], Math.min(dp[0][j], dp[1][j - 1]));
                }
            }
            dp = replace(dp, min.length());
        }
        return dp[1][min.length()];

    }

    public static int[][] replace(int[][] mat, int l) {
        for (int i = 0; i < l; i++) {
            mat[0][i] = mat[1][i];
        }
        return mat;
    }

    static double normalizedSimilarity(String a, String b) {
        int dist = distance(a, b);
        return 1 - (double) dist / Math.max(a.length(), b.length());
    }
}
