package org.example;




public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        causesStackOverflow(1);
    }

    public static void causesStackOverflow(int count){
        System.out.printf(count+"\n");
        causesStackOverflow(++count);


    }

}