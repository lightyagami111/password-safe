/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psafe;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import org.controlsfx.control.TextFields;
import psafe.data.DataSource;
import psafe.data.DataSourceInMemoryImpl;
import psafe.dialog.EntrySaveDialog;
import psafe.dialog.EntryUpdateDialog;
import psafe.dialog.HistoryDialog;
import psafe.model.PSafeModel;
import psafe.model.PSafeModelTableView;

/**
 *
 * @author Ivaylo-MSOL
 */
public class PSafeApplication extends Application {
    
    DataSource dataSource = new DataSourceInMemoryImpl();

    @Override
    public void start(Stage primaryStage) {
        
        EntrySaveDialog addDialog = new EntrySaveDialog(primaryStage, dataSource);
        
        EntryUpdateDialog updateDialog = new EntryUpdateDialog(primaryStage, dataSource);
        
        HistoryDialog historyDialog = new HistoryDialog(primaryStage, dataSource);
        
        
        PSafeModelTableView table = new PSafeModelTableView(dataSource);        
        table.setEditable(true);        
        table.setPrefSize(600, 296);
        table.setLayoutX(14);
        table.setLayoutY(63);
        
        
        final TextField filterField = TextFields.createSearchField();
        filterField.setPrefWidth(250);
        filterField.setLayoutX(14);
        filterField.setLayoutY(20);
        
        table.setFilterField(filterField);
        
        
        final Button addButton = new Button("ADD");
        addButton.setPrefSize(88, 22);
        addButton.setLayoutX(277);
        addButton.setLayoutY(20);
        addButton.setAlignment(Pos.CENTER);
        addButton.setOnAction((ActionEvent e) -> {
            addDialog.showAndWait();
            table.refreshTableData();
            filterField.clear();
        });
        
        
        
        
        final Button viewButton = new Button("VIEW");
        viewButton.setPrefSize(88, 22);
        viewButton.setLayoutX(381);
        viewButton.setLayoutY(20);
        viewButton.setAlignment(Pos.CENTER);
        viewButton.setOnAction((ActionEvent e) -> {
            updateDialog.showAndWait();
            table.refreshTableData();
            filterField.clear();
        });
        
        
        
        
        final Button historyButton = new Button("HISTORY");
        historyButton.setPrefSize(88, 22);
        historyButton.setLayoutX(485);
        historyButton.setLayoutY(20);
        historyButton.setAlignment(Pos.CENTER);
        historyButton.setOnAction((ActionEvent e) -> {
            historyDialog.showAndWait();
        });
        
        table.getSelectionModel().selectedItemProperty().addListener((observable) -> {
            if (table.getSelectionModel().getSelectedItem() != null) {
                PSafeModel selectedItem = table.getSelectionModel().getSelectedItem();
                
                updateDialog.setSelectedModel(selectedItem);
                viewButton.setDisable(false);
                
                historyDialog.setSelectedModel(selectedItem);
                historyButton.setDisable(false);                
            }
            else {
                updateDialog.setSelectedModel(null);
                viewButton.setDisable(true);
                
                historyDialog.setSelectedModel(null);
                historyButton.setDisable(true);
            }
        });
        historyButton.setDisable(true);
        viewButton.setDisable(true);
        
        
        
        

        Scene scene = new Scene(new AnchorPane());
        ((AnchorPane) scene.getRoot()).getChildren().addAll(filterField, addButton, viewButton, historyButton, table);
        scene.getStylesheets().add("stylesheet.css");

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Hello World!");
        primaryStage.show();
        
        

        Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setX((primScreenBounds.getWidth() - primaryStage.getWidth()) / 2);
        primaryStage.setY((primScreenBounds.getHeight() - primaryStage.getHeight()) / 2);
    }
    
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
