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
    int turnTimer;
    boolean hasDoubled;
    int trueDealerHandValue;

    public Blackjack(int betAmount) {
        this.betAmount = betAmount;
        dealerHand = new ArrayList<>();
        playerHand = new ArrayList<>();
        placeholderCard = new Card(0,'?','n');
        turnTimer = 0;
        deck = new Deck();
        hasDoubled = false;
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
        if (turnTimer == 0) {
            System.out.println("(s)tand or (h)it or (d)ouble");

        }
        System.out.println("Stand(s) or hit(h)");


        output = scanner.nextLine();

        turnTimer++;
        switch (output) {
            case "h" :
                if (! hasDoubled){
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
                }
                else {
                    System.out.println("You have already doubled so you cant hit");
                }
                break;
            case "s" :
                endGameTurn();
                break;
            case "d" :
                betAmount = betAmount *2;
                hasDoubled = true;
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
                    endGameTurn();
                }
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
        System.out.println("Dealer playing...");
        dealerTurn();
        updateVals();
        clearScreen();
        updateVals();
        System.out.println("Your hand");
        printPlayerHand(playerHand);
        System.out.println("___________________");
        System.out.println("Dealer hand");
        printPlayerHand(dealerHand);
        if (trueDealerHandValue > 21){
            System.out.println("You win");
        }
        if(playerHandValue - (10 * Ace_check(playerHand)) <= 21 && playerHandValue - (10 * Ace_check(playerHand)) > trueDealerHandValue) {
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

    public void dealerTurn(){

        updateVals();
       if (dealerHandValue <= 17){
           dealerHand.add(deck.drawCard());
       }
       updateVals();
       if (dealerHandValue > 21) {
           trueDealerHandValue = dealerHandValue - (10 * Ace_check(dealerHand));
       }
       else {
           trueDealerHandValue = dealerHandValue;
       }
       if (trueDealerHandValue <= 17){
           dealerTurn();
       }

    }



}
