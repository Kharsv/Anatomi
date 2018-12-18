package com.kharsh;

import java.awt.*;
import java.awt.font.FontRenderContext;
import java.awt.geom.AffineTransform;

public class StriFont {
    public String stringUp;
    public String stringDown;
    public Font font;
    public double level;

    public StriFont(String stringUp, String stringDown, Font font,double level){
        this.stringUp=stringUp;
        this.stringDown=stringDown;
        this.font=font;
        this.level=level;
    }

    public StriFont(String stringUp, String stringDown, Font font){
        this.stringUp=stringUp;
        this.stringDown=stringDown;
        this.font=font;
        this.level=0.;
    }
    public StriFont(){

    }

    LinesComponent comp = new LinesComponent();
    CoordinateSystem cs = new CoordinateSystem();

    public void textLine(StriFont[] strifont,double stroka,int n){
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
                comp.addPString(strifont[i].stringUp, cs.rightTextLocation1(stroka - 0.5+strifont[i].level, vpravo), strifont[i].font);
                comp.addPString(strifont[i].stringDown, cs.rightTextLocation1(stroka + 0.4+strifont[i].level, vpravo), strifont[i].font);
            }
            else{
                if(strifont[i].stringUp.length()>0)
                    comp.addPString(strifont[i].stringUp, cs.rightTextLocation1(stroka+strifont[i].level, vpravo), strifont[i].font);
                else
                    comp.addPString(strifont[i].stringDown, cs.rightTextLocation1(stroka+strifont[i].level, vpravo), strifont[i].font);
            }
            i++;
        }
    }
}
