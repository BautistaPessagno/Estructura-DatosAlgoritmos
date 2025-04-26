public class Ejer2_3 {

    static int getLevenshteinNivelPalabra(String frase1, String frase2){

        //Primero debemos pasar a mayúscula separar en tokens.
        frase1 =  frase1.toUpperCase();
        frase2 =  frase2.toUpperCase();
        String[] tokens1 = frase1.split("[,:. ]");
        String[] tokens2 = frase2.split("[,:. ]");

        int len1 = tokens1.length;
        int len2 = tokens2.length;
        int[][] mat = new int[len1 + 1][len2 + 1];

        for(int i = 0; i < len1 + 1; i++){
            mat[i][0] = i;
        }
        for(int i = 0; i < len2 + 1; i++){
            mat[0][i] = i;
        }

        for(int i = 1; i < len1 + 1; i ++){
            for(int j = 1; j < len2 + 1; j++){
                mat[i][j] = Math.min(mat[i-1][j-1] + (tokens1[i-1].equals(tokens2[j-1]) ? 0 : 1),
                        Math.min(mat[i][j-1], mat[i-1][j]) + 1);
            }
        }


        return mat[len1][len2];
    }

    public static void main(String[] args) {
        System.out.println(Ejer2_3.getLevenshteinNivelPalabra("estructura de datos y algoritmos", "algoritmos y estructura de datos"));
        System.out.println(Ejer2_3.getLevenshteinNivelPalabra("hotel Hilton, habitación doble", "habitación doble de de lujo en hotel Hilton"));
    }
}
