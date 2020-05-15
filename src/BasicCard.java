public abstract class BasicCard {

    int cardNum;
    String cardSuit;
    String suitSymbol;
    String visualCard;

    /**
     * Constructor
     * @param cardSuit - private field assigned this suit
     * @param cardNum - private field assigned this value
     */
    BasicCard(String cardSuit, int cardNum) {
        this.cardSuit = cardSuit;
        this.cardNum = cardNum;

        convertToSymbol(cardSuit);
        createVisualCard(suitSymbol, this.cardNum);

    }

    BasicCard() {
        cardNum = 0;
        cardSuit = "";
    }

    private void convertToSymbol(String cardSuit) {
        switch (cardSuit) {
            case "Spades":
                suitSymbol = "♠";
                break;
            case "Clubs":
                suitSymbol = "♣";
                break;
            case "Hearts":
                suitSymbol = "♥";
                break;
            case "Diamonds":
                suitSymbol = "♦";
                break;
            default:
                System.out.println("Not a valid Suit");
                break;
        }
    }

    void createVisualCard(String suitSymbol, int cardNum) {
        if (cardNum != 10) {
            visualCard = " _____________\n" +
                    "|  " + cardNum + "          |\n" +
                    "|             |\n" +
                    "|             |\n" +
                    "|      " + suitSymbol + "      |\n" +
                    "|             |\n" +
                    "|             |\n" +
                    "|          " + cardNum + "  |\n" +
                    "[_____________]\n";
        }
        else {
            visualCard = " _____________\n" +
                    "|  " + cardNum + "         |\n" +
                    "|             |\n" +
                    "|             |\n" +
                    "|      " + suitSymbol + "      |\n" +
                    "|             |\n" +
                    "|             |\n" +
                    "|         " + cardNum + "  |\n" +
                    "[_____________]\n";
        }
    }

    abstract void printCard();


}
