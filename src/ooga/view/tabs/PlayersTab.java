package ooga.view.tabs;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;

import java.util.Collection;
import java.util.List;

public class PlayersTab extends DisplayTab{
    private static final String TITLE = "Player Assets";
    private static final int SPACING = 10;
    private static final double FONT_SIZE = 12;

    private  Pane myPane;


    public PlayersTab(String tabName, Pane pane) {
        super(tabName, pane);
        addTitle(TITLE);
        myPane = pane;
    }

    @Override
    void updateTab(Collection<Object> info) {
        List<Object> inform = (List<Object>) info;
        myPane.getChildren().clear();
        addTitle(TITLE);
        int i = 0;
        while (i < info.size()) {
            VBox vbox = createPlayerDisplay( inform.get(i).toString(),(Integer) inform.get(i + 1), (List<Property>) inform.get(i + 2));
            myPane.getChildren().add(vbox);
            i += 3;
        }
    }

    protected VBox createPlayerDisplay(String color, Integer number, List<Property> properties){
        VBox vbox = new VBox(SPACING);
        HBox title = new HBox();
        title.getChildren().add(new Text("Player "));
        title.getChildren().add(makeColorAndTextHBox(color, "Balance: $" + number.toString(), false, null));
        title.getChildren().add(new Text(", Owns:"));

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
