/*
Products made up of at least one part, either inhouse or outsourced.
*/

package Model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Product {
    private ObservableList<Part> associatedParts = FXCollections.observableArrayList();
    private int id;
    private String name;
    private double price;
    private int stock;
    private int min;
    private int max;

    
    public Product(int id, String name, double price, int stock, int min, int max){
        this.id = Inventory.getProductsIdCounter();
        if(Inventory.getCurrentAddState() == AddState.Add){
            Inventory.setProductsIdCounter();
        }
        this.name = name;
        this.price = price;
        this.stock = stock;
        setMax(max);
        setMin(min);
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setMin(int min) {
        if (min >= 0 && min <= this.max){
            this.min = min;
        }
    }

    public void setMax(int max) {
        if (max >= 1){
            this.max = max;
        }
    }
    
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }
    
    public void addAssociatedPart(Part part){
        associatedParts.add(part);
    }
    
    public boolean deleteAssociatedPart(Part selectedAssociatedPart){
        return associatedParts.remove(selectedAssociatedPart);
    }
    
    public ObservableList<Part> getAllAssociatedParts(){
        return this.associatedParts;
    }
}
