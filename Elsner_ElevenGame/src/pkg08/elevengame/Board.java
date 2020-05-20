package pkg08.elevengame;

import static java.lang.Integer.parseInt;
import java.util.Collections;

/**
 *
 * @author CryHeroCZ
 */
public class Board implements BoardInterface {

    Deck deck; //balicek karet
    Card[] cards; //9 karet na stole

    public Board(Deck deck, Card[] cards) {
        this.deck = deck;
        this.cards = cards;
    }

    @Override
    public String getName() {
        return "Hra jedenactka";
    }

    @Override
    public int nCards() {
        return cards.length;
    }

    @Override
    public int getDeckSize() {
        return deck.getDeckSize();
    }

    @Override
    public String getCardDescriptionAt(int index) {
        return cards[index].getSymbol() + "  " + cards[index].getValue() + "  ";
    }

    @Override
    public boolean isAnotherPlayPossible() {//je na stole JQK nebo soucet 11 A jsou ještě v balicku karty?
        return is11() || isJQK();
    }

    @Override
    public boolean playAndReplace(String[] selectedCardsPositions) {//hra..je 11? souhlasí, líznout další atd.
        //read
        if (selectedCardsPositions.length > 3 || selectedCardsPositions.length < 2) {
            return false;
        }
        if (selectedCardsPositions.length == 3) {
            boolean isJ = false;
            boolean isQ = false;
            boolean isK = false;
            for (int i = 0; i < selectedCardsPositions.length; i++) {
                if ("J".equals(cards[parseInt(selectedCardsPositions[i]) - 1].getValue())) {
                    isJ = true;
                }
                if ("Q".equals(cards[parseInt(selectedCardsPositions[i]) - 1].getValue())) {
                    isQ = true;
                }
                if ("K".equals(cards[parseInt(selectedCardsPositions[i]) - 1].getValue())) {
                    isK = true;
                }
                if (isJ && isQ && isK) {
                    moveCards(selectedCardsPositions);
                    return true;
                }
            }
            return false;
        }
        if (selectedCardsPositions.length == 2) {
            if (cards[parseInt(selectedCardsPositions[0]) - 1].getnPoints() + cards[parseInt(selectedCardsPositions[1]) - 1].getnPoints() != 11) {
                return false;
            }
            moveCards(selectedCardsPositions);
            return true;
        }
        return false;

    }

    private void moveCards(String[] selectedCardsPositions) {
        for (String selectedCardsPosition : selectedCardsPositions) {
            if (deck.getDeckSize() > 0) {
                cards[parseInt(selectedCardsPosition) - 1] = deck.getCard();
            } else {
                cards[parseInt(selectedCardsPosition) - 1] = new Card("", "", 0);
            }
        }
    }

    @Override
    public boolean hasWon() {
        return deck.getDeckSize() == 0 && cards.length == 0;
    }

    private boolean is11() {
        for (int i = 0; i < cards.length - 1; i++) {
            for (int j = i; j < cards.length; j++) {
                if (cards[i].getnPoints() != 0 && cards[j].getnPoints() != 0) {
                    int soucet = cards[i].getnPoints() + cards[j].getnPoints();
                    if (soucet == 11) {
                        return true;
                    }
                }

            }
        }
        return false;
    }

    private boolean isJQK() {
        boolean isJ = false;
        boolean isQ = false;
        boolean isK = false;
        for (Card card : cards) {
            if ("J".equals(card.getValue())) {
                isJ = true;
            }
            if ("Q".equals(card.getValue())) {
                isQ = true;
            }
            if ("K".equals(card.getValue())) {
                isK = true;
            }
            if (isJ && isQ && isK) {
                return true;
            }
        }
        return false;
    }

    public static Board startGame() {
        Deck deck = Deck.makeDeck();
        deck.shuffle();
        Card[] cards = new Card[9];
        for (int i = 0; i < cards.length; i++) {
            cards[i] = deck.getCard();
        }
        return new Board(deck, cards);
    }
}
