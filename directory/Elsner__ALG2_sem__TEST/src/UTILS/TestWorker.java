package UTILS;

import APP.Attempt;
import APP.Question;
import APP.Test;
import static UTILS.Writer.dataDirectory;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Třída obsahující různé metody pro práci se soubory.
 *
 * @author Petr Elsner
 */
public class TestWorker {

    //data
    Compare c = new Compare();
    Test test = new Test(1, null);
    private ArrayList<Question> questions = new ArrayList<>();
    private ArrayList<Test> tests = new ArrayList<>();
    private ArrayList<Attempt> attempts = new ArrayList<>();
    private ArrayList<Attempt> newattempts = new ArrayList<>();
    private Scanner sc = new Scanner(System.in);

    /**
     * Metoda, která kontroluje, jestli existuje načítaný soubor s pokusy.
     *
     * @param attemptsFilepath cesta k již existujícím pokusům
     * @return informace o provedeném úkolu
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOException
     */
    public String load(String attemptsFilepath) throws FileNotFoundException, IOException {
        File text = new File(dataDirectory, attemptsFilepath);

        if (attemptsFilepath.endsWith(".txt")) {
            if (text.createNewFile()) {
                return "Byl vytvořen textový dokument pro ukládání pokusů.";
            } else {
                loadAttemptsText(attemptsFilepath);
                return "Načítání proběhlo.";
            }

        } else if (attemptsFilepath.endsWith(".dat")) {
            if (text.createNewFile()) {
                return "Byl vytvořen datový dokument pro ukládání pokusů.";
            } else {
                loadAttemptsDat(attemptsFilepath);
                return "Načítání proběhlo.";
            }

        } else {
            throw new IllegalArgumentException("Nepodporovaná přípona souboru.");
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

        try (Scanner inTest = new Scanner(testFile)) {
            questions = new ArrayList<>();
            String index = "0";
            while (inTest.hasNext()) {
                if (!inTest.hasNext("@")) {

                    String questionText = inTest.nextLine();
                    String rightAnswer = inTest.nextLine();
                    String points = inTest.nextLine();

                    questions.add(new Question(questionText, rightAnswer, Integer.parseInt(points)));
                } else {
                    inTest.nextLine();
                    index = inTest.nextLine();
                    tests.add(new Test(Integer.parseInt(index), questions));
                    questions = new ArrayList<>();
                }
            }

            return tests;
        } catch (NumberFormatException e) {
            throw e;
        }

    }

    /**
     * Metoda načítající textový soubor s již existujícími pokusy.
     *
     * @param attemptsFilepath cesta k souboru
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOExceptionString
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
                    throw e;
                }
            }
        } catch (Exception e) {
            attempts.clear();
            throw e;
        }
    }

    /**
     * Metoda načítající binární soubor s již existujícími pokusy.
     *
     * @param attemptsFilepath cesta k souboru
     * @throws java.io.FileNotFoundException
     * @throws java.io.IOExceptionString
     */
    private void loadAttemptsDat(String attemptsFilepath) throws FileNotFoundException, IOException {
        File attemptFile = new File(Writer.dataDirectory, attemptsFilepath);
        attempts.clear();
        try (DataInputStream dis = new DataInputStream(new FileInputStream(attemptFile))) {
            while (dis.available() > 0) {
                try {

                    String firstName = dis.readUTF();
                    String lastName = dis.readUTF();
                    int points = dis.readInt();
                    int rank = dis.readInt();
                    String birthdateS = dis.readUTF();
                    LocalDate birthdate = LocalDate.parse(birthdateS);

                    try {
                        Attempt a = new Attempt(firstName, lastName, birthdate, test);
                        a.setPoints(points);
                        a.setRank(rank);
                        attempts.add(a);
                    } catch (NoSuchElementException e) {
                        throw e;

                    }
                } catch (EOFException e) {
                    throw e;
                }
            }
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
     * Metoda volající sort.
     */
    public void sortByRank() {
        c.sortByRank(attempts);
    }

    /**
     * Metoda volající sort.
     */
    public void sortByName() {
        c.sortByName(attempts);
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

    }

    public void clearNewAttempts() {
        newattempts.clear();
        tests.clear();

    }

}
