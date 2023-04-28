/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Border;
import com.mycompany.services.ServiceUsers;



/**
 *
 * @author Lenovo
 */
public class SignInForm extends BaseForm{
    
    
    public SignInForm(){
        
        Form f = new Form("Sign in", BoxLayout.yCenter());
        TextField email=new TextField("", "Email");
        TextField password=new TextField("", "Password");
        password.setConstraint(TextField.PASSWORD);
        
        Container container = new Container(new BorderLayout());
        Button inscription = new Button("Inscription");
        inscription.setUIID("Label");
        inscription.getAllStyles().setBorder(Border.createEmpty());
        inscription.getAllStyles().setBgTransparency(0);
        container.add(BorderLayout.EAST, inscription);
        
        
        Button btnValider= new Button("Login");
        
        
        f.add(email);
        f.add(password);
        f.add(btnValider);
        f.add(container);
        f.show();
        
       
        btnValider.addActionListener((ActionListener) new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                ServiceUsers.getInstance().signin(email,password);
            }
        });
        
        inscription.addActionListener(e->{
            new SignupForm();
        });
    }
    
    
}