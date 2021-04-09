package main;

import cards.Card;
import cards.Deck;
import players.Player;
import utils.Constants;

import java.util.Random;
import java.util.Scanner;

public class Game {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the name of the first player: ");
        String name1 = sc.nextLine();
        System.out.print("Enter the name of the second player: ");
        String name2 = sc.nextLine();
        Player player1 = new Player(name1);
        Player player2 = new Player(name2);

        play(player1, player2);
    }

    private static void play(Player player1, Player player2){
        Deck initialDeck = new Deck();

        Random r = new Random();
        if(r.nextBoolean()) {
            System.out.println(player1.getName() + " deals the cards.");
            player1.dealCards(initialDeck, player2);
        }
        else {
            System.out.println(player2.getName() + " deals the cards.");
            player2.dealCards(initialDeck, player1);
        }

        Card[] cardsToWin = new Card[Constants.MAIN_DECK_CAPACITY];
        int crrIdxToInsert = 0;
        Card player1Card;
        Card player2Card;

        while(player1.hasCards() && player2.hasCards()){
            player1Card = player1.showCard(cardsToWin, crrIdxToInsert++);
            player2Card = player2.showCard(cardsToWin, crrIdxToInsert++);
            checkResult(player1, player2, player1Card, player2Card, cardsToWin, crrIdxToInsert);
            cardsToWin = new Card[Constants.MAIN_DECK_CAPACITY];
            crrIdxToInsert = 0;
        }
    }

    private static void war(Player player1, Player player2, Card[] cardsToWin, int crrIdxToInsert){
        System.out.println(player1.getName() + "'s card and " + player2.getName() + "'s card are with the same" +
                " strength. WAR!");
        Card player1Card = null;
        Card player2Card = null;

        for (int i = 0; i < 3 && player1.hasCards(); i++) {
            player1Card = player1.showCard(cardsToWin, crrIdxToInsert++);
        }

        if(!player1.hasCards()){
            System.out.println(player1.getName() + " has no more cards. Showing result:");
            checkResult(player1, player2, player1Card, player2Card, cardsToWin, crrIdxToInsert);
            return;
        }

        for (int i = 0; i < 3 && player2.hasCards(); i++) {
            player2Card = player2.showCard(cardsToWin, crrIdxToInsert++);
        }

        if(!player2.hasCards()){
            System.out.println(player2.getName() + " has no more cards. Showing result:");
            checkResult(player1, player2, player1Card, player2Card, cardsToWin, crrIdxToInsert);
            return;
        }

        if(player1.hasCards() && player2.hasCards()) {
            checkResult(player1, player2, player1Card, player2Card, cardsToWin, crrIdxToInsert);
        }
    }

    private static void checkResult(Player player1, Player player2, Card player1Card, Card player2Card,
                                          Card[] cardsToWin, int crrIdxToInsert){
        if(!player1.hasCards() || !player2.hasCards()){
            if(!player1.hasCards()){
                System.out.println(player1.getName() + " has no more cards. Showing result:");
            }
            else{
                System.out.println(player2.getName() + " has no more cards. Showing result:");
            }

            showWinner(player1, player2);
        }
        else {
            if (player1Card.isStrongerThan(player2Card)) {
                System.out.println(player1.getName() + "'s card is stronger than " + player2.getName() + "'s card. " +
                        player1.getName() + " wins the cards.");

                player1.winCards(cardsToWin);
            }
            else if (player2Card.isStrongerThan(player1Card)) {
                System.out.println(player2.getName() + "'s card is stronger than " + player1.getName() + "'s card. " +
                        player2.getName() + " wins the cards.");

                player2.winCards(cardsToWin);
            }
            else {
                war(player1, player2, cardsToWin, crrIdxToInsert);
            }
        }
    }

    private static void showWinner(Player player1, Player player2) {
        System.out.println("Game over!");
        System.out.println(player1.getName() + " has won " + player1.getNumberOfWonCards()  + " cards.");
        System.out.println(player2.getName() + " has won " + player2.getNumberOfWonCards()  + " cards.");

        if(player1.getNumberOfWonCards() > player2.getNumberOfWonCards()){
            System.out.println("THE WINNER IS: " + player1.getName());
        }
        else if(player2.getNumberOfWonCards() > player1.getNumberOfWonCards()){
            System.out.println("THE WINNER IS: " + player2.getName());
        }
        else{
            System.out.println("Both " + player1.getName() + " and " + player2.getName() + " have won "
                               + player1.getNumberOfWonCards() + " cards.");
        }
    }
}