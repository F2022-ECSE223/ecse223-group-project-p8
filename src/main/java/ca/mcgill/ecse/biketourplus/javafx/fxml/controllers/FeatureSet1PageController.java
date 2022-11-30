package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;

import java.util.ArrayList;
import java.util.List;
import ca.mcgill.ecse.biketourplus.controller.*;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;


public class FeatureSet1PageController{

    @FXML
    private TableView<TOBikeTourUI> bikeTourTable; 
    
    @FXML
    private TableColumn<TOBikeTourUI, Integer> idCol;
    
    @FXML
    private TableColumn<TOBikeTourUI, Integer> startCol;
    
    @FXML
    private TableColumn<TOBikeTourUI, Integer> endCol;

    @FXML
    private TableColumn<TOBikeTourUI, String> guideCol;

    @FXML
    private TableColumn<TOBikeTourUI, String> participantsCol;

    @FXML
    private TextField inputPasswordTextField;

    @FXML
    private Button updatePasswordButton;
    
    @FXML
    private Button refreshButton;
    
    String error = "";

  /**
   * This method updates the managers password with the password in the corresponding text field
   * @author LukeBebee
   */
    @FXML
    void updatePasswordClicked(ActionEvent event) {
      ViewUtils.callController(BikeTourPlusFeatureSet1Controller.updateManager(inputPasswordTextField.getText()));
    }

  /**
   * This method refreshes the table
   * @author LukeBebee
   */
    @FXML
    void refreshClicked(ActionEvent event) {
      initialize();
    }
    
  /**
   * This method initializes the table with values from transfer objects
   * @author LukeBebee
   */
    public void initialize() {
      // get list of TOBikeTourUI to set items in table
      List<TOBikeTourUI> bikeTours = new ArrayList();
      int id = 1;
      while (true) {
        try {
          TOBikeTourUI tour = BikeTourPlusFeatureSet1Controller.populateTOBikeTourUI(id);
          bikeTours.add(tour);  
          id++;
        } catch (Exception e) {
          break;
        }
      }
      
      idCol.setCellValueFactory(new PropertyValueFactory<TOBikeTourUI, Integer>("id"));
      guideCol.setCellValueFactory(new PropertyValueFactory<TOBikeTourUI, String>("guideName"));
      startCol.setCellValueFactory(new PropertyValueFactory<TOBikeTourUI, Integer>("startWeek"));
      endCol.setCellValueFactory(new PropertyValueFactory<TOBikeTourUI, Integer>("endWeek"));
      participantsCol.setCellValueFactory(new PropertyValueFactory<TOBikeTourUI, String>("participants"));
      bikeTourTable.getItems().setAll(bikeTours);

    }
  }

