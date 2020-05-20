package pkg08.elevengame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author CryHeroCZ
 */
public class Deck {

    private List<Card> deckCards;
    private int deckSize;

    public Deck(List<Card> deckCards, int deckSize) {
        this.deckCards = deckCards;
        this.deckSize = deckSize;
    }

    public static Deck makeDeck() {
        List<Card> deckCards = new ArrayList();
        deckCards.add(new Card("kary", "A", 1));
        deckCards.add(new Card("kary", "2", 2));
        deckCards.add(new Card("kary", "3", 3));
        deckCards.add(new Card("kary", "4", 4));
        deckCards.add(new Card("kary", "5", 5));
        deckCards.add(new Card("kary", "6", 6));
        deckCards.add(new Card("kary", "7", 7));
        deckCards.add(new Card("kary", "8", 8));
        deckCards.add(new Card("kary", "9", 9));
        deckCards.add(new Card("kary", "10", 10));
        deckCards.add(new Card("kary", "J", 0));
        deckCards.add(new Card("kary", "Q", 0));
        deckCards.add(new Card("kary", "K", 0));

        deckCards.add(new Card("piky", "A", 1));
        deckCards.add(new Card("piky", "2", 2));
        deckCards.add(new Card("piky", "3", 3));
        deckCards.add(new Card("piky", "4", 4));
        deckCards.add(new Card("piky", "5", 5));
        deckCards.add(new Card("piky", "6", 6));
        deckCards.add(new Card("piky", "7", 7));
        deckCards.add(new Card("piky", "8", 8));
        deckCards.add(new Card("piky", "9", 9));
        deckCards.add(new Card("piky", "10", 10));
        deckCards.add(new Card("piky", "J", 0));
        deckCards.add(new Card("piky", "Q", 0));
        deckCards.add(new Card("piky", "K", 0));

        deckCards.add(new Card("srdce", "A", 1));
        deckCards.add(new Card("srdce", "2", 2));
        deckCards.add(new Card("srdce", "3", 3));
        deckCards.add(new Card("srdce", "4", 4));
        deckCards.add(new Card("srdce", "5", 5));
        deckCards.add(new Card("srdce", "6", 6));
        deckCards.add(new Card("srdce", "7", 7));
        deckCards.add(new Card("srdce", "8", 8));
        deckCards.add(new Card("srdce", "9", 9));
        deckCards.add(new Card("srdce", "10", 10));
        deckCards.add(new Card("srdce", "J", 0));
        deckCards.add(new Card("srdce", "Q", 0));
        deckCards.add(new Card("srdce", "K", 0));

        deckCards.add(new Card("listy", "A", 1));
        deckCards.add(new Card("listy", "2", 2));
        deckCards.add(new Card("listy", "3", 3));
        deckCards.add(new Card("listy", "4", 4));
        deckCards.add(new Card("listy", "5", 5));
        deckCards.add(new Card("listy", "6", 6));
        deckCards.add(new Card("listy", "7", 7));
        deckCards.add(new Card("listy", "8", 8));
        deckCards.add(new Card("listy", "9", 9));
        deckCards.add(new Card("listy", "10", 10));
        deckCards.add(new Card("listy", "J", 0));
        deckCards.add(new Card("listy", "Q", 0));
        deckCards.add(new Card("listy", "K", 0));
        return new Deck(deckCards, deckCards.size());
    }

    public int getDeckSize() {
        return deckSize;
    }

    void shuffle() {
        Collections.shuffle(deckCards);
    }

    Card getCard() {
        Card card = deckCards.get(0);
        deckCards.remove(card);
        deckSize--;
        return card;
    }
}
