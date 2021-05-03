package com.company;

public class Rechteck {

    int length;
    int width;

    public Rechteck(){
        //Do Nothing
    }

    public Rechteck(int tempLength, int tempWidth){
        length = tempLength;
        width = tempWidth;
    }

    public void setLength(int tempLength){
        length = tempLength;
    }

    public void getLength(){
        System.out.println(length);
    }

    public void setWidth(int tempWidth){
        width = tempWidth;
    }

    public void getWidth(){
        System.out.println(width);
    }

    public void umfang(){
        double umfang = 2*width + 2*length;
        System.out.println(umfang);
    }

}
