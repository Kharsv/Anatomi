package com.kharsh;

import java.awt.*;

public class PImage {
    private Image image;
    private Point point;
    private double width;
    private double height;
    public PImage(Image image, Point point, double width, double height){
        this.image=image;
        this.point=point;
        this.width=width;
        this.height=height;
    }
    public Image getImage(){
        return(image);
    }
    public Point getPoint(){
        return(point);
    }
    public double getWidth(){
        return(width);
    }
    public double getHeight(){
        return(height);
    }
}
