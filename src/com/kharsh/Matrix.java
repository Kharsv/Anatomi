package com.kharsh;

public class Matrix {
    private Point a;
    private Point b;
    CoordinateSystem cs = new CoordinateSystem();

    public Matrix(Point a, Point b){
        this.a=a;
        this.b=b;
    }

    public Point getA(){
        return(a);
    }

    public Point getB(){
        return(b);
    }

    public Point apix(){
        return(new Point((double)cs.orx + a.getX()* cs.mm, (double)cs.ory - a.getY()*cs.mm));
    }
    public Point bpix(){
        return(new Point((double)cs.orx + b.getX()* cs.mm, (double)cs.ory - b.getY()*cs.mm));
    }

}
