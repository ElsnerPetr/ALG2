/*

 */
package UI;

import APP.Attempt;
import APP.Question;
import APP.Test;
import UTILS.TestWorker;
import UTILS.MusicNotFoundException;
import UTILS.MusicWorker;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kuusisto.tinysound.TinySound;

/**
 *
 * @author Petr Elsner
 */
public class StudentAcces implements TestInterface {

    private Scanner sc = new Scanner(System.in);
    private Attempt attempt;
    private ArrayList<Test> tests;
    private ArrayList<Question> questions = new ArrayList<>();
    private MusicWorker m = new MusicWorker();
    private String song = "";
    private String choice;
    private int rank;
    private LocalDate birthdate;
    private Random random = new Random();
    private int points;
    private Pattern p;
    private Matcher mf;
    private Matcher ml;
    private String firstName;
    private String lastName;

    /**
     * UI metoda pro žáky.
     *
     * @param t
     */
    @Override
    public void UI(TestWorker t) {

        System.out.println("Zadej svoje jméno a příjmení:");
        p = Pattern.compile("[A-ZŘÚÍÓÁĎŽČŇÉŠ]{1}+[a-zěščřžýáíéúůü]+");
        firstName = sc.next();
        lastName = sc.next();
        mf = p.matcher(firstName);
        ml = p.matcher(lastName);
        if (!mf.find() || !ml.find()) {
            System.out.println("Jméno nebo příjmení obsahuje nepodporované znaky.");
            return;
        }

        System.out.println("Zadej datum svého narození (postupně: rok měsíc den):");
        try {
            birthdate = LocalDate.of(sc.nextInt(), sc.nextInt(), sc.nextInt());
        } catch (DateTimeException e) {
            System.out.println("Špatně zadané datum narození.");
            System.out.println("");
            return;
        }

        try {
            tests = t.loadTests("Tests.txt");
        } catch (IOException ex) {
            System.out.println("Soubor s testy nebyl nalezen.");
            return;
        } catch (NumberFormatException e) {
            System.out.println("Soubor s testy je poškozen.");
            return;
        }

        try {
            attempt = new Attempt(firstName, lastName, birthdate, tests.get(random.nextInt(tests.size() - 1)));
        } catch (NullPointerException e) {
            System.out.println("Soubor obsahující testové otázky nebyl nalezen.");
            System.out.println("");
            return;
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Se souborem není něco v pořádku. (více vstupů) -----------");
            return;
        }

        points = attempt.makeAttempt();

        rank = attempt.calculateRank(points);
        attempt.setRank(rank);
        System.out.println(attempt.getFormatedAttempt(rank));
        System.out.println("Pokračuj zadáním čehokoliv.");

        TinySound.init();
        int time = 0;
        if (points > 8) {
            song = "Music.wav";
            time = 75;
        } else if (points <= 8 && points > 2) {
            song = "Music2.ogg";
            time = 80;

        } else if (points <= 2) {
            song = "Sad.wav";
            time = 11;
        }
        try {
            m.initSong(song);
        } catch (MusicNotFoundException me) {
            System.out.println(me + song);
        }

        m.playSong(time);
        TinySound.shutdown();

        t.saveNewAttempt(attempt);
        System.out.println("Vyberte jakým způsobem chcete uložit data:");
        System.out.println("1) Soubor .txt");
        System.out.println("2) Soubor .dat");
        System.out.println("3) Soubor .txt + .dat");
        choice = sc.nextLine();

        try {
            OUTER:
            while (true) {
                choice = sc.nextLine();
                switch (choice) {
                    case "1":
                        t.saveResults("Attempts.txt");
                        break OUTER;
                    case "2":
                        t.saveResults("Attempts.dat");
                        break OUTER;
                    case "3":
                        t.saveResults("Attempts.txt");
                        t.saveResults("Attempts.dat");
                        break OUTER;
                    default:
                        System.out.println("Nevalidní volba.");
                        break;
                }
            }

        } catch (IOException e) {

        }
        t.clearNewAttempts();
    }

}
