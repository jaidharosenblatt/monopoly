package ooga.BackEnd.GameObjects.Tiles.PropertyTiles;

import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.Tile;

import java.util.Scanner;

public abstract class Property extends Tile {
    protected Player owner;
    protected int cost;
    protected String group_color;
    protected int group_number;
    protected boolean mortgaged;

    @Override
    public void action() {
        if (!isOwned()) {
            try (Scanner scanner = new Scanner(System.in)) { //replace this with front-end decision instead
                System.out.print("Would you like to buy this? [Y or N]: ");
                String input = scanner.nextLine();
                if (input.equals("Y")) {
                    if (this.visiting.getBalance() >= this.cost) {
                        this.setOwner(this.visiting);
                        this.owner.buyProperty(this);
                    }
                }
            }
        }
        else if (this.visiting != this.owner && !isMortgaged()) {
            this.visiting.payPlayer(this.owner, this.getRent());
        }
        else {
            System.out.println("just visiting, pay nothing");
        }
    }

    public abstract int getRent();

    public int getPropID() {return tileID;}

    public boolean isOwned() {return (owner == null) ? false : true;}

    public void setOwner(Player P) {this.owner = P;}

    public Player getOwner() {return this.owner;}

    public int getCost() {return this.cost;}

    public String getGroupColor() {return this.group_color;}

    public int getGroupNumber() {return this.group_number;}

    public boolean isMortgaged() {return this.mortgaged;}

    public void setMortgaged() {
        this.mortgaged = true;
        this.owner.receive((this.cost/2));
    }

    public void liftMortgage() {
        this.mortgaged = false;
        this.owner.payBank((int) (this.cost * 1.1));
    }
}
