package ooga.view;

import java.util.List;
import javafx.scene.control.Tab;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;


public class RulesTab extends Tab {
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

  private void addTitle() {
    title = new Text(TITLE);
    title.setFont(new Font(20));
    myPane.getChildren().add(title);
  }

  protected void updateInfo(List<String> info){
    myPane.getChildren().clear();
    addTitle();
    for (String rule: info){
      addText(rule);
    }
  }

  private void addText(String rule) {
    Text text = new Text(rule);
    text.setFont(new Font(FONT_SIZE));
    myPane.getChildren().add(text);
  }

  @Override


}
