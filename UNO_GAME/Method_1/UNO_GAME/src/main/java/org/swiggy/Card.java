package org.swiggy;

import java.util.Random;

public class Card {
    private Color color;
    private int value;

    public Card(Color color, int value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = Color.valueOf(color.toUpperCase());
    }


    public int getValue() {
        return value;
    }

    public boolean isSpecialActionCard() {
        return value >= 10; // Special action cards have a value of 10 or greater
    }

    public boolean isSkipCard() {
        return isSpecialActionCard() && value == 10;
    }

    public boolean isReverseCard() {
        return isSpecialActionCard() && value == 11;
    }

    public boolean isDrawTwoCard() {
        return isSpecialActionCard() && value == 12;
    }

    public boolean isWildCard() {
        return value == 13;
    }

    public boolean isWildDrawFourCard() {
        return value == 14;
    }

    public void performAction(Game game) {
        if (isSkipCard()) {
            game.skipNextPlayer();
        } else if (isReverseCard()) {
            game.reverseDirection();
        } else if (isDrawTwoCard()) {
            game.skipNextPlayer();
            game.drawCardsFromDeck(2);
        } else if (isWildCard() || isWildDrawFourCard()) {
            String selectedColor = selectColor();
            setColor(selectedColor);
            System.out.println("Selected color: " + selectedColor);
            if (isWildDrawFourCard()) {
                game.skipNextPlayer();
                game.drawCardsFromDeck(4);
            }
        }
    }

    public boolean isPlayable(Card currentCard) {
        return color == currentCard.getColor() || value == currentCard.getValue();
    }

    private String selectColor() {
        Color[] colors = Color.values();
        Random random = new Random();
        int index = random.nextInt(colors.length - 1); // Exclude the last color (WILD)
        return colors[index].toString();
    }
}
