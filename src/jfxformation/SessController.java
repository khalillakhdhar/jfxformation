/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxformation;

import classlist.Model;
import classlist.Session;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javax.swing.text.TableView;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class SessController implements Initializable {

    /**
     * Initializes the controller class.
     */
       ObservableList<String> list = FXCollections.observableArrayList();

     @FXML
     ChoiceBox liste;
      @FXML
    private Label label;
       @FXML
    private DatePicker date;
     @FXML
    private TextField duree;
     
     @FXML
    private TextArea description;
     @FXML
     private TableView listsession;
     @FXML
     private Button add;
       @FXML
    private TableColumn<Model, Integer> id;

    @FXML
    private TableColumn<Model, String> ttitre;

    @FXML
    private TableColumn<Model, String> tdescription;

    @FXML
    private TableColumn<Model, Integer> tprix;
      @FXML
    private TableColumn<Model, Integer> tduree;
       @FXML
    private TableColumn<Model, String> tdate;
        @FXML
         Session s=new Session();

       @FXML
    private javafx.scene.control.TableView<Model> table;
          ObservableList<String> formations= FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
