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
import com.codename1.ui.FontImage;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.RoundBorder;

/**
 *
 * @author Lenovo
 */
public class ListProduct extends BaseForm {
    
    public  ListProduct(){
        Form myForm = new Form("Product List", new BoxLayout(BoxLayout.Y_AXIS));
        
        Toolbar toolbar = new Toolbar();
        Button button = new Button(FontImage.MATERIAL_ADD);
        toolbar.add(BorderLayout.EAST, button);
        myForm.setToolbar(toolbar);
        button.addActionListener(e -> {
            new AddProductForm();
        });
        
        for (int i = 0; i < ServiceProduct.getInstance().affichagePRorducts().size(); i++) {
            Product product = ServiceProduct.getInstance().affichagePRorducts().get(i);
            Container productCard = new Container(new BoxLayout(BoxLayout.Y_AXIS));
            productCard.getAllStyles().setBgColor(0xff2196f3); // blue
            productCard.getAllStyles().setBorder(RoundBorder.create().rectangle(true).color(0xffffff));
            productCard.getAllStyles().setPadding(10, 10, 10, 10); // padding
            productCard.getAllStyles().setMargin(5, 5, 5, 5); // margins
            productCard.revalidate();
            productCard.repaint();

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
