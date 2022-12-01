package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;

import javafx.fxml.FXML;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import ca.mcgill.ecse.biketourplus.controller.BikeToursFeatureSetController;
import javafx.event.ActionEvent;

public class FeatureSet9PageController {
  @FXML
  private TextField weekTextField;
  @FXML
  private Button finishButton;
  @FXML
  private Button startButton;
  @FXML
  private TextField participantEmailTextField;
  @FXML
  private Button cancelButton;

  // Event Listener on Button[#finishButton].onAction
  /**
   * This finishes the trip for the participant
   * 
   * @param event
   * @author Brian Morava
   */
  @FXML
  public void finishClicked(ActionEvent event) {
    if (participantEmailTextField.getText().equals("")) {
      // JOptionPane.showMessageDialog(null, "Email text field is
      // empty.","ERROR",JOptionPane.ERROR_MESSAGE);
      ViewUtils.makePopupWindow("ERROR", "Email text field is empty.");
    } else {
      String msg =
          BikeToursFeatureSetController.finishParticipantTrip(participantEmailTextField.getText());
      if (msg.equals("Participant with email address " + participantEmailTextField.getText()
          + " does not exist")) {
        // JOptionPane.showMessageDialog(null, "Participant with email address " +
        // participantEmailTextField.getText() + " does not
        // exist","ERROR",JOptionPane.ERROR_MESSAGE);
        ViewUtils.makePopupWindow("ERROR", "Participant with email address "
            + participantEmailTextField.getText() + " does not exist");
      }
      participantEmailTextField.clear();
      weekTextField.clear();
    }
  }

  // Event Listener on Button[#startButton].onAction
  /**
   * This starts the trip for the inputted week
   * 
   * @param event
   * @author Brian Morava
   */
  @FXML
  public void startClicked(ActionEvent event) {
    if (weekTextField.getText().equals("")) {
      // JOptionPane.showMessageDialog(null, "Week not
      // specified","ERROR",JOptionPane.ERROR_MESSAGE);
      ViewUtils.makePopupWindow("ERROR", "Week not specified");
    } else {
      try {
        String msg =
            BikeToursFeatureSetController.startWeekTrips(Integer.parseInt(weekTextField.getText()));
        if (Integer.parseInt(weekTextField.getText()) < 0) {
          // JOptionPane.showMessageDialog(null, "Week specified cannot be a negative
          // value","ERROR",JOptionPane.ERROR_MESSAGE);
          ViewUtils.makePopupWindow("ERROR", "Week specified cannot be a negative value");
        } else if (!msg.equals("")) {
          // JOptionPane.showMessageDialog(null, "Could not successfully start all trips for
          // specific week\n" + msg,"ERROR",JOptionPane.ERROR_MESSAGE);
          ViewUtils.makePopupWindow("ERROR",
              "Could not successfully start all trips for specific week\n" + msg);
        }
      } catch (Exception e) {
        // JOptionPane.showMessageDialog(null, "Week specified must be an integer
        // value","ERROR",JOptionPane.ERROR_MESSAGE);
        ViewUtils.makePopupWindow("ERROR", "Week specified must be an integer value");
      }
    }
    weekTextField.clear();
    participantEmailTextField.clear();
  }

  // Event Listener on Button[#cancelButton].onAction
  /**
   * This cancels the trip for the participant
   * 
   * @param event
   * @author Brian Morava
   */
  @FXML
  public void cancelClicked(ActionEvent event) {
    if (participantEmailTextField.getText().equals("")) {
      // JOptionPane.showMessageDialog(null, "Email text field is
      // empty.","ERROR",JOptionPane.ERROR_MESSAGE);
      ViewUtils.makePopupWindow("ERROR", "Email text field is empty");
    } else {
      String msg =
          BikeToursFeatureSetController.cancelParticipantTrip(participantEmailTextField.getText());
      if (msg.equals("Participant with email address " + participantEmailTextField.getText()
          + " does not exist")) {
        // JOptionPane.showMessageDialog(null, "Participant with email address " +
        // participantEmailTextField.getText() + " does not
        // exist","ERROR",JOptionPane.ERROR_MESSAGE);
        ViewUtils.makePopupWindow("ERROR", "Participant with email address "
            + participantEmailTextField.getText() + " does not exist");
      }
      participantEmailTextField.clear();
      weekTextField.clear();
    }
  }
}
