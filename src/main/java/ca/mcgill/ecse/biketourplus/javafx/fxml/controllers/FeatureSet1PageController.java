package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;

import java.sql.Date;
import java.util.List;
import ca.mcgill.ecse.biketourplus.application.BikeTourPlusApplication;
import ca.mcgill.ecse.biketourplus.controller.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import ca.mcgill.ecse.biketourplus.model.*;

public class FeatureSet1PageController{

    @FXML
    private TableView<TOBikeTour> bikeTourTable; 
    
    @FXML
    private TableColumn<TOBikeTour, Integer> idCol;
    
    @FXML
    private TableColumn<TOBikeTour, Integer> startCol;
    
    @FXML
    private TableColumn<TOBikeTour, Integer> endCol;

    @FXML
    private TableColumn<TOBikeTour, String> guideCol;

    @FXML
    private TableColumn<TOBikeTour, List<String>> participantsCol;

    @FXML
    private TextField inputPasswordTextField;

    @FXML
    private Button updatePasswordButton;
    
    @FXML
    private Button refreshButton;
    
    String error = "";

    @FXML
    void updatePasswordClicked(ActionEvent event) {
      ViewUtils.callController(BikeTourPlusFeatureSet1Controller.updateManager(inputPasswordTextField.getText()));
    }
    
    @FXML
    void refreshClicked(ActionEvent event) {
      initialize();
    }
    
    public void initialize() {
      // get list of TOBikeTour to set items in table
//      ViewUtils.callController(BikeTourPlusFeatureSet1Controller.updateBikeTourPlus(Date startDate, int nrWeeks, int priceOfGuidePerWeek));
//      ViewUtils.callController(BikeToursFeatureSetController.initiateBikeTourCreationProcess());
      
      ObservableList<TOBikeTour> bikeTourList = ViewUtils.getTours();
      
      idCol.setCellValueFactory(new PropertyValueFactory<TOBikeTour, Integer>("id"));
      guideCol.setCellValueFactory(new PropertyValueFactory<TOBikeTour, String>("guideName"));
      startCol.setCellValueFactory(new PropertyValueFactory<TOBikeTour, Integer>("startWeek"));
      endCol.setCellValueFactory(new PropertyValueFactory<TOBikeTour, Integer>("endWeek"));
      //bikeTourParticipants.setCellValueFactory(new PropertyValueFactory<TOBikeTour, List<String>>("participants")); //idk how to get participants

      bikeTourTable.setItems(bikeTourList);      
      
    }

}

