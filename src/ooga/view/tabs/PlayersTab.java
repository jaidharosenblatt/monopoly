package ooga.view.tabs;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;

import java.util.Collection;
import java.util.List;
import java.util.ResourceBundle;

public class PlayersTab extends DisplayTab{
    private static final ResourceBundle myResources = ResourceBundle.getBundle("ooga/view/tabs/TabStringResources");
    private static final String TITLE = myResources.getString("playersTabTitle");
    private static final int SPACING = 10;
    private static final int INFO_LENGTH = 3;

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
            i += INFO_LENGTH;
        }
    }

    protected VBox createPlayerDisplay(String color, Integer number, List<Property> properties){
        VBox vbox = new VBox(SPACING);
        HBox title = new HBox();
        title.getChildren().add(new Text(myResources.getString("player")));
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
