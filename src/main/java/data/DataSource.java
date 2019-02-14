/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.List;
import model.PSafeModel;
import model.PSafeModelHistory;

/**
 *
 * @author Ivaylo-MSOL
 */
public interface DataSource {
    
    List<PSafeModel> selectAll();
    
    void insert(PSafeModel p);
    
    void update(Long id, String name, String username, String password, Integer  passwordLength, Boolean usePasswordPunctuation, String email, String notes);
    
    List<PSafeModelHistory> selectHistoryData(Long id);
    
}
