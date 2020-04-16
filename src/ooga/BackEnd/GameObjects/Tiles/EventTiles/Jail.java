package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameObjects.Tiles.Tile;

import java.util.Scanner;

public class Jail extends Tile {

    public Jail(String tileID, int boardIndex) {
        this.tileID = tileID;
        this.boardIndex = boardIndex;
        this.visiting = null;
    }

    @Override
    public void action() {
        if (this.visiting.getJailStatus()) {
            if (this.visiting.hasJailFreeCards()) {
                try (Scanner scanner = new Scanner(System.in)) { //replace this with front-end decision instead
                    System.out.print("Would you like to use your Get Out of Jail Free card? [Y or N]: ");
                    String input = scanner.nextLine();
                    if (input.equals("Y")) {
                        this.visiting.useJailFreeCard();
                        this.visiting.setFree();
                    }
                }
            }
            if (this.visiting.getJailTurn() < 2) {
                try (Scanner scanner = new Scanner(System.in)) { //replace this with front-end decision instead
                    System.out.print("Would you like to bail out of Jail? [Y or N]: ");
                    String input = scanner.nextLine();
                    if (input.equals("Y")) {
                       this.visiting.payBail();
                       this.visiting.setFree();
                    }
                    if (input.equals("N")) {
                        this.visiting.addJailTurn();
                        this.visiting.rollDice();
                        if (this.visiting.dice1 == this.visiting.dice2) {
                            this.visiting.setFree();
                        }
                    }
                }
            }
            else {
                this.visiting.payBail();
                this.visiting.setFree();
            }
        }
    }
}
