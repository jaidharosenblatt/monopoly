package ooga.view.tabs;

import java.util.*;

import javafx.geometry.Insets;
import javafx.print.Collation;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;


public class RulesTab extends DisplayTab {
  private static final ResourceBundle myResources = ResourceBundle.getBundle("ooga/view/tabs/TabStringResources");
  private static final String TITLE = myResources.getString("rulesTabTitle");
  private static final ResourceBundle rules = ResourceBundle.getBundle("ooga/view/tabs/DefaultRules");
  private static final int FONT_SIZE = 12;
  private static final int WRAPPING_WIDTH = 300;
  private static final int SPACING = 10;
  private  Pane myPane;

  public RulesTab(String tabName, Pane pane) {
    super(tabName, pane);
    this.myPane = pane;
    super.addTitle(TITLE);
    displayDefaultRules();
  }

  private void displayDefaultRules() {
    String [] ruleSet = rules.getString("ruleSet").split(",");
    Collection<Object> gameRules = new LinkedList<>();
    for (String rule : ruleSet){
      String str = rule.toUpperCase()+": " + rules.getString(rule);
      gameRules.add(str);
    }
    updateTab(gameRules);
  }


  @Override
  void updateTab(Collection<Object> info) {
    myPane.getChildren().clear();
    addTitle(TITLE);
    for (Object rule: info){
      try{
        addText((String) rule);
      }
      catch (ClassCastException exception){
        System.out.println(myResources.getString("stringException"));
      }
    }
  }





  private void addText(String rule) {
    HBox hbox = new HBox();
    Text text = new Text(rule);
    text.setFont(new Font(FONT_SIZE));
    text.setWrappingWidth(WRAPPING_WIDTH); //TODO:make this not hard-coded
    text.setTextAlignment(TextAlignment.LEFT);
    hbox.getChildren().add(text);
    HBox.setMargin(text, new Insets(0 , 0, 0, SPACING));
    myPane.getChildren().add(hbox);
  }



}
