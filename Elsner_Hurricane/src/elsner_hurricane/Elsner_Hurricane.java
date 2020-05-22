package elsner_hurricane;

import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * @author CryHeroCZ
 */
public class Elsner_Hurricane {
    
    public static Scanner sc = new Scanner(System.in);
   
    public static void main(String[] args) {
        HurricanesEditor hrc = new HurricanesEditor();
        try{
            hrc.load();
        }catch(FileNotFoundException e){
            System.out.println("Chyba pri nacitani souboru");
        }
        
        int choise;
        boolean goOn = true;
        while(goOn){
            System.out.println("Vyber moznost:");
            System.out.println("");
            System.out.println("1. Hurikany v obdobi od ... do ...");
            System.out.println("2. Info o konkretnim hurikanu");
            System.out.println("3. Hurikany setridene dle rychlosti");
            System.out.println("0. Konec programu");
            choise = sc.nextInt();
            switch(choise){
                case 1: hurricanesFromTo(hrc);
                break;
                case 2: hurricaneInfo(hrc);
                break;
                case 3: sortedBySpeed(hrc);
                break;
                case 0: goOn = false;
                break;
                default: System.out.println("Neplatna volba.");
                break;
            }
        }
        
    }

    private static void hurricanesFromTo(HurricanesEditor hrc) {
        System.out.println("Zadej obdobi (rok rok):");
        int yearFrom = sc.nextInt();
        int yearTo = sc.nextInt();
        System.out.println(hrc.getInfoFromTo(yearFrom,yearTo));
    }

    private static void hurricaneInfo(HurricanesEditor hrc) {
        System.out.println("Zadej jmeno huricanu:");
        String name = sc.next();
        System.out.println(hrc.getCategoryAndSpeed(name));
    }

    private static void sortedBySpeed(HurricanesEditor hrc) {
        System.out.println("Setridene huricany dle rychosti:");
        System.out.println(hrc.getSortedBySpeed());
    }
    
}
