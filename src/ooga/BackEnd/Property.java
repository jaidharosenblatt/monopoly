package ooga.BackEnd;

public class Property {

    private String title_deed = null;
    private int cost = 0;
    private int base_rent = 0;
    private int monopoly_rent = 0;
    private int rent_one_house = 0;
    private int rent_two_house = 0;
    private int rent_three_house = 0;
    private int rent_four_house = 0;
    private int rent_hotel = 0;
    private int house_cost = 0;
    private int houses = 0;
    private String group_color = null;
    private int group_number = 0;

    public Property(int propID) {
        this.title_deed = null;
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
    }

    public int getRent(Player P) {
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
            if (P.hasMonopoly(this)) {
                return this.monopoly_rent;
            }
            else {
                return this.base_rent;
            }

        }
    }
    public int getCost() {return this.cost;}

    public int getHouseCost() {return this.house_cost;}

    public int getHouses() {return this.houses;}

    public String getGroupColor() {return this.group_color;}

    public int getGroupNumber() {return this.group_number;}
}
