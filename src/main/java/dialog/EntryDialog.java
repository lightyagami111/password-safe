/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import java.io.File;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import data.DataSource;

/**
 *
 * @author Ivaylo-MSOL
 */
abstract class EntryDialog extends BasicDialog {
    
    protected Button saveBtn;
    protected GridPane gridpane;
    
    protected TextField nameFld;
    protected TextField userNameFld;
    protected TextField passwordFld;
    protected TextField passwordLengthFld;
    protected CheckBox passwordPunctuationCheckbox;
    protected TextField emailFld;
    protected TextArea notesFld;

    public EntryDialog(Stage owner, DataSource dataSource) {
        super(owner, dataSource);

        gridpane = new GridPane();
        gridpane.setPadding(new Insets(5));
        gridpane.setHgap(5);
        gridpane.setVgap(5);
        
        Label nameLbl = new Label("Name : ");
        gridpane.add(nameLbl, 0, 1);
        nameFld = new TextField();
        nameFld.setPrefWidth(250);
        gridpane.add(nameFld, 1, 1);
        nameFld.requestFocus();
        

        Label userNameLbl = new Label("User Name: ");
        gridpane.add(userNameLbl, 0, 2);
        userNameFld = new TextField();
        gridpane.add(userNameFld, 1, 2);
        

        Label passwordLbl = new Label("Password: ");
        gridpane.add(passwordLbl, 0, 3);
        passwordFld = new TextField();
        gridpane.add(passwordFld, 1, 3);
                
        
        
        passwordLengthFld = new TextField("8");
        passwordLengthFld.setPrefWidth(40);
        gridpane.add(passwordLengthFld, 0, 4);
        
        HBox passwordBox = new HBox();
        Label punctLbl = new Label("Include Punctuation: ");
        punctLbl.setPadding(new Insets(0, 0, 0, 15));
        passwordPunctuationCheckbox = new CheckBox();                
        
        Button genPassButton = new Button("GEN PASS");
        genPassButton.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                close();
            }
        });
        
        passwordBox.getChildren().add(punctLbl);
        passwordBox.getChildren().add(passwordPunctuationCheckbox);
        passwordBox.getChildren().add(genPassButton);
        
        gridpane.add(passwordBox, 1, 4);
        
        
        
        Label emailLbl = new Label("E-mail : ");
        gridpane.add(emailLbl, 0, 5);
        emailFld = new TextField();
        gridpane.add(emailFld, 1, 5);
        
        
        
        Label notesLbl = new Label("Notes : ");
        gridpane.add(notesLbl, 0, 6);
        notesFld = new TextArea();
        gridpane.add(notesFld, 0, 7, 3, 3);
        
        Label filesLbl = new Label("Files : ");
        gridpane.add(filesLbl, 0, 12);
        final Button openMultipleButton = new Button("Open Files...");
        gridpane.add(openMultipleButton, 1, 12);
        
        final FileChooser fileChooser = new FileChooser();
        openMultipleButton.setOnAction(
            new EventHandler<ActionEvent>() {
                @Override
                public void handle(final ActionEvent e) {
                    List<File> list =
                        fileChooser.showOpenMultipleDialog(owner);
                    if (list != null) {
                        for (File file : list) {
                            
                        }
                    }
                }
            });

        initializeSaveBtn();
                
        
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                close();
            }
        });
        
        gridpane.add(saveBtn, 0, 14);
        gridpane.add(cancelBtn, 2, 14);
        GridPane.setHalignment(cancelBtn, HPos.RIGHT);
                
        
        root.getChildren().add(gridpane);
        
    }
    
    
    
    
    
    abstract void initializeSaveBtn();
    
    
}