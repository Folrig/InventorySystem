/*
Main class entry point of the program.
Test classes are instantiated to facilitate quickly testing and
debugging of the MVC within the entire system.
*/

package spencerjames_inventorysystem;

import Model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SpencerJames_InventorySystem extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/View_Controller/MainScreen.fxml"));
        
        Scene scene = new Scene(root);
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        InHouse testPartOne = new InHouse(0, "Throttle", 2.99, 5, 0, 10, 25);
        Outsourced testPartTwo = new Outsourced(0, "Clutch", 2.99, 16, 0, 52, "Wayne Ent.");
        InHouse testPartThree = new InHouse(0, "Engine", 1.95, 32, 0, 15, 29);
        Outsourced testPartFour = new Outsourced(0, "Steering Wheel", 4.99, 9, 1, 83, "The Daily Planet");
        InHouse testPartFive = new InHouse(0, "Windshield", 2.49, 315, 4, 832, 92);
        
        Product testProductOne = new Product(0, "Batmobile", 3.99, 3, 1, 5);
        Product testProductTwo = new Product(0, "Quinjet", 72.95, 43, 4, 107);
        
        testProductOne.addAssociatedPart(testPartOne);
        testProductOne.addAssociatedPart(testPartTwo);
        testProductOne.addAssociatedPart(testPartThree); 
        testProductOne.addAssociatedPart(testPartFour);
        testProductTwo.addAssociatedPart(testPartOne);
        testProductTwo.addAssociatedPart(testPartTwo);
        testProductTwo.addAssociatedPart(testPartFive);
        
        Inventory.addPart(testPartOne);
        Inventory.addPart(testPartTwo);
        Inventory.addPart(testPartThree);
        Inventory.addPart(testPartFour);
        Inventory.addPart(testPartFive);
        Inventory.addProduct(testProductOne);
        Inventory.addProduct(testProductTwo);
        
        launch(args);
    }
    
}
