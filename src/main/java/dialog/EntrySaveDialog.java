/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import data.DataSource;
import model.PSafeModel;

/**
 *
 * @author Ivaylo-MSOL
 */
public class EntrySaveDialog extends EntryDialog {

    public EntrySaveDialog(Stage owner, DataSource dataSource) {
        super(owner, dataSource);
        setTitle("NEW....");
    }
    
    @Override
    public void showAndWait() {        
        nameFld.clear();
        userNameFld.clear();
        passwordFld.clear();
        emailFld.clear();
        notesFld.clear();
        passwordLengthFld.setText("8");
        passwordPunctuationCheckbox.setSelected(false);
        super.showAndWait();
    }

    @Override
    void initializeSaveBtn() {
        saveBtn = new Button("Save");
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                PSafeModel p = new PSafeModel();
                p.setName(nameFld.getText());
                p.setUsername(userNameFld.getText());
                p.setPassword(passwordFld.getText());
                p.setPasswordLength(Integer.valueOf(passwordLengthFld.getText()));
                p.setUsePasswordPunctuation(passwordPunctuationCheckbox.isSelected());
                p.setEmail(emailFld.getText());
                p.setNotes(notesFld.getText());
                dataSource.insert(p);
                close();
            }
        });
    }
    
}
