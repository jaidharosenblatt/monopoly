package ooga;

public interface Property {
    public String getCategory();

    public int getCost();

    public int getRentPrice();

    public int getHousePrice();

    public String getPathToImage();

    public boolean isMortgaged();

    public void changeImage(String pathToImage);
}
