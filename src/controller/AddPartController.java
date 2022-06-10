/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;
import model.Inventory;
import model.Part;

/**
 * FXML Controller class
 *
 * @author Michael
 */
public class AddPartController implements Initializable {

    Stage stage;
    Parent scene;

    @FXML
    private RadioButton addInRBtn;

    @FXML
    private ToggleGroup sGroup;

    @FXML
    private RadioButton addOutRBtn;

    @FXML
    private TextField addNameTxt;

    @FXML
    private TextField addInvTxt;

    @FXML
    private TextField addPriceTxt;

    @FXML
    private TextField addMaxTxt;

    @FXML
    private TextField addsourceTxt;

    @FXML
    private TextField addminTxt;

    @FXML
    private Label addSourceLbl;

    @FXML
    void onActionDisplayMain(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();


    }

    @FXML
    void onActionInHouse(ActionEvent event) {
        addSourceLbl.setText("Machine ID");

    }

    @FXML
    void onActionOutsourced(ActionEvent event) {
        addSourceLbl.setText("Company Name");

    }

    @FXML
    void onActionSavePart(ActionEvent event)throws IOException {

        try{
            int id = Inventory.autoPartID();
            String name = addNameTxt.getText();
            Double price = Double.parseDouble(addPriceTxt.getText());
            int stock = Integer.parseInt(addInvTxt.getText());
            int min = Integer.parseInt(addminTxt.getText());
            int max = Integer.parseInt(addMaxTxt.getText());

            if(max < min){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Warning Dialog");
                alert.setContentText("Max cannot be less than min.");
                alert.showAndWait();
                return;
            }

            if(addInRBtn.isSelected()){
                int machineId = Integer.parseInt(addsourceTxt.getText());
                Inventory.addPart(new InHouse(id,name,price,stock,min,max,machineId));
            }
            else{
                String companyName = addsourceTxt.getText();
                Inventory.addPart(new Outsourced(id,name,price,stock,min,max,companyName));
            }

            stage = (Stage)((Button)event.getSource()).getScene().getWindow();
            scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
            stage.setScene(new Scene(scene));
            stage.show();
        }
        catch(NumberFormatException e){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Please enter a valid value for each Text Field");
            alert.showAndWait();
        }




    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }




}
