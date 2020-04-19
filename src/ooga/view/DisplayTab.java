package ooga.view;

import java.util.List;

import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public abstract class DisplayTab {
  Tab myTab;
  Pane myPane;
  public DisplayTab(String tabName, Pane pane){
    myTab = new Tab(tabName, pane);
    myPane = pane;
  }

  abstract void updateTab(List<Object> info);

  protected Tab getTab(){return myTab;}

  protected void addTitle(String str) {
    HBox hbox = new HBox();
    Text title = new Text(str);
    title.setFont(new Font(20));
    hbox.setAlignment(Pos.CENTER);
    hbox.getChildren().add(title);
    myPane.getChildren().add(hbox);
  }

}
