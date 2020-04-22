package ooga.view.tabs;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;

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
    void updateTab(List<Object> info) {
        int i = 0;
        while (i < info.size()) {
            VBox vbox = createPlayerDisplay((String) info.get(i).toString(),(Integer) info.get(i + 1), (List<Property>) info.get(i + 2));
            myPane.getChildren().add(vbox);
            i += 3;
        }
    }

    protected VBox createPlayerDisplay(String color, Integer number, List<Property> properties){
        VBox vbox = new VBox(SPACING);
        HBox title = makeColorAndTextHbox(color, "Balance: " + number.toString(), false);
        vbox.getChildren().add(title);
        for (Property p: properties){
            HBox property = makeColorAndTextHbox(p.getGroupColor(),p.getTitle(), true);
            vbox.getChildren().add(property);
        }
        return vbox;
    }


    private HBox makeColorAndTextHbox(String color, String text, boolean property) {
        Circle circle = new Circle(FONT_SIZE /2, Color.web(color));
        Text playerNum = new Text(text);
        HBox hBox = new HBox(SPACING);
        hBox.getChildren().addAll(circle,playerNum);
        if (property) {
            hBox.setAlignment(Pos.CENTER);
        }
        return hBox;
    }
}
