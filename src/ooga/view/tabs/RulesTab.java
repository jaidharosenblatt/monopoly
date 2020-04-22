package ooga.view.tabs;

import java.util.List;
import javafx.geometry.Insets;
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
    super.addTitle(TITLE);
    addText("a (10 points).Find a fair gamble with two outcomes that would strictlydecrease Bob’s expected utility.b (10 points).Find a fair gamble with two outcomes that would strictlyincrease Bob’s expected utility.2. Finding Nash equilibria of normal-form games. (50 points.)Findallthe Nash equilibria of each of the following five two-player normal-form games.  Argue why the games have no other Nash equilibria.  (Hint:  forsome of these games, you may wish to use strict dominance or iterated strictdominance, because any strategy eliminated by (iterated) strict dominance can-not get positive probability in any Nash equilibrium.  Also keep in mind thatyou may want to use strict dominance by a mixed strategy.)");
    addText("a (10 points).Find a fair gamble with two outcomes that would strictlydecrease Bob’s expected utility.b (10 points).Find a fair gamble with two outcomes that would strictlyincrease Bob’s expected utility.2. Finding Nash equilibria of normal-form games. (50 points.)Findallthe Nash equilibria of each of the following five two-player normal-form games.  Argue why the games have no other Nash equilibria.  (Hint:  forsome of these games, you may wish to use strict dominance or iterated strictdominance, because any strategy eliminated by (iterated) strict dominance can-not get positive probability in any Nash equilibrium.  Also keep in mind thatyou may want to use strict dominance by a mixed strategy.)");
    addText("a (10 points).Find a fair gamble with two outcomes that would strictlydecrease Bob’s expected utility.b (10 points).Find a fair gamble with two outcomes that would strictlyincrease Bob’s expected utility.2. Finding Nash equilibria of normal-form games. (50 points.)Findallthe Nash equilibria of each of the following five two-player normal-form games.  Argue why the games have no other Nash equilibria.  (Hint:  forsome of these games, you may wish to use strict dominance or iterated strictdominance, because any strategy eliminated by (iterated) strict dominance can-not get positive probability in any Nash equilibrium.  Also keep in mind thatyou may want to use strict dominance by a mixed strategy.)");
    addText("a (10 points).Find a fair gamble with two outcomes that would strictlydecrease Bob’s expected utility.b (10 points).Find a fair gamble with two outcomes that would strictlyincrease Bob’s expected utility.2. Finding Nash equilibria of normal-form games. (50 points.)Findallthe Nash equilibria of each of the following five two-player normal-form games.  Argue why the games have no other Nash equilibria.  (Hint:  forsome of these games, you may wish to use strict dominance or iterated strictdominance, because any strategy eliminated by (iterated) strict dominance can-not get positive probability in any Nash equilibrium.  Also keep in mind thatyou may want to use strict dominance by a mixed strategy.)");

    addText("a (10 points).Find a fair gamble with two outcomes that would strictlydecrease Bob’s expected utility.b (10 points).Find a fair gamble with two outcomes that would strictlyincrease Bob’s expected utility.2. Finding Nash equilibria of normal-form games. (50 points.)Findallthe Nash equilibria of each of the following five two-player normal-form games.  Argue why the games have no other Nash equilibria.  (Hint:  forsome of these games, you may wish to use strict dominance or iterated strictdominance, because any strategy eliminated by (iterated) strict dominance can-not get positive probability in any Nash equilibrium.  Also keep in mind thatyou may want to use strict dominance by a mixed strategy.)");

  }



  @Override
  void updateTab(List<Object> info) {
    myPane.getChildren().clear();
    addTitle(TITLE);
    for (Object rule: info){
      try{
        addText((String) rule);
      }
      catch (Exception exception){
        System.out.println("Rules tab only accepts Strings");
      }
    }
  }





  private void addText(String rule) {
    HBox hbox = new HBox();
    Text text = new Text(rule);
    text.setFont(new Font(FONT_SIZE));
    text.setWrappingWidth(250); //TODO:make this not hard-coded
    text.setTextAlignment(TextAlignment.LEFT);
    hbox.getChildren().add(text);
    HBox.setMargin(text, new Insets(0 , 0, 0, 10));
    myPane.getChildren().add(hbox);
  }



}
