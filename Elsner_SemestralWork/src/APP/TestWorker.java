package APP;

import UTILS.BinaryWriter;
import UTILS.TextWriter;
import UTILS.Writer;
import static UTILS.Writer.dataDirectory;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.NoSuchElementException;
import java.util.Scanner;
import kuusisto.tinysound.Music;
import kuusisto.tinysound.TinySound;

/**
 * Třída obsahující různé metody pro práci se soubory.
 *
 * @author Petr Elsner
 */
public class TestWorker {

    //data
    Test test = new Test(1, null);
    ArrayList<Question> questions = new ArrayList<>();
    ArrayList<Test> tests = new ArrayList<>();
    ArrayList<Attempt> attempts = new ArrayList<>();
    ArrayList<Attempt> newattempts = new ArrayList<>();
    Scanner sc = new Scanner(System.in);
    Music song;

    /**
     * Metoda, která kontroluje, jestli existuje načítaný soubor s pokusy.
     *
     * @param attemptsFilepath cesta k již existujícím pokusům
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public void load(String attemptsFilepath) throws FileNotFoundException, IOException {
        File text = new File(dataDirectory, attemptsFilepath);

        if (text.createNewFile()) {
            System.out.println("Byl vytvořen textový dokument pro ukládání pokusů.");
        } else {
            loadAttemptsText(attemptsFilepath);
        }

    }

    /**
     * Metoda načítající soubor s testy.
     *
     * @param testsFilepath cesta k testům
     * @return pole s testy
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public ArrayList<Test> loadTests(String testsFilepath) throws FileNotFoundException, IOException {

        File testFile = new File(Writer.dataDirectory, testsFilepath);
        if (testFile.exists()) {

            try (Scanner inTest = new Scanner(testFile)) {
                ArrayList<Question> questions = new ArrayList<>();
                String index = "0";
                while (inTest.hasNext()) {
                    if (!inTest.hasNext("@")) {

                        String questonText = inTest.nextLine();
                        String rightAnswer = inTest.nextLine();
                        String points = inTest.nextLine();

                        questions.add(new Question(questonText, rightAnswer, Integer.parseInt(points)));
                    } else {
                        inTest.nextLine();
                        index = inTest.nextLine();
                        tests.add(new Test(Integer.parseInt(index), questions));
                        questions = new ArrayList<>();
                    }
                }

                return tests;
            } catch (NumberFormatException e) {
                System.out.println("Se souborem testů není něco v pořádku. (méně vstupů)");
                return null;
            }
        } else {
            System.out.println("Soubor obsahující testy nenalezen.");
            return null;
        }
    }

    /**
     * Metoda načítající textový soubor s již existujícími pokusy.
     *
     * @param attemptsFilepath cesta k souboru
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    private void loadAttemptsText(String attemptsFilepath) throws FileNotFoundException, IOException {
        File attemptFile = new File(Writer.dataDirectory, attemptsFilepath);
        attempts.clear();
        try (Scanner inAttempt = new Scanner(attemptFile)) {
            while (inAttempt.hasNext()) {
                String firstName = inAttempt.next();
                String lastName = inAttempt.next();
                int points = inAttempt.nextInt();
                int rank = inAttempt.nextInt();
                String birthdateS = inAttempt.next();
                LocalDate birthdate = LocalDate.parse(birthdateS);
                try {
                    Attempt a = new Attempt(firstName, lastName, birthdate, test);
                    a.setPoints(points);
                    a.setRank(rank);
                    attempts.add(a);
                } catch (NoSuchElementException e) {
                    System.err.println("NoSuchElementException");
                }
            }
        } catch (Exception e) {
            System.out.println("Soubor je poškozen. Funkce učitelského módu nemusí zobrazovat všechny pokusy.");
            attempts.clear();
        }
    }

    /**
     * Metoda ukládající každý ukončený pokus do po newattempts.
     *
     * @param attempt pokus
     */
    public void saveNewAttempt(Attempt attempt) {

        newattempts.add(attempt);
    }

    /**
     * Metoda řadící pokusy podle známek.
     */
    public void sortByRank() {
        Collections.sort(attempts, Attempt.RankComparator);
    }

    /**
     * Metoda řadící pokusy podle jmen.
     */
    public void sortByName() {
        Collections.sort(attempts, Attempt.LastNameComparator);
    }

    /**
     * Metoda vypisující všechny uskutečněné pokusy.
     */
    public void showAttempts() {
        System.out.println("|Jméno|       |Příjmení|        |Body|Známka|Datum narození|");
        for (Attempt attempt : this.attempts) {
            System.out.println(attempt);
        }
    }

    /**
     * Metoda vypisující všechny dostupné testy.
     */
    public void showTests() {
        for (Test test : this.tests) {
            System.out.println(test);
        }
    }

    //gettery
    public ArrayList<Test> getTests() {
        return tests;
    }

    public ArrayList<Attempt> getAttempts() {
        return attempts;
    }

    /**
     * Metoda načítající soubor hudby.
     *
     * @param musicFilepath umístění souboru hudby
     */
    public void initSong(String musicFilepath) {

        File musicFile = new File(Writer.dataDirectory, musicFilepath);
        if (musicFile.exists()) {
            song = TinySound.loadMusic(musicFile);
        } else {
            song = null;
        }

    }

    /**
     * Metoda spouštějící hudbu.
     *
     * @param time čas, po který bude písnička hrát
     */
    public void playSong(int time) {

        try {
            song.play(true);
            Scanner sc = new Scanner(System.in);

            for (int i = 0; i < time; i++) {

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                }
                if (sc.hasNext()) {
                    song.stop();
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("Hudba nebyla nalezena.");
            System.out.println("");
        }
    }

    /**
     * Metoda přehrávající hudbu v prvotním menu.
     *
     * @return choice vrací volbu, použitou pro další chod programu
     */
    public int playMenuSong() {
        int choice = 5;

        try {
            song.play(true);

            while (true) {

                if (sc.hasNextInt()) {
                    song.stop();
                    choice = sc.nextInt();
                    break;
                } else if (!sc.hasNextInt()) {
                    song.stop();
                    System.out.println("Neplatná volba.");
                    sc.next();
                    return choice = 5;

                }
            }
            return choice;

        } catch (NullPointerException e) {
            System.out.println("Menu hudba nebyla nalezena.");
            choice = sc.nextInt();
            return choice;
        }

    }

    /**
     * Metoda volající Writery, kvůli zapsání dat.
     *
     * @param resultFilepath umístění souboru pro zápis.
     * @throws java.io.IOException
     */
    public void saveResults(String resultFilepath) throws IOException {
        Writer w = null;
        if (resultFilepath.endsWith(".txt")) {
            w = new TextWriter();

        } else if (resultFilepath.endsWith(".dat")) {
            w = new BinaryWriter();

        } else {
            throw new IllegalArgumentException("Nepodporovaná přípona souboru.");
        }
        w.saveResults(resultFilepath, newattempts);
        newattempts.clear();
        tests.clear();

    }

}
