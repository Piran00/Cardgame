public class Card {
    char suit;
    int val;
    char face;

    public Card(int val, char suit,char face) {
        this.val = val;
        this.suit = suit;
        this.face = face;

    }
    public String DisplayCard()
    {
        String message;
        if (face != 'n') {
            message = String.format(
                    " _____ \n" +
                    "|%c    |\n" +
                    "|  %c  |\n" +
                    "|    %c|\n" +
                    " -----\n", face, suit, face);
        }
        else {
            message = String.format(
                    " _____ \n" +
                    "|%d    |\n" +
                    "|  %c  |\n" +
                    "|    %d|\n" +
                    " -----\n", val, suit, val);
        }
        System.out.println(message);
        return message;
    }
}
//c s h d