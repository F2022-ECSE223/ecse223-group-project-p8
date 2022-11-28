package ca.mcgill.ecse.biketourplus.javafx.fxml.controllers;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import ca.mcgill.ecse.biketourplus.controller.*;
//import ca.mcgill.ecse.biketourplus.model.BikeTourPlus;
import ca.mcgill.ecse.biketourplus.application.BikeTourPlusApplication;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class FeatureSet1PageController implements Initializable{

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

    @FXML
    void updatePasswordClicked(ActionEvent event) {
      BikeTourPlusFeatureSet1Controller.updateManager(inputPasswordTextField.getText());
    }
    
    // get list of TOBikeTour to set items in table
    BikeTourPlus btp  = BikeTourPlusApplication.getBikeTourPlus();
    ObservableList<TOBikeTour> bikeTourList = FXCollections.observableArrayList(
        
        );

    @Override
    public void initialize(URL location, ResourceBundle resources) {
      // TODO Auto-generated method stub
      BikeTourPlus btp  = BikeTourPlusApplication.getBikeTourPlus();
      
      bikeTourID.setCellValueFactory(new PropertyValueFactory<TOBikeTour, Integer>("id"));
      bikeTourGuide.setCellValueFactory(new PropertyValueFactory<TOBikeTour, String>("guideName"));
      bikeTourStartWeek.setCellValueFactory(new PropertyValueFactory<TOBikeTour, Integer>("startWeek"));
      bikeTourEndWeek.setCellValueFactory(new PropertyValueFactory<TOBikeTour, Integer>("endWeek"));
      bikeTourParticipants.setCellValueFactory(new PropertyValueFactory<TOBikeTour, List<String>>("participants"));

      bikeTourTable.setItems(bikeTourList);
      
    }

}
