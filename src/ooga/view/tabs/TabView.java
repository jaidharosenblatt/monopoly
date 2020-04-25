package ooga.view.tabs;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ooga.BackEnd.GameObjects.Player;
import ooga.BackEnd.GameObjects.Tiles.PropertyTiles.Property;
import ooga.BackEnd.GameObjects.Tiles.Tile;

/**
 * @author Austin
 * This class is composed of the JFX TabPane. This allows it to hold and display all of the tabs. It also handles switching
 * between the display of the different tabs. This class instantiates the various types of tabs via reflection and a class registry.
 * Upon instantiation this class creates a tab pane and fills it with the different tabs. It has the methods createTab,
 * updateRules, changeRuleSet, intializeProperties, updatePlayersTab, addTabPaneToRoot, and PlayerInfoToList.
 */
public class TabView {

  private static final double SPACING = 10;
  private static final ResourceBundle myResources = ResourceBundle.getBundle("ooga/view/tabs/TabStringResources");

  private final int playersTabID;
  private final int propertiesTabID;


  List<DisplayTab> tabs = new ArrayList<>();
  private int rulesTabID;
  TabPane tabPane;
  private Map<String, Class> tabsRegistry = new HashMap<>();
  Collection<Object> ownedProperty = new HashSet<>();

  /**
   * The constructor for the TabView class which creates the TabPane and adds the respective tabs to it. Also receives and ID
   * to differentiate between tabs.
   * @param width the width of the TabPane
   * @param height the height of the TabPane display
   */
  public TabView(double width, double height){
    tabPane = new TabPane();
    tabPane.setPrefSize(width, height);
    tabsRegistry.put(myResources.getString("players"), PlayersTab.class);
    tabsRegistry.put(myResources.getString("rules"), RulesTab.class);
    tabsRegistry.put(myResources.getString("properties"), PropertiesTab.class);

    playersTabID = this.createTab(myResources.getString("players"));
    rulesTabID = this.createTab(myResources.getString("rules"));
    propertiesTabID = this.createTab(myResources.getString("properties"));

  }

  /**
   * This method is used to create a new tab. It will create a new DisplayTab that corresponds to the label passed in.
   * It will then add this tab to the list tabs. For example, to create the RulesTab, pass in the String "Rules".
   * @param label The text that will go on the tab selector on the top of the pane
   * @return the index of where in the list of tabs this one is.
   */
  protected int createTab(String label) {
    VBox vbox =  new VBox(SPACING);
    ScrollPane scrollPane = new ScrollPane();
    scrollPane.setContent(vbox);
    vbox.setAlignment(Pos.TOP_LEFT);
    vbox.setFillWidth(true);
    String tabType = label+ myResources.getString("tab");
    DisplayTab displayTab;
    try {
      displayTab = (DisplayTab) tabsRegistry.get(label).getDeclaredConstructor(String.class, Pane.class).newInstance(tabType, vbox);
    }
    catch(NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex){
      displayTab = new RulesTab(myResources.getString("rules"),vbox);
    }
    tabs.add(displayTab);
    displayTab.getTab().setContent(scrollPane);
    tabPane.getTabs().add(displayTab.getTab());
    return tabs.size()-1;
  }

  /**
   * Invoke the updateTab on the RulesTab
   * @param info Strings of all of the new rules
   */
  protected void updateRules(List<Object> info){
    tabs.get(rulesTabID).updateTab(info);
  }

  /**
   * Prompt the RulesTab to refresh its rules from a properties file. The properties file is assumed to be in the tabs package.
   * Assumes this file is named in the format GameTypeRules
   * @param newGameType the name of the game type
   */
  public void changeRuleSet(String newGameType){
    RulesTab rulesTab = (RulesTab) tabs.get(rulesTabID);
    rulesTab.changeRules(newGameType);
  }

  /**
   * Takes in a list of tiles from the View class and sorts through them to just find the properties. These properties are then
   * passed to the propertyTab so the starting tiles can be displayed.
   * @param tiles a list of all of the tiles on the board at start-up
   */
  public void initializeProperties(List<Tile> tiles){
    PropertiesTab propertiesTab = (PropertiesTab) tabs.get(propertiesTabID);
    List properties = new ArrayList<Property>();
    for(Tile tile: tiles){
      if (tile instanceof Property){
        properties.add(tile);
      }
    }
    propertiesTab.initializeProperties(properties);
  }

  /**
   * Use to update the PlayersTab to correctly display their current assets.
   * @param currentPlayers a Map of all of the players in the game
   */
  public void updatePlayersTab(Map<Integer, Player> currentPlayers){
    Collection<Object> information = playerInfoToList(currentPlayers);
    tabs.get(playersTabID).updateTab(information);
    tabs.get(propertiesTabID).updateTab(ownedProperty);
  }

  /**
   * Helper method to take the map of players and parse out all of the necessary data and add it to a list
   * @param currentPlayers the map of id to players
   * @return a list containing all of the relevant data for display
   */
  private List<Object> playerInfoToList(Map<Integer, Player> currentPlayers) {
    List<Object> playerInfoList = new ArrayList<>();
    for (Integer i: currentPlayers.keySet()){
      Player playerInfo = (currentPlayers.get(i));
      playerInfoList.add(playerInfo.getPlayerColor());
      playerInfoList.add(playerInfo.getCashBalance());
      playerInfoList.add(playerInfo.getProperties());
      playerInfoList.add(playerInfo.getName());
      ownedProperty.addAll(playerInfo.getProperties());
    }
    return playerInfoList;
  }

  /**
   * Adds the TabPane to the given scene root
   * @param root The root of the scene
   */
  public void addTabPaneToGroup(Group root){
    root.getChildren().add(tabPane);
  }


}
