public class MinPathFinder {

    public int getMinPath(int[][] v){
        int[][] ans = new int[v.length][v[0].length];
        ans[0][0] = v[0][0];
        for(int i = 1; i < v[0].length; i++){
            ans[0][i] = v[0][i] + ans[0][i - 1];
        }
        for (int i = 1; i < v.length; i++){
            ans[i][0] = v[i][0] + ans[i - 1][0];
        }
        for(int i = 1; i < v.length; i++){
            for(int j = 1; j < v[0].length; j++){
                ans[i][j] = v[i][j] + Math.min(ans[i-1][j], ans[i][j-1]);
            }
        }
        return ans[v.length-1][v[0].length-1];
    }
}
