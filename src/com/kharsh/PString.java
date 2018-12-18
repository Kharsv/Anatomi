package com.kharsh;

import java.awt.*;

public class PString{
    public String string;
    public Point point;
    public Font font;

    public PString(Font font){
        this.string = new String();
        this.point = new Point();
        this.font = font;
    }

    public PString(String string, Point point,Font font){
        this.string = string;
        this.point = point;
        this.font = font;
    }

    public String getString() {
        return (string);
    }
    public Point getPoint(){
        return(point);
    }

    public Font getFont(){
        return(font);
    }

    public int getWidth(){
        return(font.getSize()*string.length());
    }
}
