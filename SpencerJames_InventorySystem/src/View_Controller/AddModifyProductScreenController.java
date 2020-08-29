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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddModifyProductScreenController implements Initializable {
    
    Stage stage;
    Parent scene;
    
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
    private TableView<?> productsAllPartsTableView;

    @FXML
    private TableColumn<?, ?> productsAllPartsIdTableColumn;

    @FXML
    private TableColumn<?, ?> productsAllPartsNameTableColumn;

    @FXML
    private TableColumn<?, ?> productsAllPartsInvTableColumn;

    @FXML
    private TableColumn<?, ?> productsAllPartsPriceTableColumn;

    @FXML
    private TextField productsSearchTextField;

    @FXML
    private TableView<?> productsUsedPartsTableView;

    @FXML
    private TableColumn<?, ?> productsUsedPartsIdTableColumn;

    @FXML
    private TableColumn<?, ?> productsUsedPartsNameTableColumn;

    @FXML
    private TableColumn<?, ?> productsUsedPartsInvTableColumn;

    @FXML
    private TableColumn<?, ?> productsUsedPartsPriceTableColumn;

    @FXML
    void onProductsAddBtnClicked(ActionEvent event) {

    }

    @FXML
    void onProductsCancelBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
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
        // TODO
    }       
}