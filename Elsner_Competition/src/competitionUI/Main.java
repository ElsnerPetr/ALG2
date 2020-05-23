package competitionUI;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import competition.app.Competition;

/**
 *
 * @author CryHeroCZ
 */
public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Competition c = new Competition();
        
        try {
            while (true) { //nekonečná smyčka, dokud prostě nezadáme fungující soubor
                try {  //ošetření chyby nenalezení file
                    System.out.println("Zadej nazvy vstupnich souboru");
                    String startFile = sc.next();
                    String finishFile = sc.next();
                    c.load(startFile, finishFile);
                    break;
//} catch (IOException e){} //když budeme mít víc catchů, tak bychom měli mít hierarchii správně - IOException je výš jak File not found
                } catch (FileNotFoundException e) {   //když se to podělá (to try) tak udělá to co je v catch
                    System.out.println("Zadaný špatný soubor. Zadej znovu.");
                }
            }

            System.out.println(c.getResults());
            System.out.println("Zadej nazev vystupniho souboru");
            String resultFile = sc.next();
            c.saveResults(resultFile);
            System.out.println("Data byla uložena");  //kontrola, že se to udělalo
        } catch (IOException e) {
            System.out.println("Chyba pro cteni a zapisu.");
        } catch (NoSuchElementException e){
            System.out.println(e.getMessage());
        }
    }

}
//DU upravit tak, aby fungovalo že se všechny soubory nacházejí v tý složce data