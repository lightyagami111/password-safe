/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dialog;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import data.DataSource;

/**
 *
 * @author Ivaylo-MSOL
 */
public class BasicDialog extends Stage {
    
    protected final DataSource dataSource;
    protected Group root;
    protected Scene scene;

    public BasicDialog(Stage owner, DataSource dataSource) {
        super();
        initOwner(owner);
        setResizable(false);                
        
        root = new Group();
        scene = new Scene(root);
        setScene(scene);
        
        
        
        sizeToScene();
        initModality(Modality.APPLICATION_MODAL); 
        
        this.dataSource = dataSource;
    }
    
}
