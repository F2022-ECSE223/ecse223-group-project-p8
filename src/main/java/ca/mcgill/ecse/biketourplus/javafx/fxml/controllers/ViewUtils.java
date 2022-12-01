package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;


import java.util.List;
import ca.mcgill.ecse.biketourplus.controller.*;
import ca.mcgill.ecse.biketourplus.javafx.fxml.BikeTourPlusFXMLView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;



// This class is adapted from the BTMS example repository
public class ViewUtils {

  /** Calls the controller and shows an error, if applicable. */
  public static boolean callController(String result) {
    if (result.isEmpty()) {
      // BikeTourPlusFXMLView.getInstance().refresh();
      showSuccess("Successfully made changes");
      return true;
    }
    showError(result);
    return false;
  }

  /** Calls the controller and returns true on success. This method is included for readability. */
  public static boolean successful(String controllerResult) {
    return callController(controllerResult);
  }

  /**
   * Creates a popup window.
   *
   * @param title: title of the popup window
   * @param message: message to display
   */
  public static void makePopupWindow(String title, String message) {
    Stage dialog = new Stage();
    dialog.initModality(Modality.APPLICATION_MODAL);
    VBox dialogPane = new VBox();

    // create UI elements
    Text text = new Text(message);
    Button okButton = new Button("OK");
    okButton.setOnAction(a -> dialog.close());

    // display the popup window
    int innerPadding = 10; // inner padding/spacing
    int outerPadding = 100; // outer padding
    dialogPane.setSpacing(innerPadding);
    dialogPane.setAlignment(Pos.CENTER);
    dialogPane.setPadding(new Insets(innerPadding, innerPadding, innerPadding, innerPadding));
    dialogPane.getChildren().addAll(text, okButton);
    Scene dialogScene = new Scene(dialogPane, outerPadding + 5 * message.length(), outerPadding);
    dialog.setScene(dialogScene);
    dialog.setTitle(title);
    dialog.show();
  }

  public static void showError(String message) {
    makePopupWindow("Error", message);
  }

  public static void showSuccess(String message) {
    makePopupWindow("Success", message);
  }


  /**
   * Feature set 1 helper method gets a list of bike tour transfer objects
   *
   * @return ObservableList<TOBikeTour> tourList
   */
  // public static ObservableList<TOBikeTour> getTours() {
  // int id = 1;
  // ObservableList<TOBikeTour> tourList = FXCollections.observableArrayList();
  // while (true) {
  // try {
  // tourList.add(BikeTourPlusFeatureSet1Controller.getBikeTour(id));
  // //System.out.println("ID " + id + " added");
  // } catch (Exception e) {
  // //System.out.println("Error:" + e.getMessage());
  // return tourList;
  // }
  // }
  // }

  public static boolean isAlpha(String name) {
    for (Character ch : name.toCharArray()) {
      if (!Character.isLetter(ch) && !Character.isWhitespace(ch)) {
        return false;
      }
    }
    return true;
  }

}
