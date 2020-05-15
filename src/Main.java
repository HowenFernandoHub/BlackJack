import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        System.out.println("Type 'START' to begin game");
        String userInput = scan.next();

        if (userInput.equalsIgnoreCase("start")) {
            boolean playing = true;

        // Main portion of game

            while (playing) {
                Deck mainDeck = new Deck();

                System.out.println("Enter Number of Players (between 1 and 3)");

                int numOfPlayers = scan.nextInt();      // Number of people that will play

                // Loop executes until numOfPlayers is 3 or less
                while (numOfPlayers > 3 || numOfPlayers < 1) {
                    System.out.println("Number of Players must be between 1 and 3\n" + "Enter Number of Players (between 1 and 3)");
                    numOfPlayers = scan.nextInt();
                }

                numOfPlayers += 1;      // Account for the Dealer

                Player[] playerList = new Player[numOfPlayers];     // Array holds Player objects


                // Fills playerList with players
                for (int i = 0; i < playerList.length; i++) {
                    playerList[i] = new Player("Player" + (i + 1));
                }

                // Last Player element of playerList is the Dealer
                playerList[numOfPlayers - 1] = new Player("Dealer");

                mainDeck.shuffle();

                deal(playerList, mainDeck);

                for (Player player : playerList) {
                    deal(player, mainDeck, new Scanner(System.in));
                }

                checkWinner(playerList);

                // Clear all hands and decks
                for (Player player : playerList) {
                    player.clearCards();
                }
                mainDeck.clearCards();

                System.out.println("Enter 'y' to play again and 'n' to exit game");
                String endingInput = scan.next();

                if (endingInput.equalsIgnoreCase("n")) {
                    System.out.println("Thanks for playing");
                    playing = false;
                }
            }

        }

        else if (userInput.equalsIgnoreCase("n")) {
            System.out.println("Bye then!");

        }

    }

    static void deal(Player[] playerList, Deck mainDeck) {
        for (Player currPlayer : playerList) {
            BasicCard currCard;
            currCard = mainDeck.draw();
            currCard.printCard();
            System.out.println(currPlayer.getPlayerNum() + "'s Card\n\n\n");

            if (currCard.visualCard.charAt(18) == 'A') {         // Checking if Card is an Ace
                aceCardProtocol((AceCard) currCard);
            }
            currPlayer.add(currCard);

        }
    }

    static void deal(Player currPlayer, Deck mainDeck, Scanner scan) {

        // Check that it is not the dealer's turn
        if (!currPlayer.playerNum.equalsIgnoreCase("dealer")) {
            System.out.println("\n\n" + currPlayer.getPlayerNum() + "'s Turn\n");

            BasicCard currCard;
            currCard = mainDeck.draw();     // Set currCard to card drawn from mainDeck
            currCard.printCard();
            currPlayer.printDeckMembers();



            currPlayer.add(currCard);       // Add card to player's hand

            System.out.println(currPlayer.getPlayerNum() + ", your cards total to: " + currPlayer.playerTotal);

            hitOrStay(currPlayer, mainDeck, scan);
        }

        else {
            System.out.println("Dealer's Turn");
            currPlayer.printDeckMembers();
            currPlayer.add(mainDeck.draw());     // Adding card to Dealer hand without showing
            checkIfBust(currPlayer);
            System.out.println(currPlayer.getPlayerNum() + ", your cards total to: " + currPlayer.playerTotal);
            if (currPlayer.playerTotal <= 16) {
                hitOrStay(currPlayer, mainDeck, "h");
            }
            else {
                hitOrStay(currPlayer, mainDeck, "s");
            }
        }

    }

    static void hitOrStay(Player currPlayer, Deck mainDeck, Scanner scan) {
        System.out.println("Would you like to hit or stay?\n" + "Enter 'h' for hit or 's' for stay, enter 'ace' to automatically receive an Ace");

        String userInput = scan.next();

        switch (userInput) {
            case "h":
                BasicCard currCard = mainDeck.draw();
                currCard.printCard();
                currPlayer.add(currCard);
                // This shows what your total is
                System.out.println(currPlayer.playerNum + "'s total is " + currPlayer.playerTotal);

                // Will break out of hitOrStay if this returns true;
                if (checkIfBust(currPlayer)) {
                    break;
                }
                // Recursive all will ask if they want to stay or hit again
                hitOrStay(currPlayer, mainDeck, scan);
                break;

            case "ace":
                AceCard cardToFind = (binarySearchForAce(mainDeck, 1));
                aceCardProtocol(cardToFind);
                currPlayer.add(cardToFind);
                System.out.println(currPlayer.playerNum + "'s total is " + currPlayer.playerTotal);
                // Will break out of hitOrStay if this returns true;
                if (checkIfBust(currPlayer)) {
                    break;
                }
                // Recursive all will ask if they want to stay or hit again
                hitOrStay(currPlayer, mainDeck, scan);
                break;

            default:
                System.out.println(currPlayer.playerNum + "'s total is " + currPlayer.playerTotal);
                System.out.println(currPlayer.playerNum + " has ended their turn\n\n");
                break;
        }
    }

    static void hitOrStay(Player currPlayer, Deck mainDeck, String hit) {
        System.out.println("Dealer stays if total >= 17 and hits if total < 17 ");

        switch (hit) {
            case "h":
                BasicCard currCard = mainDeck.draw();
                currCard.printCard();

                currPlayer.add(currCard);
                // This shows what your total is
                System.out.println(currPlayer.playerNum + "'s total is " + currPlayer.playerTotal);

                // Will break out of hitOrStay if this returns true;
                if (checkIfBust(currPlayer)) {
                    break;
                }
                else if (currPlayer.playerTotal <= 16) {
                    // Recursive all will ask if they want to stay or hit again
                    hitOrStay(currPlayer, mainDeck, hit);
                }
                break;

            default:
                System.out.println(currPlayer.playerNum + "'s total is " + currPlayer.playerTotal);
                System.out.println(currPlayer.playerNum + " has ended their turn\n\n");
                break;
        }
    }

    // Checking if went bust
    static boolean checkIfBust(Player currPlayer) {
        if (currPlayer.playerTotal >= 21) {
            System.out.println("Sorry " + currPlayer.playerNum + ", you went bust :/\n\n");
            return true;
        }
        return false;
    }


    static AceCard binarySearchForAce(Deck mainDeck, int aceNum) {
            mainDeck.mySort();

            int lowCardIndex = 0;
            int midCardIndex;
            int highCardIndex;

            highCardIndex = mainDeck.size() - 1;

            while (highCardIndex >= lowCardIndex) {
                midCardIndex = (highCardIndex + lowCardIndex) / 2;

                if (mainDeck.get(midCardIndex).cardNum < aceNum) {
                    lowCardIndex = midCardIndex + 1;
                }

                else if (mainDeck.get(midCardIndex).cardNum > aceNum) {
                    highCardIndex = midCardIndex - 1;
                }
                else {
                    return (AceCard) mainDeck.get(midCardIndex);
                }
            }

            return null;
    }

    static void aceCardProtocol(AceCard myAce) {

        System.out.println("Keep Value of your ace as " + myAce.cardNum + " or switch?\n" +
                            "Enter 'k' for keep and 's' for switch");
        Scanner scan = new Scanner(System.in);
        String userInput = scan.next();

        if (userInput.equalsIgnoreCase("s")) {
            myAce.switchCardNum();
        }

    }

    public static void checkWinner(Player[] playerList) {
        System.out.println("Dealer's score was: " + playerList[playerList.length- 1].playerTotal);
        System.out.println("Highest score was: ");

        Player winner = playerList[playerList.length - 1];

        for (Player currPlayer : playerList) {
            if (currPlayer.playerTotal > winner.playerTotal) {
                if (currPlayer.playerTotal <= 21) {
                    winner = currPlayer;
                }
            }
        }

        System.out.println(winner.playerNum);
    }

}
