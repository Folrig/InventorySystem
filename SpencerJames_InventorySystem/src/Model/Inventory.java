package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Inventory {
    private static ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int partsIdCounter = 100;
    private static int productsIdCounter = 200;
    private static AddState currentAddState = AddState.Add;
    private static Part partToModify;
    private static Product productToModify;

    public static void addPart(Part newPart){
        allParts.add(newPart);
    }
    
    public static void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    
    public static Part lookupPart(int partId){
        Part partToLookup = null;
        for (int i = 0; i < allParts.size(); i++){
            if (allParts.get(i).getId() == partId){
                partToLookup = allParts.get(i);
            }
        }
        if (partToLookup != null){
            return partToLookup;
        }
        else{
            return null;
        }
    }
    
    public static Product lookupProduct(int productId){
        Product productToLookup = null;
        for (int i = 0; i < allProducts.size(); i++){
            if (allProducts.get(i).getId() == productId){
                productToLookup = allProducts.get(i);
            }
        }
        if (productToLookup != null){
            return productToLookup;
        }
        else{
            return null;
        }
    }
    
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> filteredParts = FXCollections.observableArrayList();
        for (Part part : allParts){
            if (part.getName().contains(partName)){
                filteredParts.add(part);
            }
        }
        if (!filteredParts.isEmpty()){
            return filteredParts;
        }
        else{
            return null;
        }
    }
    
    public static ObservableList<Product> lookupProduct(String productName){
        ObservableList<Product> filteredProducts = FXCollections.observableArrayList();
        for (Product product : allProducts){
            if (product.getName().contains(productName)){
                filteredProducts.add(product);
            }
        }
        if (!filteredProducts.isEmpty()){
            return filteredProducts;
        }
        else{
            return null;
        }
    }
    
    public static void updatePart(int index, Part selectedPart){
        
    }
    
    public static void updateProduct(int index, Product newProduct){
        
    }
    
    public static boolean deletePart(Part selectedPart){
        if(allParts.remove(selectedPart)){
            return true;
        }
        else{
            return false;
        }    
    }
    
    public static boolean deleteProduct(Product selectedProduct){
        if(allProducts.remove(selectedProduct)){
            return true;
        }
        else{
            return false;
        }
    }
    
    public static ObservableList<Part> getAllParts() {
        return allParts;
    }

    public static ObservableList<Product> getAllProducts() {
        return allProducts;
    }

    public static int getPartsIdCounter() {
        return partsIdCounter;
    }

    public static int getProductsIdCounter() {
        return productsIdCounter;
    }

    public static AddState getCurrentAddState() {
        return currentAddState;
    }
    
    public static Part getPartToModify() {
        return partToModify;
    }

    public static Product getProductToModify() {
        return productToModify;
    }

    public static void setPartsIdCounter() {
        partsIdCounter++;
    }

    public static void setProductsIdCounter() {
        productsIdCounter++;
    }

    public static void setCurrentAddState(AddState aCurrentAddState) {
        currentAddState = aCurrentAddState;
    }
       
    public static void setPartToModify(Part aPartToModify) {
        partToModify = aPartToModify;
    }

    public static void setProductToModify(Product aProductToModify) {
        productToModify = aProductToModify;
    }
}
