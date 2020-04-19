package ooga.view;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import ooga.api.view.PlayerInfo;

public class TabView {

  private static final double SPACING = 10;
  private final int playersTabID;
  List<DisplayTab> tabs = new ArrayList<>();
  private int rulesTabID;
  TabPane tabPane;
  private Map<String, Class> tabsRegistry = new HashMap<>();

  public TabView(double width, double height){
    tabPane = new TabPane();
    tabPane.setPrefSize(width, height);
    tabsRegistry.put("Rules", RulesTab.class);
    tabsRegistry.put("Players", PlayersTab.class);
    rulesTabID = this.createTab("Rules");
    playersTabID = this.createTab("Players");

  }

  protected int createTab(String label) {
    VBox vbox =  new VBox(SPACING);
    vbox.setAlignment(Pos.TOP_LEFT);
    vbox.setFillWidth(true);
    String tabType = label+ "Tab";
    DisplayTab displayTab;
    try {
      displayTab = (DisplayTab) tabsRegistry.get(label).getDeclaredConstructor(String.class, Pane.class).newInstance(tabType, vbox);
    }
    catch(NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException ex){
      displayTab = new RulesTab("Rules",vbox);
    }
    tabs.add(displayTab);
    tabPane.getTabs().add(displayTab.getTab());
    return tabs.size()-1;
  }
  protected void updateRules(List<Object> info){
    tabs.get(rulesTabID).updateTab(info);
  }

  protected void updatePlayersTab(Map<Integer, PlayerInfo> currentPlayers){
    List<Object> information = playerInfoToList(currentPlayers);
    tabs.get(playersTabID).updateTab(information);
  }

  private List<Object> playerInfoToList(Map<Integer, PlayerInfo> currentPlayers) {
    List<Object> playerInfoList = new ArrayList<>();
    for (Integer i: currentPlayers.keySet()){
      PlayerInfo playerInfo = (currentPlayers.get(i));
      playerInfoList.add(playerInfo.getPlayerColor());
      playerInfoList.add(playerInfo.getCashBalance());
      playerInfoList.add(playerInfo.getPropertiesUnmodifiable());
    }
    return playerInfoList;
  }

  protected void addTabPaneToView(View root){
    root.getChildren().add(tabPane);
  }


}
