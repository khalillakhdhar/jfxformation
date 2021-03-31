/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxformation;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import classlist.Formation;
import classlist.Model;

import classlist.Session;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author khali
 */

public class SessionController implements Initializable {
     ObservableList<String> list = FXCollections.observableArrayList();
@FXML
AnchorPane content;
     @FXML
     ChoiceBox liste;
      @FXML
    private Label label;
       @FXML
    private DatePicker date;
     @FXML
    private TextField duree;
       @FXML
    private Label message;

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

     
    /**
     * Initializes the controller class.
     */
          
         
public void refresh() throws IOException{               

Node node;
node = (Node)FXMLLoader.load(getClass().getResource("Session.fxml"));
content.getChildren().setAll(node);
 }

public void switching() throws IOException{               
 Node node;
node = (Node)FXMLLoader.load(getClass().getResource("Formations.fxml"));
content.getChildren().setAll(node);
 }
 @FXML
    private void handlenextAction(ActionEvent event) throws IOException {
      switching();
    }
     @FXML
    private void handlestat(ActionEvent event) throws IOException {
 Node node;
node = (Node)FXMLLoader.load(getClass().getResource("Calendar.fxml"));
content.getChildren().setAll(node);    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            table.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> plot());

            System.out.println("aucun");
            // TODO
                Formation f=new Formation();
try {
            // TODO
                Model m=new Model();

                            Session s=new Session();

            
            ResultSet rs=s.afficheSession();
                        ObservableList<Model> elements = FXCollections.observableArrayList();

            while(rs.next())
            {
                Model x=new Model();
                x.setId(rs.getInt("id"));
                x.setDescription(rs.getString("description"));
                x.setTitre(rs.getString("titre"));
               x.setPrix(rs.getInt("prix"));
               x.setDuree(rs.getInt("duree"));
                   x.setDate(rs.getString("date"));

                elements.add(x);
                
            }
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
                        tdate.setCellValueFactory(new PropertyValueFactory<>("date"));

            ttitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            tprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            tdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            tduree.setCellValueFactory(new PropertyValueFactory<>("duree"));

            
            table.setItems(elements);
        } catch (SQLException ex) {
            Logger.getLogger(FormationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
           ResultSet rs=f.listFormation();
     ObservableList<Model> list = FXCollections.observableArrayList();
    while(rs.next())
    {

     
        formations.add(rs.getString("titre"));
       liste.getItems().add(rs.getString("titre"));
        System.out.println(rs.getString("titre"));
    }
    
        } catch (SQLException ex) {
            Logger.getLogger(SessionController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }    
    
     @FXML
    private void handleButtonAction(ActionEvent event) throws SQLException, IOException {
        System.out.println("Ajout!");
      add();
    }
       @FXML
    private void handleModifAction(ActionEvent event) throws SQLException, IOException {
         up();
    }
 
          @FXML
    private void handledeleteAction(ActionEvent event) throws SQLException, IOException {
      delete();
    }
//    
    
    @FXML
   void plot()
    {
        Model m=table.getSelectionModel().getSelectedItem();
     System.out.print(m.toString());
     duree.setText(String.valueOf(m.getDuree()));
     label.setText(String.valueOf(m.getId()));
     
    }
  
    void lister() throws SQLException
    {
    
    try {
            // TODO
                Model m=new Model();

                            Session s=new Session();

            
            ResultSet rs=s.afficheSession();
                        ObservableList<Model> elements = FXCollections.observableArrayList();

            while(rs.next())
            {
                Model x=new Model();
                x.setId(rs.getInt("id"));
                x.setDescription(rs.getString("description"));
                x.setTitre(rs.getString("titre"));
               x.setPrix(rs.getInt("prix"));
               x.setDuree(rs.getInt("duree"));
                elements.add(x);
                
            }
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            ttitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            tprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            tdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            tduree.setCellValueFactory(new PropertyValueFactory<>("duree"));

            
            table.setItems(elements);
        } catch (SQLException ex) {
            Logger.getLogger(FormationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

 void add() throws SQLException
    {
              try
  {
    Session f= new Session(Integer.parseInt(duree.getText()), liste.getSelectionModel().getSelectedItem().toString(), date.getValue().toString());
    f.createSession();
        refresh();

  }
   catch(Exception ex)
          {
          message.setText("veuillez remplir correctement");
          
          }

    
    }
      void up() throws SQLException
    {
         try
  {
     Session f= new Session(Integer.parseInt(duree.getText()), liste.getSelectionModel().getSelectedItem().toString(), date.getValue().toString());
     f.setId(Integer.parseInt(label.getText()));
    f.updateSession(Integer.parseInt(label.getText()));
    refresh();
  }
 catch(Exception ex)
          {
          message.setText("veuillez choisir une session");
          
          }
    
    }
          void delete() throws SQLException
    {
  Session f= new Session();
  try
  {
     f.setId(Integer.parseInt(label.getText()));
    f.deleteSession(Integer.parseInt(label.getText()));
          refresh();

  }
  catch(Exception ex)
          {
          message.setText("veuillez choisir une session");
          
          }
    }    
    }

