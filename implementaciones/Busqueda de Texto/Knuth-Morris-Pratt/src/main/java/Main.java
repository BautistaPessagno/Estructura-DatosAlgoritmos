public class Main {
    public static void main(String[] args) {
        char[] target= "abracadabra".toCharArray();
        char[] query= "ra".toCharArray();
        System.out.println( KMP.kmp( query, target) );  //2,9

        target= "abracadabra".toCharArray();
        query= "abra".toCharArray();
        System.out.println(KMP.kmp( query, target) );  //0,7

        target= "abracadabra".toCharArray();
        query= "aba".toCharArray();
        System.out.println(KMP.kmp( query, target) );  //-1

        target= "ab".toCharArray();
        query= "aba".toCharArray();
        System.out.println(KMP.kmp( query, target) );  //-1

        target= "xa".toCharArray();
         query= "aba".toCharArray();
        System.out.println(KMP.kmp( query, target) );  //-1

        target= "abracadabras".toCharArray();
        query= "abras".toCharArray();
        System.out.println(KMP.kmp( query, target) );  //7

    }
}