/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.mycompany.services.ServiceProduct;

/**
 *
 * @author bader
 */
public class AddProductForm extends BaseForm{
    
    private TextField nameField;
    private TextField priceField;
    private TextField quantityFiled;
    private Button addButton;
    
     public AddProductForm(){
         
        Form form = new Form("Add Product", BoxLayout.x());
        
        nameField = new TextField();
        priceField = new TextField();
        quantityFiled = new TextField();
        addButton = new Button("Add");

        // Set the layout manager for the form
        form.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Add the components to the form
        form.add(new Label("Name :"));
        form.add(nameField);
        form.add(new Label("Price :"));
        form.add(priceField);
        form.add(new Label("Quantity ;"));
        form.add(quantityFiled);
        form.add(addButton);
        form.show();
        
        // Add an action listener to the button to handle user input
        addButton.addActionListener(e -> {
            // Validate the input and save the product to the database or API
            String name = nameField.getText();
            String price = priceField.getText();
            String quantity = quantityFiled.getText();

            ServiceProduct.getInstance().AddProduct(name,price,quantity);
            
            nameField.setText("");
            priceField.setText("");
            quantityFiled.setText("");
        });
         
     }
}
