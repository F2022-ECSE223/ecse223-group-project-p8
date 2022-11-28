package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;

import javafx.fxml.FXML;
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
	@FXML
	public void deleteGearClicked(ActionEvent event) {
	  BikeTourPlusFeatureSet5Controller.deleteGear(gearNameTextField.getText());
	  gearNameTextField.clear();
	}
	// Event Listener on Button[#updateGearButton].onAction
	@FXML
	public void updateGearClicked(ActionEvent event) {
	  BikeTourPlusFeatureSet5Controller.updateGear(gearNameTextField.getText(), newGearNameTextField.getText(), 100*Integer.parseInt(gearPriceTextField.getText()));
	  gearNameTextField.clear();
	  newGearNameTextField.clear();
	  gearPriceTextField.clear();
	}
	// Event Listener on Button[#addGearButton].onAction
	@FXML
	public void addGearClicked(ActionEvent event) {
	  BikeTourPlusFeatureSet5Controller.addGear(gearNameTextField.getText(), 100*Integer.parseInt(gearPriceTextField.getText()));
	  gearNameTextField.clear();
	  gearPriceTextField.clear();
	}
}
