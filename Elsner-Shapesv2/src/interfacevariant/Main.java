
package interfacevariant;


import java.util.ArrayList;

/**
 *
 * @author CryHeroCZ
 */
public class Main {

    
    public static void main(String[] args) {
        Circle c1 = Circle.getInstanceD(8);
        Rectangle r1 = new Rectangle(2, 3);
        Circle c2 = Circle.getInstanceR(2.6);

        System.out.println("1.varianta");
        double allArea1 = c1.computeArea() + r1.getArea() + c2.computeArea();
        System.out.println(allArea1);

        System.out.println("2.varianta");
        //může obsahovat cokoli, co je typově kompatibilní s Object
        ArrayList shapes2 = new ArrayList();   //dynamické pole objektů typu Object
        shapes2.add(c1);
        shapes2.add(r1);
        shapes2.add(Circle.getInstanceR(2.6));

        double allArea2 = 0;
        for (int i = 0; i < shapes2.size(); i++) {
            if (shapes2.get(i) instanceof Circle) {
                allArea2 += ((Circle) shapes2.get(i)).computeArea();
            } else if (shapes2.get(i) instanceof Rectangle) {
                allArea2 += ((Rectangle) shapes2.get(i)).getArea();
            }
        }
        System.out.println(allArea2);

        System.out.println("3.varianta");  //tahle je upravená pro interface -- protože to je zkopčený tamto a je to dělaný na shape
        ArrayList<ShapeInterface> shapes3 = new ArrayList<>(); //dynamické pole objektů typu ShapeInterface
        //může obsahovat cokoli, co je typově kompatibilní s Shape (Shape, Circle, Rectangle - protože jsme tam psali extends)
        //ShapeInterface s = new Rectangle(5,6);  //je ok, protože extended
        //Rectangle r = new ShapeInterface(); // nejde ani přiřadit, ani vytvořit objekt
        shapes3.add(c1);
        shapes3.add(r1);
        shapes3.add(Circle.getInstanceR(2.6));
        
        double allArea3 = 0;
        for (ShapeInterface shape : shapes3) {   //foreach - viz warpaul a předchozí seminárky
             allArea3 += shape.computeArea();  //polymorfism - mnohotvárná - za různých okolnístí ukazuje na různé kódy (když tam bude rectangle-flákne tam computeArea z rectangle)
        }
        System.out.println(allArea3);
    }
}
// hl. myšlenka, dědit můžu jen z jedný třídy, implementovat z kdo ví z kolika
