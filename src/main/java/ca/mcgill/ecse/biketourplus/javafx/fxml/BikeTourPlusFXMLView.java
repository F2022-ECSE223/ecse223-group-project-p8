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
  
  private static BikeTourPlusFXMLView instance;
  public static final EventType<Event> REFRESH_EVENT = new EventType<>("REFRESH");
  private List<Node> refreshableNodes = new ArrayList<>();


  

  @Override
  /**
   * Sets the size of the main window and starts the application
   * 
   * @param primaryStage The stage we're using for the application
   * @author 
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
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  
  
  public static BikeTourPlusFXMLView getInstance() {
    return instance;
  }
  
  // fire the refresh event to all registered nodes
  public void refresh() {
    for (Node node : refreshableNodes) {
      node.fireEvent(new Event(REFRESH_EVENT));
    }
  }
  
  
}