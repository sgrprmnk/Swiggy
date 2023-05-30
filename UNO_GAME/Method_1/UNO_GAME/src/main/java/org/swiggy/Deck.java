package org.swiggy;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Deck {
    private List<Card> cards;
    private List<Card> discardPile;

    public Deck() {
        this.cards = new ArrayList<>();
        this.discardPile = new ArrayList<>();
    }

    public void initialize() {
        for (Color color : Color.values()) {
            if (color != null) {
                for (int value = 0; value <= 9; value++) {
                    cards.add(new Card(color, value));
                    cards.add(new Card(color, value));
                }
            }
        }
        // Add special action cards
        for (int i = 0; i < 4; i++) {
            cards.add(new Card(null, 10));
        }
        shuffle();
    }

    public void shuffle() {
        Random random = new Random();
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Card temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            replenish();
        }
        Card card = cards.remove(cards.size() - 1);
        discardPile.add(card);
        return card;
    }

    private void replenish() {
        Card topCard = discardPile.remove(discardPile.size() - 1);
        cards.addAll(discardPile);
        discardPile.clear();
        discardPile.add(topCard);
        shuffle();
    }
}
