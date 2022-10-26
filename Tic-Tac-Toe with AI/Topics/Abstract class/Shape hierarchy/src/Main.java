
abstract class Shape {

    abstract double getPerimeter();

    abstract double getArea();
}

class Triangle extends Shape {
    private double a;
    private double b;
    private double c;

    Triangle(double a, double b, double c) {
        super();
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getPerimeter() {
        return a + b + c;
    }

    public double getArea() {
        double halfP = getPerimeter() / 2;
        return Math.sqrt(halfP * (halfP - a) * (halfP - b) * (halfP - c));
    }
}

class Rectangle extends Shape {
    private double a;
    private double b;

    Rectangle(double a, double b) {
        super();
        this.a = a;
        this.b = b;
    }

    public double getPerimeter() {
        return (a + b) * 2;
    }

    public double getArea() {
        return a * b;
    }
}

class Circle extends Shape {
    double r;

    Circle(double r) {
        super();
        this.r = r;
    }
    
    public double getPerimeter() {
        return 2 * Math.PI * r;
    }

    public double getArea() {
        return Math.PI * r * r;
    }
}
