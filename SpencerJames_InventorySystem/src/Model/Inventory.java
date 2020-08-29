package Model;

import javafx.collections.ObservableList;

public class Inventory {
    private ObservableList<Part> allParts;
    private ObservableList<Product> allProducts;

    public void addPart(Part newPart){
        allParts.add(newPart);
    }
    
    public void addProduct(Product newProduct){
        allProducts.add(newProduct);
    }
    
    public Part lookupPart(int partId){
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
    
    public Product lookupProduct(int productId){
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
    
    public Part lookupPart(String partName){
        Part partToLookup = null;
        for (int i = 0; i < allParts.size(); i++){
            if (allParts.get(i).getName() == partName){
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
    
    public Product lookupProduct(String productName){
        Product productToLookup = null;
        for (int i = 0; i < allProducts.size(); i++){
            if (allProducts.get(i).getName() == productName){
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
    
    public void updatePart(int index, Part selectedPart){
        
    }
    
    public void updateProduct(int index, Product newProduct){
        
    }
    
    public boolean deletePart(Part selectedPart){
        return false;    
    }
    
    public boolean deleteProduct(Product selectedProduct){
        return false;
    }
    
    public ObservableList<Part> getAllParts() {
        return allParts;
    }

    public ObservableList<Product> getAllProducts() {
        return allProducts;
    }
}
