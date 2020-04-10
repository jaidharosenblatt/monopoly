package ooga.view;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TabViewTester extends Application {
  TabView myTabView;
  @Override
  public void start(Stage primaryStage) throws Exception {
    Group root = new Group();
    createTabs(root);
    Scene scene = new Scene(root,200,200);
    primaryStage.setScene(scene);
    primaryStage.show();
  }

  private void createTabs(Group root) {
    myTabView = new TabView(300,200);
    myTabView.createTab("Rules");
    myTabView.addTabPaneToRoot(root);
  }
}
