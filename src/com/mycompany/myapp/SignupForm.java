/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;
import com.mycompany.services.ServiceUsers;
import java.util.Vector;


/**
 *
 * @author Lenovo
 */
public class SignupForm extends BaseForm{

    public SignupForm(){
       
         
        Form f = new Form("Sign up", BoxLayout.y());
        TextField firstname=new TextField("", "First name");
        TextField lastname=new TextField("", "Last name");
        TextField email=new TextField("", "Email");
        TextField password=new TextField("", "Password");
        Picker date_naissance=new Picker();
        password.setConstraint(TextField.PASSWORD);
        
        Button btnValider= new Button("Save");
        
        Vector<String> vectorRole;
        vectorRole=new Vector();
        
        vectorRole.add("ffffffff");
        vectorRole.add("fsdfsd");
        
        ComboBox<String>roles=new ComboBox<>(vectorRole);
        
        f.add(firstname);
        f.add(lastname);
        f.add(email);
        f.add(password);
        f.add(date_naissance);
        
        f.add(roles);
        f.add(btnValider);
        f.show();
        
        btnValider.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceUsers.getInstance().signup(firstname,lastname,password,email,date_naissance,roles);
            }
        });
    } 
   
  
}
