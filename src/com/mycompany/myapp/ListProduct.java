/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.myapp;

import com.codename1.ui.Button;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.company.entity.Product;
import com.mycompany.services.ServiceProduct;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.plaf.RoundBorder;
import static com.mycompany.services.ServiceProduct.resultOk;

/**
 *
 * @author Lenovo
 */
public class ListProduct extends BaseForm {
    
    public  ListProduct(){
        Form myForm = new Form("Product List", new BoxLayout(BoxLayout.Y_AXIS));
        
        Toolbar toolbar = new Toolbar();
        
        Button addproduct = new Button(FontImage.MATERIAL_ADD);
        toolbar.add(BorderLayout.EAST, addproduct);
        
        addproduct.addActionListener(e -> {
            new AddProductForm();
        });
        
        Button back = new Button(FontImage.MATERIAL_BACKPACK);
        toolbar.add(BorderLayout.WEST, back);
        
        back.addActionListener(e -> {
            new AddProductForm();
        });
        
        myForm.setToolbar(toolbar);
        
        for (int i = 0; i < ServiceProduct.getInstance().affichagePRorducts().size(); i++) {
            
            Product product = ServiceProduct.getInstance().affichagePRorducts().get(i);
            Container productCard = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            
            Container buttonContainer = new Container(new FlowLayout());
         
            Button editButton = new Button(FontImage.MATERIAL_EDIT);
            editButton.addActionListener((evt) -> {
                new EditProduct(product);
            });

            Button deleteButton = new Button(FontImage.MATERIAL_DELETE);
            deleteButton.addPointerPressedListener(l -> {
            
            Dialog dig = new Dialog("Suppression");
            
            if(dig.show("Suppression","Vous voulez supprimer ce produit ?","Annuler","Oui")) {
                dig.dispose();
            }
            else {
                dig.dispose();
            }
                //n3ayto l suuprimer men service Reclamation
               ServiceProduct.getInstance().deleteProduct(product.getId());
               
           
        });

            // Add the buttons to the button container
            buttonContainer.add(editButton);
            buttonContainer.add(deleteButton);

        

            // Add the card details and the buttons to the grid layout
            productCard.add(buttonContainer);

            Label nameLabel = new Label(product.getName());

            Label pricelabel = new Label("$"+" "+ product.getPrice());

            Label quantitylabel = new Label(product.getQuantity());
            //quantitylabel.getAllStyles().setFgColor(0xFFFFFF); // white text


            productCard.add(nameLabel).add(pricelabel).add(quantitylabel);
            myForm.add(productCard);
        }
        myForm.show();
    }
    

    
}
