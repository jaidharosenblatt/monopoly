package ooga.BackEnd.GameObjects;

public class Cards {

    private Player cardHolder;
    private String type;

    public Cards(String type) {
        this.type = type;
        this.cardHolder = null;
    }

    public void action() {
        if (this.type.equals("Chance")) {
            int probality = (int) (Math.random() * 16) + 1;
            switch (probality) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    break;
                case 16:
                    break;
            }
        }
        if (this.type.equals("Community")) {
            int probality = (int) (Math.random() * 17) + 1;
            switch (probality) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                case 12:
                    break;
                case 13:
                    break;
                case 14:
                    break;
                case 15:
                    break;
                case 16:
                    break;
                case 17:
                    break;
            }
        }
    }
}
