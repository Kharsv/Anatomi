package com.kharsh;

import java.awt.*;

public class Ocenka {
    public static int nn;
    private static PString pstr;
//    public float x;
//    public float y;
//    public float xu;
//    public float yu;
    private static double distance;
    public Ocenka(){

    }

    public static PString getPString(){
        return(pstr);
    }

    public static String getString(){
        return(pstr.getString());
    }
/*
    public static CoordinateSystem cs = new CoordinateSystem();
    public static int ocenka(Point p, Point pp){
        LinesComponent comp = new LinesComponent();
       distance = Math.sqrt((p.getX()-pp.getX())*(p.getX()-pp.getX())+(p.getY()-pp.getY())*(p.getY()-pp.getY()));
       Font font = new Font("Times new Roman",Font.PLAIN,25);
       if(distance > 1.5){
           nn=0;
           String str = String.format("Ну нет, это неправильно ! Оценка %d !",nn);
           pstr = new PString(str, cs.downTextLocation,font);
       }
       if(distance<=1.5 && distance > 0.5){
            nn=1;
           String str = String.format("Неплохо, но попробуйте еще раз ! Оценка %d !",nn);
            pstr = new PString(str, cs.downTextLocation,font);
       }
        if(distance<=0.5 && distance > 0.25){
            nn=2;
            String str = String.format("Хорошо, но надо тренироваться ! Оценка %d !",nn);
            pstr = new PString(str, cs.downTextLocation,font);
        }
        if(distance<=0.25){
            nn=3;
            String str = String.format("Отлично ! Оценка %d !",nn);
            pstr = new PString(str, cs.downTextLocation,font);
        }
//        comp.clearPStrings();
        comp.addPString(pstr.getString(),cs.downTextLocation, font);
   //     comp.repaint();
        return(nn);
    }
*/

}
