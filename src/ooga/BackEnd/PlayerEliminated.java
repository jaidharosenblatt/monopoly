package ooga.BackEnd;

import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;

import java.util.List;

public class PlayerEliminated {
    private List<Property> lostProperties;

    public PlayerEliminated(Player eliminated, Player survivor){
        lostProperties = eliminated.getProperties();
        for (int i=0; i<lostProperties.size(); i++){
            survivor.addProperty(lostProperties.get(i));
        }
        if (eliminated.hasJailFreeCards()){
            survivor.getJailFreeCard();
        }
    }
}
