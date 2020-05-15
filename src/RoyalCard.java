public class RoyalCard extends BasicCard {


    char royalCardVal;
    int cardIndicator;

    public RoyalCard(String cardSuit, int cardNum) {
        super(cardSuit, cardNum);
        this.cardNum = 10;
        cardIndicator = cardNum;

        createVisualCard(suitSymbol, cardIndicator);
    }

    @Override
    void printCard() {
        System.out.println(visualCard);
    }

    @Override
    void createVisualCard(String suitSymbol, int cardNum) {
        switch (cardNum) {
            case 11:
                royalCardVal = 'J';
                break;
            case 12:
                royalCardVal = 'Q';
                break;
            case 13:
                royalCardVal = 'K';
                break;
            case 14:
                royalCardVal = 'A';
                break;
            default:
                break;
        }
        visualCard = " _____________\n" +
                "|  " +  royalCardVal + "          |\n" +
                "|             |\n" +
                "|             |\n" +
                "|      " +  suitSymbol + "      |\n" +
                "|             |\n" +
                "|             |\n" +
                "|          " +  royalCardVal + "  |\n" +
                "[_____________]\n";

    }


}
