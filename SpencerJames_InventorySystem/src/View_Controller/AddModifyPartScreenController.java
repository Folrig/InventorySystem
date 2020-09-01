/*
Controller that facilitates the data within the data model
through the GUI in the view of the AddModifyPartScreen
*/

package View_Controller;

import Model.*;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddModifyPartScreenController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    @FXML
    private Label partsAddModLabel;

    @FXML
    private RadioButton partsInhouseRadioBtn;

    @FXML
    private RadioButton partsOutsourcedRadioBtn;

    @FXML
    private Label partsSourceLabel;

    @FXML
    private TextField partsIDTextField;

    @FXML
    private TextField partsNameTextView;

    @FXML
    private TextField partsInvTextField;

    @FXML
    private TextField partsPriceTextField;

    @FXML
    private TextField partsMaxTextField;

    @FXML
    private TextField partsSourceTextField;

    @FXML
    private TextField partsMinTextField;

    @FXML
    void onPartsInhouseRadioBtnTog(ActionEvent event) {
        partsSourceLabel.setText("Machine ID");
        partsSourceTextField.clear();
        partsSourceTextField.setPromptText("Mach ID");
    }

    @FXML
    void onPartsOutsourcedRadioBtnTog(ActionEvent event) {
        partsSourceLabel.setText("Company Name");
        partsSourceTextField.clear();
        partsSourceTextField.setPromptText("Comp Name");
    }
    
    @FXML
    void onPartsCancelBtnClicked(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Parts Change?");
        alert.setHeaderText("Return to main menu?");
        alert.setContentText("OK to return to main menu\n"
            + "Cancel to go back to parts menu");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.setTitle("Main Menu");
            scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onPartsSaveBtnClicked(ActionEvent event) throws IOException {
        int idInput = Integer.parseInt(partsIDTextField.getText());
        String nameInput = partsNameTextView.getText();
        int invInput = Integer.parseInt(partsInvTextField.getText());
        double priceInput = Double.parseDouble(partsPriceTextField.getText());
        int maxInput = Integer.parseInt(partsMaxTextField.getText());
        String sourceInput = partsSourceTextField.getText();
        int minInput = Integer.parseInt(partsMinTextField.getText());
        
        if(partsInhouseRadioBtn.isSelected()){
            InHouse partToAdd = new InHouse(idInput, nameInput, priceInput,
                invInput, minInput, maxInput, Integer.parseInt(sourceInput));
            if(Inventory.getCurrentAddState() == AddState.Add){
                Inventory.addPart(partToAdd);
            }
            else{
                Inventory.updatePart(0, partToAdd);
            }
        }
        else{
            Outsourced partToAdd = new Outsourced(idInput, nameInput,
                priceInput, invInput, minInput, maxInput, sourceInput);
            if(Inventory.getCurrentAddState() == AddState.Add){
                Inventory.addPart(partToAdd);
            }
            else{
                Inventory.updatePart(0, partToAdd);
            }
        }
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setTitle("Main Menu");
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (Inventory.getCurrentAddState() == AddState.Add){
            partsAddModLabel.setText("Add Part");
            partsIDTextField.setText(Integer.toString(Inventory.getPartsIdCounter()));
        }
        else{
            partsAddModLabel.setText("Modify Part");
            partsIDTextField.setText(Integer.toString(Inventory.getPartToModify().getId()));
            partsNameTextView.setText(Inventory.getPartToModify().getName());
            partsInvTextField.setText(Integer.toString(Inventory.getPartToModify().getStock()));
            partsPriceTextField.setText(Double.toString(Inventory.getPartToModify().getPrice()));
            partsMaxTextField.setText(Integer.toString(Inventory.getPartToModify().getMax()));
            if(Inventory.getPartToModify() instanceof InHouse){
                partsInhouseRadioBtn.setSelected(true);
                partsOutsourcedRadioBtn.setSelected(false);
                InHouse tempPart = (InHouse)Inventory.getPartToModify();
                partsSourceTextField.setText(Integer.toString(tempPart.getMachineId()));
            }
            else{
                partsInhouseRadioBtn.setSelected(false);
                partsOutsourcedRadioBtn.setSelected(true);
                Outsourced tempPart = (Outsourced)Inventory.getPartToModify();
                partsSourceLabel.setText("Company Name");
                partsSourceTextField.setText(tempPart.getCompanyName());
            }
            partsMinTextField.setText(Integer.toString(Inventory.getPartToModify().getMin()));
        }
    }    
}
