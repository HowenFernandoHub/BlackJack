import java.util.LinkedList;
import java.util.Random;

public class Deck extends LinkedList<BasicCard> implements DeckOperations {


    /**
     * Constructor: Calls populate and adds 52 cards to Deck
     */
    public Deck() {
        populate();
    }

    public Deck(int size) {

        for (int i = 0; i < size; i++) {
            add(new NumberCard());
        }

    }

    @Override
    public void populate() {

        for (int i = 0; i < 4; i++) {

            String newCardSuit;
            int[] possibleCardNum = {2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14};

            switch (i) {
                case 0:
                    newCardSuit = "Spades";
                    break;
                case 1:
                    newCardSuit = "Clubs";
                    break;
                case 2:
                    newCardSuit = "Hearts";
                    break;
                case 3:
                    newCardSuit = "Diamonds";
                    break;
                default:
                    newCardSuit = "";
                    break;
            }

            for (int currVal : possibleCardNum) {

                if (currVal <= 10) {
                    add(new NumberCard(newCardSuit, currVal));
                }
                else if ((currVal > 10) && (currVal < 14)) {
                    add(new RoyalCard(newCardSuit, currVal));
                }
                else if (currVal == 14) {
                    add(new AceCard(newCardSuit, currVal));
                }

            }
        }
    }

    @Override
    public BasicCard draw() {
        return pop();
    }

    @Override
    public void clearCards() {
        clear();
    }

    public void printDeckMembers() {

        for (int i = 0; i < size(); i++) {
            get(i).printCard();
        }

    }

    public Deck mySort() {

        Deck sortedCopy = this;     // Create copy that we will sort so that we don't change original Deck

        for (int i = 0; i < sortedCopy.size() - 1; i++) {

            int indexOfMin = i;     // index to be selected

            for (int j = i + 1; j < sortedCopy.size(); j++) {
                if (sortedCopy.get(j).cardNum < sortedCopy.get(indexOfMin).cardNum) {
                    indexOfMin = j;     //sets new index of min
                }
            }

            // Next perform swap on selected element
            BasicCard temp = sortedCopy.get(i);
            sortedCopy.set(i, sortedCopy.get(indexOfMin));
            sortedCopy.set(indexOfMin, temp);
        }
        return sortedCopy;
    }




    @Override
    public void shuffle() {
        Random rand = new Random();

        for (int i = 0; i < size(); i++) {

            int indexToSwap = rand.nextInt(size());

            // Swap cards
            BasicCard tempCard = get(indexToSwap);
            set(indexToSwap, get(i));
            set(i, tempCard);

        }
    }

}
