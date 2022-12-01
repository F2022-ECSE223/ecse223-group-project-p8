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
    private TableColumn<TOBikeTourUI, Integer> guideTourCost;
    
    
    @FXML
    private TableView<TOParticipantCost> participantTable; 
    
    @FXML
    private TableColumn<TOParticipantCost, String> nameCol;
    
    @FXML
    private TableColumn<TOParticipantCost, String> statusCol;
    
    @FXML
    private TableColumn<TOParticipantCost, Integer> itemCostCol;

    @FXML
    private TableColumn<TOParticipantCost, Integer> totalCostCol;

    @FXML
    private TableColumn<TOParticipantCost, String> authCol;
    
    @FXML
    private TableColumn<TOParticipantCost, Integer> refundCol;
    
    @FXML
    private TableColumn<TOParticipantCost, Integer> tourCol;
    
    
    
    

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
   * @author Jacques Zaarour
   */
    public void initialize() {
//       get list of TOBikeTour to set items in table
      List<TOBikeTour> bikeTours = new ArrayList();
      List<TOParticipantCost> participants = new ArrayList();
      int id = 1;
      while (true) {
        try {
          TOBikeTour tour = BikeTourPlusFeatureSet1Controller.getBikeTour(id);
//          System.out.println(tour.toString());
          bikeTours.add(tour); 
          participants.addAll(tour.getParticipantCosts());
          id++;
        } catch (Exception e) {
          break;
        }
      }
      
      // get list of TOBikeTourUI
      List<TOBikeTourUI> bikeToursUI = new ArrayList();
      id = 1;
      while (true) {
        try {
          TOBikeTourUI tourUI = BikeTourPlusFeatureSet1Controller.populateTOBikeTourUI(id);
          bikeToursUI.add(tourUI);  
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
      guideTourCost.setCellValueFactory(new PropertyValueFactory<TOBikeTourUI, Integer>("guideTourCost"));
      
      
      
      nameCol.setCellValueFactory(new PropertyValueFactory<TOParticipantCost, String>("participantName"));
      statusCol.setCellValueFactory(new PropertyValueFactory<TOParticipantCost, String>("status"));
      itemCostCol.setCellValueFactory(new PropertyValueFactory<TOParticipantCost, Integer>("totalCostForBookableItems"));
      totalCostCol.setCellValueFactory(new PropertyValueFactory<TOParticipantCost, Integer>("totalCostForBikingTour"));
      authCol.setCellValueFactory(new PropertyValueFactory<TOParticipantCost, String>("authorizationCode"));
      refundCol.setCellValueFactory(new PropertyValueFactory<TOParticipantCost, Integer>("refundedPercentageAmount"));
      tourCol.setCellValueFactory(new PropertyValueFactory<TOParticipantCost, Integer>("tourID"));

      
      
      participantTable.getItems().setAll(participants);
//      bikeTourTable.getItems().setAll(bikeTours);
      bikeTourTable.getItems().setAll(bikeToursUI);
    }
}

