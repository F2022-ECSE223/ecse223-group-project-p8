package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;

import java.util.List;

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

public class FeatureSet1PageController{

    @FXML
    private TableView<TOBikeTour> bikeTourTable; 
    
    @FXML
    private TableColumn<TOBikeTour, Integer> bikeTourID;
    
    @FXML
    private TableColumn<TOBikeTour, Integer> bikeTourStartWeek;
    
    @FXML
    private TableColumn<TOBikeTour, Integer> bikeTourEndWeek;

    @FXML
    private TableColumn<TOBikeTour, String> bikeTourGuide;

    @FXML
    private TableColumn<TOBikeTour, List<String>> bikeTourParticipants;

    @FXML
    private TextField inputPasswordTextField;

    @FXML
    private Button updatePasswordButton;
    
    String error = "";

    @FXML
    void updatePasswordClicked(ActionEvent event) {
      try {
        error = BikeTourPlusFeatureSet1Controller.updateManager(inputPasswordTextField.getText());
      } catch (Exception e) {
        System.out.println(error);
      }
    }
    
    public void initialize() {
      // get list of TOBikeTour to set items in table
      boolean idValid = true;
      int id = 0;
      ObservableList<TOBikeTour> bikeTourList = FXCollections.observableArrayList();
      
      while(idValid) {
        try {
          bikeTourList.add(BikeTourPlusFeatureSet1Controller.getBikeTour(id));
          id++;
        } catch (Exception e) {
          idValid = false; // no more bike tours to display
        }
      }

      bikeTourID.setCellValueFactory(new PropertyValueFactory<TOBikeTour, Integer>("id"));
      bikeTourGuide.setCellValueFactory(new PropertyValueFactory<TOBikeTour, String>("guideName"));
      bikeTourStartWeek.setCellValueFactory(new PropertyValueFactory<TOBikeTour, Integer>("startWeek"));
      bikeTourEndWeek.setCellValueFactory(new PropertyValueFactory<TOBikeTour, Integer>("endWeek"));
      //bikeTourParticipants.setCellValueFactory(new PropertyValueFactory<TOBikeTour, List<String>>("participants")); //idk how to get participants

      bikeTourTable.setItems(bikeTourList);
      
    }

}

