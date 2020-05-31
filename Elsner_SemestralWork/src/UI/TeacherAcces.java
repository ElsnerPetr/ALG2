package UI;

import APP.TestWorker;
import UTILS.Writer;
import java.io.File;
import java.util.Scanner;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;

/**
 *
 * @author Petr Elsner
 */
public class TeacherAcces implements TestInterface {

    TestWorker t = new TestWorker();
    Scanner sc = new Scanner(System.in);
    int choice;
    int choicesort;

    /**
     * UI metoda pro učitele.
     *
     * @param t
     */
    @Override
    public void UI(TestWorker t) {

        System.out.println("Zadejte heslo:");
        String zadaneHeslo = sc.nextLine();
        String heslo = "[hH]eslo[ ]+je[ ]+heslo";

        if (zadaneHeslo.matches(heslo)) {

            TinySound.init();

            File musicFile = new File(Writer.dataDirectory, "Happy.wav");//data/
            if (musicFile.exists()) {
                Music song = TinySound.loadMusic(musicFile);
                song.play(true);
                song.setVolume(0.3);
            }else {
                System.out.println("Hudba nenalezena.");
            }

            while (true) {

                while (true) {
                    System.out.println("");
                    System.out.println("Přejete si:");
                    System.out.println("1) Zobrazit aktuální výsledky žáků.");
                    System.out.println("2) Seřadit tabulku výsledků.");
                    System.out.println("3) Zobrazit testy.");
                    System.out.println("4) Vystoupit z profesorského módu.");
                    choice = sc.nextInt();

                    if (choice == 1) {
                        System.out.println("");
                        t.showAttempts();

                    } else if (choice == 2) {

                        System.out.println("Vyberte typ seřazení:");
                        System.out.println("1) Podle známek.");
                        System.out.println("2) Podle jmen žáků.");

                        choicesort = sc.nextInt();

                        if (choicesort == 1) {
                            t.sortByRank();

                        } else if (choicesort == 2) {
                            t.sortByName();

                        } else {
                            System.out.println("Neplatný vstup.");
                        }

                        t.showAttempts();

                    } else if (choice == 3) {
                        try {                            
                            t.loadTests("Tests.txt");
                        } catch (Exception ex) {
                            System.out.println("V souboru testů není něco v pořádku.");
                        }
                        t.showTests();
                    } else if (choice == 4) {
                        System.out.println("Vystupuji z profesorského módu.");
                        System.out.println("");
                        TinySound.shutdown();

                        break;
                    } else {
                        System.out.println("Neplatná volba.");
                    }

                }
                break;
            }

        } else {
            System.out.println("Přístup odepřen.");
        }
    }
}
