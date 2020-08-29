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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class MainScreenController implements Initializable {
    
    Stage stage;
    Parent scene;
    
    @FXML
    private TextField partsTextField;

    @FXML
    private TableView<?> partsTableView;

    @FXML
    private TableColumn<?, ?> partsIdTableColumn;

    @FXML
    private TableColumn<?, ?> partsNameTableColumn;

    @FXML
    private TableColumn<?, ?> partsInvLevelTableColumn;

    @FXML
    private TableColumn<?, ?> partsPriceTableColumn;

    @FXML
    private TextField productsTextField;

    @FXML
    private TableView<?> productsTableView;

    @FXML
    private TableColumn<?, ?> productsIdTableColumn;

    @FXML
    private TableColumn<?, ?> productsNameTableColumn;

    @FXML
    private TableColumn<?, ?> productsInvTableColumn;

    @FXML
    private TableColumn<?, ?> productsPriceTableColumn;

    @FXML
    void onExitBtnClicked(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onPartsAddBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddModifyPartScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onPartsDeleteBtnClicked(ActionEvent event) {

    }

    @FXML
    void onPartsModifyBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddModifyPartScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onPartsSearchBtnClicked(ActionEvent event) {

    }

    @FXML
    void onProductsAddBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddModifyProductScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onProductsDeleteBtnClicked(ActionEvent event) {

    }

    @FXML
    void onProductsModifyBtnClicked(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("AddModifyProductScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onProductsSearchBtnClicked(ActionEvent event) {

    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
}
