package ca.mcgill.ecse.biketourplus.javafx.fxml;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BikeTourPlusFXMLView extends Application {

  public static final EventType<Event> REFRESH_EVENT = new EventType<>("REFRESH");
  private static BikeTourPlusFXMLView instance;
  private List<Node> refreshableNodes = new ArrayList<>();



  @Override
  /**
   * Sets the size of the main window and starts the application
   * from btp example
   * @param primaryStage The stage we're using for the application
   */
  public void start(Stage primaryStage) {
    try {
      var root = (Pane) FXMLLoader.load(getClass().getResource("MainPage.fxml"));
      var scene = new Scene(root);
      primaryStage.setScene(scene);
      primaryStage.setMinWidth(800);
      primaryStage.setMinHeight(600);
      primaryStage.setTitle("BikeTourPlus");
      primaryStage.show();
      refresh();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public static BikeTourPlusFXMLView getInstance() {
    return instance;
  }

  // Register the node for receiving refresh events
  public void registerRefreshEvent(Node node) {
    refreshableNodes.add(node);
  }

  // Register multiple nodes for receiving refresh events
  public void registerRefreshEvent(Node... nodes) {
    for (var node : nodes) {
      refreshableNodes.add(node);
    }
  }

  // remove the node from receiving refresh events
  public void removeRefreshableNode(Node node) {
    refreshableNodes.remove(node);
  }

  // fire the refresh event to all registered nodes
  public void refresh() {
    for (Node node : refreshableNodes) {
      node.fireEvent(new Event(REFRESH_EVENT));
    }
  }


}
