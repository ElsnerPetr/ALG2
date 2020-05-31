package UI;

import APP.TestWorker;
import java.io.IOException;
import java.util.Scanner;
import kuusisto.tinysound.TinySound;

/**
 *
 * @author Petr Elsner
 * @version v1.2 30.5.2020
 */
public class Main {

    /**
     * Hlavní metoda, volající ostatní UI.
     *
     * @param args1 the command line arguments
     */
    static boolean works = true;
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        String nazev = "Attempts.txt";
        String nazevdat = "Attempts.dat";

        TestWorker t = new TestWorker();
        TeacherAcces u = new TeacherAcces();
        StudentAcces s = new StudentAcces();

        //samotné menu
        int vyber;
        while (works) {

            //load
            t.load(nazev);
            System.out.println("---------------------------------------------------------");
            System.out.println("Vítejte v online testu pro první ročníky základních škol!");
            System.out.println("Vyberte typ oprávnění:");
            System.out.println("1) Žák");
            System.out.println("2) Učitel");
            System.out.println("3) Ukončení programu");

            TinySound.init();
            TinySound.setGlobalVolume(0.5);
            t.initSong("Menu.ogg");
            vyber = t.playMenuSong();
            System.out.println("");

            if (vyber == 1) {
                System.out.println("-----------------");
                System.out.println("Žákovský přístup.");
                System.out.println("-----------------");
                s.UI(t);

                t.saveResults(nazev);
                t.saveResults(nazevdat);

            } else if (vyber == 2) {
                System.out.println("------------------");
                System.out.println("Učitelský přístup.");
                System.out.println("------------------");
                u.UI(t);

            } else if (vyber == 3) {
                System.out.println("Ukončuji program.");
                TinySound.shutdown();
                works = false;

            } else {
                System.out.println("Neplatná volba.");
            }
        }
    }
}
