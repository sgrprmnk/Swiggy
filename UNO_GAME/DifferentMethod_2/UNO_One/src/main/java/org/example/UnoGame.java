package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class UnoGame {
    public static String computerTurn(List<String> cards, String topCard) {
        for (int i = 0; i < cards.size(); i++) {
            if (cards.get(i).charAt(0) == topCard.charAt(0) || cards.get(i).charAt(1) == topCard.charAt(1)) {
                String temp = cards.get(i);
                cards.remove(i);
                return temp;
            }
        }
        return "no";
    }

    public static void playerCall(int i) {
        if (i == 0)
            System.out.print("Player 1, play your card: ");
        else
            System.out.println("Player " + (i + 1) + ", it's your turn.");
    }

    public static void shuffle(List<String> cards) {
        Random random = new Random();
        for (int i = cards.size() - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            String temp = cards.get(i);
            cards.set(i, cards.get(j));
            cards.set(j, temp);
        }
    }

    public static void main(String[] args) {
        List<String> totalCards = new ArrayList<>(List.of(
                "R0", "R1", "R2", "R3", "R4", "R5", "R6", "R7", "R8", "R9", "RS",
                "B0", "B1", "B2", "B3", "B4", "B5", "B6", "B7", "B8", "B9", "BS",
                "G0", "G1", "G2", "G3", "G4", "G5", "G6", "G7", "G8", "G9", "GS",
                "Y0", "Y1", "Y2", "Y3", "Y4", "Y5", "Y6", "Y7", "Y8", "Y9", "YS"
        ));

        List<String> cards = new ArrayList<>(totalCards);
        int cardCount = 44;
        List<String> P1 = new ArrayList<>(7);
        List<String> P2 = new ArrayList<>(7);
        List<String> P3 = new ArrayList<>(7);

        shuffle(totalCards);
        System.out.println("WELCOME TO UNO GAME!!!");
        System.out.println();
        System.out.println("Please read the instructions before playing.");
        System.out.println();
        System.out.println("The cards have been shuffled!");
        System.out.println("You are Player 1, and these are your cards:");

        Random random = new Random();
        for (int j = 0; j < 7; j++) {
            P1.add(cards.get(random.nextInt(cardCount)));
            cardCount--;
            cards.remove(P1.get(j));
            System.out.print(P1.get(j) + " ");
        }
        System.out.println();

        for (int j = 0; j < 7; j++) {
            P2.add(cards.get(random.nextInt(cardCount)));
            cardCount--;
            cards.remove(P2.get(j));
        }

        for (int j = 0; j < 7; j++) {
            P3.add(cards.get(random.nextInt(cardCount)));
            cardCount--;
            cards.remove(P3.get(j));
        }

        String topCard = cards.get(random.nextInt(cardCount));
        String temp, emp = "no";
        int currentPlayerIndex = 0;
        System.out.println("Top card: " + topCard);

        while (!P1.isEmpty() && !P2.isEmpty() && !P3.isEmpty()) {
            if (currentPlayerIndex == 0) {
                playerCall(currentPlayerIndex);
                for (String card : P1) {
                    System.out.print(card + " ");
                }
                System.out.println();
                Scanner scanner = new Scanner(System.in);
                temp = scanner.next();
                if (!temp.equals(emp) && (temp.charAt(0) != topCard.charAt(0) && temp.charAt(1) != topCard.charAt(1))) {
                    System.out.println("Wrong move! Please try again.");
                    temp = scanner.next();
                }
                if (!temp.equals(emp) && (temp.charAt(0) != topCard.charAt(0) && temp.charAt(1) != topCard.charAt(1))) {
                    System.out.println("You are not playing according to the rules.");
                    System.out.println("You lost!!");
                    System.out.println("Game ended!!");
                    return;
                }

                if (!temp.equals(emp)) {
                    cards.add(topCard);
                    cardCount++;
                    topCard = temp;
                    if (temp.charAt(1) == 'S') {
                        currentPlayerIndex = (currentPlayerIndex + 1) % 3;
                        playerCall(currentPlayerIndex);
                        System.out.println("Skipped");
                    }
                    P1.remove(temp);
                } else {
                    P1.add(cards.get(random.nextInt(cardCount)));
                    cards.remove(P1.get(P1.size() - 1));
                    cardCount--;
                }
                if (P1.isEmpty()) {
                    System.out.println("Player 1 wins!!!");
                    break;
                }
            } else if (currentPlayerIndex == 1) {
                playerCall(currentPlayerIndex);
                String temp1 = computerTurn(P2, topCard);
                System.out.println(temp1);
                if (temp1.charAt(1) == 'S') {
                    currentPlayerIndex = (currentPlayerIndex + 1) % 3;
                    playerCall(currentPlayerIndex);
                    System.out.println("Skipped");
                }
                if (!temp1.equals(emp)) {
                    cards.add(topCard);
                    cardCount++;
                    topCard = temp1;
                } else {
                    P2.add(cards.get(random.nextInt(cardCount)));
                    cards.remove(P2.get(P2.size() - 1));
                    cardCount--;
                }
                if (P2.isEmpty()) {
                    System.out.println("Player 2 wins!!!");
                    break;
                }
            } else if (currentPlayerIndex == 2) {
                playerCall(currentPlayerIndex);
                String temp2 = computerTurn(P3, topCard);
                System.out.println(temp2);
                if (temp2.charAt(1) == 'S') {
                    currentPlayerIndex = (currentPlayerIndex + 1) % 3;
                    playerCall(currentPlayerIndex);
                    System.out.println("Skipped");
                }
                if (!temp2.equals(emp)) {
                    cards.add(topCard);
                    cardCount++;
                    topCard = temp2;
                } else {
                    P3.add(cards.get(random.nextInt(cardCount)));
                    cards.remove(P3.get(P3.size() - 1));
                    cardCount--;
                }
                if (P3.isEmpty()) {
                    System.out.println("Player 3 wins!!!");
                    break;
                }
            }
            currentPlayerIndex = (currentPlayerIndex + 1) % 3;
        }
    }
}
