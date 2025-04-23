public class Soundex {
    public static char mapChar(char c){
        switch (c){
            case 'a','e','i','o','u','y','w','h': return '0';
            case 'b','f','p','v':return '1';
            case 'c', 'g', 'j', 'k', 'q', 's', 'x', 'z': return '2';
            case 'd','t': return '3';
            case 'l': return '4';
            case 'm','n': return '5';
            case 'r': return '6';
        }
        return c;
    }

    public static String soundex(String s){
        s = s.toLowerCase();
        char [] in = s.toCharArray();
        char[] out = {'0', '0', '0', '0'};
        out[0] = in[0];

        char last = mapChar(in[0]);

        int count = 1;
        for(int i = 1; i < in.length && count < 4; i++){
            char current = mapChar(in[i]);

            if(current != last &&  current != '0'){
                out[count++] = current;
            }
            last = current;
        }
        return new String(out).toUpperCase();
    }

    public static double similarity(String a, String b){
        String s1 = soundex(a);
        String s2 = soundex(b);

        double count = 0;
        for(int i = 0; i < s1.length(); i++){
            if(s1.charAt(i) == s2.charAt(i)) {
                count++;
            }
        }
        return count / 4;

    }


}
