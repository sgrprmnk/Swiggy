# Swiggy
UNO Game

Design a class structure for a UNO game that allows multiple players to participate. The game should support the standard rules of UNO, including drawing cards, playing cards, skipping turns, reversing the direction of play, and declaring a winner.

Please provide a detailed design that includes the following:

1. Player:
   - Define a class representing a player in the game. Each player should have a unique identifier, a name, and a hand of cards.
   - The player class should have methods to:
     - Receive a set of initial cards at the start of the game.
     - Draw a card from the deck.
     - Play a card from their hand.
     - Receive and apply the effects of special cards (e.g., skip, reverse).
     - Check if the player has any valid cards to play.

2. Card:
   - Define a class representing a single card in the UNO deck. Each card should have a color (red, blue, green, or yellow) and a value.
   - The card class should have methods to:
     - Get and set the color and value of the card.
     - Determine if the card is a special action card (e.g., skip, reverse, draw two).
     - Perform any special actions associated with the card.

3. Deck:
   - Define a class representing the deck of cards used in the game. The deck should consist of a standard UNO deck with colored cards (numbered 0-9) and special action cards (skip, reverse, draw two).
   - The deck class should have methods to:
     - Initialize the deck with the standard set of cards.
     - Shuffle the cards.
     - Draw a card from the deck.
     - Replenish the deck when it runs out.

4. Game:
   - Define a class representing the game itself. The game class should manage the flow of the game and handle player turns.
   - The game class should have methods to:
     - Start the game by initializing the deck, distributing cards to players, and determining the first player.
     - Handle the order of turns, taking into account skips and reverses.
     - Validate and apply card plays, including checking if a card is a valid play based on the current card in play.
     - Check for win conditions, such as a player running out of cards.
     - Declare a winner at the end of the game.

5. Additional Rules:
   - Describe any additional rules or features you would like to include in your UNO game, such as wild cards, special wild action cards, or scoring.
   - Provide a detailed explanation of how these additional rules would be implemented in your class structure.

Note: This is a detailed description of the class structure for the UNO game. Feel free to expand upon the design and provide further details or additional classes/methods as needed.


Here's a description of the different types of cards you can include in your UNO-like game:

1. Numbered Cards:
   - These cards make up the majority of the deck and are identified by both a color and a number.
   - The numbers range from 0 to 9, and there are four cards for each combination of color and number.
   - When playing a numbered card, the next player must play a card of the same color or number.

2. Special Action Cards:
   - These cards have special actions associated with them and can affect gameplay.
   - Skip Card: When played, the next player in turn is "skipped," and their turn is skipped.
   - Reverse Card: When played, the direction of play is reversed, so the order of turns is reversed.
   - Draw Two Card: When played, the next player must draw two cards from the deck and skip their turn.
   - Wild Card: This card allows the player to choose the color that continues the game. It does not have a specific number and can be played on any card.

3. Special Wild Action Cards (optional):
   - These cards introduce additional actions and choices to the game.
   - Wild Draw Four Card: Similar to the Wild Card, the player gets to choose the color. In addition, the next player must draw four cards from the deck and skip their turn. However, this card can only be played if the player does not have a card of the current color.

You can consider adjusting the number of special action cards based on your preferences to balance gameplay.

Remember to incorporate these card types into your Card class, including methods to determine the card type and perform the associated actions.


