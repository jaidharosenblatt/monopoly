package ooga.BackEnd;

public class Roll {
    public int dice1;
    public int dice2;

    public Roll() {
        rollDice();
    }

    public void rollDice() {
        dice1 = (int) (Math.random()*6) + 1;
        dice2 = (int) (Math.random()*6) + 1;
    }
}
