/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Product;
import model.*;

/**
 * FXML Controller class
 *
 * @author Michael
 */
public class AddProductController implements Initializable {
    private final ObservableList<Part> productParts = FXCollections.observableArrayList();

    Stage stage;
    Parent scene;

    private Product newProduct = null;

    @FXML
    private TextField addProductNameTxt;

    @FXML
    private TextField addProductInventoryTxt;

    @FXML
    private TextField addProductPriceTxt;

    @FXML
    private TextField addProductMaxTxt;

    @FXML
    private TextField addProductMinTxt;

    @FXML
    private TextField addPartSearchTxt;

    @FXML
    private TableView<Part> availablePartsTableView;

    @FXML
    private TableColumn<Part, Integer> allPartsIDCol;

    @FXML
    private TableColumn<Part, String> allPartsNameCol;

    @FXML
    private TableColumn<Part, Integer> allPartsInventoryCol;

    @FXML
    private TableColumn<Part, Double> allPartsPriceCol;

    @FXML
    private TableView<Part> productPartsTableView;

    @FXML
    private TableColumn<?, ?> productPartIDCol;

    @FXML
    private TableColumn<?, ?> productPartNameCol;

    @FXML
    private TableColumn<?, ?> productPartInventoryCol;

    @FXML
    private TableColumn<?, ?> productPartPriceCol;

    @FXML
    void onActionAddPart(ActionEvent event) {
        Part selectedPart = availablePartsTableView.getSelectionModel().getSelectedItem();
        Part productPartAdd = null;

        int id = selectedPart.getId();
        String name = selectedPart.getName();
        int stock = selectedPart.getStock();
        Double price = selectedPart.getPrice();
        int min = selectedPart.getMin();
        int max = selectedPart.getMax();



        if (selectedPart instanceof InHouse){
            int machineID = ((InHouse) selectedPart).getMachineId();
            productPartAdd = (new InHouse(id,name,price,stock,min,max,machineID));
            productParts.add(productPartAdd);
        }
        else if (selectedPart instanceof Outsourced){
            String companyName = ((Outsourced) selectedPart).getCompanyName();
            productPartAdd = (new Outsourced(id,name,price,stock,min,max,companyName));
            productParts.add(productPartAdd);
        }

        //productPartsTableView.setItems(productParts);




    }


    @FXML
    void onActionDisplayMain(ActionEvent event) throws IOException {
        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    @FXML
    void onActionRemovePart(ActionEvent event) {
        Part deletePart = productPartsTableView.getSelectionModel().getSelectedItem();
        productParts.remove(deletePart);

    }

    @FXML
    void onActionSaveProduct(ActionEvent event) throws IOException {
        ObservableList<Part> allPartsList = Inventory.getAllParts();

        int id = Inventory.autoProductID();
        String name = addProductNameTxt.getText();
        double price = Double.parseDouble(addProductPriceTxt.getText());
        int stock = Integer.parseInt(addProductInventoryTxt.getText());
        int min = Integer.parseInt(addProductMinTxt.getText());
        int max = Integer.parseInt(addProductMaxTxt.getText());

        newProduct = new Product(id,name,price,stock,min,max);

        for (int i = 0; i < Inventory.getAllParts().size(); i++){
            Part searchPart = productParts.get(i);

            if(searchPart == allPartsList.get(i)){
                newProduct.addAssociatedPart(productParts.get(i));

            }


            //newProduct.addAssociatedPart(newPartListItem);
        }

        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    @FXML
    void onActionSearchPart(ActionEvent event) {

        String search = addPartSearchTxt.getText();
        ObservableList<Part> filteredParts = Inventory.lookupPart(search);
        try {
            if (filteredParts.size() == 0) {
                int id = Integer.parseInt(search);
                Part idSearch = Inventory.lookupPart(id);
                if (idSearch != null) {
                    filteredParts.add(idSearch);
                }
            }
            availablePartsTableView.setItems(filteredParts);
            addPartSearchTxt.setText("");
        } catch (NumberFormatException e) {
        }

    }


    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        availablePartsTableView.setItems(Inventory.getAllParts());

        allPartsIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        allPartsNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        allPartsInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        allPartsPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productPartsTableView.setItems(productParts);

        productPartIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        productPartNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        productPartInventoryCol.setCellValueFactory(new PropertyValueFactory<>("inventory"));

        productPartPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

    }
}
