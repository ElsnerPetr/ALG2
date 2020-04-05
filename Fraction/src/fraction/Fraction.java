
package fraction;

import java.util.Scanner;

/**
 *
 * @author CryHeroCZ
 */
public class Fraction {

    //data
    public static Scanner sc = new Scanner(System.in);
    private int cit;
    private int jmen;

    //konstruktor
    public Fraction(int cit, int jmen) {
        if (jmen == 0) {
            throw new IllegalArgumentException("Jmenovatel musí být nenuový.");
        }
        this.cit = cit;
        this.jmen = jmen;
    }

    //metody
    public static Fraction setNumber() {
        System.out.println("Zadej čitatel a jmenovatel čísla.");
        int citatel = sc.nextInt();
        int jmenovatel = sc.nextInt();

        return new Fraction(citatel, jmenovatel);
    }

    public int getCit() {
        return cit;
    }

    public int getJmen() {
        return jmen;
    }

    @Override
    public String toString() {
        return "čitatel/jmenovatel = " + cit + "/" + jmen;
    }

    public Fraction zjednoduseni() {
        int a;
        int b;
        int pomocna;
                
        if(this.cit > this.jmen){
            a = this.cit;
            b = this.jmen;
        }else{
            a = this.jmen;
            b = this.cit;
        }
        
        while(b != 0){
            pomocna = a;
            a = b;
            b = pomocna % b;
        }
        
        int zjcit = this.cit / a;
        int zjjmen = this.jmen / a;
        
        return new Fraction(zjcit, zjjmen);
    }

}
