package com.company;

public class Main {

    public static void main(String[] args) {

        Rechteck r1 = new Rechteck(15, 23);
        Rechteck r2 = new Rechteck();
        r2.setLength(5);
        r2.setWidth(2);
        r1.umfang();
        r2.umfang();

        Quadrat q1 = new Quadrat(2, 90);
        Quadrat q2 = new Quadrat();
        q1.setLength(3);
        q1.setWidth(4);
        q1.umfang();
        q2.umfang();
    }
}
