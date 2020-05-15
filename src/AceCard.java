public class AceCard extends RoyalCard {

    public AceCard(String cardSuit, int cardNum) {
        super(cardSuit, cardNum);
        this.cardNum = 1;
    }

    void switchCardNum() {
        if (this.cardNum == 1) {
            this.cardNum = 11;
        }
        else {
            this.cardNum = 1;
        }
    }
}
