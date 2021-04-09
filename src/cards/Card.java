package cards;

public class Card {
    private String rank;
    private String suit;
    private int index;

    public Card(String rank, String suit, int index){
        if(isValidRank(rank)){
            this.rank = rank;
        }
        else{
            this.rank = "2";
        }

        if(isValidSuit(suit)){
            this.suit = suit;
        }
        else{
            this.suit = "clubs";
        }

        if(index >= 2 && index <= 14){
            this.index = index;
        }
    }

    private boolean isValidRank(String rank){
        return ((rank.charAt(0) >= '2' && rank.charAt(0) <= '9') ||
                (rank.length() == 2 && rank.equals("10")) ||
                rank.charAt(0) == 'J' || rank.charAt(0) == 'Q' || rank.charAt(0) == 'K' || rank.charAt(0) == 'A');
    }

    private boolean isValidSuit(String suit){
        return suit.equals("clubs") || suit.equals("diamonds") || suit.equals("hearts") || suit.equals("spades");
    }

    public boolean isStrongerThan(Card other){
        return this.index > other.index;
    }

    public String toString(){
        return this.rank + " of " + this.suit;
    }
}
