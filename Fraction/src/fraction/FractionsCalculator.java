package fraction;

/**
 *
 * @author CryHeroCZ
 */
public class FractionsCalculator {

    public static void main(String[] args) {
        Fraction f1 = Fraction.setNumber();
        Fraction f2 = Fraction.setNumber();

        Fraction plusf = plus(f1, f2);
        System.out.println("plus: " + plusf);

        Fraction minusf = minus(f1, f2);
        System.out.println("minus: " + minusf);

        Fraction nasobenif = nasobeni(f1, f2);
        System.out.println("nasobeni: " + nasobenif);

        Fraction delenif = deleni(f1, f2);
        System.out.println("děleni: " + delenif);
    }

    private static Fraction plus(Fraction f1, Fraction f2) {
        int c1 = f1.getCit();
        int c2 = f2.getCit();
        int j1 = f1.getJmen();
        int j2 = f2.getJmen();

        int c1n = c1 * j2;
        int c2n = c2 * j1;

        int c = c1n + c2n;
        int j = j1 * j2;
        Fraction f = new Fraction(c, j);
        f = f.zjednoduseni();
        return f;
    }

    private static Fraction minus(Fraction f1, Fraction f2) {
        int c1 = f1.getCit();
        int c2 = f2.getCit();
        int j1 = f1.getJmen();
        int j2 = f2.getJmen();

        int c1n = c1 * j2;
        int c2n = c2 * j1;

        int c = c1n - c2n;
        int j = j1 * j2;
        Fraction f = new Fraction(c, j);
        f = f.zjednoduseni();
        return f;
    }

    private static Fraction nasobeni(Fraction f1, Fraction f2) {
        int c1 = f1.getCit();
        int c2 = f2.getCit();
        int j1 = f1.getJmen();
        int j2 = f2.getJmen();
        int c = c1 * c2;
        int j = j1 * j2;
        Fraction f = new Fraction(c, j);
        f = f.zjednoduseni();
        return f;
    }

    private static Fraction deleni(Fraction f1, Fraction f2) {
        int c1 = f1.getCit();
        int c2 = f2.getCit();
        int j1 = f1.getJmen();
        int j2 = f2.getJmen();
        int c = c1 * j2;
        int j = j1 * c2;
        Fraction f = new Fraction(c, j);
        f = f.zjednoduseni();
        return f;
    }
}
