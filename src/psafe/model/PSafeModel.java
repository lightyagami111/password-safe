/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package psafe.model;

import java.io.Serializable;
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
public class PSafeModel implements Serializable {
    
    private Long id;
    private String  name;
    private String  username;
    private String  password;
    private Integer  passwordLength;
    private Boolean usePasswordPunctuation;
    private String  email;
    private String  notes;
}
