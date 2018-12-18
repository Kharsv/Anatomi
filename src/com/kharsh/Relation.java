package com.kharsh;

public class Relation {
    private double a;
    private double b;
    private double c;
    private int rel;
    public Relation(double a, double b, double c, int rel){
        this.a = a;
        this.b =b;
        this.c =c;
        this.rel = rel;
    }

    public double getA(){
        return(a);
    }

    public double getB(){
        return(b);
    }
    public double getC(){
        return(c);
    }
    public int getR(){
        return(rel);
    }

    public static String relaT(int rel){
        String string = "";
        switch(rel){
            case 2:
                string = ">=";
                break;
            case 1:
                string = ">";
                break;
            case 0:
                string = "=";
                break;
            case -1:
                string = "<";
                break;
            case -2:
                string = "<=";
                break;
        }
        return(string);
    }

    public static int codOtnoshenia(String str){
        int nn=3;
        switch(str){
            case ">=":
                nn=2;
                break;
            case ">":
                nn=1;
                break;
            case "=":
                nn=0;
                break;
            case "<":
                nn=-1;
                break;
            case "<=":
                nn=-2;
                break;
            default:
                nn=10;
                break;
        }
        return(nn);
    }
}
