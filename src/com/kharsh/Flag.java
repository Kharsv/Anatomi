package com.kharsh;
import java.awt.*;

public class Flag {
    private Matrix m;
    private Color color;

    public Flag(){

    }
    public Flag(Matrix m, Color color){
        this.m = m;
        this.color=color;
    }

    Matrix getMatrix(){
        return(m);
    }
    Color getColor(){
        return(color);
    }
}
