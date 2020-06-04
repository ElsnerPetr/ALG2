package UI;

import UTILS.TestWorker;
import UTILS.MusicNotFoundException;
import UTILS.MusicWorker;
import java.io.IOException;
import java.time.format.DateTimeParseException;
import java.util.InputMismatchException;
import java.util.Scanner;
import kuusisto.tinysound.TinySound;

/**
 *
 * @author Petr Elsner
 * @version v1.6 1.6.2020
 */
public class Main {

    /**
     * Hlavní metoda, volající ostatní UI.
     *
     * @param args1 the command line arguments
     */
    private static boolean works = true;
    private static Scanner sc = new Scanner(System.in);
    private static MusicWorker m = new MusicWorker();
    private static TestWorker t = new TestWorker();
    private static TeacherAcces u = new TeacherAcces();
    private static StudentAcces s = new StudentAcces();
    private static String choice;
    private static String choiceLoad;
    private static String data;

    public static void main(String[] args) throws IOException {

        System.out.println("Z čeho chcete načítat vstupní soubory?");
        System.out.println("1) Attempts.txt");
        System.out.println("2) Attempts.dat");

        OUTER:
        while (true) {
            choiceLoad = sc.nextLine();
            switch (choiceLoad) {

                case "1":
                    data = "Attempts.txt";
                    break OUTER;
                case "2":
                    data = "Attempts.dat";
                    break OUTER;
                default:
                    System.out.println("Neplatná volba");
            }
        }

        //samotné menu
        while (works) {

            //load
            try {
                System.out.println(t.load(data));
            } catch (DateTimeParseException e) {
                System.out.println("Soubor je poškozen. LocalDate.");
            } catch (IOException em) {   
                System.out.println("");
            } catch (InputMismatchException emm) {
                System.out.println("Soubor je poškozen. InputMismatch.");
            }

            System.out.println("---------------------------------------------------------");
            System.out.println("Vítejte v online testu pro první ročníky základních škol!");
            System.out.println("Vyberte typ oprávnění:");
            System.out.println("1) Žák");
            System.out.println("2) Učitel");
            System.out.println("3) Ukončení programu");

            TinySound.init();
            TinySound.setGlobalVolume(0.5);
            try {
                m.initSong("Menu.ogg");
                choice = m.playMenuSong();

            } catch (MusicNotFoundException me) {
                System.out.println(me + "Menu.ogg");
                choice = sc.nextLine();
            }

            System.out.println("");

            switch (choice) {
                case "1":
                    System.out.println("-----------------");
                    System.out.println("Žákovský přístup.");
                    System.out.println("-----------------");
                    s.UI(t);
                    break;
                case "2":
                    System.out.println("------------------");
                    System.out.println("Učitelský přístup.");
                    System.out.println("------------------");
                    u.UI(t);
                    break;
                case "3":
                    System.out.println("Ukončuji program.");
                    TinySound.shutdown();
                    works = false;
                    break;
                default:
                    System.out.println("Neplatná volba.");
                    break;
            }
        }
    }
}
