/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jfxformation;

import classlist.Session;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.chart.AreaChart;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author khali
 */
public class CalendarController implements Initializable {
@FXML
AnchorPane content;
    @FXML
private BarChart<String,Number> chart;
    @FXML
    Text moyenne;
    @FXML
    Text nombre;
      @FXML
    Text durem;
          @FXML
    Text nbf;
    /**
     * Initializes the controller class.
     */
      XYChart.Series<String,Number> series=new XYChart.Series<>();
  Session s=new Session();
    
void stats() throws SQLException    
{
    
    ResultSet rs=s.charts();
   
  int moyenneduree=s.Moyenneduree();
  int totalesession=s.calcule();
  int prix=s.Moyenne();
  int nombrefor=s.nbf();
  moyenne.setText(String.valueOf(prix));
  nbf.setText(String.valueOf(nombrefor));
  durem.setText(String.valueOf(moyenneduree));
  nombre.setText(String.valueOf(totalesession));
    while (rs.next())
    {
        System.out.println(rs.getString("formation")+" "+ rs.getInt("duree"));
      
    series.getData().add(new XYChart.Data<>(rs.getString("formation"), rs.getInt("duree")));
    System.out.println(series.getData().toString());
    }
    chart.getData().add(series);
}

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
    try {
        // TODO
        stats();
    } catch (SQLException ex) {
        Logger.getLogger(CalendarController.class.getName()).log(Level.SEVERE, null, ex);
    }
    }    
           
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
}
