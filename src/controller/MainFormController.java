/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.awt.event.KeyEvent;
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
import javafx.scene.control.Label;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Inventory;
import model.Part;
import model.Product;

/**
 *
 * @author Michael
 */
public class MainFormController implements Initializable {

    Stage stage;
    Parent scene;

    private Part selectedPart = null;

    @FXML
    private TextField partSearchTxt;

    @FXML
    private TableView<Part> partsTableView;

    @FXML
    private TableColumn<Part, Integer> partIDCol;

    @FXML
    private TableColumn<Part, String> partNameCol;

    @FXML
    private TableColumn<Part, Integer> partInventoryCol;

    @FXML
    private TableColumn<Part, Double> partPriceCol;

    @FXML
    private TextField productSearchTxt;

    @FXML
    private TableView<Product> productsTableView;

    @FXML
    private TableColumn<Product, Integer> productIDCol;

    @FXML
    private TableColumn<Product, String> productNameCol;

    @FXML
    private TableColumn<Product, Integer> productInventoryCol;

    @FXML
    private TableColumn<Product, Double> productPriceCol;

    @FXML
    void onActionAddPartMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionAddProductMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/AddProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionModifyPartMenu(ActionEvent event) throws IOException {
        selectedPart = partsTableView.getSelectionModel().getSelectedItem();
        ModifyPartController.updatePart(selectedPart);

        /*
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/view/ModifyPart.fxml"));
        loader.load();
         */



        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyPart.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionModifyProductMenu(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/ModifyProduct.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionDeletePartMenu(ActionEvent event) {
        Part selectedPart = partsTableView.getSelectionModel().getSelectedItem();
        Inventory.deletePart(selectedPart);

    }

    @FXML
    void onActionDeleteProduct(ActionEvent event) {

    }

    @FXML
    void onActionExit(ActionEvent event) {
        System.exit(0);

    }

    @FXML
    void searchPart(ActionEvent event) {
        String search = partSearchTxt.getText();
        ObservableList<Part> filteredParts = Inventory.lookupPart(search);
        try{
            if(filteredParts.size() == 0){
                int id = Integer.parseInt(search);
                Part idSearch = Inventory.lookupPart(id);
                if(idSearch != null){
                    filteredParts.add(idSearch);
                }
            }
            partsTableView.setItems(filteredParts);
            partSearchTxt.setText("");
        }
        catch(NumberFormatException e){
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        partsTableView.setItems(Inventory.getAllParts());

        partIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        partNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        partInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        partPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));

        productsTableView.setItems((Inventory.getAllProducts()));

        productIDCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        productNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        productInventoryCol.setCellValueFactory(new PropertyValueFactory<>("stock"));

        productPriceCol.setCellValueFactory(new PropertyValueFactory<>("price"));


    }

}
