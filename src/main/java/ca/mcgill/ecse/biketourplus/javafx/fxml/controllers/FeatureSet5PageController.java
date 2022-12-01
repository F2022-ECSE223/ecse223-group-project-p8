package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;

import javafx.fxml.FXML;
import javax.swing.JOptionPane;
import ca.mcgill.ecse.biketourplus.Persistence.BikeTourPlusPersistence;
import ca.mcgill.ecse.biketourplus.application.BikeTourPlusApplication;
import ca.mcgill.ecse.biketourplus.controller.*;

import javafx.scene.control.Button;

import javafx.scene.control.TextField;

import javafx.event.ActionEvent;

import javafx.scene.control.TableColumn;

public class FeatureSet5PageController {
  @FXML
  private TextField gearNameTextField;
  @FXML
  private TextField newGearNameTextField;
  @FXML
  private TextField gearPriceTextField;
  @FXML
  private Button deleteGearClickedButton;
  @FXML
  private Button updateGearButton;
  @FXML
  private Button addGearButton;
  @FXML
  private TableColumn bikeTourTable;

  // Event Listener on Button[#deleteGearClickedButton].onAction
  /**
   * this deletes the inputted gear
   * 
   * @param event
   * @author Brian Morava
   */
  @FXML
  public void deleteGearClicked(ActionEvent event) {
    if (gearNameTextField.getText().equals("")) {
      // JOptionPane.showMessageDialog(null, "Gear name text field is
      // empty.","ERROR",JOptionPane.ERROR_MESSAGE);
      ViewUtils.makePopupWindow("ERROR", "Gear name text field is empty.");
    } else {
      String msg = BikeTourPlusFeatureSet5Controller.deleteGear(gearNameTextField.getText());
      if (!msg.equals("")) {
        // JOptionPane.showMessageDialog(null, "The piece of gear is in a combo and cannot be
        // deleted","ERROR",JOptionPane.ERROR_MESSAGE);
        ViewUtils.makePopupWindow("ERROR", "The piece of gear is in a combo and cannot be deleted");
      }
      gearNameTextField.clear();
      newGearNameTextField.clear();
      gearPriceTextField.clear();
    }
  }

  // Event Listener on Button[#updateGearButton].onAction
  /**
   * This updates the gear item inputted
   * 
   * @param event
   * @author Brian Morava
   */
  @FXML
  public void updateGearClicked(ActionEvent event) {
    if (gearNameTextField.getText().equals("")) {
      // JOptionPane.showMessageDialog(null, "Gear name text field is
      // empty.","ERROR",JOptionPane.ERROR_MESSAGE);
      ViewUtils.makePopupWindow("ERROR", "Gear name text field is empty.");
    } else if (gearPriceTextField.getText().equals("")) {
      // JOptionPane.showMessageDialog(null, "Gear price text field is
      // empty.","ERROR",JOptionPane.ERROR_MESSAGE);
      ViewUtils.makePopupWindow("ERROR", "Gear price text field is empty.");
    } else {
      try {
        if (newGearNameTextField.getText().equals("")) {
          newGearNameTextField.setText(gearNameTextField.getText());
        }
        String msg = BikeTourPlusFeatureSet5Controller.updateGear(gearNameTextField.getText(),
            newGearNameTextField.getText(),
            (int) (100 * Double.parseDouble(gearPriceTextField.getText())));
        if (msg.equals("A combo with the same name already exists")) {
          // JOptionPane.showMessageDialog(null, "A combo with the same name already
          // exists","ERROR",JOptionPane.ERROR_MESSAGE);
          ViewUtils.makePopupWindow("ERROR", "A combo with the same name already exists");
        } else if (msg.equals("A piece of gear with the same name already exists")) {
          // JOptionPane.showMessageDialog(null, "A piece of gear with the same name already
          // exists","ERROR",JOptionPane.ERROR_MESSAGE);
          ViewUtils.makePopupWindow("ERROR", "A piece of gear with the same name already exists");
        } else if (msg.equals("The piece of gear does not exist")) {
          // JOptionPane.showMessageDialog(null, "The piece of gear does not
          // exist","ERROR",JOptionPane.ERROR_MESSAGE);
          ViewUtils.makePopupWindow("ERROR", "The piece of gear does not exist");
        } else if (msg.equals("The name must not be empty")) {
          // JOptionPane.showMessageDialog(null, "New gear name text field is
          // empty.","ERROR",JOptionPane.ERROR_MESSAGE);
          ViewUtils.makePopupWindow("ERROR", "New gear name text field is empty");
        } else if (msg.equals("The price per week must be greater than or equal to 0")) {
          // JOptionPane.showMessageDialog(null, "New gear price cannot be
          // negative.","ERROR",JOptionPane.ERROR_MESSAGE);
          ViewUtils.makePopupWindow("ERROR", "New gear price cannot be negative.");
        }
      } catch (Exception e) {
        // JOptionPane.showMessageDialog(null, "Price entered isn't a numerical
        // value","ERROR",JOptionPane.ERROR_MESSAGE);
        ViewUtils.makePopupWindow("ERROR", "Price entered isn't a numerical value");
      }
    }
    gearNameTextField.clear();
    newGearNameTextField.clear();
    gearPriceTextField.clear();
  }

  // Event Listener on Button[#addGearButton].onAction
  /**
   * This adds the gear item inputted
   * 
   * @param event
   * @author Brian Morava
   */
  @FXML
  public void addGearClicked(ActionEvent event) {
    if (gearPriceTextField.getText().equals("")) {
      // JOptionPane.showMessageDialog(null, "Gear price text field is
      // empty.","ERROR",JOptionPane.ERROR_MESSAGE);
      ViewUtils.makePopupWindow("ERROR", "Gear price text field is empty.");
    } else {
      try {
        String msg = BikeTourPlusFeatureSet5Controller.addGear(gearNameTextField.getText(),
            (int) (Double.parseDouble(gearPriceTextField.getText())));
        if (!msg.equals("")) {
          // JOptionPane.showMessageDialog(null, msg,"ERROR",JOptionPane.ERROR_MESSAGE);
          ViewUtils.makePopupWindow("ERROR", msg);
        }
      } catch (Exception e) {
        // JOptionPane.showMessageDialog(null, "Price entered isn't a numerical
        // value","ERROR",JOptionPane.ERROR_MESSAGE);
        ViewUtils.makePopupWindow("ERROR", "Price entered isn't a numerical value");
      }
    }
    gearNameTextField.clear();
    newGearNameTextField.clear();
    gearPriceTextField.clear();
  }
}
