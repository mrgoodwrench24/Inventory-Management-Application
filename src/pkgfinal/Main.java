/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pkgfinal;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.InHouse;
import model.Outsourced;
import model.Inventory;
import model.Part;

/**
 *
 * @author Michael
 */
public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/view/MainForm.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */


    public static void main(String[] args) {


        Outsourced os1 = new Outsourced(Inventory.autoPartID(),"Iridium Spark Plug",8.99,20,5,30,"AC Delco");
        Inventory.addPart(os1);

        Outsourced os2 = new Outsourced(Inventory.autoPartID(),"Double Platinum Spark Plug",7.89,20,5,30,"AC Delco");
        Inventory.addPart(os2);

        InHouse in1 = new InHouse(Inventory.autoPartID(),"SureBilt Orange Extension Cord",31.49,5,2,15,693840);
        Inventory.addPart(in1);

        InHouse in2 = new InHouse(Inventory.autoPartID(),"AutoZone Refrigerant",11.99,20,10,30,370967);
        Inventory.addPart(in2);





        launch(args);
    }

}
