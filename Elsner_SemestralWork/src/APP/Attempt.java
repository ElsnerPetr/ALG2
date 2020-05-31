package APP;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.Scanner;

/**
 * Třída typu objekt, popisující "pokus" o splnění testu.
 *
 * @author Petr Elsner
 */
public class Attempt {

    //data:
    Scanner sc = new Scanner(System.in);
    private final String firstName;
    private final String lastName;
    private int points = 0;
    private int rank;
    private final LocalDate birthdate;
    private Test test;

    //konstruktor:
    public Attempt(String firstName, String lastName, LocalDate birthdate, Test test) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthdate = birthdate;
        this.test = test;

    }

    //settery
    public void setRank(int rank) {
        this.rank = rank;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    //gettery
    public String getFirstName() {
        return firstName;
    }

    public int getPoints() {
        return points;
    }

    public String getLastName() {
        return lastName;
    }

    public int getRank() {
        return rank;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    @Override
    public String toString() {
        return String.format("%-15s%-20s%-5d%-5d%-15s", firstName, lastName, points, rank, birthdate);
    }

    /**
     * Metoda porovnávající příjmení.
     */
    public static Comparator<Attempt> LastNameComparator = new Comparator<Attempt>() {

        public int compare(Attempt a1, Attempt a2) {

            String StudentName1 = a1.getLastName().toUpperCase();
            String StudentName2 = a2.getLastName().toUpperCase();

            return StudentName1.compareTo(StudentName2);

        }
    };

    /**
     * Metoda porovnávající známky.
     */
    public static Comparator<Attempt> RankComparator = new Comparator<Attempt>() {

        public int compare(Attempt a1, Attempt a2) {

            int rank1 = a1.getRank();
            int rank2 = a2.getRank();

            return rank1 - rank2;

        }
    };

    /**
     * Metoda, která spustí test.
     *
     * @return počet zíkaných bodů
     */
    public int makeAttempt() {

        for (Question question : this.test.getQuestions()) {
            System.out.println(question.getQuestionText());
            String answer = sc.nextLine();
            if (answer.equals(question.getRightAnswear())) {
                this.setPoints(this.getPoints() + question.getPoints());
            }
        }
        return this.getPoints();
    }

    /**
     * Metoda, přepočítající získané body na známku.
     * 
     * @param počet bodů
     * @return známka
     */
    public int calculateRank(int points) {
        int rank = 0;
        if (points <= 2) {
            rank = 5;
        } else if (points <= 4 && points > 2) {
            rank = 4;
        } else if (points <= 6 && points > 4) {
            rank = 3;
        } else if (points <= 8 && points > 6) {
            rank = 2;
        } else if (points <= 10 && points > 8) {
            rank = 1;
        }
        return rank;
    }

    /**
     * Metoda popisující výsledek pokusu.
     * 
     * @param známka
     * @return popisující věta
     */
    public String getFormatedAttempt(int rank) {

        StringBuilder sb = new StringBuilder("Dostal/a jsi známku: " + rank);
        String text = "";

        if (rank == 1) {
            text = "    Velmi dobře! Gratuluji! :)";
        } else if (rank < 5 && rank > 1) {
            text = "    Splněno, gratuluji.";
        } else if (rank == 5) {
            text = "    Bohužel se máš ještě co učit, nesplněno. :(";
        } else {
            System.out.println(" Neplatná známka.");
        }
        sb.append(text);
        return sb.toString();
    }

}
