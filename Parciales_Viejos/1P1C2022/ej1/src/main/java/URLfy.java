public class URLfy {
    public static void main(String[] args) {

        URLfy urlfy = new URLfy();
        char [] arreglo = new char[] { 'e', 's', ' ', 'u', 'n', ' ', 'e', 'j', 'e', 'm', 'p', 'l', 'o', '\0', '\0', '\0', '\0'};
        urlfy.reemplazarEspacios(arreglo);
        System.out.println(arreglo);
        arreglo= new char [] {'a', ' ', 'b', ' ', 'c', ' ', 'd', ' ', 'e', ' ', 'f', ' ', 'g', ' ', 'h', 'o', 'l', 'a', '\0', '\0',
                '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0', '\0'};
        urlfy.reemplazarEspacios(arreglo);
        System.out.println(arreglo);
        arreglo= new char [] {' ', ' ', 'e', 's', 'p', 'a', 'c', 'i', 'o', 's', ' ', ' ', '\0', '\0', '\0', '\0', '\0', '\0',
                '\0', '\0'};
        urlfy.reemplazarEspacios(arreglo);
        System.out.println(arreglo);
    }
    public void reemplazarEspacios(char [] arregloParam) {
        int len = arregloParam.length;
        int i = len - 1;

        while(i >= 0 && arregloParam[i] == '\0'){
            i--;
        }
        len--;
        while(i >= 0) {
            if (arregloParam[i] == ' ') {
                arregloParam[len--] = '0';
                arregloParam[len--] = '2';
                arregloParam[len--] = '%';
                i--;
            } else {
                arregloParam[len--] = arregloParam[i--];
            }
        }
    }
}
