/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.ConnectionRequest;
import com.codename1.ui.ComboBox;
import com.codename1.ui.TextField;
import com.codename1.ui.spinner.Picker;

/**
 *
 * @author bader
 */
public class ServiceProduct {
    
     //singleton 
    public static ServiceProduct instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceProduct getInstance() {
        if(instance == null )
            instance = new ServiceProduct();
        return instance ;
    }
    
    
    
    public ServiceProduct() {
        req = new ConnectionRequest();
        
    }
    
     public void AddProduct(String name,String price,String quantity){
     }
}
