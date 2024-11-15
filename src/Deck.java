import java.util.ArrayList;
import java.util.List;
public class Deck {
    List<Card> OGPile = new ArrayList<>();
    List<Card> drawPile = new ArrayList<>();
    List<Card> discardPile = new ArrayList<>();
    List<Card> handPile = new ArrayList<>();


    public Deck() {
        char[] suits = {'♣','♠','♦','♥'};
        int[] val= {2,3,4,5,6,7,8,9,10,110,210,310,511};
        char[] face =  {'J','q','k','a'};
        for (char suit : suits) {
            for (int i : val) {
                if (i == 110) {
                    OGPile.add(new Card(10, suit,'J'));
                } else if (i == 210) {
                    OGPile.add(new Card(10, suit,'Q'));
                } else if (i == 310) {
                    OGPile.add(new Card(10, suit,'K'));
                } else if (i == 511) {
                    OGPile.add(new Card(11, suit,'A'));
                } else {
                    OGPile.add(new Card(i, suit,'n'));
                }
            }
            ;
        }
        ;
        drawPile = OGPile;
    }

    public void Shuffle(int numberOfShuffle) {
        drawPile = OGPile;
        discardPile = new ArrayList<>();
        for (int i = 0; i < numberOfShuffle; i++) {
            int x = (int) (Math.random() * drawPile.size());
            Card shuffleCard = drawPile.get(x);
            drawPile.remove(x);
            drawPile.add(shuffleCard);
        }
    }

    public List<Card> drawCards(int numeberOfDraw) {
        handPile = new ArrayList<>();
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < numeberOfDraw; i++) {
            Card y;
            int x = (int) (Math.random() * drawPile.size());
            discardPile.add(drawPile.get(x));
            y = drawPile.get(x);
            drawPile.remove(x);
            drawnCards.add(y);
        }
        handPile.addAll(drawnCards);

        return drawnCards;
    }
    public Card drawCard() {
        handPile = new ArrayList<>();
        Card y;
        int x = (int) (Math.random() * drawPile.size());
        discardPile.add(drawPile.get(x));
        y = drawPile.get(x);
        drawPile.remove(x);
        handPile.add(y);
        return y;
    }


}
