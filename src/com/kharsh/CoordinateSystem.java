package com.kharsh;

//import javafx.geometry.Rectangle2D;
//import sun.plugin.dom.css.Rect;
//import org.w3c.dom.css.Rect;

import javax.swing.*;
import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;


public class CoordinateSystem{

    public final static String adresURL = "http://217.23.139.149:8080/myWeb_war/";
 //   public  final static String adresURL = "http://localhost:8080/";

    public static double sdvigVlevo = 1.3;
    public static int battleRegime = 0;
    public static String canCel = "Отмена";
    public static String fio= "Сергею Фёдоровичу Сопрунову";
    public static String[] topics = new String[20];
    public static int status=0;
    public static int ntopics=0;
    public static String[] when = new String[20];
    public static String[] menuTopics = new String[100];
    public static int[] ocenka = new int[20];
    public static int width=1920;
    public static int height=1080;
    public static Font font; // = new Font("Times new Roman",Font.PLAIN,(int)(25.*width/1920));
    public static Font fontBold;
    public static Font fontSmall; // = new Font("Times new Roman",Font.PLAIN,(int)(20.*width/1920));
    public static Font fontItalic; // = new Font("Times new Roman",Font.ITALIC,(int)(25.*width/1920));
    public static Font fontBig; // = new Font("Times new Roman",Font.PLAIN,(int)(60.*width/1920));
    public static Flag flag;
    public static Matrix mDano;
    public static Matrix mOtvet;
    public static Matrix white;
    public static Matrix mat;
    public static Matrix identity;
    public static int otstup=30;
    public static int hskobka=80;
    public static int wskobka=20;
    public static int wchislo=45;
    public static int pravilno=0;
    public static String login = "";
    public static String login1 = "";
 //   public static String usersFileName = "";
 //   public static String usersFileNameJop = "";
    public static String totalName = "";
    public static String totalName1 = "";
//    public static String totalName2 = "";
    public static LinkedList<Point> ppp = new LinkedList<>();
    public static double distance=10.;
    public static double stroka;
    public static Relation rr;
    public static int SubTopic;
    public static Point p;
    public static Point pp;
    public static double na;
    public static double nb;
    public static int ocen;
    public static int ocenTopic;
//    public static Boolean goAhead;
    public static Boolean clickPoints=false;
    public static Boolean clickMatch =false;
    public static Boolean clickScalarProduct=false;
    public static Boolean clickLinearOper=false;
    public static Boolean showPicture = false;
    public static Boolean animacia=false;
    public static Boolean viborMade=false;
    public static Boolean viborMadeOtmena=false;
    public static int count;
    public static int countMatch;
    public static int maxCount=6;
    Color color=Color.BLACK;
    float Thickness = 3.f;
    public static int orx=200;
    public static int ory=250;
    public static int mm = 80;
    public static int rightSpace = 700;
    public static int downSpace = 200;
 //   public static int maxX= (int)(3.f*orx);
 //   public static int maxY = (int)(2.3f*ory);
    public static int fxp = (int)(0.95f*orx);
    public static int fxm = fxp;
    public static int fyp = (int)(0.95f*ory);
    public static int fym = fyp;
    public static int lxp = (int)(0.96f*fxp);
    public static int lxm = (int)(0.96f*fxm);
    public static int lyp = fyp - fxp + lxp;  //(int)(0.95f*fyp);
    public static int lym = fym - fxm + lxm;  //          (int)(0.95f*fym);
    public static int ramkaWidth = fxm + fxp + rightSpace - orx + fxm;
    public static int ramkaHeight = 0;
//    public Matrix identity = new Matrix(new Point(1,0), new Point(0,1));



    public Boolean isNumber(String string){
        Boolean res = true;
        int count=0;
        int n=0;
        if(string.length()==0)
            res=false;
        else {
            while (string.charAt(n) == ' ' && n < string.length())
                n++;
            if (n > string.length())
                res = false;
            else {
                for (int i = n; i < string.length(); ++i) {
                    if ((int) string.charAt(i) < 48 || (int) string.charAt(i) > 57) {
                        if (string.charAt(i) == '.' || string.charAt(i) == ',')
                            count++;
                        else {
                            if (string.charAt(i) == '-' && i == n)
                                ;
                            else
                                res = false;
                        }
                    }
                    if (count > 1)
                        res = false;
                }
            }
        }
        return(res);
    }

    public Boolean vnutry(Matrix m){
        return(m.apix().getX() > orx - lxm && m.apix().getX() < orx+lxp
                && m.apix().getY() > ory - lyp && m.apix().getY() < ory+lym
                &&m.bpix().getX() > orx - lxm && m.bpix().getX() < orx+lxp
                && m.bpix().getY() > ory - lyp && m.bpix().getY() < ory+lym);
    }

    public Matrix mulMat(Matrix m, Matrix n){
        return(new Matrix(new Point(m.getA().getX()*n.getA().getX()+m.getB().getX()*n.getA().getY(),m.getA().getY()*n.getA().getX()+m.getB().getY()*n.getA().getY()), new Point(m.getA().getX()*n.getB().getX()+m.getB().getX()*n.getB().getY(),m.getA().getY()*n.getB().getX()+m.getB().getY()*n.getB().getY())));
    }

    public static Point downTextLocation() {
        return(new Point(orx - fxp, ory + fym + (int)(35.*height/1080.)));
    }

    public Point rightTextLocation(double stroka){
        return(new Point(orx + fxp +50.*width/1920., ory - fyp + 20.*height/1080. + (int)(stroka*35.*height/1080.)));
    }

    public Point rightTextLocation1(double stroka,double vpravo){
        return(new Point(orx + fxp +50.*width/1920. + (int)vpravo, ory - fyp + 20.*height/1080. + (int)(stroka*35.*height/1080.)));
    }

    public Point rand2(Boolean full){
        double xx;
        double yy;
        if(full){
            xx = Math.random() * (lxp + lxm) + orx - lxm;
            yy = Math.random() * (lyp + lym) + ory - lyp;
        }
        else {
            xx = (Math.random() * (lxp + lxm - 2 * mm)) + orx - lxm + mm;
            yy = (Math.random() * (lyp + lym - 2 * mm)) + ory - lyp + mm;
        }
        return(new Point(xx,yy));
    }

    public Matrix rand4(double size){
        double ax;
        double ay;
        double bx;
        double by;
        do {
            ax = Math.round(((Math.random()-0.5) * (double) lxp * size / mm)*10.)/10.;
            ay = Math.round(((Math.random()-0.5) * (double) lxp * size / mm)*10.)/10.;
            bx = Math.round(((Math.random()-0.5) * (double) lxp * size / mm)*10.)/10.;
            by = Math.round(((Math.random()-0.5) * (double) lxp * size / mm)*10.)/10.;
        }while(Math.abs(ax*by-bx*ay)<3*size*size || Math.abs(ax)<size*0.7 || Math.abs(ay) <size*0.7 || Math.abs(bx) < size*0.7 || Math.abs(by) < size*0.7);
        return(new Matrix(new Point(ax,ay),new Point(bx,by)));
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

    public Boolean meet(Point point, Relation relation){
        Boolean res=true;
        switch(relation.getR()){
            case -2:
                res = point.getX()*relation.getA() + point.getY()*relation.getB() <= relation.getC();
                break;
            case -1:
                res = point.getX()*relation.getA() + point.getY()*relation.getB() < relation.getC();
                break;
            case 0:
                res = point.getX()*relation.getA() + point.getY()*relation.getB() == relation.getC();
                break;
            case 1:
                res = point.getX()*relation.getA() + point.getY()*relation.getB() > relation.getC();
                break;
            case 2:
                res = point.getX()*relation.getA() + point.getY()*relation.getB() >= relation.getC();
                break;
        }
        return(res);
    }

    public Relation rand3(){
        double xx = Math.random()*2*3.14159;
        Relation r = new Relation(lxp*Math.cos(xx)/mm,lxp*Math.sin(xx)/mm,Math.random()*lyp/mm,(int)(Math.random()*100)%5 -2 );
        return(r);
    }

    public Line granica(Relation relation){
        Relation relatio = new Relation(relation.getA(),-relation.getB(),relation.getC()*mm + relation.getA()*orx - relation.getB()*ory,relation.getR());
 //       relatio.b = -relation.b;
 //       relatio.c = relation.c * mm + relation.a * orx - relation.b * ory;
        Line line = new Line();
        Point p1 = new Point();
        Point p2 = new Point();
        if(relatio.getA() == 0. && relatio.getB() !=0.) {
            p1 = new Point(orx - lxm, relatio.getC() / relatio.getB());
            p2 = new Point(orx + lxp, relatio.getC() / relatio.getB());
        }
        else {
            if (relatio.getB() == 0. && relatio.getA() != 0.) {
                p1 = new Point(relatio.getC() / relatio.getA(), ory + lym);
                p2 = new Point(relatio.getC() / relatio.getA(), ory - lyp);
            }
            else{
                if(ory-lyp < (relatio.getC()-relatio.getA()*(orx+lxp))/relatio.getB() && (relatio.getC()-relatio.getA()*(orx+lxp))/relatio.getB() < ory+lym){
                    p1 = new Point(orx + lxp,(relatio.getC()-relatio.getA()*(orx+lxp))/relatio.getB());
                    if(ory-lyp < (relatio.getC()-relatio.getA()*(orx-lxm))/relatio.getB() && (relatio.getC()-relatio.getA()*(orx-lxm))/relatio.getB() < ory+lym) {
                        p2 = new Point(orx - lxm, (relatio.getC() - relatio.getA() * (orx - lxm)) / relatio.getB());
                    }
                    else{
                        if(orx-lxm < (relatio.getC()-relatio.getB()*(ory-lyp))/relatio.getA() && (relatio.getC()-relatio.getB()*(ory-lyp))/relatio.getA() < orx+lxp) {
                            p2 = new Point((relatio.getC() - relatio.getB() * (ory - lyp)) / relatio.getA(),ory - lyp);
                        }
                        else{
                            p2 = new Point((relatio.getC() - relatio.getB() * (ory + lyp)) / relatio.getA(),ory + lyp);
                        }
                    }
                }
                else{
                    if(ory-lyp < (relatio.getC()-relatio.getA()*(orx-lxm))/relatio.getB() && (relatio.getC()-relatio.getA()*(orx-lxm))/relatio.getB() < ory+lym){
                        p1 = new Point(orx - lxm,(relatio.getC()-relatio.getA()*(orx-lxm))/relatio.getB());
                        if(orx-lxm < (relatio.getC()-relatio.getB()*(ory-lyp))/relatio.getA() && (relatio.getC()-relatio.getB()*(ory-lyp))/relatio.getA() < orx+lxp) {
                            p2 = new Point((relatio.getC() - relatio.getB() * (ory - lyp)) / relatio.getA(),ory - lyp);
                        }
                        else{
                            p2 = new Point((relatio.getC() - relatio.getB() * (ory + lyp)) / relatio.getA(),ory + lyp);
                        }
                    }
                    else {
                            p1 = new Point((relatio.getC()- relatio.getB() * (ory - lyp)) / relatio.getA(), ory - lyp);
                            p2 = new Point((relatio.getC() - relatio.getB() * (ory + lym)) / relatio.getA(), ory + lym);
                    }
                }
            }
        }
        return(new Line(p1,p2));
    }

    public double betweenRelations(Relation r1, Relation r2){
        double distance=6.;
        double m1 = Math.sqrt(r1.getA()*r1.getA()+r1.getB()*r1.getB());
        double m2 = Math.sqrt(r2.getA()*r2.getA()+r2.getB()*r2.getB());
        if(Math.abs(r1.getR()) == Math.abs(r2.getR())){
            if(r2.getR()== 0) {
                distance = (Math.abs(r1.getA()*r2.getB()-r1.getB()*r2.getA()) + Math.abs(r1.getA()*r2.getC()-r1.getC()*r2.getA()))/m1/m2;
            }
            else {
                m1 = m1*r1.getR() / Math.abs(r1.getR());
                m2 = m2*r2.getR() / Math.abs(r2.getR());
                distance = Math.sqrt((r1.getA()/m1-r2.getA()/m2)*(r1.getA()/m1-r2.getA()/m2) + (r1.getB()/m1-r2.getB()/m2)*(r1.getB()/m1-r2.getB()/m2) + (r1.getC()/m1-r2.getC()/m2)*(r1.getC()/m1-r2.getC()/m2));
            }
        }
        return(distance);
    }

    public void titulList(){
        LinesComponent comp = new LinesComponent();
        comp.ramka=true;
        Font fontBig = new Font("Times new Roman",Font.ITALIC,(int)(150.*width/1920.));
        if(battleRegime>0){
            comp.addPString("Режим баттла", rightTextLocation1(0.5,-fxp/2.), fontBold);
            comp.addPString(totalName1, rightTextLocation1(1.5,-fxp*sdvigVlevo), font);
            comp.addPString(totalName, rightTextLocation1(1.5,0.), font);
        }
        else {
            if (totalName.length() > 0) {
                comp.addPString(totalName, rightTextLocation(0.5), fontSmall);
            }
        }
        comp.addPString("Анатомия",new Point(orx - fxm/4,ory -fyp/3),fontBig);
      //  comp.addPString("Линейности", new Point(orx,ory),fontBig);
        comp.addPString("Игры, упражнения, баллы и баттлы",new Point(orx,ory+fym/3),font);
        comp.addPString("Этот экземпляр лицензирован " + fio,new Point(orx,(int)(ory+fym*5./12)),font);
        comp.addPString("Пока только для своих",new Point(orx,ory+fym/2),font);
        comp.addPString("Разработано в Институте Новых Технологий",new Point(orx-fxm/2,ory+fym-(int)(6.*height/108.)),font);
        comp.addPString("115162, Москва, Мытная, 50.",new Point(orx-fxm/2,ory+fym-(int)(3.*height/108.)),font);
        comp.addPString("7 (495) 221 26 45, 7 (495) 223 92 45",new Point(orx-fxm/2,ory+fym),font);
        comp.addPString("www.int-edu.ru",new Point(orx-fxm/2,ory+fym+(int)(3.*height/108.)),font);

        comp.logoTip = true;
        comp.repaint();
    }

    public static int ocenka(Point p, Point pp){
        return(ocenkaDistance(Math.sqrt((p.getX()-pp.getX())*(p.getX()-pp.getX())+(p.getY()-pp.getY())*(p.getY()-pp.getY()))));
    }


    public static int ocenkaDistance(double distance){
        int nn=0;
        String str ="";
//        PString pstr;
        LinesComponent comp = new LinesComponent();
//        Font font = new Font("Times new Roman",Font.PLAIN,25);
        if(distance > 1.5){
            nn=0;
            str = String.format("Ну нет, это неправильно ! Оценка %d !",nn);
 //           pstr = new PString(str, downTextLocation,font);
        }
        if(distance<=1.5 && distance > 0.5){
            nn=1;
            str = String.format("Неплохо, но попробуйте еще раз ! Оценка %d !",nn);
//            pstr = new PString(str, downTextLocation,font);
        }
        if(distance<=0.5 && distance > 0.25){
            nn=2;
            str = String.format("Хорошо, но надо тренироваться ! Оценка %d !",nn);
//            pstr = new PString(str, downTextLocation,font);
        }
        if(distance<=0.25){
            nn=3;
            str = String.format("Отлично ! Оценка %d !",nn);
//            pstr = new PString(str, downTextLocation,font);
        }
//        comp.clearPStrings();
        comp.addPString(str, downTextLocation(), font);
        //     comp.repaint();
        return(nn);
    }

    public static int ocenkaDistance(double distance,boolean typedown){
        int nn=0;
        String str ="";
//        PString pstr;
        LinesComponent comp = new LinesComponent();
//        Font font = new Font("Times new Roman",Font.PLAIN,25);
        if(distance > 1.5){
            nn=0;
            str = String.format("Ну нет, это неправильно ! Оценка %d !",nn);
            //           pstr = new PString(str, downTextLocation,font);
        }
        if(distance<=1.5 && distance > 0.5){
            nn=1;
            str = String.format("Неплохо, но попробуйте еще раз ! Оценка %d !",nn);
//            pstr = new PString(str, downTextLocation,font);
        }
        if(distance<=0.5 && distance > 0.25){
            nn=2;
            str = String.format("Хорошо, но надо тренироваться ! Оценка %d !",nn);
//            pstr = new PString(str, downTextLocation,font);
        }
        if(distance<=0.25){
            nn=3;
            str = String.format("Отлично ! Оценка %d !",nn);
//            pstr = new PString(str, downTextLocation,font);
        }
//        comp.clearPStrings();
        if(typedown)
            comp.addPString(str, downTextLocation(), font);
        //     comp.repaint();
        return(nn);
    }

    public static String shifr(String str,int nn){
        String string="";
        int i;
        for(i=0;i<str.length();i++)
           string = string + (char)((str.charAt(i)+7+nn)%26 + 97);
           return(string);
    }

    public static String shifrChar(char[] str,int nn){
        String string="";
        for(int i = 0;i<str.length;++i)
            string = string + (char)((str[i]+7+nn)%26 + 97);
        return(string);
    }

    static Send sD = new Send();

    public static void makeRecord(String topic,int maxCount){
        String str ="";
        String otvet = "";
        String otvet1 = "";
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        if(login.length()>0) {
            str = String.format("login=%s&date=%s&topic=%s&ocenka=%s&maxcount=%s&battleregime=%s", login, dateFormat.format(now), topic, ocenTopic, maxCount, battleRegime);
            otvet = sD.doPost(adresURL + "makeRecord", str);
            str = String.format("login=%s&date=%s&topic=%s&ocenka=%s&maxcount=%s&battleregime=%s", login, "00-000-0000", "durak", "0","0","0");
            otvet1 = sD.doPost(adresURL + "makeRecord", str);
            if(!otvet.contains("Good")) {
                File fileJop = new File(login + ".jop");
                if (fileJop.exists() && maxCount > 0 && ocenTopic > 0) {
                    try {
                        OutputStreamWriter frJop = new OutputStreamWriter(new FileOutputStream(fileJop, true), "UTF8");
                        str = String.format("%s;%s; %d; %d; %d;", dateFormat.format(now), topic, ocenTopic, maxCount, battleRegime);
                        frJop.write(str);
                        frJop.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void domik(int centrx,int centry,Color color){
        LinesComponent comp = new LinesComponent();
        int razmer=mm;
        double mulx = 1.;
        double muly = 1.2;
        int[] xx = {centrx,centrx-(int)(razmer*mulx),centrx+(int)(razmer*mulx)};
        int[] yy = {centry-(int)(1.5*razmer*muly),centry-(int)(.5*razmer*muly),centry-(int)(.5*razmer*muly)};
        comp.addTreugolnik(xx,yy,color);
        comp.addLine(centrx-(int)(.8*razmer*mulx),centry+(int)(.5*razmer*muly),centrx+(int)(.8*razmer*mulx),centry+(int)(.5*razmer*muly),color,3.f);
        comp.addLine(centrx-(int)(.8*razmer*mulx),centry+(int)(.5*razmer*muly),centrx-(int)(.8*razmer*mulx),centry-(int)(.5*razmer*muly),color,3.f);
        comp.addLine(centrx+(int)(.8*razmer*mulx),centry+(int)(.5*razmer*muly),centrx+(int)(.8*razmer*mulx),centry-(int)(.5*razmer*muly),color,3.f);
    }

    public void printInRectangle(Point p1,Point p2, String text, Font font){
        LinesComponent comp = new LinesComponent();
        String[] textMassiv = new String[30];
        int i=0,j=0,k=0,n=0;
        String strokaMinus = "";
        String stroka="";
        String stroka1="";
        String stroka2="";
        while(i<text.length() && j<30 && j*2.5*font.getStringBounds(stroka, new FontRenderContext(new AffineTransform(), true, true)).getHeight()<p2.getY()-p1.getY()){
            while(font.getStringBounds(stroka, new FontRenderContext(new AffineTransform(), true, true)).getWidth()<p2.getX()-p1.getX()) {
                strokaMinus = stroka;
                k=i;
                while (i<text.length() && text.charAt(i++) != ' ' ) {
                    stroka += text.charAt(i-1);
                }
                stroka += ' ';
            }
            stroka = strokaMinus;
            i=k;
            n=0;
            while(font.getStringBounds(stroka, new FontRenderContext(new AffineTransform(), true, true)).getWidth()<p2.getX()-p1.getX()) {
                stroka1 = "";
                stroka2 = stroka;
                while (n < stroka.length() && font.getStringBounds(stroka, new FontRenderContext(new AffineTransform(), true, true)).getWidth() < p2.getX() - p1.getX()) {
                    strokaMinus = stroka;
                    while (n < stroka.length() && stroka.charAt(n++) != ' ') {
                        stroka1 += stroka.charAt(n - 1);
                        stroka2 = stroka2.substring(1);
                    }
                    stroka1 += "  ";
                    n++;
                    stroka2 = stroka2.substring(1);
                    stroka = stroka1 + stroka2;
                }
                if (n >= stroka.length()) {
                    n = 0;
                }
            }
            textMassiv[j++]=strokaMinus;
            stroka="";
        }
        for(k=0;k<j;++k){
           comp.addPString(textMassiv[k], new Point(p1.getX(),p1.getY()+k*1.*font.getStringBounds(stroka, new FontRenderContext(new AffineTransform(), true, true)).getHeight()), font);
        }
      //  return(textMassiv);
    }


}


