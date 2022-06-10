/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

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
import model.Inventory;
import model.Part;

/**
 * FXML Controller class
 *
 * @author Michael
 */
public class ModifyProductController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private TextField modifyPartNameTxt;

    @FXML
    private TextField modifyPartInventoryTxt;

    @FXML
    private TextField modifyPartPriceTxt;

    @FXML
    private TextField modifyPartMaxTxt;

    @FXML
    private TextField modifyPartMinTxt;

    @FXML
    private TextField modifyPartSearchTxt;

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
    private TableView<?> productPartsTableView;

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

    }

    @FXML
    void onActionDisplayMain(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    @FXML
    void onActionRemovePart(ActionEvent event) {

    }

    @FXML
    void onActionSaveProductModification(ActionEvent event) {

    }

    @FXML
    void onActionSearchPart(ActionEvent event) {

        String search = modifyPartSearchTxt.getText();
        ObservableList<Part> filteredParts = Inventory.lookupPart(search);
        try{
            if(filteredParts.size() == 0){
                int id = Integer.parseInt(search);
                Part idSearch = Inventory.lookupPart(id);
                if(idSearch != null){
                    filteredParts.add(idSearch);
                }
            }
            availablePartsTableView.setItems(filteredParts);
            modifyPartSearchTxt.setText("");
        }
        catch(NumberFormatException e){
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
    }

}
