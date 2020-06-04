package UI;

import UTILS.TestWorker;
import UTILS.MusicNotFoundException;
import UTILS.Writer;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;

/**
 *
 * @author Petr Elsner
 */
public class TeacherAcces implements TestInterface {

    private Scanner sc = new Scanner(System.in);
    private String choice;
    private String choicesort;
    private String zadaneHeslo;
    private String heslo;

    /**
     * UI metoda pro učitele.
     *
     * @param t
     */
    @Override
    public void UI(TestWorker t) {

        System.out.println("Zadejte heslo:");
        zadaneHeslo = sc.nextLine();
        heslo = "[hH]eslo[ ]+je[ ]+heslo";

        if (zadaneHeslo.matches(heslo)) {

            TinySound.init();

            File musicFile = new File(Writer.dataDirectory, "Happy.wav");
            try {
                if (musicFile.exists()) {
                    Music song = TinySound.loadMusic(musicFile);
                    song.play(true);
                    song.setVolume(0.3);
                } else {
                    throw new MusicNotFoundException("Soubor hudby nebyl nazezen: Happy.wav");
                }
            } catch (MusicNotFoundException me) {
                System.out.println(me);
            }

            while (true) {

                OUTER:
                while (true) {
                    System.out.println("-----------");
                    System.out.println("Přejete si:");
                    System.out.println("1) Zobrazit aktuální výsledky žáků.");
                    System.out.println("2) Seřadit tabulku výsledků.");
                    System.out.println("3) Zobrazit testy.");
                    System.out.println("4) Vystoupit z profesorského módu.");
                    choice = sc.nextLine();
                    switch (choice) {
                        case "1":
                            System.out.println("");
                            t.showAttempts();
                            break;
                        case "2":
                            System.out.println("Vyberte typ seřazení:");
                            System.out.println("1) Podle známek.");
                            System.out.println("2) Podle jmen žáků.");
                            choicesort = sc.nextLine();
                            switch (choicesort) {
                                case "1":
                                    t.sortByRank();
                                    break;
                                case "2":
                                    t.sortByName();
                                    break;
                                default:
                                    System.out.println("Neplatný vstup.");
                                    break;
                            }
                            t.showAttempts();
                            break;
                        case "3":
                            try {
                                t.loadTests("Tests.txt");
                            } catch (IOException ex) {
                                System.out.println("Soubor s testy nebyl nalezen.");
                            } catch (NumberFormatException e) {
                                System.out.println("Soubor s testy je poškozen.");
                            }
                            t.showTests();
                            break;
                        case "4":
                            System.out.println("Vystupuji z profesorského módu.");
                            System.out.println("");
                            TinySound.shutdown();
                            break OUTER;
                        default:
                            System.out.println("Neplatná volba.");
                            break;
                    }
                }
                break;
            }

        } else {
            System.out.println("Přístup odepřen.");
        }
    }
}
