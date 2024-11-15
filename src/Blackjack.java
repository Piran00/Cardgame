import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Blackjack {
    List<Card> dealerHand;
    List<Card> playerHand;
    int playerHandValue;
    int dealerHandValue;
    int betAmount;
    Deck deck;
    Scanner scanner = new Scanner(System.in);
    String output;


    public Blackjack(int betAmount) {
        this.betAmount = betAmount;
        dealerHand = new ArrayList<>();
        playerHand = new ArrayList<>();


        deck = new Deck();
    }

    public void gameStart() {
        dealerHand.addAll(deck.drawCards(2));
        dealerHandValue = countCards(dealerHand);
        playerHand.addAll(deck.drawCards(2));
        playerHandValue = countCards(playerHand);
        gameTurn();
    }
    public int countCards(List<Card> hand) {
        int x = 0;
        for(Card card : hand) {
            x += card.val;
        }
        return x;
    }

    public void gameTurn() {
        clearScreen();
        System.out.println("Your hand");
        printPlayerHand();
        System.out.println("Dealer hand");
        dealerHand.get(0).DisplayCard();

        System.out.println("Stand(s) or hit(h)");


        output = scanner.nextLine();


        if (Objects.equals(output, "h")) {
            playerHand.add( deck.drawCard());

            updateVals();

            printPlayerHand();
            if( playerHandValue > 21) {
                clearScreen();
                printPlayerHand();
                System.out.println("You loose");
                System.exit(0);
            }
            else {
                gameTurn();
            }
        } else if (Objects.equals(output, "s")) {
            endGameTurn();
        }

    }

    public void updateVals() {
        dealerHandValue = countCards(dealerHand);
        playerHandValue = countCards(playerHand);

    }
    public void printPlayerHand() {
        for (Card card : playerHand) {
            card.DisplayCard();
        }
    }


    public void endGameTurn() {

    }



    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                // Use ANSI escape codes for Unix-like systems
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }



}
