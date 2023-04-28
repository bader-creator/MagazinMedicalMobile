/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.company.entity.Product;
import com.mycompany.services.ServiceProduct;

/**
 *
 * @author bader
 */
public class EditProduct extends BaseForm{
    
  
    private TextField nameField;
    private TextField priceField;
    private TextField quantityFiled;
    private Button editbutton;
    
    public EditProduct(Product product){
         
        Form form = new Form("Edit Product", BoxLayout.x());
        
        Toolbar toolbar = new Toolbar();
        
        Button back = new Button(FontImage.MATERIAL_ADD);
        toolbar.add(BorderLayout.WEST, back);
        
        back.addActionListener(e -> {
            new ListProduct();
        });
        form.setToolbar(toolbar);
        
        nameField = new TextField(product.getName());
        priceField = new TextField(product.getPrice());
        quantityFiled = new TextField(product.getQuantity());
        editbutton = new Button("Update");

        // Set the layout manager for the form
        form.setLayout(new BoxLayout(BoxLayout.Y_AXIS));

        // Add the components to the form
        form.add(new Label("Name :"));
        form.add(nameField);
        form.add(new Label("Price :"));
        form.add(priceField);
        form.add(new Label("Quantity ;"));
        form.add(quantityFiled);
        form.add(editbutton);
        form.show();
        
        // Add an action listener to the button to handle user input
        editbutton.addActionListener(e -> {
            // Validate the input and save the product to the database or API
            String name = nameField.getText();
            String price = priceField.getText();
            String quantity = quantityFiled.getText();
            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            ServiceProduct.getInstance().modifierReclamation(product);
            
            nameField.setText("");
            priceField.setText("");
            quantityFiled.setText("");
        });
         
     }
}
