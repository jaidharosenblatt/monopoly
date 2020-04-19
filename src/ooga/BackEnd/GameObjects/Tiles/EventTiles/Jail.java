package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameObjects.Tiles.Tile;

import java.util.Scanner;

public class Jail extends Event {

    public Jail() {}

    public Jail(String tileID, int boardIndex) {
        this.tileID = tileID;
        this.boardIndex = boardIndex;
        this.visiting = null;
    }

    @Override
    public void action() {
        if (this.visiting.isJailed()) {
            System.out.println(this.visiting.getName() + " is currently in Jail.");
            if (this.visiting.hasJailFreeCards()) {
                Scanner myObj = new Scanner(System.in); //replace this with front-end decision instead
                System.out.println("Would you like to use your Get Out of Jail Free Card? [Y or N]: ");
                String decision = myObj.nextLine();
                if (decision.equals("Y")) {
                    this.visiting.useJailFreeCard();
                    this.visiting.setFree();
                    this.visiting.rollDice();
                    this.visiting.moveTo(10 + this.visiting.dice1 + this.visiting.dice2);
                }
            }
            if (this.visiting.getJailTurn() < 2) {
                Scanner myObj = new Scanner(System.in); //replace this with front-end decision instead
                System.out.println("Would you like to bail out of Jail? [Y or N]: ");
                String decision = myObj.nextLine();
                if (decision.equals("Y")) {
                    this.visiting.payBail();
                    this.visiting.setFree();
                    this.visiting.rollDice();
                    this.visiting.moveTo(10 + this.visiting.dice1 + this.visiting.dice2);
                }
                if (decision.equals("N")) {
                    this.visiting.addJailTurn();
                    this.visiting.rollDice();
                    if (this.visiting.dice1 == this.visiting.dice2) {
                        this.visiting.setFree();
                        this.visiting.moveTo(10 + this.visiting.dice1 + this.visiting.dice2);
                    }
                }
            }
            else {
                this.visiting.payBail();
                this.visiting.setFree();
                this.visiting.rollDice();
                this.visiting.moveTo(10 + this.visiting.dice1 + this.visiting.dice2);
            }
        }
    }
}
