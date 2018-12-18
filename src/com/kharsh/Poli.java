package com.kharsh;

import java.awt.*;
import java.util.LinkedList;
import javax.swing.JComponent;

public class Poli {
public static int h = 64;
public static int w = 20;
// CoordinateSystem cs = new CoordinateSystem();

    public static void logoii(){
        CoordinateSystem cs = new CoordinateSystem();
  //      int h =64;
  //      int w =20;
        int leftupperx = cs.orx-cs.lxm;
        int leftuppery = cs.ory-cs.lyp+w;
        LinesComponent ii = new LinesComponent();
        ii.xx[0]= leftupperx;  //leftupperx;
        ii.xx[1]= leftupperx;   //leftupperx;
        ii.xx[2]= leftupperx+(int)(w*(1.-Math.cos(3.14159/6.)));
        ii.xx[3]= leftupperx+(int)(w*(1.-Math.cos(3.14159/3.)));
        ii.xx[4]= leftupperx+w;
        ii.xx[5]= leftupperx+w;
        ii.yy[0]= leftuppery;
        ii.yy[1]= leftuppery+h-w;
        ii.yy[2]= leftuppery+h-(int)(w*(1.-Math.sin(3.14159/6.)));
        ii.yy[3]= leftuppery+h-(int)(w*(1.-Math.sin(3.14159/3.)));
        ii.yy[4]= leftuppery+h;
        ii.yy[5]= leftuppery;
//        ii.color=Color.BLACK;
        ii.nVershin = 6;
    }




    public static void logonn(){
        CoordinateSystem cs = new CoordinateSystem();
   //     int h =64;
   //     int w =20;
        int leftupperx = cs.orx-cs.lxm+w+w/5;
        int leftuppery = cs.ory-cs.lyp+w;
        LinesComponent ii = new LinesComponent();
        ii.xx[0]= leftupperx;  //leftupperx;
        ii.xx[1]= leftupperx;   //leftupperx;
        ii.xx[2]= leftupperx+w;  //(int)(w*(1.-Math.cos(3.14159/6.)));
        ii.xx[3]= leftupperx+w;  //(int)(w*(1.-Math.cos(3.14159/3.)));
        ii.xx[4]= leftupperx+w+w/5;
        ii.xx[5]= leftupperx+w+w/5;
//        ii.xx[6]=leftupperx+w+w/5+(int)(w*(1.-Math.cos(3.14159/6.)));
//        ii.xx[7]=leftupperx+w+w/5+(int)(w*(1.-Math.cos(3.14159/3.)));
        ii.xx[6]=leftupperx+w+w/5+w;
        ii.xx[7]=leftupperx+w+w/5+w;
        ii.xx[8]=leftupperx+w+w/5+w-(int)(w*(1.-Math.cos(3.14159/6.)));
        ii.xx[9]=leftupperx+w+w/5+w-(int)(w*(1.-Math.cos(3.14159/3.)));
        ii.xx[10]=leftupperx+w+w/5;
        ii.yy[0]= leftuppery;
        ii.yy[1]= leftuppery+h;
        ii.yy[2]= leftuppery+h; //-(int)(w*(1.-Math.sin(3.14159/6.)));
        ii.yy[3]= leftuppery+3;  //-(int)(w*(1.-Math.sin(3.14159/3.)));
        ii.yy[4]= leftuppery+3;
        ii.yy[5]= leftuppery+h; //-w;
//        ii.yy[6]= leftuppery+h-w+(int)(w*(Math.sin(3.14159/6.)));
//        ii.yy[7]= leftuppery+h-w+(int)(w*(Math.sin(3.14159/3.)));
        ii.yy[6]= leftuppery+h;
        ii.yy[7]= leftuppery+w;
        ii.yy[8]= leftuppery+(int)(w*(1.-Math.sin(3.14159/6.)));
        ii.yy[9]= leftuppery+(int)(w*(1.-Math.sin(3.14159/3.)));
        ii.yy[10]= leftuppery;
        ii.nVershin = 11;
    }
    public static void logott(){
        CoordinateSystem cs = new CoordinateSystem();
  //      int h =64;
  //      int w =20;
        int leftupperx = cs.orx-cs.lxm+w+w/5+2*w+2*w/5;
        int leftuppery = cs.ory-cs.lyp;
        LinesComponent ii = new LinesComponent();
        ii.xx[0]= leftupperx;  //leftupperx;
        ii.xx[1]= leftupperx;   //leftupperx;
        ii.xx[2]= leftupperx-w/2;  //(int)(w*(1.-Math.cos(3.14159/6.)));
        ii.xx[3]= leftupperx-w/2;  //(int)(w*(1.-Math.cos(3.14159/3.)));
        ii.xx[4]= leftupperx;
        ii.xx[5]= leftupperx;
        ii.xx[6]=leftupperx+(int)(w*(1.-Math.cos(3.14159/6.)));
        ii.xx[7]=leftupperx+(int)(w*(1.-Math.cos(3.14159/3.)));
        ii.xx[8]=leftupperx+w;
        ii.xx[9]=leftupperx+w;
        ii.xx[10]=leftupperx+w+w/2;
        ii.xx[11]=leftupperx+w+w/2;
        ii.xx[12]=leftupperx+w;
        ii.xx[13]=leftupperx+w;

        ii.yy[0]= leftuppery;
        ii.yy[1]= leftuppery+w;
        ii.yy[2]= leftuppery+w; //-(int)(w*(1.-Math.sin(3.14159/6.)));
        ii.yy[3]= leftuppery+w+3;  //-(int)(w*(1.-Math.sin(3.14159/3.)));
        ii.yy[4]= leftuppery+w+3;
        ii.yy[5]= leftuppery+h;
        ii.yy[6]= leftuppery+h+(int)(w*(Math.sin(3.14159/6.)));
        ii.yy[7]= leftuppery+h+(int)(w*(Math.sin(3.14159/3.)));
        ii.yy[8]= leftuppery+h+w;
        ii.yy[9]= leftuppery+w+3;
        ii.yy[10]= leftuppery+w+3;
        ii.yy[11]= leftuppery+w;
        ii.yy[12]= leftuppery+w;
        ii.yy[13]=leftuppery;
        ii.nVershin = 14;
    }

    public static void logoo1(){
        CoordinateSystem cs = new CoordinateSystem();
  //      int h =64;
  //      int w =20;
        int leftupperx = cs.orx-cs.lxm;
        int leftuppery = cs.ory-cs.lyp;
        LinesComponent ii = new LinesComponent();
        ii.xx[0]= leftupperx;
        ii.xx[1]= leftupperx+(int)(w*0.4);
        ii.xx[2]= leftupperx;
        ii.xx[3]= leftupperx-w/5;
        ii.yy[0]= leftuppery - w/4;
        ii.yy[1]= leftuppery + w/2 -w/4;
        ii.yy[2]= leftuppery - w/4 + w;
        ii.yy[3]= leftuppery + w/2 -w/4;
        ii.nVershin = 4;
    }

    public static void logoo2(){
        CoordinateSystem cs = new CoordinateSystem();
  //      int h =64;
  //      int w =20;
        int leftupperx = cs.orx-cs.lxm;
        int leftuppery = cs.ory-cs.lyp;
        LinesComponent ii = new LinesComponent();
        ii.xx[0]= leftupperx + w;
        ii.xx[1]= leftupperx+w+w/5;
        ii.xx[2]= leftupperx + w;
        ii.xx[3]= leftupperx+w-(int)(w*0.4);
        ii.yy[0]= leftuppery - w/4;
        ii.yy[1]= leftuppery + w/2 -w/4;
        ii.yy[2]= leftuppery - w/4 + w;
        ii.yy[3]= leftuppery + w/2 -w/4;
        ii.nVershin = 4;
    }

    public static void logoo3(){
        CoordinateSystem cs = new CoordinateSystem();
   //     int h =64;
   //     int w =20;
        int leftupperx = cs.orx-cs.lxm;
        int leftuppery = cs.ory-cs.lyp;
        LinesComponent ii = new LinesComponent();
        ii.xx[0]= leftupperx + 1;
        ii.xx[1]= leftupperx+w/2;
        ii.xx[2]= leftupperx + w - 1;
        ii.xx[3]= leftupperx+w/2;
        ii.yy[0]= leftuppery - w/4 + w +1;
        ii.yy[1]= leftuppery - w/4 + w - (int)(w*0.4);
        ii.yy[2]= leftuppery - w/4 + w +1;
        ii.yy[3]= leftuppery - w/4 + w + w/5;
        ii.nVershin = 4;
    }

    public static void logoo4(){
        CoordinateSystem cs = new CoordinateSystem();
  //      int h =64;
  //      int w =20;
        int leftupperx = cs.orx-cs.lxm;
        int leftuppery = cs.ory-cs.lyp;
        LinesComponent ii = new LinesComponent();
        ii.xx[0]= leftupperx + 1;
        ii.xx[1]= leftupperx+w/2;
        ii.xx[2]= leftupperx + w - 1;
        ii.xx[3]= leftupperx+w/2;
        ii.yy[0]= leftuppery - w/4 - 1;
        ii.yy[1]= leftuppery - w/4 + (int)(w*0.4);
        ii.yy[2]= leftuppery - w/4 - 1;
        ii.yy[3]= leftuppery - w/4 - w/5;
        ii.nVershin = 4;
    }
}


