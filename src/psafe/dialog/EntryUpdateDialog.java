/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psafe.dialog;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import psafe.data.DataSource;
import psafe.model.PSafeModel;

/**
 *
 * @author Ivaylo-MSOL
 */
public class EntryUpdateDialog extends EntryDialog {

    private PSafeModel selectedModel;
    private Button editBtn;
    
    public EntryUpdateDialog(Stage owner, DataSource dataSource) {
        super(owner, dataSource);
        setTitle("UPDATE");

        editBtn = new Button("Edit");
        editBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                for (int i = 0; i < gridpane.getChildren().size(); i++) {
                    Node node = gridpane.getChildren().get(i);
                    node.setDisable(false);
                }
                editBtn.setVisible(false);
            }
        });

        disableForm();
        
        gridpane.add(editBtn, 1, 14);

    }

    @Override
    void initializeSaveBtn() {
        saveBtn = new Button("Update");
        saveBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent event) {
                dataSource.update(selectedModel.getId(), nameFld.getText(), userNameFld.getText(), passwordFld.getText(), Integer.valueOf(passwordLengthFld.getText()), passwordPunctuationCheckbox.isSelected(), emailFld.getText(), notesFld.getText());
                close();
            }
        });
    }

    @Override
    public void showAndWait() {        
        if (selectedModel != null) {
            nameFld.setText(selectedModel.getName());
            userNameFld.setText(selectedModel.getUsername());
            passwordFld.setText(selectedModel.getPassword());
            passwordLengthFld.setText(selectedModel.getPasswordLength().toString());
            passwordPunctuationCheckbox.setSelected(selectedModel.getUsePasswordPunctuation());
            emailFld.setText(selectedModel.getEmail());
            notesFld.setText(selectedModel.getNotes());
        }
        disableForm();
        super.showAndWait();
    }
    
    
    private void disableForm() {
        for (int i = 0; i < gridpane.getChildren().size(); i++) {
            Node node = gridpane.getChildren().get(i);
            node.setDisable(true);
        }
        editBtn.setVisible(true);
        editBtn.setDisable(false);
    }

    public PSafeModel getSelectedModel() {
        return selectedModel;
    }

    public void setSelectedModel(PSafeModel selectedModel) {
        this.selectedModel = selectedModel;
    }

}
