
import java.util.List;
public class Main {
    public static void main(String[] args) {
    Deck deck = new Deck();
    deck.Shuffle(52);
    List<Card> hand = deck.drawCard(2);
    System.out.println( hand.get(1).suit);
        }
    }
