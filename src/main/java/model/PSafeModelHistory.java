/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.time.Instant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 *
 * @author Ivaylo-MSOL
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PSafeModelHistory {
    
    private PSafeModel oldModel;
    private PSafeModel newModel;
    private Instant date;
    
}
