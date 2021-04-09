package cards;

import utils.Constants;

import java.util.Random;

public class Deck {
    private Card[] cards = new Card[Constants.MAIN_DECK_CAPACITY];

    private int dealtCards;

    public Deck() {
        this.dealtCards = 0;
        this.fillDeck();
    }

    public Card getCrrCard(){
        Card card = cards[dealtCards];
        this.cards[dealtCards] = null;
        this.dealtCards++;
        return card;
    }

    private void fillDeck() {
        //CREATES SHUFFLED DECK

        Random r = new Random();

        for (int suitNum = 0; suitNum < 4; suitNum++) {
            String crrSuit = getSuit(suitNum);
            String crrRank = "";
            for (int rankNum = 2; rankNum <= 14; rankNum++) {
                if (rankNum >= 2 && rankNum <= 10) {
                    crrRank = String.valueOf(rankNum);
                } else {
                    if (rankNum == 11) {
                        crrRank = "J";
                    } else if (rankNum == 12) {
                        crrRank = "Q";
                    } else if (rankNum == 13) {
                        crrRank = "K";
                    } else {
                        crrRank = "A";
                    }
                }

                Card crrCard = new Card(crrRank, crrSuit, rankNum);
                int crrRandomPos = r.nextInt(Constants.MAIN_DECK_CAPACITY);

                while (cards[crrRandomPos] != null) {
                    crrRandomPos = r.nextInt(Constants.MAIN_DECK_CAPACITY);
                }

                this.cards[crrRandomPos] = crrCard;
            }
        }
    }

    private String getSuit(int suitNum){
        if(suitNum == 0){
            return "clubs";
        }
        if(suitNum == 1){
            return "diamonds";
        }

        if(suitNum == 2){
            return "hearts";
        }

        return "spades";
    }
}