package ooga.BackEnd.GameObjects.Tiles.PropertyTiles;

/**
 * @author rodrigoaraujo A Street tile. Extends Property and adds unique features
 * only accessible to streets, such as houses.
 */

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

    /**
     * default constructor for parsing
     */

    public Street() {}

    /**
     * Creates the street class (subclass of Property)
     *
     * @param tileID String value of number gotten from XML
     * @param boardIndex int value from 0-39
     * @param cost int value of purchasing cost
     * @param title_deed String value for display name on board
     * @param base_rent int value of rent without monopoly
     * @param monopoly_rent int value of rent with monopoly
     * @param rent_one_house int value of rent with 1 house
     * @param rent_two_house int value of rent with 2 houses
     * @param rent_three_house int value of rent with 3 houses
     * @param rent_four_house int value of rent with 4 houses
     * @param rent_hotel int value of rent with hotel
     * @param house_cost int value of cost of purchasing a house
     * @param group_color String value of color set
     * @param group_number int value of of total in color set
     */

    public Street(String tileID, int boardIndex, int cost, String title_deed, int base_rent, int monopoly_rent, int rent_one_house, int rent_two_house, int rent_three_house, int rent_four_house, int rent_hotel, int house_cost, String group_color, int group_number) {
        this.tileID = tileID;
        this.boardIndex = boardIndex;
        this.cost = cost;
        this.title_deed = title_deed;
        this.base_rent = base_rent;
        this.monopoly_rent = monopoly_rent;
        this.rent_one_house = rent_one_house;
        this.rent_two_house = rent_two_house;
        this.rent_three_house = rent_three_house;
        this.rent_four_house = rent_four_house;
        this.rent_hotel = rent_hotel;
        this.house_cost = house_cost;
        this.group_color = group_color;
        this.group_number = group_number;
        this.owner = null;
        this.houses = 0;
        this.mortgaged = false;
    }

    public void setBaseRent(int rent) {this.base_rent = rent;}

    public int getBaseRent() {return this.base_rent;}

    public void setMonopolyRent(int rent) {this.monopoly_rent = rent;}

    public int getMonopolyRent() {return this.monopoly_rent;}

    public void setRent1H(int rent) {this.rent_one_house = rent;}

    public int getRent1H() {return this.rent_one_house;}

    public void setRent2H(int rent) {this.rent_two_house = rent;}

    public int getRent2H() {return this.rent_two_house;}

    public void setRent3H(int rent) {this.rent_three_house = rent;}

    public int getRent3H() {return this.rent_three_house;}

    public void setRent4H(int rent) {this.rent_four_house = rent;}

    public int getRent4H() {return this.rent_four_house;}

    public void setRentHotel(int rent) {this.rent_hotel = rent;}

    public int getRentHotel() {return this.rent_hotel;}

    public void setHouseCost(int cost) {this.house_cost = cost;}

    public int getHouseCost() {return this.house_cost;}

    public int getHouses() {return this.houses;}

    public void addHouse(int amount) {this.houses += amount;}

    public void removeHouse(int amount) {this.houses -= amount;}

    /**
     * Returns rent cost by checking how many (if any) houses are
     * on the property and whether or not the owner has a monopoly.
     *
     * @return int
     */

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

    /**
     * Wipes the mortgage and house values (unique to Street property)
     */

    @Override
    public void bankrupt() {
        this.mortgaged = false;
        this.houses = 0;
    }

    /**
     * When mortgaged, Street properties need to sell off any houses
     */

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
