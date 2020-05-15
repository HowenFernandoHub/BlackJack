
public class Player extends Deck {

    String playerNum;
    int playerTotal = 0;

    public Player(String playerNum) {
        super(0);
        this.playerNum = playerNum;

    }


    boolean play() {
        BasicCard cardToPlay = pop();

        cardToPlay.printCard();

        if (cardToPlay.cardNum > 10) {
            return true;
        }

        return false;

    }

    @Override
    public boolean add(BasicCard basicCard) {
        playerTotal += basicCard.cardNum;
        return super.add(basicCard);
    }

    public String getPlayerNum() {
        return playerNum;
    }

}