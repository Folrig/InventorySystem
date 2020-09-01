/*
Controller that facilitates the data within the data model
through the GUI in the view of the MainScreen
*/

package View_Controller;

import Model.Inventory;
import Model.AddState;
import Model.Part;
import Model.Product;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class MainScreenController implements Initializable {
    Stage stage;
    Parent scene;
    
    @FXML
    private TextField partsTextField;

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Part, Integer> partsIdTableColumn;

    @FXML
    private TableColumn<Part, String> partsNameTableColumn;

    @FXML
    private TableColumn<Part, Integer> partsInvLevelTableColumn;

    @FXML
    private TableColumn<Part, Double> partsPriceTableColumn;

    @FXML
    private TextField productsTextField;

    @FXML
    private TableView<Product> productsTableView;

    @FXML
    private TableColumn<Product, Integer> productsIdTableColumn;

    @FXML
    private TableColumn<Product, String> productsNameTableColumn;

    @FXML
    private TableColumn<Product, Integer> productsInvTableColumn;

    @FXML
    private TableColumn<Product, Double> productsPriceTableColumn;

    @FXML
    void onExitBtnClicked(ActionEvent event) {
        System.exit(0);
    }

    @FXML
    void onPartsAddBtnClicked(ActionEvent event) throws IOException {
        Inventory.setCurrentAddState(AddState.Add);
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setTitle("Add A New Part");
        scene = FXMLLoader.load(getClass().getResource("AddModifyPartScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onPartsDeleteBtnClicked(ActionEvent event) {
        Part partToDelete = partsTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Okay To Delete?");
        alert.setHeaderText("Deleting " + partToDelete.getName());
        alert.setContentText("Click OK to confirm deletion or cancel to go back.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Inventory.deletePart(partToDelete);
            populatePartsTableView();
        }
    }

    @FXML
    void onPartsModifyBtnClicked(ActionEvent event) throws IOException {
        Inventory.setPartToModify(partsTableView.getSelectionModel().getSelectedItem());
        if(Inventory.getPartToModify() != null){
            Inventory.setCurrentAddState(AddState.Modify);
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.setTitle("Modify Existing Part");
            scene = FXMLLoader.load(getClass().getResource("AddModifyPartScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onPartsSearchBtnClicked(ActionEvent event) {
        String searchInput = partsTextField.getText().toLowerCase();
        ObservableList<Part> filteredParts = FXCollections.observableArrayList();
        if(searchInput != null && !"".equals(searchInput)){
            try{
                int searchId = Integer.parseInt(searchInput);
                Part partToReturn = Inventory.lookupPart(searchId);
                if(partToReturn != null){
                    filteredParts.add(partToReturn);
                    partsTableView.setItems(filteredParts);
                    partsIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    partsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                    partsInvLevelTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
                    partsPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                }
                else{
                    populatePartsTableView();
                    showDialogBox("Part");                
                }
            }
            catch(Exception e){
                filteredParts = Inventory.lookupPart(searchInput);
                if(filteredParts != null && !filteredParts.isEmpty()){
                    partsTableView.setItems(filteredParts);
                    partsIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    partsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                    partsInvLevelTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
                    partsPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                }
                else{
                    populatePartsTableView();
                    showDialogBox("Part");
                }
            }
            finally{
                if(searchInput == null || "".equals(searchInput)){
                    populatePartsTableView();
                }
            }
        }
        else{
            populatePartsTableView();
        }
    }

    @FXML
    void onProductsAddBtnClicked(ActionEvent event) throws IOException {
        Inventory.setCurrentAddState(AddState.Add);
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setTitle("Add A New Product");        
        scene = FXMLLoader.load(getClass().getResource("AddModifyProductScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onProductsDeleteBtnClicked(ActionEvent event) {
        Product productToDelete = productsTableView.getSelectionModel().getSelectedItem();
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Okay To Delete?");
        alert.setHeaderText("Deleting " + productToDelete.getName());
        alert.setContentText("Click OK to confirm deletion or cancel to go back.");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            Inventory.deleteProduct(productToDelete);
            populateProductsTableView();
        }
    }

    @FXML
    void onProductsModifyBtnClicked(ActionEvent event) throws IOException {
        Inventory.setProductToModify(productsTableView.getSelectionModel().getSelectedItem());
        if(Inventory.getProductToModify() != null){
            Inventory.setCurrentAddState(AddState.Modify);
            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            stage.setTitle("Modify Existing Product");
            scene = FXMLLoader.load(getClass().getResource("AddModifyProductScreen.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
    }

    @FXML
    void onProductsSearchBtnClicked(ActionEvent event) {
        String searchInput = productsTextField.getText().toLowerCase();
        ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
        if(searchInput != null && !"".equals(searchInput)){
            try{
                int searchId = Integer.parseInt(searchInput);
                Product productToReturn = Inventory.lookupProduct(searchId);
                if(productToReturn != null){
                    filteredProducts.add(productToReturn);
                    productsTableView.setItems(filteredProducts);
                    productsIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    productsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                    productsInvTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
                    productsPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                }
                else{
                    populateProductsTableView();
                    showDialogBox("Product");
                }
            }
            catch(Exception e){
                filteredProducts = Inventory.lookupProduct(searchInput);
                if(filteredProducts != null && !filteredProducts.isEmpty()){
                    productsTableView.setItems(filteredProducts);
                    productsIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    productsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                    productsInvTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
                    productsPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                }
                else{
                    populateProductsTableView();
                    showDialogBox("Product");                
                }
            }
            finally{
                if(searchInput == null || "".equals(searchInput)){
                    populateProductsTableView();
                }
            }
        }
        else{
            populateProductsTableView();
        }
    }

    private void populatePartsTableView(){
        partsTableView.setItems(Inventory.getAllParts());
        partsIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        partsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        partsInvLevelTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        partsPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    
    private void populateProductsTableView(){
        productsTableView.setItems(Inventory.getAllProducts());
        productsIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsInvTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    
    private void showDialogBox(String partOrProd){
        Alert alert = new Alert(AlertType.WARNING);
        alert.setTitle(partOrProd + " Not Found");
        alert.setHeaderText("The " + partOrProd.toLowerCase() + " you were searching for was not found.");
        alert.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populatePartsTableView();
        populateProductsTableView();
    }    
}
