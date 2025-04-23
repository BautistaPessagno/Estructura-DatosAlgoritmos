import java.util.ArrayList;
import java.util.List;

public class KMP {
    public static int[] next(char[] query){
        //implementacion
        int[] next = new int[query.length];
        int border = 0;
        int rec = 1;
        next[0] = 0;
        while(rec < query.length){
            if(query[rec]!= query[border]){
                if(border != 0){
                    border = next[border-1];
                }
                else{
                    next[rec++] = 0;
                }
            }
            else{
                border++;
                next[rec++] = border;
            }
        }
        return next;
    }

    public static ArrayList<Integer> kmp(char[] query, char[] target) {
        ArrayList<Integer> index = new ArrayList<>();
        int[] next = next(query);
        int j = 0;
        for(int i = 0; i < target.length; i++){
            if(query[j] == target[i]){
                j++;
            }
            else if(query[j] != target[i] && j != 0){
                j = next[j-1];
                i--;
            }
            if(j == query.length){
               index.add(i-j+1);
               j = 0;
               i--;
            }
        }
        return index;
    }
}
