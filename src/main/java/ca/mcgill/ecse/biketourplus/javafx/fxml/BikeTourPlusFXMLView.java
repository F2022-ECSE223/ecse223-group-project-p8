package ca.mcgill.ecse.biketourplus.javafx.fxml;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class BikeTourPlusFXMLView extends Application {
  
  
  

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
  
  
  
}