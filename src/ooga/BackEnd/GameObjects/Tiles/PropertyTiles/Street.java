package ooga.BackEnd.GameObjects.Tiles.PropertyTiles;

public class Street extends Property {

    private int base_rent;
    private int monopoly_rent;
    private int rent_one_house;
    private int rent_two_house;
    private int rent_three_house;
    private int rent_four_house;
    private int rent_hotel;
    private int house_cost;
    private int houses;

    public Street(int tileID) {
        this.tileID = tileID;
        this.boardIndex = 0;
        this.owner = null;
        this.cost = 0;
        this.base_rent = 0;
        this.monopoly_rent = 0;
        this.rent_one_house = 0;
        this.rent_two_house = 0;
        this.rent_three_house = 0;
        this.rent_four_house = 0;
        this.rent_hotel = 0;
        this.house_cost = 0;
        this.houses = 0;
        this.group_color = null;
        this.group_number = 0;
        this.mortgaged = false;
    }

    public int getHouseCost() {return this.house_cost;}

    public int getHouses() {return this.houses;}

    public void addHouse(int amount) {this.houses += amount;}

    public void removeHouse(int amount) {this.houses -= amount;}

    @Override
    public int getRent() {
        if (this.houses == 1) {
            return this.rent_one_house;
        }
        else if (this.houses == 2) {
            return this.rent_two_house;
        }
        else if (this.houses == 3) {
            return this.rent_three_house;
        }
        else if (this.houses == 4) {
            return this.rent_four_house;
        }
        else if (this.houses >= 5) {
            return this.rent_hotel;
        }
        else {
            if (this.owner.hasMonopoly(this)) {
                return this.monopoly_rent;
            }
            else {
                return this.base_rent;
            }

        }
    }

    @Override
    public void setMortgaged() {
        this.mortgaged = true;
        int sellHouses = 0;
        if (this.houses > 0) {
            sellHouses = (this.houses * this.house_cost) / 2;
        }
        this.owner.receive((this.cost/2) + sellHouses);
    }
}
