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
    int loseCounter;
    int winCounter;
    public Blackjack(int betAmount) {
        this.betAmount = betAmount;
        dealerHand = new ArrayList<>();
        playerHand = new ArrayList<>();
        placeholderCard = new Card(0,'?','n');
        turnTimer = 0;
        deck = new Deck();
        hasDoubled = false;
        loseCounter = 0;
        winCounter =0;
    }

    public void gameStart() {
        hasDoubled = false;
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
            String message = String.format("(s)tand or (h)it or (d)ouble\n Games won: %d    Games lost: %d",winCounter,loseCounter);
            System.out.println(message);

        }

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
                        loseSession();                }
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
                        loseSession();
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
            winSession();        }
        else if(playerHandValue - Ace_check(playerHand) <= 21 && playerHandValue - Ace_check(playerHand) > trueDealerHandValue) {
            System.out.println("You win");
            winSession();
        }
        else {
            System.out.println("You loose");
            loseSession();
        }
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
        return u * 10;

    }

    public void dealerTurn(){

        updateVals();
       if (dealerHandValue <= 17){
           dealerHand.add(deck.drawCard());
       }
       updateVals();
       if (dealerHandValue > 21) {
           trueDealerHandValue = dealerHandValue - Ace_check(dealerHand);
       }
       else {
           trueDealerHandValue = dealerHandValue;
       }
       if (trueDealerHandValue <= 17){
           dealerTurn();
       }

    }

    public List<Card> discardHand(List<Card> handToDiscard){
        handToDiscard = new ArrayList<>();
        return handToDiscard;

    }

    public void endSession(){
        updateVals();
        clearScreen();
        print_gamehands(true);
        w8();
        clearScreen();
       playerHand = discardHand(playerHand);
       dealerHand = discardHand(dealerHand);
        updateVals();
        turnTimer = 0;

        gameStart();

    }

    public void winSession(){
        winCounter ++;
        endSession();
    }

    public void loseSession(){
        loseCounter ++;
        endSession();
    }

    public void w8(){
        try {
            Thread.sleep(5000); // Pause for 3000 milliseconds (3 seconds)
        } catch (InterruptedException e) {
            e.printStackTrace(); // Handle any interruption exceptions
        }
    }




}
