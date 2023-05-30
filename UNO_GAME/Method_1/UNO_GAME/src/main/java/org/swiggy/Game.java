package org.swiggy;

import java.util.List;
import java.util.ArrayList;
import java.util.Random;

public class Game {
    private List<Player> players;
    private Deck deck;
    private List<Card> discardPile;
    private int currentPlayerIndex;
    private int direction;

    public Game(List<Player> players) {
        this.players = players;
        this.deck = new Deck();
        this.discardPile = new ArrayList<>();
        this.currentPlayerIndex = 0;
        this.direction = 1; // 1 for forward, -1 for reverse
    }

    public void start() {
        deck.initialize();
        distributeInitialCards();
        determineFirstPlayer();
    }

    private void distributeInitialCards() {
        for (Player player : players) {
            List<Card> initialCards = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                initialCards.add(deck.drawCard());
            }
            player.receiveInitialCards(initialCards);
        }
    }

    private void determineFirstPlayer() {
        Random random = new Random();
        currentPlayerIndex = random.nextInt(players.size());
    }

    public void playTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        System.out.println("Player " + currentPlayer.getName() + "'s turn.");
        System.out.println("Current card in play: " + discardPile.get(discardPile.size() - 1));

        if (currentPlayer.hasValidCards(discardPile.get(discardPile.size() - 1).getColor(), discardPile.get(discardPile.size() - 1).getValue())) {
            Card cardToPlay = currentPlayer.chooseCardToPlay(discardPile.get(discardPile.size() - 1).getColor(), discardPile.get(discardPile.size() - 1).getValue());
            validateAndApplyCardPlay(currentPlayer, cardToPlay);
        } else {
            System.out.println("Player " + currentPlayer.getName() + " has no valid cards to play.");
            currentPlayer.drawCard(deck);
            endTurn();
        }
    }

    private void validateAndApplyCardPlay(Player player, Card card) {
        Card currentCard = discardPile.get(discardPile.size() - 1);
        if (card.isPlayable(currentCard)) {
            discardPile.add(card);
            player.playCard(card.getValue());
            player.applySpecialCardEffect(card, this);

            if (card.isWildCard() || card.isWildDrawFourCard()) {
                String selectedColor = player.selectColor(); // Prompt the player to choose a color
                card.setColor(selectedColor);
                System.out.println("Player " + player.getName() + " selected color: " + selectedColor);
            }

            updateCurrentPlayer();
            endTurn();
        } else {
            System.out.println("Invalid card play. Try again.");
        }
    }

    private void updateCurrentPlayer() {
        currentPlayerIndex = (currentPlayerIndex + direction + players.size()) % players.size();
    }

    public void skipNextPlayer() {
        currentPlayerIndex = (currentPlayerIndex + direction + players.size()) % players.size();
    }

    public void reverseDirection() {
        direction *= -1;
    }

    public void drawCardsFromDeck(int count) {
        Player currentPlayer = players.get(currentPlayerIndex);
        for (int i = 0; i < count; i++) {
            currentPlayer.drawCard(deck);
        }
    }

    public boolean checkWinConditions() {
        Player currentPlayer = players.get(currentPlayerIndex);
        return currentPlayer.getHand().isEmpty();
    }

    public Player declareWinner() {
        Player currentPlayer = players.get(currentPlayerIndex);
        return currentPlayer;
    }
    private void endTurn() {
        Player currentPlayer = players.get(currentPlayerIndex);
        System.out.println("Player " + currentPlayer.getName() + " ends their turn.");
        System.out.println("------------------------");
    }
}
