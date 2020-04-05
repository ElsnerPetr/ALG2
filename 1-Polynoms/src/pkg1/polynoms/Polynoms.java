package pkg1.polynoms;

/**
 * Knihovni / library class
 *
 * @author Petr.Elsner
 */
public class Polynoms {

    private Polynoms() { //aby se nemohl vytvořit objekt 

    }

    public static Polynom sum(Polynom a, Polynom b) {
        boolean isBigger = a.getDegree() > b.getDegree();
        Polynom max = isBigger ? a : b; //Math.max( a.getDegree(), b.getDegree())
        Polynom min = isBigger ? b : a;

        double[] sumCoef = new double[max.getDegree() + 1];
        //součet polynomů => 6 0 3 5 + 1 3 6 = 7 3 9 5

        for (int i = 0; i < max.getDegree() + 1; i++) {
            sumCoef[i] = max.getCoefAt(i);
        }
        for (int i = 0; i < min.getDegree() + 1; i++) {
            sumCoef[i] = sumCoef[i] + min.getCoefAt(i);
        }

        return Polynom.getInstanceReverted(sumCoef);
    }

    //dú - multiply
   
    public static Polynom multiply(Polynom a, Polynom b) {  //vojta
        double[] res = new double[a.getLength() + b.getLength() - 1];
        for (int i = 0; i < a.getLength(); i++) {
            for (int j = 0; j < b.getLength(); j++) {
                res[i + j] += a.getCoefAt(i) * b.getCoefAt(j);

            }
        }
        Polynom result = Polynom.getInstance(res);
        return result;
    }
    
    
    public static void main(String[] args) {
        Polynom p1 = Polynom.getInstance(5, 3, 0, 6); //(x3 x2 x1 -)
        Polynom p2 = Polynom.getInstance(6, 3, 1);
        System.out.println(Polynoms.sum(p1, p2));
        System.out.println(Polynoms.multiply(p1, p2));
        
        Polynom multi = multiply(p1, p2);
        System.out.println(multi.LepsiVypis());
    }

}
