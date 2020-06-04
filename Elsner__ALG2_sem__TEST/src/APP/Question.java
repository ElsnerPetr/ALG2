package APP;

/**
 * Třída typu objekt, popisující otázky obsažené v testu.
 *
 * @author Petr Elsner
 */
public class Question {

    //data
    private final String questionText;
    private final String rightAnswear;
    private String studentAnswear;
    private final int points;

    //kostruktor
    public Question(String questionText, String rightAnswear, int points) {
        this.questionText = questionText;
        this.rightAnswear = rightAnswear;
        this.points = points;

    }

    //gettery
    public int getPoints() {
        return points;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getRightAnswear() {
        return rightAnswear;
    }

    @Override
    public String toString() {
        return "[ " + questionText + ", správná odpověď: " + rightAnswear + ", body: " + points + " ] ";
    }

    /**
     * Metoda porovnávající odpověď žáka se správnou odpovědí na otázku.
     * 
     * @return pravdivost odpovědi
     */
    public boolean isAnswearCorrect() {
        boolean isCorrect = false;

        if (studentAnswear.equalsIgnoreCase(rightAnswear)) {
            isCorrect = true;
        }
        return isCorrect;
    }

}
