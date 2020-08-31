/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spencerjames_inventorysystem;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author James Spencer
 */
public class SpencerJames_InventorySystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        InHouse testPartOne = new InHouse(0, "Throttle", 2.99, 5, 0, 10, 25);
        Outsourced testPartTwo = new Outsourced(0, "Clutch", 2.99, 16, 0, 52, "Wayne Ent.");
        InHouse testPartThree = new InHouse(0, "Engine", 1.95, 32, 0, 15, 29);
        
        Product testProduct = new Product(0, "Batmobile", 3.99, 3, 1, 5);
        testProduct.addAssociatedPart(testPartOne);
        testProduct.addAssociatedPart(testPartTwo);
        testProduct.addAssociatedPart(testPartThree);    
        
        Inventory.addPart(testPartOne);
        Inventory.addPart(testPartTwo);
        Inventory.addPart(testPartThree);
        Inventory.addProduct(testProduct);
        
        launch(args);
    }
    
}
