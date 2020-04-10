package ooga.view;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.VBox;

public class TabView {

  private static final double SPACING = 10;
  List<Tab> tabs = new ArrayList<Tab>();
  TabPane tabPane;

  public TabView(double width, double height){
    tabPane = new TabPane();
    tabPane.setPrefSize(width, height);
  }

  protected int createTab(String label){
    VBox vbox =  new VBox(SPACING);
    vbox.setAlignment(Pos.TOP_CENTER);
    Tab tab = new RulesTab(label, vbox);
    tabs.add(tab);
    tabPane.getTabs().add(tab);
    return tabs.size()-1;
  }

  protected void updateTab(int tabNum, List<String> info){
  }

  protected void addTabPaneToRoot(Group root){
    root.getChildren().add(tabPane);
  }


}
