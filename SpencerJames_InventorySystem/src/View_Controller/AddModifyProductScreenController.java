/*
Controller that facilitates the data within the data model
through the GUI in the view of the AddModifyProductScreen
*/

package View_Controller;

import Model.AddState;
import Model.Inventory;
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
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class AddModifyProductScreenController implements Initializable {
    
    Stage stage;
    Parent scene;
    ObservableList<Part> productAssociatedPartsTempList = FXCollections.observableArrayList();
    
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
        Part partToAdd = productsAllPartsTableView.getSelectionModel().getSelectedItem();
        productAssociatedPartsTempList.add(partToAdd);
        populateUsedPartsTableView();
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
        if(productsUsedPartsTableView.getSelectionModel().getSelectedItem() != null){
            Part partToDelete = productsUsedPartsTableView.getSelectionModel().getSelectedItem();
            Part partInTempList = null;
            for(Part part : productAssociatedPartsTempList){
                if(part.getId() == partToDelete.getId()){
                    partInTempList = partToDelete;
                }
            }
            if(partInTempList != null){
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Okay To Delete?");
                alert.setHeaderText("Deleting " + partInTempList.getName());
                alert.setContentText("Click OK to confirm deletion or cancel to go back.");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.get() == ButtonType.OK){
                    productAssociatedPartsTempList.remove(partInTempList);
                }
            }
        }
    }

    @FXML
    void onProductsSaveBtnClicked(ActionEvent event) throws IOException {
        if(productAssociatedPartsTempList.isEmpty()){
            this.showNoPartsDialogBox();
            return;
        }
        
        int idNum = Integer.parseInt(productsIdTextField.getText());
        String name = productsNameTextField.getText();
        double price = Double.parseDouble(productsPriceTextField.getText());
        int stock = Integer.parseInt(productsInvTextField.getText());
        int min = Integer.parseInt(productsMinTextField.getText());
        int max = Integer.parseInt(productsMaxTextField.getText());
        Product productToAdd = new Product(idNum, name, price, stock, min, max);
        
        for(Part part : productAssociatedPartsTempList){
                productToAdd.addAssociatedPart(part);
        }
        
        if(Inventory.getCurrentAddState() == AddState.Add){
            Inventory.addProduct(productToAdd);
        }
        else{
            Inventory.updateProduct(idNum, productToAdd);
        }
        
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        stage.setTitle("Main Menu");
        scene = FXMLLoader.load(getClass().getResource("MainScreen.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();
    }

    @FXML
    void onProductsSearchBtnClicked(ActionEvent event) {
        String searchInput = productsSearchTextField.getText().toLowerCase();
        ObservableList<Part> filteredParts = FXCollections.observableArrayList();
        if(searchInput != null && !"".equals(searchInput)){
            try{
                int searchId = Integer.parseInt(searchInput);
                Part partToReturn = Inventory.lookupPart(searchId);
                if(partToReturn != null){
                    filteredParts.add(partToReturn);
                    productsAllPartsTableView.setItems(filteredParts);
                    productsAllPartsIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    productsAllPartsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                    productsAllPartsInvTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
                    productsAllPartsPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                }
                else{
                    populateAllPartsTableView();
                    showSearchDialogBox("Part");                
                }
            }
            catch(Exception e){
                filteredParts = Inventory.lookupPart(searchInput);
                if(filteredParts != null && !filteredParts.isEmpty()){
                    productsAllPartsTableView.setItems(filteredParts);
                    productsAllPartsIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
                    productsAllPartsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                    productsAllPartsInvTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
                    productsAllPartsPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
                }
                else{
                    populateAllPartsTableView();
                    showSearchDialogBox("Part"); 
                }
            }
            finally{
                if(searchInput == null || "".equals(searchInput)){
                    populateAllPartsTableView();
                }
            }
        }
        else{
            populateAllPartsTableView();
        }
    }
    
    void populateAllPartsTableView(){
        productsAllPartsTableView.setItems(Inventory.getAllParts());
        productsAllPartsIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productsAllPartsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsAllPartsInvTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsAllPartsPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
    }
    
    void populateUsedPartsTableView(){
        productsUsedPartsTableView.setItems(productAssociatedPartsTempList);
        productsUsedPartsIdTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        productsUsedPartsNameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        productsUsedPartsInvTableColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        productsUsedPartsPriceTableColumn.setCellValueFactory(new PropertyValueFactory<>("price"));        
    }
    
    private void showSearchDialogBox(String partOrProd){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(partOrProd + " Not Found");
        alert.setHeaderText("The " + partOrProd.toLowerCase() + " you were searching for was not found.");
        alert.showAndWait();
    }
    
    private void showNoPartsDialogBox(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Invalid Entry");
        alert.setHeaderText("Please ensure that a product has at least one associated part!");
        alert.showAndWait();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        populateAllPartsTableView();
        if(Inventory.getCurrentAddState() == AddState.Add){
            productsAddModLabel.setText("Add Product");
            productsIdTextField.setText(Integer.toString(Inventory.getProductsIdCounter()));
        }
        else{
            productsAddModLabel.setText("Modify Product");
            productAssociatedPartsTempList = Inventory.getProductToModify().getAllAssociatedParts();
            populateUsedPartsTableView();
            productsIdTextField.setText(Integer.toString(Inventory.getProductToModify().getId()));
            productsNameTextField.setText(Inventory.getProductToModify().getName());
            productsInvTextField.setText(Integer.toString(Inventory.getProductToModify().getStock()));
            productsPriceTextField.setText(Double.toString(Inventory.getProductToModify().getPrice()));
            productsMaxTextField.setText(Integer.toString(Inventory.getProductToModify().getMax()));
            productsMinTextField.setText(Integer.toString(Inventory.getProductToModify().getMin()));
        }
    }       
}