import java.util.ArrayList;
import java.util.List;
public class Deck {
    List<Card> OGPile = new ArrayList<>();
    List<Card> drawPile = new ArrayList<>();
    List<Card> discardPile = new ArrayList<>();


    public Deck() {
        char[] suits = {'c','s','h','d'};
        int[] val= {2,3,4,5,6,7,8,9,10,10,10,10,11};
        char[] face =  {'j','q','k','a'};
        for (char suit : suits) {
            for (int i : val) {

                OGPile.add(new Card(i, suit));
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

    public List<Card> drawCard(int numeberOfDraw) {
        List<Card> drawnCards = new ArrayList<>();
        for (int i = 0; i < numeberOfDraw; i++) {
            Card y;
            int x = (int) (Math.random() * drawPile.size());
            discardPile.add(drawPile.get(x));
            y = drawPile.get(x);
            drawPile.remove(x);
            drawnCards.add(y);
        }
        return drawnCards;
    }


}
