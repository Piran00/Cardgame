import java.util.ArrayList;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class Blackjack {
    List<Card> dealerHand;
    Card placeholderCard;
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
        placeholderCard = new Card(0,'?','n');

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
        print_gamehands(false);
        System.out.println("Stand(s) or hit(h)");


        output = scanner.nextLine();


        if (Objects.equals(output, "h")) {
            playerHand.add( deck.drawCard());

            print_gamehands(false);
            if( playerHandValue > 21) {
              if(playerHandValue - (10 * Ace_check(playerHand)) <= 21) {
                  endGameTurn();
              }
              else {
                  print_gamehands(true);
                  System.out.println("You loose");
                  System.exit(0);
              }
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
    public void printPlayerHand(List<Card> hand) {
        for (Card card : hand) {
            card.DisplayCard();
        }
    }


    public void endGameTurn() {
        clearScreen();
        updateVals();
        System.out.println("Your hand");
        printPlayerHand(playerHand);
        System.out.println("___________________");
        System.out.println("Dealer hand");
        printPlayerHand(dealerHand);

        if(playerHandValue - (10 * Ace_check(playerHand)) <= 21 && playerHandValue - (10 * Ace_check(playerHand)) > dealerHandValue) {
            System.out.println("You win");
        }
        else {
            System.out.println("You loose");
        }
        clearScreen();
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

    public void print_gamehands(boolean show_dealer) {
        clearScreen();
        updateVals();
        System.out.println("Your hand");
        printPlayerHand(playerHand);
        System.out.println("Dealer hand");
        if (show_dealer) {
        printPlayerHand(dealerHand);
        }
        else {
            dealerHand.get(0).DisplayCard();
            placeholderCard.DisplayCard();
        }
    }

    public int Ace_check(List<Card> check_hand) {
        int u = 0;
        for (Card i : check_hand) {
           if (i.val == 11) {
                u ++;
            }
        }
        return u;

    }



}
