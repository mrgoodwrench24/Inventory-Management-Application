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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.InputMethodEvent;
import javafx.stage.Stage;
import model.Part;
import model.InHouse;
import model.Inventory;
import model.Outsourced;

/**
 * FXML Controller class
 *
 * @author Michael
 */
public class ModifyPartController implements Initializable {

    Stage stage;
    Parent scene;

    private static Part partUpdate = null;

    public static void updatePart(Part selectedPart){
        partUpdate = selectedPart;

    }

    private int indexSearch(Part partToUpdate){
        ObservableList<Part> allPartsList = Inventory.getAllParts();

        for(int i=0;i < allPartsList.size(); i++){
            Part idSearch = allPartsList.get(i);

            if(idSearch.getId() == partToUpdate.getId()){
                return i;
            }
        }

        return 0;

    }

    boolean inHouse = false;

    int updateIndex = indexSearch(partUpdate);

    @FXML
    private RadioButton modifyInRBtn;

    @FXML
    private TextField modifyPartID;

    @FXML
    private ToggleGroup sourceAction;

    @FXML
    private RadioButton modifyOutRBtn;

    @FXML
    private TextField modifyNameTxt;

    @FXML
    private TextField modifyInvTxt;

    @FXML
    private TextField modifyPriceTxt;

    @FXML
    private TextField modifyMaxTxt;

    @FXML
    private TextField modifySourceTxt;

    @FXML
    private TextField modifyMinTxt;

    @FXML
    private Label modifySourceLabel;


    @FXML
    void onActionInHouse(ActionEvent event) {
        modifySourceLabel.setText("Machine ID");
        inHouse = true;

    }

    @FXML
    void onActionOutsourced(ActionEvent event) {
        modifySourceLabel.setText("Company Name");
        inHouse = false;

    }


    @FXML
    void onActionDisplayMain(ActionEvent event) throws IOException {
        stage = (Stage)((Button)event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }

    @FXML
    void onActionSaveInPart(ActionEvent event) throws IOException {

        int id = partUpdate.getId();
        String name = modifyNameTxt.getText();
        Double price = Double.parseDouble(modifyPriceTxt.getText());
        int stock = Integer.parseInt(modifyInvTxt.getText());
        int min = Integer.parseInt(modifyMinTxt.getText());
        int max = Integer.parseInt(modifyMaxTxt.getText());
        String machineId = modifySourceTxt.getText();
        String companyName = modifySourceTxt.getText();

        if(max < min){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Warning Dialog");
            alert.setContentText("Max cannot be less than min.");
            alert.showAndWait();
            return;
        }


        if (inHouse == true){
            Part updateInHouse = (new InHouse(id,name,price,stock,min,max,Integer.parseInt(machineId)));
            Inventory.updatePart(updateIndex,updateInHouse);

        }

        else if (inHouse == false){
            String companyID = modifySourceTxt.getText();
            Part updateOutsourced = (new Outsourced(id,name,price,stock,min,max,companyName));
            Inventory.updatePart(updateIndex,updateOutsourced);
        }

        partUpdate = null;



        stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        scene = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));
        stage.setScene(new Scene(scene));
        stage.show();

    }



    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

        indexSearch(partUpdate);

        if(partUpdate instanceof InHouse){
            modifyInRBtn.setSelected(true);
            modifySourceLabel.setText("Machine ID");
            String machineID = String.valueOf(((InHouse) partUpdate).getMachineId());
            modifySourceTxt.setText(machineID);
        }
        else{
            modifyOutRBtn.setSelected(true);
            modifySourceLabel.setText("Company Name");
            modifySourceTxt.setText(((Outsourced) partUpdate).getCompanyName());
        }
        modifyPartID.setText(String.valueOf(partUpdate.getId()));
        modifyNameTxt.setText(partUpdate.getName());
        modifyInvTxt.setText(String.valueOf(partUpdate.getStock()));
        modifyPriceTxt.setText(String.valueOf(partUpdate.getPrice()));
        modifyMaxTxt.setText(String.valueOf(partUpdate.getMax()));
        modifyMinTxt.setText(String.valueOf(partUpdate.getMin()));

    }

}
