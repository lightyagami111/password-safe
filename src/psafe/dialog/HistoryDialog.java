/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psafe.dialog;

import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Locale;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableColumn.CellDataFeatures;
import javafx.scene.control.TreeTableView;
import javafx.stage.Stage;
import psafe.data.DataSource;
import psafe.model.PSafeModel;
import psafe.model.PSafeModelHistory;

/**
 *
 * @author Ivaylo-MSOL
 */
public class HistoryDialog extends BasicDialog {
    
    private PSafeModel selectedModel;

    public HistoryDialog(Stage owner, DataSource dataSource) {
        super(owner, dataSource);           
    }
    
    public PSafeModel getSelectedModel() {
        return selectedModel;
    }

    public void setSelectedModel(PSafeModel selectedModel) {
        this.selectedModel = selectedModel;
    }

    @Override
    public void showAndWait() {
        setTitle("history : " + selectedModel.getName());
        
        TreeItem<String[]> treeItemRoot = new TreeItem<>(new String[] {"Root"});
        treeItemRoot.setExpanded(true);
        
        
        
        
        List<PSafeModelHistory> selectHistoryData = this.dataSource.selectHistoryData(selectedModel.getId());
        for (PSafeModelHistory pSafeModelHistory : selectHistoryData) {
            DateTimeFormatter formatter =
                        DateTimeFormatter.ofLocalizedDateTime( FormatStyle.SHORT ).withLocale( Locale.UK ).withZone( ZoneId.systemDefault() );
            
            TreeItem<String[]> treeHistoryItemRoot = new TreeItem<>(new String[] {formatter.format(pSafeModelHistory.getDate())});
            treeHistoryItemRoot.setExpanded(false);
        
            PSafeModel o = pSafeModelHistory.getOldModel();
            PSafeModel n = pSafeModelHistory.getNewModel();
            TreeItem<String[]> childNode1 = new TreeItem<>(new String[] {"OLD", o.getName(), o.getUsername(), o.getPassword(), o.getPasswordLength().toString(), o.getUsePasswordPunctuation().toString(), o.getEmail(), o.getNotes()});
            TreeItem<String[]> childNode2 = new TreeItem<>(new String[] {"NEW", n.getName(), n.getUsername(), n.getPassword(), n.getPasswordLength().toString(), n.getUsePasswordPunctuation().toString(), n.getEmail(), n.getNotes()});
            
            treeHistoryItemRoot.getChildren().setAll(childNode1, childNode2);
            treeItemRoot.getChildren().addAll(treeHistoryItemRoot);
        }
        
        
        
        
        TreeTableColumn<String[], String> column = new TreeTableColumn<>("ItemHistory");
        column.setPrefWidth(120);
        column.setCellValueFactory((CellDataFeatures<String[], String> p) -> new ReadOnlyStringWrapper(p.getValue().getValue()[0]));
        
        TreeTableColumn<String[], String> nameCol = new TreeTableColumn<>("Name");
        nameCol.setMinWidth(120);
        nameCol.setCellValueFactory((CellDataFeatures<String[], String> p) -> new ReadOnlyStringWrapper(
                p.getValue().getValue().length > 1 ? p.getValue().getValue()[1] : ""
        ));

        TreeTableColumn<String[], String> usernameCol = new TreeTableColumn<>("Username");
        usernameCol.setMinWidth(120);
        usernameCol.setCellValueFactory((CellDataFeatures<String[], String> p) -> new ReadOnlyStringWrapper(
                p.getValue().getValue().length > 2 ? p.getValue().getValue()[2] : ""
        ));

        TreeTableColumn<String[], String> passCol = new TreeTableColumn<>("Password");
        passCol.setMinWidth(188);
        passCol.setCellValueFactory((CellDataFeatures<String[], String> p) -> new ReadOnlyStringWrapper(
                p.getValue().getValue().length > 3 ? p.getValue().getValue()[3] : ""
        ));
        
        TreeTableColumn<String[], String> passLengthCol = new TreeTableColumn<>("P Length");
        passLengthCol.setMinWidth(50);
        passLengthCol.setCellValueFactory((CellDataFeatures<String[], String> p) -> new ReadOnlyStringWrapper(
                p.getValue().getValue().length > 4 ? p.getValue().getValue()[4] : ""
        ));
        
        TreeTableColumn<String[], String> passPunctuationCol = new TreeTableColumn<>("P Use Punctuation");
        passPunctuationCol.setMinWidth(50);
        passPunctuationCol.setCellValueFactory((CellDataFeatures<String[], String> p) -> new ReadOnlyStringWrapper(
                p.getValue().getValue().length > 5 ? p.getValue().getValue()[5] : ""
        ));
        
        TreeTableColumn<String[], String> emailCol = new TreeTableColumn<>("Email");
        emailCol.setMinWidth(188);
        emailCol.setCellValueFactory((CellDataFeatures<String[], String> p) -> new ReadOnlyStringWrapper(
                p.getValue().getValue().length > 6 ? p.getValue().getValue()[6] : ""
        ));        
        
        TreeTableColumn<String[], String> notesCol = new TreeTableColumn<>("Notes");
        notesCol.setMinWidth(220);
        notesCol.setCellValueFactory((CellDataFeatures<String[], String> p) -> new ReadOnlyStringWrapper(
                p.getValue().getValue().length > 7 ? p.getValue().getValue()[7] : ""
        ));
        
        TreeTableView<String[]> treeTableView = new TreeTableView<>(treeItemRoot);
        treeTableView.getColumns().add(column);
        treeTableView.getColumns().add(nameCol);
        treeTableView.getColumns().add(usernameCol);
        treeTableView.getColumns().add(passCol);
        treeTableView.getColumns().add(passLengthCol);
        treeTableView.getColumns().add(passPunctuationCol);
        treeTableView.getColumns().add(emailCol);
        treeTableView.getColumns().add(notesCol);
        
        root.getChildren().add(treeTableView);

        
        
        super.showAndWait(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
