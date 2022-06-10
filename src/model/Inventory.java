/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author Michael
 */
public class Inventory {
    private static final ObservableList<Part> allParts = FXCollections.observableArrayList();
    private static final ObservableList<Product> allProducts = FXCollections.observableArrayList();
    private static int partAutoID;
    private static int productAutoID;

    /**
     *
     * @return partAutoID
     */
    public static int autoPartID() {
        int idLoc = allParts.size();

        if(idLoc == 0){
            Inventory.partAutoID = 100;
            return partAutoID;
        }
        else{
            Inventory.partAutoID = partAutoID + 1;
            return partAutoID;
        }


    }
    /**
     *
     * @return productAutoID
     */
    public static int autoProductID() {
        int idLoc = allProducts.size();

        if(idLoc == 0){
            Inventory.productAutoID = 200;
            return productAutoID;
        }
        else{
            Inventory.productAutoID = productAutoID + 1;
            return productAutoID;
        }


    }

    /**
     *
     * @param newPart
     */
    public static void addPart(Part newPart){

        allParts.add(newPart);
    }

    /**
     *
     * @param newProduct
     */
    public static void addProduct(Product newProduct){

        allProducts.add(newProduct);
    }

    /**
     *
     */
    public static Part lookupPart(int partID){
        ObservableList<Part> allPartsList = Inventory.getAllParts();

        for(int i=0;i < allPartsList.size(); i++){
            Part idSearch = allPartsList.get(i);

            if(idSearch.getId() == partID){
                return idSearch;
            }
        }

        return null;

    }

    /**
     *
     */
    public static Product lookupProduct(int productID){
        return null;

    }

    /**
     *
     */
    public static ObservableList<Part> lookupPart(String partName){
        ObservableList<Part> partNameSearch = FXCollections.observableArrayList();

        ObservableList<Part> allPartsList = Inventory.getAllParts();

        for(Part searchList : allPartsList){
            if(searchList.getName().contains(partName)){
                partNameSearch.add(searchList);
            }
        }

        return partNameSearch;

    }

    /**
     *
     */
    public static ObservableList<Product> lookupProduct(String productName){
        return null;

    }

    /**
     *
     */
    public static void updatePart(int index, Part selectedPart){
        Inventory.getAllParts().set(index, selectedPart);




    }

    /**
     *
     */
    public static void updateProduct(int index, Product newProduct){

    }

    /**
     *
     */
    public static boolean deletePart(Part selectedPart){
        Inventory.getAllParts().remove(selectedPart);
        return false;

    }

    /**
     *
     */
    public static boolean deleteProduct(Product selectedProduct){
        return false;

    }


    /**
     *
     * @return all parts
     */
    public static ObservableList<Part> getAllParts(){
        return allParts;
    }

    /**
     * @return all products
     */
    public static ObservableList<Product> getAllProducts(){
        return allProducts;
    }



}
