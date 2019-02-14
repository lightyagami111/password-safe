/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import model.PSafeModel;
import model.PSafeModelHistory;
import utils.UnoptimizedDeepCopy;


public class DataSourceInMemoryImpl implements DataSource {
    
    List<PSafeModel> data = new ArrayList<>(Arrays.asList(
                new PSafeModel(1l, "discord", "ivaylo.rusev", "1111222pass", 10, true, "jacob.smith@example.com", "note note \n note"),
                new PSafeModel(2l, "discord 2", "ivaylo.rusev", "1111222pass", 11, false, "jacob.smith@example.com", "note note \n note")
            ));
    
    List<PSafeModelHistory> historyData = new ArrayList<>();
    
    Long lastId = 2l;

    @Override
    public List<PSafeModel> selectAll() {
        return data;
    }

    @Override
    public void insert(PSafeModel p) {
        lastId++;
        p.setId(lastId);
        data.add(p);
    }

    @Override
    public void update(Long id, String name, String username, String password, Integer  passwordLength, Boolean usePasswordPunctuation, String email, String notes) {
        PSafeModel dbModel = data.stream().filter(db -> db.getId().compareTo(id)==0).findFirst().get();
        
        historyData.add(new PSafeModelHistory((PSafeModel) UnoptimizedDeepCopy.copy(dbModel), new PSafeModel(id, name, username, password, passwordLength, usePasswordPunctuation, email, notes), Instant.now()));
        
        dbModel.setName(name);
        dbModel.setUsername(username);
        dbModel.setPassword(password);
        dbModel.setEmail(email);
        dbModel.setNotes(notes);
    }

    @Override
    public List<PSafeModelHistory> selectHistoryData(Long id) {
        return historyData.stream().filter(p -> p.getOldModel().getId().compareTo(id)==0).collect(Collectors.toList());
    }
    
    
    
    
}
