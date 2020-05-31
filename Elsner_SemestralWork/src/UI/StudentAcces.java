/*

 */
package UI;

import APP.Attempt;
import APP.Question;
import APP.Test;
import static UI.Main.sc;
import APP.TestWorker;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import kuusisto.tinysound.TinySound;

/**
 *
 * @author Petr Elsner
 */
public class StudentAcces implements TestInterface {

    private Attempt attempt;
    ArrayList<Test> tests;
    ArrayList<Question> questions = new ArrayList<>();

    int points;

    /**
     * UI metoda pro žáky.
     *
     * @param t
     */
    @Override
    public void UI(TestWorker t) {
        System.out.println("Zadej svoje jméno a příjmení:");

        Pattern p = Pattern.compile("[A-ZŘÚÍÓÁĎŽČŇÉŠ]{1}[a-zěščřžýáíéúůü]+");
        String firstName = sc.next();
        String lastName = sc.next();
        Matcher mf = p.matcher(firstName);
        Matcher ml = p.matcher(lastName);
        if (!mf.find() || !ml.find()) {
            System.out.println("Jméno nebo příjmení obsahuje nepodporované znaky.");
            return;
        }

        LocalDate birthdate;
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
        }

        Random random = new Random();

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

        int rank = attempt.calculateRank(points);
        attempt.setRank(rank);
        System.out.println(attempt.getFormatedAttempt(rank));
        System.out.println("Pokračuj zadáním čehokoliv.");

        TinySound.init();
        String song = "";
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
        t.initSong(song);
        t.playSong(time);
        TinySound.shutdown();

        t.saveNewAttempt(attempt);
    }

}
