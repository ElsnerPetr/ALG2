package pkg08.elevengame;

import java.util.Scanner;

/**
 *
 * @author CryHeroCZ
 */
public class Main {

    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        Board board = Board.startGame();
        System.out.println("**************************** " + board.getName() + " ****************************");
        displayCards(board);
        System.out.println("************************************************************************");
        displayDeck(board);
        System.out.println("************************************************************************");
        while (board.isAnotherPlayPossible()) { // jeste jsou v balicku karty A existuje JQK nebo sum11
            System.out.println("Vyber karty:");
            String[] selectedCardsPositions = sc.nextLine().split(" +");//jedna nebo více mezer - regulární výraz
            if (!board.playAndReplace(selectedCardsPositions)) {
                System.out.println("Nevalidni tah");
            } else {
                System.out.println("**************************** " + board.getName() + " ****************************");
                displayCards(board);
                System.out.println("************************************************************************");
                displayDeck(board);
                System.out.println("************************************************************************");
            }
        }
        if (board.hasWon()) {
            System.out.println("Gratuluji - vyhrál jsi");
        } else {
            System.out.println("Smůla - prohrál jsi");
        }
    }

    private static void displayCards(BoardInterface board) {
        for (int i = 0; i < board.nCards(); i++) {
            System.out.format("|%1d| %20s ", i + 1, board.getCardDescriptionAt(i));
            if ((i + 1) % 3 == 0) {
                System.out.println("");
            }
        }
    }

    private static void displayDeck(BoardInterface board) {
        System.out.println("V balicku je " + board.getDeckSize() + " karet.");
    }

}
