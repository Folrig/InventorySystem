package View_Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;

public class AddModifyPartScreenController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    @FXML
    private Label partsAddModLabel;

    @FXML
    private RadioButton partsInhouseRadioBtn;

    @FXML
    private ToggleGroup partsSourceTG;

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
    void onPartsCancelBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onPartsSaveBtnClicked(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
