package de.blackthonderjr.wurzelzieher;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Rechner {
    public static String textRechnung = "";
    public static String rechnung(double number, int steps) {
        double numberA;
        double numberB;
        double oldNumberA;
        int step2;

        textRechnung = textRechnung + "a(0) = 1\n";
        textRechnung = textRechnung + "b(0) = " + number + "\n";

        numberA = (1.0 + number) / 2.0;
        numberA = round(numberA);
        textRechnung = textRechnung + "\na(1) = 1 + " + number + " / 2 = " + numberA;

        numberB = number / numberA;
        numberB = round(numberB);
        textRechnung = textRechnung + "\nb(1) = " + number + " / " + numberA + " = " + numberB + "\n";


        for (int i = 1; i < steps; i++) {
            oldNumberA = numberA;
            numberA = calcA(numberA, numberB);
            step2 = i+1;

            textRechnung = textRechnung + "\na(" + step2 +") = " + oldNumberA +" + " + numberB + " / 2 = " + numberA;
            numberB = calcB(numberA, number);
            textRechnung = textRechnung + "\nb(" + step2 +") = " + number + " / " + numberA + " = " + numberB + "\n";
        }
        return String.valueOf(numberB).replace(".", ",");
    }

    public static double round(double zahl) {
        BigDecimal bd = new BigDecimal(zahl);
        bd = bd.setScale(5, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

    public static double calcA(double numberA, double numberB){
        numberA = (numberB + numberA) / 2.0;
        numberA = round(numberA);
        return numberA;
    }
    public static double calcB(double numberA, double number){
        double numberB = number / numberA;
        numberB = round(numberB);
        return numberB;
    }
    public static String getRechnung(){
        return textRechnung;
    }
    public static void clearRechnung(){
        textRechnung = "";
    }
}