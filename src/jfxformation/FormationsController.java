/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxformation;

import classlist.Formation;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.swing.text.TableView;

/**
 *
 * @author khali
 */
public class FormationsController implements Initializable {
    @FXML
AnchorPane content;
    @FXML
    private Label label;
      @FXML
    private Label message;
       @FXML
    private TextField titre;
     @FXML
    private TextField prix;
     @FXML
    private TextArea description;
     @FXML
     private TableView listformations;
     @FXML
     private Button add;
       @FXML
    private javafx.scene.control.TableView<Formation> table;

    @FXML
    private TableColumn<Formation, Integer> id;

    @FXML
    private TableColumn<Formation, String> ttitre;

    @FXML
    private TableColumn<Formation, String> tdescription;

    @FXML
    private TableColumn<Formation, Integer> tprix;
    
    
      @FXML
    private void handlestat(ActionEvent event) throws IOException {
 Node node;
node = (Node)FXMLLoader.load(getClass().getResource("Calendar.fxml"));
content.getChildren().setAll(node);    }
    
    
    Formation f=new Formation();
void initiale() throws SQLException
{

   read();

}

         
public void refresh() throws IOException{               
//    try {
//        
//    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("Session.fxml"));
//            Parent root = (Parent) fxmlLoader.load();
//            Stage stage = new Stage();
//            stage.setScene(new Scene(root));  
//            stage.show();
//     //       jfxformation.Jfxformation.stg.close();
//    } catch(Exception e) {
//       e.printStackTrace();
//      }
Node node;
node = (Node)FXMLLoader.load(getClass().getResource("Formations.fxml"));
content.getChildren().setAll(node);
 }
public void switching() throws IOException{               
 Node node;
node = (Node)FXMLLoader.load(getClass().getResource("Session.fxml"));
content.getChildren().setAll(node);
 }
 @FXML
    private void handlenextAction(ActionEvent event) throws IOException {
      switching();
    }
 void read() throws SQLException
 {
 
  ResultSet rs=f.listFormation();
     ObservableList<Formation> list = FXCollections.observableArrayList();
    while(rs.next())
    {
    Formation x=new Formation();
    x.setId(rs.getInt("id"));
        x.setDescription(rs.getString("description"));
        x.setTitre(rs.getString("titre"));
        x.setPrix(rs.getInt("prix"));
        
        list.add(x);

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
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            
                        table.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> plot());

            
            ResultSet rs=f.listFormation();
            ObservableList<Formation> list = FXCollections.observableArrayList();
            while(rs.next())
            {
                Formation x=new Formation();
                x.setId(rs.getInt("id"));
                x.setDescription(rs.getString("description"));
                x.setTitre(rs.getString("titre"));
               x.setPrix(rs.getInt("prix"));
                list.add(x);
                
            }
            id.setCellValueFactory(new PropertyValueFactory<>("id"));
            ttitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
            tprix.setCellValueFactory(new PropertyValueFactory<>("prix"));
            tdescription.setCellValueFactory(new PropertyValueFactory<>("description"));
            
            
            table.setItems(list);
        } catch (SQLException ex) {
            Logger.getLogger(FormationsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    
    void add() throws IOException 
    {
                   try
  {
    Formation f= new Formation(titre.getText(), Integer.parseInt(prix.getText()), description.getText());
    f.createFormation();
          refresh();
  }
                      catch(IOException | NumberFormatException ex)
          {
          message.setText("prix non numérique!");
          
          }
    
    }
      void up() throws SQLException, IOException
    {
                 try
  {
    Formation f= new Formation(titre.getText(), Integer.parseInt(prix.getText()), description.getText());
    f.setId(Integer.parseInt(label.getText()));
    f.updateFormation(f.getId());
          refresh();
  }              catch(Exception ex)
          {
          message.setText("titre existant ou prix non numérique!");
          
          }
    }
          void delete() throws SQLException
    {
        try{
    Formation f= new Formation();
    f.setId(Integer.parseInt(label.getText()));
    f.deleteFormation(f.getId());
refresh();
        }
              catch(Exception ex)
          {
          message.setText("titre existant ou prix non numérique!");
          
          }
    
    }
     void plot()
    {
        Formation m=table.getSelectionModel().getSelectedItem();
     System.out.print(m.toString());
     titre.setText(String.valueOf(m.getTitre()));
     prix.setText(String.valueOf(m.getPrix()));
          description.setText(String.valueOf(m.getDescription()));
          label.setText(String.valueOf(m.getId()));

     id.setText(String.valueOf(m.getId()));
     
     
    }
}
