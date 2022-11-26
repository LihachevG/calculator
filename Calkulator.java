package calculator;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Calkulator {
    private static boolean rimsk = false;//римские цифры
    private static int a;//первое число
    private static int b;//второе число

    private static Map<String, Integer> rim = new HashMap<>();

    static {
        rim.put("I", 1);
        rim.put("II", 2);
        rim.put("III", 3);
        rim.put("IV", 4);
        rim.put("V", 5);
        rim.put("VI", 6);
        rim.put("VII", 7);
        rim.put("VIII", 8);
        rim.put("IX", 9);
        rim.put("X", 10);
        rim.put("XX", 20);
        rim.put("XXX", 30);
        rim.put("XL", 40);
        rim.put("L", 50);
        rim.put("LX", 60);
        rim.put("LXX", 70);
        rim.put("LXXX", 80);
        rim.put("XC", 90);
        rim.put("C", 100);

    }

    public static void main(String[] args) throws Exception {
        System.out.println("Введите пример для расчета: целые числа либо римские, либо арабские от 1 до 10, знаки '+,-,*,/', всё пишется через пробел");
        BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
        String s = buf.readLine();
        buf.close();
        System.out.println(calc(s));
    }

    public static String calc(String input) throws Exception {
        String[] s = input.split(" ");
        proverka(s);
        int resultat=0;
        switch (s[1]){
            case ("+"):
                resultat = a+b;
                break;
            case ("-"):
                resultat = a-b;
                break;
            case ("*"):
                resultat = a*b;
                break;
            case ("/"):
                resultat = a/b;
        }


        if (rimsk) {
            return perevod(resultat);
        }else {
            System.out.println("Результат");
            return ""+resultat;
        }

    }


    public static boolean proverka(String[] s) throws Exception {

        if (s.length != 3) {
            System.out.println("Неверный ввод примера");
            throw new Exception();
        }
        if (!s[1].equals("+") && !s[1].equals("*") && !s[1].equals("-") && !s[1].equals("/")) {
            System.out.println("Неверный математический знак");
            throw new Exception();
        }


        a = cifry(s[0]);
        boolean rimA=rimsk;
        rimsk=false;
        b = cifry(s[2]);
        boolean rimB=rimsk;

        if (rimA!=rimB){
            System.out.println("Оба числа должны быть либо римские либо арабские");
            throw new Exception();
        }

        if (a<1 || a>10 || b < 1 || b>10){
            System.out.println("Одно или оба числа не в нужном диапазоне");
            throw new Exception();
        }
        return true;
    }

    public static int cifry(String s) throws Exception {
        int chislo = 0;
        try{
            chislo = Integer.parseInt(s);
        }catch (NumberFormatException e){
            if (rim.containsKey(s)){
                chislo = rim.get(s);
                rimsk = true;
            }else{
                System.out.println(s + " - не число");
                throw new Exception();
            }
        }
        return chislo;
    }

    public static String perevod(int chislo) throws Exception {
        if (chislo<1){
            System.out.println("Результат ноль или отрицательное число, что записать римскими цифрами невозможно");
            throw new Exception();
        }

        String s = arabVrim(chislo);
        if (s==null){
            String cifra = ""+chislo;
            s = arabVrim(Integer.parseInt(cifra.substring(0,1))*10) + arabVrim(Integer.parseInt(cifra.substring(1)));
        }

        System.out.println("Результат");
        return s;
    }

    public static String arabVrim(int c){
        for (String i: rim.keySet()){
            if (rim.get(i)==c){
                return i;
            }
        }
        return null;
    }
}