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
  protected void updateRules(List<Object> info){
    tabs.get(rulesTabID).updateTab(info);
  }

  public void changeRuleSet(String newGameType){
    RulesTab rulesTab = (RulesTab) tabs.get(rulesTabID);
    rulesTab.changeRules(newGameType);
  }

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

  public void updatePlayersTab(Map<Integer, Player> currentPlayers){
    Collection<Object> information = playerInfoToList(currentPlayers);
    tabs.get(playersTabID).updateTab(information);
    tabs.get(propertiesTabID).updateTab(ownedProperty);
  }

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

  public void addTabPaneToGroup(Group root){
    root.getChildren().add(tabPane);
  }


}
