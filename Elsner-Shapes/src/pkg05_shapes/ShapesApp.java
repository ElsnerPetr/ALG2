//dú - doplnění
package pkg05_shapes;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author CryHeroCZ
 */
public class ShapesApp {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Shape> shapes = new ArrayList();

    public static void main(String[] args) {
        int volba;
        double a, b, r, d;
        do {
            volba = seznamVoleb();
            switch (volba) {
                case 1:
                    plusCtverec();
                    break;
                case 2:
                    plusObdelnik();
                    break;
                case 3:
                    plusKruhR();
                    break;
                case 4:
                    plusKruhD();
                    break;
                case 5:
                    System.out.println("Celková plocha: " + fullArea());
                    break;
                case 6:
                    shapes.clear();
                    break;
                case 0:
                    break;

            }
        } while (volba != 0);

    }

    private static int seznamVoleb() {
        System.out.println("*------------------------*");
        System.out.println("*1 - Přidání čtverce.*");
        System.out.println("*2 - Přidání obdélníku.*");
        System.out.println("*3 - Přidání kruhu (r).*");
        System.out.println("*4 - Přidání kruhu (d).*");
        System.out.println("*5 - Výpočet celkové plochy.*");
        System.out.println("*6 - Resetování.*");
        System.out.println("*0 - Konec.*");
        System.out.println("*------------------------*");
        int volba = sc.nextInt();
        return volba;
    }

    private static void plusCtverec() {
        System.out.println("Zadejte délku strany čtverce.");
        double a = sc.nextDouble();
        shapes.add(new Square(a));
    }

    private static void plusObdelnik() {
        System.out.println("Zadejte délky stran obdélníka.");
        double a = sc.nextDouble();
        double b = sc.nextDouble();
        shapes.add(new Rectangle(a, b));
    }

    private static void plusKruhR() {
        System.out.println("Zadejte poloměr kruhu.");
        double r = sc.nextDouble();
        shapes.add(Circle.getInstanceR(r));
    }

    private static void plusKruhD() {
        System.out.println("Zadejte průměr kruhu.");
        double d = sc.nextDouble();
        shapes.add(Circle.getInstanceD(d));
    }

    private static double fullArea() {
        double area = 0;
        for (Shape shape : shapes) {
            area += shape.computeArea();
        }
        return area;
    }

}
