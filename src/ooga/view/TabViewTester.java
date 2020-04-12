package ooga.view;

import java.util.List;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TabViewTester extends Application {
  TabView myTabView;
  private int rulesTabID;
  @Override
  public void start(Stage primaryStage) throws Exception {
    Group root = new Group();
    createTabs(root);
    Scene scene = new Scene(root,300,200);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void createTabs(Group root) {
     myTabView = new TabView(300,200);
    rulesTabID = myTabView.createTab("Rules");
    myTabView.addTabPaneToRoot(root);
    myTabView.updateTab(rulesTabID, List.of("Rule1", "Rule2", "Rule3"));
  }
}
