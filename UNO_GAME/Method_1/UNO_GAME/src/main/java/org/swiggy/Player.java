package org.swiggy;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Player {
    private int id;
    private String name;
    private List<Card> hand;

    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.hand = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Card> getHand() {
        return hand;
    }

    public void receiveInitialCards(List<Card> initialCards) {
        hand.addAll(initialCards);
    }

    public Card drawCard(Deck deck) {
        Card card = deck.drawCard();
        hand.add(card);
        return card;
    }

    public Card playCard(int index) {
        return hand.remove(index);
    }

    public void applySpecialCardEffect(Card card, Game game) {
        if (card.isSkipCard()) {
            game.skipNextPlayer();
        } else if (card.isReverseCard()) {
            game.reverseDirection();
        } else if (card.isDrawTwoCard()) {
            game.skipNextPlayer();
            game.drawCardsFromDeck(2);
        } else if (card.isWildCard() || card.isWildDrawFourCard()) {
            if (card.isWildCard()) {
                String selectedColor = selectColor();
                card.setColor(selectedColor);
                System.out.println("Player " + getName() + " selected color: " + selectedColor);
            } else if (card.isWildDrawFourCard()) {
                String selectedColor = selectColor();
                card.setColor(selectedColor);
                System.out.println("Player " + getName() + " selected color: " + selectedColor);
                game.skipNextPlayer();
                game.drawCardsFromDeck(4);
            }
        }
    }

    public boolean hasValidCards(Color currentColor, int currentValue) {
        for (Card card : hand) {
            if (card.getColor() == currentColor || card.getValue() == currentValue || card.isWildCard() || card.isWildDrawFourCard()) {
                return true;
            }
        }
        return false;
    }

    protected String selectColor() {
        Color[] colors = Color.values();
        Random random = new Random();
        int index = random.nextInt(colors.length);
        return colors[index].toString();
    }

    public Card chooseCardToPlay(Color currentColor, int currentValue) {
        List<Card> validCards = new ArrayList<>();

        for (Card card : hand) {
            if (card.getColor() == currentColor || card.getValue() == currentValue || card.isWildCard() || card.isWildDrawFourCard()) {
                validCards.add(card);
            }
        }

        if (!validCards.isEmpty()) {
            // Prompt the player to choose a card from the valid cards
            // Here, we'll simply choose the first valid card in the list
            return validCards.get(0);
        } else {
            // No valid cards found, return null to indicate inability to play a card
            return null;
        }
    }

}
