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
    private TableColumn<TOBikeTour, List<TOParticipantCost>> participantsCol;
    
//    @FXML
//    private TableView<TOParticipantCost> participantTable; 
//    
//    @FXML
//    private TableColumn<TOParticipantCost, String> nameCol;
//    
//    @FXML
//    private TableColumn<TOParticipantCost, String> statusCol;
//    
//    @FXML
//    private TableColumn<TOParticipantCost, Integer> itemCostCol;
//
//    @FXML
//    private TableColumn<TOParticipantCost, Integer> totalCostCol;
//
//    @FXML
//    private TableColumn<TOParticipantCost, String> authCol;
//    
//    @FXML
//    private TableColumn<TOParticipantCost, Integer> refundCol;
    
    
    
    

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
      // get list of TOBikeTour to set items in table
      List<TOBikeTour> bikeTours = new ArrayList();
      //List<TOParticipantCost> participants = new ArrayList();
      int id = 1;
      while (true) {
        try {
          TOBikeTour tour = BikeTourPlusFeatureSet1Controller.getBikeTour(id);
          //System.out.println(tour.toString());
          bikeTours.add(tour); 
          //participants.addAll(tour.getParticipantCosts());
          id++;
        } catch (Exception e) {
          break;
        }
      }
      
      idCol.setCellValueFactory(new PropertyValueFactory<TOBikeTour, Integer>("id"));
      guideCol.setCellValueFactory(new PropertyValueFactory<TOBikeTour, String>("guideName"));
      startCol.setCellValueFactory(new PropertyValueFactory<TOBikeTour, Integer>("startWeek"));
      endCol.setCellValueFactory(new PropertyValueFactory<TOBikeTour, Integer>("endWeek"));
      participantsCol.setCellValueFactory(new PropertyValueFactory<TOBikeTour, List<TOParticipantCost>>("participantCosts"));
      
      
      
//      nameCol.setCellValueFactory(new PropertyValueFactory<TOParticipantCost, String>("participantName"));
//      statusCol.setCellValueFactory(new PropertyValueFactory<TOParticipantCost, String>("status"));
//      itemCostCol.setCellValueFactory(new PropertyValueFactory<TOParticipantCost, Integer>("totalCostForBookableItems"));
//      totalCostCol.setCellValueFactory(new PropertyValueFactory<TOParticipantCost, Integer>("totalCostForBikingTour"));
//      authCol.setCellValueFactory(new PropertyValueFactory<TOParticipantCost, String>("authorizationCode"));
//      refundCol.setCellValueFactory(new PropertyValueFactory<TOParticipantCost, Integer>("refundedPercentageAmount"));
//      
//      
//      participantTable.getItems().setAll(participants);
      bikeTourTable.getItems().setAll(bikeTours);
    }
}

