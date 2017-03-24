package org.usfirst.frc.team4737.lib.fusion;

/**
 * @author Brian Semrau
 * @version 3/1/2017
 */
public class Point {

    public double x;
    public double y;

    public Point() {
        x = 0;
        y = 0;
    }

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Point(Point p) {
        this.x = p.x;
        this.y = p.y;
    }

    public Point(Point center, double degrees, double magnitude) {
        this.x = center.x + Math.cos(Math.toRadians(degrees)) * magnitude;
    }

    public Point add(Point p) {
        x += p.x;
        y += p.y;
        return this;
    }

    public Point sub(Point p) {
        x -= p.x;
        y -= p.y;
        return this;
    }

    public Point mult(double scale) {
        x *= scale;
        y *= scale;
        return this;
    }

    public Point plus(Point p) {
        return new Point(x + p.x, y + p.y);
    }

    public Point minus(Point p) {
        return new Point(x - p.x, y - p.y);
    }

    public Point scaled(double scale) {
        return new Point(x * scale, y * scale);
    }

    public double angleTo(Point p) {
        return Math.toDegrees(Math.atan2(p.y - y, p.x - x));
    }

}
