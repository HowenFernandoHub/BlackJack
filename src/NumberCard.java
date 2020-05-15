public class NumberCard extends BasicCard {

    public NumberCard() {
        super();
    }

    public NumberCard(String cardSuit, int cardNum) {
        super(cardSuit, cardNum);
    }

    @Override
    void printCard() {
        System.out.println(visualCard);
    }


}
