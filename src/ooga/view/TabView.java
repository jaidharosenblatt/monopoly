package ooga.view;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class TabView {

  private static final double SPACING = 10;
  List<DisplayTab> tabs = new ArrayList<>();
  TabPane tabPane;

  public TabView(double width, double height){
    tabPane = new TabPane();
    tabPane.setPrefSize(width, height);
  }

  protected int createTab(String label){
    VBox vbox =  new VBox(SPACING);
    vbox.setAlignment(Pos.TOP_LEFT);
    vbox.setFillWidth(true);
    DisplayTab displayTab = new RulesTab(label, vbox);
    tabs.add(displayTab);
    tabPane.getTabs().add(displayTab.getTab());
    return tabs.size()-1;
  }

  protected void updateTab(int tabNum, List<String> info){
    tabs.get(tabNum).updateTab(info);
  }

  protected void addTabPaneToRoot(Group root){
    root.getChildren().add(tabPane);
  }


}
