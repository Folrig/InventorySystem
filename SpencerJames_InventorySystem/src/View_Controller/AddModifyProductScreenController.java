package View_Controller;

import Model.AddState;
import Model.Inventory;
import Model.Part;
import Model.Product;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddModifyProductScreenController implements Initializable {
    
    Stage stage;
    Parent scene;
    Product productToModify;
    
    @FXML
    private Label productsAddModLabel;

    @FXML
    private TextField productsIdTextField;

    @FXML
    private TextField productsNameTextField;

    @FXML
    private TextField productsInvTextField;

    @FXML
    private TextField productsPriceTextField;

    @FXML
    private TextField productsMaxTextField;

    @FXML
    private TextField productsMinTextField;

    @FXML
    private TableView<Part> productsAllPartsTableView;

    @FXML
    private TableColumn<Part, Integer> productsAllPartsIdTableColumn;

    @FXML
    private TableColumn<Part, String> productsAllPartsNameTableColumn;

    @FXML
    private TableColumn<Part, Integer> productsAllPartsInvTableColumn;

    @FXML
    private TableColumn<Part, Double> productsAllPartsPriceTableColumn;

    @FXML
    private TextField productsSearchTextField;

    @FXML
    private TableView<Part> productsUsedPartsTableView;

    @FXML
    private TableColumn<Part, Integer> productsUsedPartsIdTableColumn;

    @FXML
    private TableColumn<Part, String> productsUsedPartsNameTableColumn;

    @FXML
    private TableColumn<Part, Integer> productsUsedPartsInvTableColumn;

    @FXML
    private TableColumn<Part, Double> productsUsedPartsPriceTableColumn;

    @FXML
    void onProductsAddBtnClicked(ActionEvent event) {

    }

    @FXML
    void onProductsCancelBtnClicked(ActionEvent event) throws IOException {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Cancel Products Change?");
        alert.setHeaderText("Return to main menu?");
        alert.setContentText("OK to return to main menu\n"
            + "Cancel to go back to products menu");

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
    void onProductsDeleteBtnClicked(ActionEvent event) {

    }

    @FXML
    void onProductsSaveBtnClicked(ActionEvent event) {

    }

    @FXML
    void onProductsSearchBtnClicked(ActionEvent event) {

    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if(Inventory.getCurrentAddState() == AddState.Add){
            productsAddModLabel.setText("Add Product");
            productsIdTextField.setText(Integer.toString(Inventory.getProductsIdCounter()));
        }
        else{
            productsAddModLabel.setText("Modify Product");
        }
    }       
}