public class ExactSearch {
    public static int indexOf(char[] query, char[] c) {
        int idc = 0;
        int idquery  =0;
        while (idc < c.length && idquery < query.length) {
            if(c[idc] == query[idquery]){
                idquery++;
                idc++;
                if(idquery == query.length){
                    return idc - idquery;
                }
            }else{
                idc = idc - idquery + 1;
                idquery = 0;
            }
        }
        return -1;
    }
        //ej pasado: peor por usar dos for's
//        if(query.length > c.length) {
//            return -1;
//        }
//        for (int i = 0; i < c.length; i++) {
//            if (c[i] == query[0]) {
//                int j = 0;
//                while (j < query.length && (i+j) < c.length && c[i + j] == query[j]) {
//                    j++;
//                }
//                if (j == query.length) {
//                    return i;
//                }
//            }
//        }
//        return -1;
//    }
}
