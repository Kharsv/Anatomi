package com.kharsh;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.util.LinkedList;
//import java.lang.Thread;
import javax.swing.*;

public class LinesComponent extends JPanel implements Runnable{

//    static Boolean logo=true;
    static Color backGround;
    static Boolean coordsys=false;
    static Boolean logoTip=false;
    static Boolean ramka = false;
    static int[] xx = new int[20];
    static int[] yy = new int[20];

//    static int[][] xxx = new int[3][3];
//    static int[][] yyy = new int[3][3];

    static Flag[] xxx = new Flag[3];

    static int nVershin;
//    static public double movex=500.;
//    static public double movey=200.;
    static public int menuTopic=0;
    static public int ii;
    StriFont[] strFont = new StriFont[10];
 //   StriFont sf = new StriFont();
    CoordinateSystem cs = new CoordinateSystem();

    double dist=10.;



//    public LinesComponent(){
//        if(menuTopic == 1)
//            (new Thread(this)).start();
//    }



 //   cs.ramkaHeight = cs.fyp+cs.fym + 30 + (int)font.getStringBounds("www.int-edu.ru", new FontRenderContext(new AffineTransform(), true, true)).getHeight();

    public void movie(){
        String string = "";

        if(menuTopic > -1 && menuTopic <7) {
            ii = (ii + 1) % 2;
                cs.stroka=0;
                clearAll();
                addCoordinateSysytem();
                coordsys = true;
                logoTip = false;
                addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
                drawFlag(new Flag(cs.white, Color.WHITE));
                drawFlag(xxx[ii]);
            if(menuTopic==0){
                cs.stroka=0.;
                addPString(cs.menuTopics[8], cs.rightTextLocation(cs.stroka++),cs.fontBold);
                cs.stroka++;
                string = String.format("Впечатайте в диалог матрицу, которая определяет");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("линейное преобразование, при котором жёлтый флаг");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("переходит в белый:");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
            }
            if(menuTopic == 1) {
                cs.stroka=0.;
                addPString(cs.menuTopics[9], cs.rightTextLocation(cs.stroka++),cs.fontBold);
                cs.stroka++;
                string = String.format("Слева на рисунке Вы видите жёлтый и белый флаги,");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("которые являются зеркальными отражениями друг");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);

                string = String.format("друга относительно оси    Зеркальное отражение");
                addPString(string, cs.rightTextLocation(cs.stroka), cs.font);
                double vpravo = cs.font.getStringBounds("друга относительно оси ", new FontRenderContext(new AffineTransform(), true, true)).getWidth();
                addPString("x.", cs.rightTextLocation1(cs.stroka++, vpravo), cs.fontItalic);
                string = String.format("относительно любой прямой, проходящей через на-");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("чало координат, является линейным преобразованием.");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("Очень легкая задача: впечатайте в диалог матрицу");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("зеркального отражения относительно оси");
                addPString(string, cs.rightTextLocation(cs.stroka), cs.font);
                vpravo = cs.font.getStringBounds("зеркального отражения относительно оси ", new FontRenderContext(new AffineTransform(), true, true)).getWidth();
                addPString("x.", cs.rightTextLocation1(cs.stroka++, vpravo), cs.fontItalic);
            }

            if(menuTopic == 2) {
                cs.stroka=0.;
                addPString(cs.menuTopics[9], cs.rightTextLocation(cs.stroka++),cs.fontBold);
                cs.stroka++;
                string = String.format("Снова Вы видите жёлтый и белый флаги, которые");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("являются зеркальными отражениями друг друга, но");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("на этот раз относительно оси     Впечатайте в ");
                addPString(string, cs.rightTextLocation(cs.stroka), cs.font);
                double vpravo = cs.font.getStringBounds("на этот раз относительно оси ", new FontRenderContext(new AffineTransform(), true, true)).getWidth();
                addPString("y.", cs.rightTextLocation1(cs.stroka++, vpravo), cs.fontItalic);
                string = String.format("диалог матрицу зеркального отражения относительно");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("оси");
                addPString(string, cs.rightTextLocation(cs.stroka), cs.font);
                vpravo = cs.font.getStringBounds("оси  ", new FontRenderContext(new AffineTransform(), true, true)).getWidth();
                addPString("y.", cs.rightTextLocation1(cs.stroka++, vpravo), cs.fontItalic);
            }

            if (menuTopic == 3) {
                cs.stroka=0.;
                addPString(cs.menuTopics[9], cs.rightTextLocation(cs.stroka++),cs.fontBold);
                cs.stroka++;
                addLine(cs.orx - cs.lym, cs.ory + cs.lym, cs.orx + cs.lyp, cs.ory - cs.lyp, Color.BLACK, 3.f);
                string = String.format("Слева на рисунке Вы видите жёлтый и белый флаги,");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("которые являются зеркальными отражениями друг");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("друга относительно диагональной прямой, задаваемой");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("уравнением           Впечатайте в диалог матрицу зер-");
                addPString(string, cs.rightTextLocation(cs.stroka), cs.font);
                double vpravo = cs.font.getStringBounds("уравнением ", new FontRenderContext(new AffineTransform(), true, true)).getWidth();
                addPString("y = x.", cs.rightTextLocation1(cs.stroka++, vpravo), cs.fontItalic);
                string = String.format("кального отражения относительно этой приямой.");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
            }
            if (menuTopic == 4) {
                cs.stroka=0.;
                addPString(cs.menuTopics[9], cs.rightTextLocation(cs.stroka++),cs.fontBold);
                cs.stroka++;
                addLine(cs.orx - cs.lym, cs.ory + cs.lym, cs.orx + cs.lyp, cs.ory - cs.lyp, Color.BLACK, 3.f);
                drawFlag(xxx[2]);
                string = String.format("Это были очень простые примеры, вероятно, Вы легко");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("справились с заданиями. Рассмотрим пример похитрее.");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("Слева на рисунке Вы видите жёлтый, синий и белый");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("флаги. Синий флаг является зеркальным отражением");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("жёлтого относительно оси     а белый флаг является");
                addPString(string, cs.rightTextLocation(cs.stroka), cs.font);
                double vpravo = cs.font.getStringBounds("черного относительно оси ", new FontRenderContext(new AffineTransform(), true, true)).getWidth();
                addPString("x,", cs.rightTextLocation1(cs.stroka++, vpravo), cs.fontItalic);
                string = String.format("отражением синего относительно диагональной пря-");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("мой. Впечатайте в диалог матрицу линейного преобра-");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("зования, которое является результатом последователь-");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("ного выполнения двух отражений: сначала относительно");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                vpravo = cs.font.getStringBounds("оси ", new FontRenderContext(new AffineTransform(), true, true)).getWidth();
                addPString("x,", cs.rightTextLocation1(cs.stroka, vpravo), cs.fontItalic);
                string = String.format("оси     а потом относительно диагональной прямой.");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
            }
            if(menuTopic == 5){
                cs.stroka=0.;
                addPString(cs.menuTopics[9], cs.rightTextLocation(cs.stroka++),cs.fontBold);
                cs.stroka++;
                addLine(cs.orx - cs.lym, cs.ory + cs.lym, cs.orx + cs.lyp, cs.ory - cs.lyp, Color.BLACK, 3.f);
                drawFlag(xxx[2]);
                string = String.format("Что будет, если поменять последовательность выпол-");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("нения зеркальных отражений? Слева на рисунке Вы");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("опять видите желтый, синий и белый флаги. Синий");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("флаг является зеркальным отражением желтого относи-");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("тельно диагональной прямой, а белый флаг является");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                strFont[0] = new StriFont("отражением синего относительно оси ", "", cs.font);
                strFont[1] = new StriFont("x. ", "", cs.fontItalic);
                strFont[2] = new StriFont("Впечатайте в", "", cs.font);
                textLine(strFont, cs.stroka++, 3);
                //       double vpravo = font.getStringBounds("отражением желтого относительно оси ", new FontRenderContext(new AffineTransform(), true, true)).getWidth();
                //       comp.addPString("x.", cs.rightTextLocation1(cs.stroka, vpravo), fontItalic);
                //       string = String.format("отражением синего относительно оси    Впечатайте в");
                string = String.format("диалог матрицу линейного преобразования, которое");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("является результатом последовательного выполнения");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("двух отражений: сначала относительно диагональной");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                double vpravo = cs.font.getStringBounds("прямой, а потом относительно оси  ", new FontRenderContext(new AffineTransform(), true, true)).getWidth();
                addPString("x.", cs.rightTextLocation1(cs.stroka, vpravo), cs.fontItalic);
                string = String.format("прямой, а потом относительно оси");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
            }
            if(menuTopic == 6){
                cs.stroka=0.;
                addPString(cs.menuTopics[10], cs.rightTextLocation(cs.stroka++),cs.fontBold);
                cs.stroka++;
                string = String.format("Сейчас Вам предлагается очень трудная задача,");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("правильное решение которой оценивается в трой-");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("ном размере. Слева на рисунке Вы видите желтый");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("и белый флаги. Требуется построить матрицу, ко-");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("торая определяет линейное преобразование, при ");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
                string = String.format("котором желтый флаг переходит в белый.");
                addPString(string, cs.rightTextLocation(cs.stroka++), cs.font);
            }
        }
        if(menuTopic > 6 && menuTopic < 11){
            clearAll();
            addCoordinateSysytem();
            coordsys = true;
            logoTip = false;
            addPString(String.format("%d",cs.SubTopic+1), new Point(cs.orx-cs.lxm,cs.ory-cs.lyp + cs.hskobka/2),cs.fontBig);
            drawFlag(cs.flag);
            cs.white = cs.mulMat(cs.mat,cs.white);
            drawFlag(new Flag(cs.white, Color.WHITE));
            cs.stroka=0.;
            addPString(cs.menuTopics[13], cs.rightTextLocation(cs.stroka++),cs.fontBold);
            cs.stroka++;
            if(menuTopic == 7) {
                strFont[0] = new StriFont("Матрица ", "", cs.font);
                strFont[1] = new StriFont("L, ", "", cs.fontItalic);
                strFont[2] = new StriFont("визуализированная для нас в виде", "", cs.font);
                textLine(strFont, cs.stroka++, 3);
                strFont[0] = new StriFont("меняющегося белого флага, пробегает некоторую", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
                strFont[0] = new StriFont("траекторию в группе ", "", cs.font);
                strFont[1] = new StriFont("GL(2). ", "", cs.fontItalic);
                strFont[2] = new StriFont("Матрица ", "", cs.font);
                strFont[3] = new StriFont("T ", "", cs.fontItalic);
                strFont[4] = new StriFont("в этом", "", cs.font);
                textLine(strFont, cs.stroka++, 5);
                strFont[0] = new StriFont("контексте называется касательной матрицей, а", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
                strFont[0] = new StriFont("пробегаемое матрицей ", "", cs.font);
                strFont[1] = new StriFont("L ", "", cs.fontItalic);
                strFont[2] = new StriFont("подмножество матриц", "", cs.font);
                textLine(strFont, cs.stroka++, 3);
                strFont[0] = new StriFont("в группе ", "", cs.font);
                strFont[1] = new StriFont("GL(2) ", "", cs.fontItalic);
                strFont[2] = new StriFont("называется однопараметрической", "", cs.font);
                textLine(strFont, cs.stroka++, 3);
                strFont[0] = new StriFont("подгрупой или просто траекторией.", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
            }
            if(menuTopic ==8){
                strFont[0] = new StriFont("А произошло вот, что: вся траектория (или одно-", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
                strFont[0] = new StriFont("параметрическая подгруппа) состоит из ортогональ-", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
                strFont[0] = new StriFont("ных матриц, флаг поворачивается, как твердое", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
                strFont[0] = new StriFont("тело. Кососимметрические матрицы являются каса-", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
                strFont[0] = new StriFont("тельными не только для группы ", "", cs.font);
                strFont[1] = new StriFont("GL(2), ", "", cs.fontItalic);
                strFont[2] = new StriFont("но и для её", "", cs.font);
                textLine(strFont, cs.stroka++, 3);
                strFont[0] = new StriFont("подгруппы  ", "", cs.font);
                strFont[1] = new StriFont("O(2). ", "", cs.fontItalic);
                textLine(strFont, cs.stroka++, 2);
                strFont[0] = new StriFont("Положим теперь ", "", cs.font);
                strFont[1] = new StriFont("T = ", "", cs.fontItalic);
                strFont[2] = new StriFont("(", "", cs.fontBig);
                strFont[3] = new StriFont("0.7   -1", "0.4   -0.7", cs.fontItalic);
                strFont[4] = new StriFont(")", "", cs.fontBig);
                textLine(strFont, cs.stroka++, 5);
                cs.stroka+=0.5;
                strFont[0] = new StriFont("Здесь важно то, что сумма диагональных элементов", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
                strFont[0] = new StriFont("матрицы ", "", cs.font);
                strFont[1] = new StriFont("T  ", "", cs.fontItalic);
                strFont[2] = new StriFont("равна нулю. Вообще сумма диагональных", "", cs.font);
                textLine(strFont, cs.stroka++, 3);
                strFont[0] = new StriFont("элементов матрицы называется ее следом, наша мат-", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
                strFont[0] = new StriFont("рица ", "", cs.font);
                strFont[1] = new StriFont("T ", "", cs.fontItalic);
                strFont[2] = new StriFont("имеет нулевой след. Посмотрим, как будет", "", cs.font);
                textLine(strFont, cs.stroka++, 3);
                strFont[0] = new StriFont("меняться матрица ", "", cs.font);
                strFont[1] = new StriFont("L, ", "", cs.fontItalic);
                strFont[2] = new StriFont("для этого переходите на следую-", "", cs.font);
                textLine(strFont, cs.stroka++, 3);
                strFont[0] = new StriFont("щую страницу.", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
            }
            if(menuTopic == 9){
                strFont[0] = new StriFont("На этот раз белый флаг меняется, но его площадь", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
                strFont[0] = new StriFont("остается постоянной: флаг вытягивается в длину,", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
                strFont[0] = new StriFont("и одновременно расплющивается. Имеет место такой", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
                strFont[0] = new StriFont("закон: скорость изменения площади белого флага", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
                strFont[0] = new StriFont("пропорциональна следу касательной матрицы.", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
                cs.stroka++;
                strFont[0] = new StriFont("Вы получили первое представление о том, как", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
                strFont[0] = new StriFont("устроен Мир группы ", "", cs.font);
                strFont[1] = new StriFont("GL(2), ", "", cs.fontItalic);
                strFont[2] = new StriFont("во многом он схож с", "", cs.font);
                textLine(strFont, cs.stroka++, 3);
                strFont[0] = new StriFont("нашим Миром: единичная матрица аналогична началу", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
                strFont[0] = new StriFont("координат, а операция умножения матриц аналогична", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
                strFont[0] = new StriFont("сложению векторов. Перейдем теперь к упражнениям", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
                strFont[0] = new StriFont("по стрельбе.", "", cs.font);
                textLine(strFont, cs.stroka++, 1);
            }
            if(menuTopic == 10){
                drawFlag(new Flag(cs.mDano,Color.RED));
                dist = Math.sqrt((cs.mDano.getA().getX() - cs.white.getA().getX()) * (cs.mDano.getA().getX() - cs.white.getA().getX()) + (cs.mDano.getA().getY() - cs.white.getA().getY()) * (cs.mDano.getA().getY() - cs.white.getA().getY()) + (cs.mDano.getB().getY() - cs.white.getB().getY()) * (cs.mDano.getB().getY() - cs.white.getB().getY()) + (cs.mDano.getB().getX() - cs.white.getB().getX()) * (cs.mDano.getB().getX() - cs.white.getB().getX()));
            }
        }
    }


    public void run(){
        if(menuTopic > -1 && menuTopic <7 ) {
            int i = 0;
            cs.animacia=true;
            while (i < 6) {
                try {
                    i++;
                    Thread.sleep(600);
                    movie();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cs.animacia=false;
        }
        if(menuTopic > 6 && menuTopic < 11){
            int i=0;
            cs.animacia=true;
            while(i<300 && cs.vnutry(cs.white) && dist>0.25) {
                try {
                    i++;
                    Thread.sleep(50);
                    movie();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            cs.animacia=false;
        }
    }

    private static class Line {
        final int x1;
        final int y1;
        final int x2;
        final int y2;
        final Color color;
        final float Thickness;

        public Line(int x1, int y1, int x2, int y2, Color color, float Thickness) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
            this.color = color;
            this.Thickness = Thickness;
        }
    }

    private static class CircleColor {
        Circle circle;
        Color color;

        public CircleColor(Circle circle, Color color){
            this.circle = circle;
            this.color = color;
        }
    }

    private static class Treugolnik{
        private int[] x = new int[3];
        private int[] y = new int[3];
        private Color color;
        public Treugolnik(int[] x, int[] y, Color color){
            this.x = x;
            this.y = y;
            this.color = color;
        }
        public int[] getX(){
            return(x);
        }

        public int[] getY(){
            return(y);
        }

        public Color getColor(){
            return(color);
        }
    }


    private static LinkedList<Line> lines = new LinkedList<Line>();
    private static LinkedList<CircleColor> circles = new LinkedList<>();
    private static LinkedList<PString> pStrings = new LinkedList<>();
    private static LinkedList<Treugolnik> treug = new LinkedList<>();
    private static LinkedList<PImage> pimages = new LinkedList<>();

    public void addPImage(PImage pimage){
        pimages.add(pimage);
    }


    public void addPString(String string, Point point,Font font){
        pStrings.add(new PString(string,point,font));
        repaint();
    }

    public void addCircle(double x, double y, double w, double h, Color color,float Th){
        circles.add(new CircleColor(new Circle(x-w/2,y-h/2,w,h,Th),color));
        repaint();
    }

    public void addLine(int x1, int x2, int x3, int x4, Color color,float Thickness) {
        lines.add(new Line(x1, x2, x3, x4, color,Thickness));
        repaint();
    }

    public void addTreugolnik(int[] x, int[] y, Color color){
        treug.add(new Treugolnik(x,y,color));
        super.repaint();
    }

    public void clearLines() {
        lines.clear();
//        circles.clear();
//        pStrings.clear();
//        coordsys=false;
//        logoTip=false;
        repaint();
    }

    public void clearCircles() {
 //       lines.clear();
        circles.clear();
 //       pStrings.clear();
 //       coordsys=false;
 //       logoTip=false;
        repaint();
    }

    public void clearPStrings() {
 //       lines.clear();
 //       circles.clear();
        pStrings.clear();
 //       coordsys=false;
 //       logoTip=false;
        repaint();
    }

    public void clearTreug(){
        clearTreug();
        repaint();
    }

    public void clearAll() {
        lines.clear();
        circles.clear();
        pStrings.clear();
        treug.clear();
        pimages.clear();
        coordsys=false;
        logoTip=false;
        repaint();
    }


    public void addArrow(int x1,int y1,int x2,int y2,Color color,float Thickness){
        lines.add(new Line(x1,y1,x2,y2,color,Thickness));
        int l_nak= 15,w_nak=5;
        double r=Math.sqrt((x2-x1)*(x2-x1)+(y2-y1)*(y2-y1));
        lines.add(new Line(x2,y2,x2-(int)(l_nak*(double)(x2-x1)/r)+(int)(w_nak*(double)(y2-y1)/r),y2-(int)(l_nak*(double)(y2-y1)/r)-(int)(w_nak*(double)(x2-x1)/r),color,0.7f*Thickness));
        lines.add(new Line(x2,y2,x2-(int)(l_nak*(double)(x2-x1)/r)-(int)(w_nak*(double)(y2-y1)/r),y2-(int)(l_nak*(double)(y2-y1)/r)+(int)(w_nak*(double)(x2-x1)/r),color,0.7f*Thickness));
        repaint();
    }

    public void addMarkedArrow(int x1,int y1,int x2,int y2,Color color,float Thickness,String mark){
        Font font = new Font("Times new Roman",Font.ITALIC,25);
        int znak = 1;
        addArrow(x1,y1,x2,y2,color,Thickness);
        Rectangle2D rect = font.getStringBounds(mark, new FontRenderContext(new AffineTransform(), true, true));
        double w = rect.getWidth();
        double h = rect.getHeight();
        double r = (x2-x1)*(x2-x1)+(y2-y1)*(y2-y1);
        double xxx = x1 + (double)(x2 - x1)/2;
        double yyy = y1 + (double)(y2 - y1)/2;
        double rr=0.8*(Math.abs(x2-x1)*h+Math.abs(y2-y1)*w)/r;
        if(x2-x1>=0.)
            znak=1;
        else
            znak=-1;
        xxx+=(y2-y1)*rr*znak-w*0.5;
        yyy-=(x2-x1)*rr*znak-h*0.5;
        addPString(mark,new Point(xxx,yyy),font);
    }

    public void addCoordinateSysytem(){
 //       CoordinateSystem cs = new CoordinateSystem();
        addArrow(cs.orx-cs.lxm,cs.ory,cs.orx+cs.lxp,cs.ory,cs.color,cs.Thickness);
        addArrow(cs.orx,cs.ory+cs.lym,cs.orx,cs.ory-cs.lyp,cs.color,cs.Thickness);
        for(int i=1; i*cs.mm < cs.lxp-20; i++)
            addLine(cs.orx+i*cs.mm,cs.ory,cs.orx+i*cs.mm,cs.ory-10,cs.color,cs.Thickness*0.7f);
        for(int i=1; i*cs.mm < cs.lxm-20; i++)
            addLine(cs.orx-i*cs.mm,cs.ory,cs.orx-i*cs.mm,cs.ory-10,cs.color,cs.Thickness*0.7f);
        for(int i=1; i*cs.mm < cs.lyp-20; i++)
            addLine(cs.orx,cs.ory-i*cs.mm,cs.orx+10,cs.ory-i*cs.mm,cs.color,cs.Thickness*0.7f);
        for(int i=1; i*cs.mm < cs.lym-20; i++)
            addLine(cs.orx,cs.ory+i*cs.mm,cs.orx+10,cs.ory+i*cs.mm,cs.color,cs.Thickness*0.7f);
//        Font font = new Font("Times new Roman",Font.ITALIC,25);
        PString xs = new PString("x",new Point(cs.orx + cs.lxp - 15, cs.ory - 15),cs.fontItalic);
        PString x1 = new PString("1",new Point(cs.orx + cs.mm, cs.ory - 15),cs.fontItalic);
        PString ys = new PString("y",new Point(cs.orx + 15, cs.ory - cs.lyp +10),cs.fontItalic);
        addPString(xs.getString(), xs.getPoint(),xs.getFont());
        addPString(x1.getString(), x1.getPoint(),x1.getFont());
        addPString(ys.getString(), ys.getPoint(),ys.getFont());
          repaint();
    }

    public void drawFlag(Flag flag){
        int[] xxx = new int[3];
        int[] yyy = new int[3];
        xxx[0] = cs.orx;
        yyy[0] = cs.ory;
        xxx[1] = (int) flag.getMatrix().apix().getX();
        yyy[1] = (int) flag.getMatrix().apix().getY();
        xxx[2] = (int) flag.getMatrix().bpix().getX();
        yyy[2] = (int) flag.getMatrix().bpix().getY();
        addTreugolnik(xxx, yyy, flag.getColor());
        addCircle(xxx[1],yyy[1], 10, 10, Color.BLACK, 10.f);
        addLine(cs.orx, cs.ory, (int) flag.getMatrix().apix().getX(), (int) flag.getMatrix().apix().getY(), Color.BLACK, 3.f);
        addLine(cs.orx, cs.ory, (int) flag.getMatrix().bpix().getX(),(int) flag.getMatrix().bpix().getY(), Color.BLACK, 3.f);
        addLine((int)flag.getMatrix().apix().getX(),(int) flag.getMatrix().apix().getY(), (int) flag.getMatrix().bpix().getX(), (int) flag.getMatrix().bpix().getY(), Color.BLACK, 3.f);
    }

    Poli po = new Poli();
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        super.repaint();
        for(PImage pimage : pimages){
            g.drawImage(pimage.getImage(), (int)pimage.getPoint().getX(), (int)pimage.getPoint().getY(), (int)pimage.getWidth(), (int)pimage.getHeight(),null);
        }
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(new BasicStroke(cs.Thickness*2));
        g2.setColor(Color.BLACK);
        g.setColor(Color.BLACK);
        if(ramka) {
   //         double h = cs.font.getStringBounds("www.int-edu.ru", new FontRenderContext(new AffineTransform(), true, true)).getHeight();
            g2.drawRoundRect(cs.orx - cs.fxm, cs.ory - cs.fyp, cs.fxm + cs.fxp + cs.rightSpace - (int) (0.05 * cs.orx), cs.fyp + cs.fym +(int) (0.13 * cs.ory), 20, 20);
        }
        if(coordsys)
            g2.drawRoundRect(cs.orx - cs.fxm, cs.ory - cs.fyp, cs.fxm + cs.fxp, cs.fyp + cs.fym, 20, 20);
        if(logoTip){
   //         Poli po = new Poli();
            po.logoii();
            g.fillPolygon(xx, yy, nVershin);
            po.logonn();
            g.fillPolygon(xx, yy, nVershin);
            po.logott();
            g.fillPolygon(xx, yy, nVershin);
            po.logoo1();
            g.fillPolygon(xx, yy, nVershin);
            po.logoo2();
            g.fillPolygon(xx, yy, nVershin);
            po.logoo3();
            g.fillPolygon(xx, yy, nVershin);
            po.logoo4();
            g.fillPolygon(xx, yy, nVershin);
        }




 //       Font font1 = new Font("Times new Roman",Font.PLAIN,25);
 //       g.setFont(font1);


        for(Treugolnik treugolnik : treug){
            g2.setColor(treugolnik.getColor());
            g2.fillPolygon(treugolnik.getX(),treugolnik.getY(),3);
        }

        for (Line line : lines) {
            g2.setStroke(new BasicStroke(line.Thickness));
            g2.setColor(line.color);
            g2.drawLine(line.x1, line.y1, line.x2, line.y2);
        }

        for (CircleColor circleC : circles) {
            g2.setStroke(new BasicStroke(circleC.circle.Thickness));
            g2.setColor(circleC.color);
            g2.draw(circleC.circle);
        }

        g2.setColor(Color.BLACK);
        for (PString pstring : pStrings) {
            g2.setFont(pstring.getFont());
            g2.drawString(pstring.getString(),(int)pstring.getPoint().getX(), (int)pstring.getPoint().getY());
        }
    }

    private void textLine(StriFont[] strifont,double stroka,int n){
        int i=0;
        double down=0;
        while(i<n && down == 0.){
            if(strifont[i].stringUp.length()>0 && strifont[i].stringDown.length()>0)
                down=0.5;
            i++;
        }
        stroka+=down;
        i=0;
        double maxvpravo=0;
        double vpravo=0;
        while(i<n){
            if(i>0) {
                maxvpravo = strifont[i - 1].font.getStringBounds(strifont[i - 1].stringUp, new FontRenderContext(new AffineTransform(), true, true)).getWidth();
                if (strifont[i - 1].font.getStringBounds(strifont[i - 1].stringDown, new FontRenderContext(new AffineTransform(), true, true)).getWidth() > maxvpravo)
                    maxvpravo = strifont[i - 1].font.getStringBounds(strifont[i - 1].stringDown, new FontRenderContext(new AffineTransform(), true, true)).getWidth();
                vpravo += maxvpravo;
            }
            if(strifont[i].stringUp.length()>0 && strifont[i].stringDown.length()>0) {
                addPString(strifont[i].stringUp, cs.rightTextLocation1(stroka - 0.5, vpravo), strifont[i].font);
                addPString(strifont[i].stringDown, cs.rightTextLocation1(stroka + 0.4, vpravo), strifont[i].font);
            }
            else{
                if(strifont[i].stringUp.length()>0)
                    addPString(strifont[i].stringUp, cs.rightTextLocation1(stroka, vpravo), strifont[i].font);
                else
                    addPString(strifont[i].stringDown, cs.rightTextLocation1(stroka, vpravo), strifont[i].font);
            }
            i++;
        }
    }
}