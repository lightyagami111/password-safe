/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Arrays;
import java.util.List;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import data.DataSource;
import utils.TableUtils;

/**
 *
 * @author Ivaylo-MSOL
 */
public class PSafeModelTableView extends TableView<PSafeModel> {
    
    private ObservableList<PSafeModel> masterData = FXCollections.observableArrayList();
    private final DataSource dataSource;
    private TextField filterField;

    public PSafeModelTableView(DataSource dataSource) {
        super();
        
        this.dataSource = dataSource;
        this.masterData = FXCollections.observableArrayList(this.dataSource.selectAll());
        
        initTableColumns();
        this.setItems(masterData);
        
        this.getSelectionModel().setCellSelectionEnabled(true);
        
        TableUtils.installCopyPasteHandler(this);
    }
    
    
    private void initTableColumns() {
        TableColumn<PSafeModel, String> nameCol = new TableColumn<>("Name");
        nameCol.setMinWidth(120);
        nameCol.setCellValueFactory(new PropertyValueFactory<PSafeModel, String>("name"));

        TableColumn<PSafeModel, String> usernameCol = new TableColumn<>("Username");
        usernameCol.setMinWidth(120);
        usernameCol.setCellValueFactory(new PropertyValueFactory<PSafeModel, String>("username"));

        TableColumn<PSafeModel, String> passCol = new TableColumn<>("Password");
        passCol.setMinWidth(188);
        passCol.setCellValueFactory(new PropertyValueFactory<PSafeModel, String>("password"));
        
        TableColumn<PSafeModel, String> emailCol = new TableColumn<>("Email");
        emailCol.setMinWidth(188);
        emailCol.setCellValueFactory(new PropertyValueFactory<PSafeModel, String>("email"));        
        
        TableColumn<PSafeModel, String> notesCol = new TableColumn<>("Notes");
        notesCol.setMinWidth(220);
        notesCol.setCellValueFactory(new PropertyValueFactory<PSafeModel, String>("notes"));

        
        this.getColumns().addAll(Arrays.asList(nameCol, usernameCol, passCol, emailCol, notesCol));
    }
    
    
    public void refreshTableData() {
        masterData.removeAll(masterData);
        List<PSafeModel> selectAll = dataSource.selectAll();
        selectAll.forEach((pSafeModel) -> { masterData.add(pSafeModel); });
        this.setItems(masterData);        
    }

    public void setFilterField(TextField filterField) {
        this.filterField = filterField;
        
        filterField.textProperty().addListener(new InvalidationListener() {

            @Override
            public void invalidated(Observable o) {
                if(filterField.textProperty().get().isEmpty()) {
                    setItems(masterData);
                    return;
                }
                ObservableList<PSafeModel> tableItems = FXCollections.observableArrayList();
                ObservableList<TableColumn<PSafeModel, ?>> cols = getColumns();
                for(int i=0; i<masterData.size(); i++) {
                    
                    for(int j=0; j<cols.size(); j++) {
                        TableColumn col = cols.get(j);
                        if (col.getText().equals("Password")) {
                            continue;
                        }
                        String cellValue = col.getCellData(masterData.get(i)).toString();
                        cellValue = cellValue.toLowerCase();
                        if(cellValue.contains(filterField.textProperty().get().toLowerCase())) {
                            tableItems.add(masterData.get(i));
                            break;
                        }                        
                    }

                }
                setItems(tableItems);
            }
        });
        
    }
    
    
    
    
    
}
