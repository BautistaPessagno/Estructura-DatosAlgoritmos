public class Main {
    public static void main(String[] args) {
        System.out.println(Soundex.soundex("threshold"));
        System.out.println(Soundex.soundex("hold"));
        System.out.println(Soundex.soundex("phone"));
        System.out.println(Soundex.soundex("foun"));

        System.out.println(Soundex.similarity("threshold", "hold"));
        System.out.println(Soundex.similarity("phone", "foun"));
    }
}