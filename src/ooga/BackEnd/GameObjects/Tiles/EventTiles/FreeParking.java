package ooga.BackEnd.GameObjects.Tiles.EventTiles;

import ooga.BackEnd.GameLogic.Decisions.Decision;
import ooga.BackEnd.GameObjects.Tiles.Tile;
import ooga.util.PropertiesGetter;

import java.util.List;

/**
 * @author rodrigoaraujo Event tile for free parking
 */

public class FreeParking extends Event {

    private static final List<String> option = List.of(PropertiesGetter.getPromptFromKey("Ok"));

    /**
     * default constructor for parsing
     */

    public FreeParking() {}

    public FreeParking(String tileID, int boardIndex) {
        this.tileID = tileID;
        this.boardIndex = boardIndex;
        this.visiting = null;
    }

    @Override
    public void action() {
        Decision d = new Decision(PropertiesGetter.getPromptFromKey("freeparking"), option);
        view.makeUserDecision(d);
    }
}

