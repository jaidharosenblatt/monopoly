package ooga.view.tabs;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;

import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

/**
 * This class is a child of the DisplayTab class. This one is used to display the assets of each player. This included
 * both their current cash balance and the properties they own.
 */
public class PlayersTab extends DisplayTab{
    private static final ResourceBundle myResources = ResourceBundle.getBundle("ooga/view/tabs/TabStringResources");
    private static final String TITLE = myResources.getString("playersTabTitle");
    private static final int SPACING = 10;
    private static final int INFO_LENGTH = 4;

    private  Pane myPane;

    /**
     * Construct a PlayersTab
     * @param tabName the name to be displayed at the top of the tab
     * @param pane the pane that will enclose all of the display elements of this tab.
     */
    public PlayersTab(String tabName, Pane pane) {
        super(tabName, pane);
        addTitle(TITLE);
        myPane = pane;
    }

    /**
     * update the players display. Assumes the list is formatted in the following order for each Player: String of their color,
     * cash amount, List of properties they own, and the player's name
     * @param info The updated information that needs to be displayed in the respective tab.
     */
    @Override
    void updateTab(Collection<Object> info) {
        List<Object> inform = (List<Object>) info;
        myPane.getChildren().clear();
        addTitle(TITLE);
        int i = 0;
        while (i < info.size()) {
            VBox vbox = createPlayerDisplay( inform.get(i).toString(),(Integer) inform.get(i + 1), (List<Property>) inform.get(i + 2),(String) inform.get(i+3));
            myPane.getChildren().add(vbox);
            i += INFO_LENGTH;
        }
    }

    /**
     * Creates the display area for a player. This includes displaying their name and color, their cash balance, and their
     * owned properties. Uses the displayTab makeColorAndTextHBox.
     * @param color the web formatted color of the player
     * @param number the cash amount a player has
     * @param properties a list of the properties a player owns
     * @param playerName the name of the player that they entered upon start-up
     * @return A VBox layout that organizes all this display information nicely.
     */
    protected VBox createPlayerDisplay(String color, Integer number, List<Property> properties, String playerName){
        VBox vbox = new VBox(SPACING);
        HBox title = new HBox();
        title.getChildren().add(new Text(playerName + " "));
        title.getChildren().add(makeColorAndTextHBox(color, myResources.getString("balance") + number.toString(), false, null));
        title.getChildren().add(new Text(myResources.getString("owns")));

        vbox.getChildren().add(title);
        for (Property p: properties){
            HBox property = makeColorAndTextHBox(p.getGroupColor(),p.getTitle(), true, p);
            vbox.getChildren().add(property);
        }
        HBox spacer = new HBox();
        spacer.getChildren().add(new Text(""));
        vbox.getChildren().add(spacer);
        return vbox;
    }

}
