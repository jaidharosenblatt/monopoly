package ooga.view;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import ooga.api.view.Property;

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
            VBox vbox = createPlayerDisplay((String) info.get(i),(Integer) info.get(i+1), (List<Property>) info.get(i+2));
            myPane.getChildren().add(vbox);
            i += 3;
        }
    }

    protected VBox createPlayerDisplay(String color, Integer number, List<Property> properties){
        VBox vbox = new VBox(SPACING);
        HBox title = makeColorAndTextHbox(color, "Player" + number.toString());
        vbox.getChildren().add(title);
        for (Property p: properties){
            HBox property = makeColorAndTextHbox(p.getCategory(),p.toString());
            vbox.getChildren().add(property);
        }
        return vbox;
    }


    private HBox makeColorAndTextHbox(String color, String text) {
        String [] colorStrings = color.split(",");
        int [] colors = new int[colorStrings.length];
        for (int i =0; i < colors.length; i ++)
            colors[i] = Integer.parseInt(colorStrings[i]);
        Circle circle = new Circle(FONT_SIZE /2, Color.rgb(colors[0], colors[1], colors[2]));
        Text playerNum = new Text(text);
        HBox hBox = new HBox(SPACING);
        hBox.getChildren().addAll(circle,playerNum);
        return hBox;
    }
}
