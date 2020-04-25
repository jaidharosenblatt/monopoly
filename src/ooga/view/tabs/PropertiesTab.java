package ooga.view.tabs;

import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;

import java.util.*;

/**
 * This class is a child of DisplayTab. This specific tab is used to display all of the available properties (i.e. properties
 * still owned by the bank). It assumes the intializeProperties method will be called before the updateTab method is.
 */
public class PropertiesTab extends DisplayTab{
    private static final ResourceBundle myResources = ResourceBundle.getBundle("ooga/view/tabs/TabStringResources");
    private static final String TITLE = myResources.getString("propertiesTabTitle");
    private static final int SPACING = 10;

    private List<Property> availableProperty;


    private Pane myPane;

    /**
     * Creates a new PropertiesTab
     * @param tabName the name of the tab that will be displayed in the top of the pane
     * @param pane the pane type that will house all of the information this tab holds.
     */
    public PropertiesTab(String tabName, Pane pane) {
        super(tabName, pane);
        addTitle(TITLE);
        myPane = pane;
    }

    /**
     * This method is called on start-up and takes in all of the properties available at the start of the game
     * @param properties a List of all of the available properties.
     */
    protected void initializeProperties(List<Property> properties){
        availableProperty = new ArrayList<>(properties);
    }

    /**
     * Used to remove the purchased properties from the display
     */
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

    /**
     * Comparator to sort the properties by their color/grouping.
     */
    class SortByColor implements Comparator<Property> {
        @Override
        public int compare(Property o1, Property o2) {
            return o1.getGroupNumber() - o2.getGroupNumber();
        }
    }
}
