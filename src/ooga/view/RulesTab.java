package ooga.view;

import java.util.List;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Tab;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class RulesTab extends DisplayTab {
  private static final String TITLE = "Game Rules";
  private static final int SPACING = 10;
  private static final int FONT_SIZE = 12;
  private  Pane myPane;
  private Text title;
  public RulesTab(String tabName, Pane pane) {
    super(tabName, pane);
    this.myPane = pane;
    addTitle();
  }

  @Override
  void updateTab(List<String> info) {
    myPane.getChildren().clear();
    addTitle();
    for (String rule: info){
      addText(rule);
    }
  }

  private void addTitle() {
    HBox hbox = new HBox();
    title = new Text(TITLE);
    title.setFont(new Font(20));
    hbox.setAlignment(Pos.CENTER);
    hbox.getChildren().add(title);
    myPane.getChildren().add(hbox);
  }



  private void addText(String rule) {
    HBox hbox = new HBox();
    Text text = new Text(rule);
    text.setFont(new Font(FONT_SIZE));
    text.maxWidth(Integer.MAX_VALUE);
    text.setTextAlignment(TextAlignment.LEFT);
    hbox.getChildren().add(text);
    HBox.setMargin(text, new Insets(0 , 0, 0, 10));
    myPane.getChildren().add(hbox);
  }



}
