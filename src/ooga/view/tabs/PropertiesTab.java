package ooga.view.tabs;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;

import java.util.*;


public class PropertiesTab extends DisplayTab{
    private static final ResourceBundle myResources = ResourceBundle.getBundle("ooga/view/tabs/TabStringResources");
    private static final String TITLE = myResources.getString("propertiesTabTitle");
    private static final int SPACING = 10;

    private List<Property> availableProperty;


    private Pane myPane;

    public PropertiesTab(String tabName, Pane pane) {
        super(tabName, pane);
        addTitle(TITLE);
        myPane = pane;
    }

    protected void initializeProperties(List<Property> properties){
        availableProperty = new ArrayList<>(properties);
    }

    @Override
    void updateTab(Collection<Object> info) {
        myPane.getChildren().clear();
        addTitle(TITLE);
        for (Object obj: info){
            availableProperty.remove(obj);
        }
        displayProperties();
    }

    private void displayProperties() {
        VBox vBox = new VBox(SPACING);
        Collections.sort(availableProperty, new SortByColor());
        for (Property property : availableProperty){
            HBox propertyDisplay = makeColorAndTextHBox(property.getGroupColor(),property.getTitle(), false, property);
            vBox.getChildren().add(propertyDisplay);
        }
        myPane.getChildren().add(vBox);
    }
    class SortByColor implements Comparator<Property> {
        @Override
        public int compare(Property o1, Property o2) {
            return o1.getGroupNumber() - o2.getGroupNumber();
        }
    }
}
