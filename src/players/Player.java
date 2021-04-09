package players;

import cards.Card;
import cards.Deck;
import utils.Constants;

public class Player {
    private String name;
    private Card[] playerCards;
    private Card[] wonCards;
    private int crrShownCardIdx;
    private int crrWonCardIdx;

    public Player(String name){
        this.name = name;
        this.playerCards = new Card[Constants.PLAYER_DECK_CAPACITY];
        this.wonCards = new Card[Constants.MAIN_DECK_CAPACITY];
        this.crrWonCardIdx = 0;
        this.crrShownCardIdx = 0;
    }

    public String getName(){
        return this.name;
    }

    public Card showCard(Card[] cardsToWin, int crrIdxToInsert){
        Card crrCard = this.playerCards[crrShownCardIdx];
        this.playerCards[crrShownCardIdx] = null;
        crrShownCardIdx++;
        cardsToWin[crrIdxToInsert] = crrCard;
        System.out.println(this.name + "'s card is " + crrCard.toString());
        return crrCard;
    }

    public void dealCards(Deck mainDeck, Player other){
        int pos1 = 0, pos2 = 0;
        for (int i = 0; i < Constants.MAIN_DECK_CAPACITY; i++) {
            Card crrCard = mainDeck.getCrrCard();
            if(i%2 == 0){
                other.playerCards[pos1++] = crrCard;
            }
            else{
                this.playerCards[pos2++] = crrCard;
            }
        }
    }

    public void winCards(Card[] cards){
        for (int i = 0; i < cards.length && cards[i] != null; i++) {
            this.wonCards[this.crrWonCardIdx] = cards[i];
            this.crrWonCardIdx++;
        }
    }

    public boolean hasCards(){
        return crrShownCardIdx < Constants.PLAYER_DECK_CAPACITY;
    }

    public int getNumberOfWonCards(){
        int count = 0;
        for (int i = 0; i < wonCards.length && wonCards[i] != null; i++) {
            count++;
        }
        return count;
    }
}