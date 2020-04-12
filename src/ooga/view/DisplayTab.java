package ooga.view;

import java.util.List;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;

public abstract class DisplayTab {
  Tab myTab;
  public DisplayTab(String tabName, Pane pane){
    myTab = new Tab(tabName, pane);
  }

  abstract void updateTab(List<String> info);

  protected Tab getTab(){return myTab;}

}
