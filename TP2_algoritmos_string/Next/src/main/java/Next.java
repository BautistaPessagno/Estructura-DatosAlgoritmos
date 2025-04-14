public class Next {
    public static int[] getNext(char[] query) {
        //implementacion
        int next[] = new int[query.length];
        int border = 0;
        int rec = 1;
        while ( rec < query.length){
            if (query[rec] != query[border]) {
                if (border != 0) {
                    border = next[border - 1];
                } else {
                    next[rec++] = 0;
                }
            } else {
                border++;
                next[rec++] = border;
            }
        }
        return next;
    }

    public static int indexOf(char[] query, char[] c) {

    }
}
