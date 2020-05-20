package pkg08.elevengame;

/**
 *
 * @author CryHeroCZ
 */
public class Card {

    private final String symbol;   //v aj "suit"
    private final String value;   //v aj "rank"
    private final int nPoints;  //A-1, J-0, Q-0, K-0   //klidně i byte- málo hodnot

    public Card(String symbol, String value, int nPoints) {
        this.symbol = symbol;
        this.value = value;
        this.nPoints = nPoints;
    }

    public String getSymbol() {
        return symbol;
    }

    public String getValue() {
        return value;
    }

    public int getnPoints() {
        return nPoints;
    }

}
