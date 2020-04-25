package ooga.view.tabs;

import java.util.*;

import javafx.geometry.Insets;
import javafx.print.Collation;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

/**
 * This class is a child of the DisplayTab and creates a tab for displaying the rules of the game. Right now there are two
 * different ways to update the rules. One is to use the updateTab method. The other is to use the changeRules method
 * which will read in a rule set from a properties file
 */
public class RulesTab extends DisplayTab {
  private static final ResourceBundle myResources = ResourceBundle.getBundle("ooga/view/tabs/TabStringResources");
  private static final String TITLE = myResources.getString("rulesTabTitle");

  private static final int FONT_SIZE = 12;
  private static final int WRAPPING_WIDTH = 300;
  private static final int SPACING = 10;
  private ResourceBundle rules;
  private  Pane myPane;

  /**
   * Construct a rules tab.
   * @param tabName the text to be displayed at the top of the pane
   * @param pane the pane that will house all of the information to be displayed in this tab
   */
  public RulesTab(String tabName, Pane pane) {
    super(tabName, pane);
    this.myPane = pane;
    super.addTitle(TITLE);
    displayRules("Default");
  }

  private void displayRules(String gameType) {
    rules = ResourceBundle.getBundle("ooga/view/tabs/"+gameType + "Rules");
    String [] ruleSet = rules.getString("ruleSet").split(",");
    Collection<Object> gameRules = new LinkedList<>();
    for (String rule : ruleSet){
      String str = rule.toUpperCase()+": " + rules.getString(rule);
      gameRules.add(str);
    }
    updateTab(gameRules);
  }

  /**
   * Change the displayed rules to a new set that come from a properties file. Assumes there is a properties file of the
   * name GameTypeRules.properties that contains the rules.
   * @param gameType the String of the type of game in format GameType
   */
  protected void changeRules(String gameType){
    displayRules(gameType);
  }

  /**
   * The other way to update the rules. This takes in directly the Strings of rules to be displayed.
   * @param info The updated information that needs to be displayed in the respective tab.
   */
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
