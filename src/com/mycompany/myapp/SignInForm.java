/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.components.OnOffSwitch;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.spinner.Picker;



/**
 *
 * @author Lenovo
 */
public class SignInForm extends BaseForm{
    
    
    public SignInForm(){
        
        Form f = new Form("Sign up", BoxLayout.y());
        TextField email=new TextField("", "Email");
        TextField password=new TextField("", "Password");
        password.setConstraint(TextField.PASSWORD);
        
   
        Button btnValider= new Button("Login");
        f.add(email);
        f.add(password);
        f.add(btnValider);
        f.show();
        
        btnValider.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent evt) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    
}
