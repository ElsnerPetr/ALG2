package pkg05_shapes;

/**
 *
 * @author CryHeroCZ
 */
public class Circle extends Shape{ //extends - circle je typově kompatibilní s shape - Circle IS A Shape

    //data
    private double r;

    //public void Circle(double r) {
    private Circle(double r) {
        this.r = r;
    }

//    public void Circle(double d) {   --- tohle nejde, protože stejnej název
//        this.r d/2.0;
//    }
    
    //tovarni metoda, factory method
    public static Circle getInstanceD(double d) {
        return new Circle(d / 2);

    }
    public static Circle getInstanceR(double r) {
        return new Circle(r);

    }
    
    

    public double getR() {
        return r;
    }

    @Override
    public String toString() {
        //return "Circle{" + "r=" + r + '}';
        return super.toString() + String.format(" r= %.2f", r); //pro zavolání metody z předka
    }
    
    public double computeArea(){
        return Math.PI*r*r;
    }
    
    
    public static void main(String[] args) {
        Circle c1 = Circle.getInstanceR(4);
        System.out.println(c1);
        System.out.println(c1.computeArea());
        
        System.out.println(c1.name);  //ukázka toho, že když je v shape protected, tak lze použít i zde
        System.out.println(c1.getShapeName()); //
        
        
    }

}
