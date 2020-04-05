package pkg1.polynoms;

import java.util.Arrays;

/**
 *
 * @author Petr.Elsner
 */
public class Polynom {

    //data
    //5x3 + 3x2 + 6  [6 0 3 5]
    private double[] coef;

    //constructors
//   [6 0 3 5]  - jak se to dá zapsat jako polynom
//   [5 3 0 6]
    private Polynom(double[] coef) {
        double[] coefTemp = new double[coef.length];   //defenzivní kope aby privátní byla taky hodnoty pole
        System.arraycopy(coef, 0, coefTemp, 0, coef.length);

        this.coef = coefTemp;
    }
    //tovarni factory metoda

    public static Polynom getInstanceReverted(double[] coef) {  //[6 0 3 5]
        return new Polynom(coef);
    }

    public static Polynom getInstance(double... coef) {  //[5 3 0 6] -- ty tři tečky jsou jakože asi pro víc prvků bo co

        double[] coefTemp = new double[coef.length];
        for (int i = 0; i < coef.length; i++) {
            coefTemp[coefTemp.length - 1 - i] = coef[i];

        }
        return new Polynom(coefTemp);
    }

    //metody
    //
    //dú = výpočet konkrétního polynomu pro zadané x --- 5x3 + 3x2 + 6 pro x = 1 hledám y = 5 + 3 + 6 = 14
    //použít hornerovo schéma   
    public double computeValue(double x) {
        double[] coefTemp = coef;
        double vysledek = coefTemp[0];
        for (int i = 1; i < coefTemp.length; i++) {
            vysledek = vysledek * x + coefTemp[i];
        }
        return vysledek;
    }

    //getter
    public double getCoefAt(int exponent) {
        return coef[exponent];
    }

    public double[] getAllCoef(int exponent) {
        return Arrays.copyOf(coef, coef.length);
    }

    public int getDegree() {
        return coef.length - 1;
    }

    public int getLength() {
        return coef.length;
    }
    
    @Override
    public String toString() {
        return "Polynom{ coef=" + Arrays.toString(coef) + "}";
    }

//dú -- nahradit return sofistikovanějším, aby to napsalo ve správném matemat tvaru 5xˇ3 + ... 
    public String LepsiVypis() {
        String vystup = "Lepsi vypis: ";
        for (int i = 0; i < coef.length - 1; i++) {
            if (coef[i] != 0) {
                vystup = vystup + " " + coef[i] + "x^" + (coef.length - 1 - i) + " +";
            }
        }
        vystup = vystup + " " + coef[coef.length - 1];
        return vystup;
    }

    //5x3 => derivate 15x2
    public Polynom derivate() {
        double[] coefD = new double[this.coef.length - 1];  //derivace je o jedno menši
        for (int i = 0; i < coefD.length; i++) {
            coefD[i] = coef[i + 1] * (i + 1);
        }
        return new Polynom(coefD);
    }

    //bonusová dú úloha, vymyslet integrál  -- work in progress
    public Polynom integrate(int a, int b) {
        double[] coefI = new double[coef.length];

        //Polynom vysledek = ;
        //return vysledek;
        return null; //aby nebuzerovalo, že to nikam neukazuje;
    }

    //testovací main metoda
    public static void main(String[] args) {
        double[] a = {6, 0, 3, 5};
        Polynom p1 = Polynom.getInstanceReverted(a);
        System.out.println(p1);
        System.out.println(p1.getCoefAt(3));

        Polynom p2 = Polynom.getInstance(a);
        System.out.println(p2);

        System.out.println(p1.derivate());

        System.out.println(p1.LepsiVypis());

    }

}
