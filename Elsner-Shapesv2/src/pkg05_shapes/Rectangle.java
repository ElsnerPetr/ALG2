
package pkg05_shapes;

/**
 * Nemenny immutable object
 * @author CryHeroCZ
 */
public class Rectangle extends Shape {
    //data
    private double a;
    private double b;
    private double area; //vypocitana data

    public Rectangle(double a, double b) {
        this.a = a;
        this.b = b;
        this.area = area();
    }

    public double getA() {
        return a;
    }

    public double getB() {
        return b;
    }
//neni teď potřeba  -- druhá varianta
    public double getArea() {
        return area;
    }
    
    public double computeArea(){
        return area;
    }

    private double area(){
        return a*b;
    }
    
    @Override
    public String toString() {
        //return "Rectangle{" + "a=" + a + ", b=" + b + '}';
        return super.toString() + String.format(" a = %.2f, b = %.2f", a , b);
    }
    
    
}
