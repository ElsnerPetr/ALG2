package APP;

import java.util.ArrayList;

/**
 * Třída typu objekt, skládající se z otázek.
 * 
 * @author Petr Elsner
 */
public class Test {
    //data:
    private final int number;
    private ArrayList<Question> questions = new ArrayList<>();

    //konstruktor
    public Test(int number, ArrayList<Question> questions) {
        this.number = number;
        this.questions = questions;
    }

    //getter
    public ArrayList<Question> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        System.out.println("Test: " + number);
        return "Otázky: " + questions;
    }

}
