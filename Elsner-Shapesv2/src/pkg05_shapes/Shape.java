package pkg05_shapes;

/**
 *
 * @author CryHeroCZ
 */
public abstract class Shape implements Comparable<Shape> { //předek
    //data
    protected String name = "Geometric object"; //protected - bude viditelný v potomkách
    
    
    //methods
    public abstract double computeArea();
    
    public String getShapeName(){ //viditelná v potomkovi
        return this.getClass().getSimpleName();
    }
    
    @Override
    public String toString() { //prekryva toString tridy Object, default implementace, ktera muze byt prekryta
        return name + ": " + getShapeName();
    }

    @Override
    public int compareTo(Shape o) {
        double a = this.computeArea();
        double b = o.computeArea();
        int res = 0;
        if(a > b){
            res = 1;
        } else if(a == b){
            res = 0;
        } else if (a < b){
            res = -1;
        }
        return res;
    }

  
        
    
}
