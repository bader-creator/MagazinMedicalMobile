/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.spinner.Picker;
import com.company.entity.Product;
import com.mycompany.myapp.AddProductForm;
import com.mycompany.myapp.SessionManager;
import com.mycompany.utils.Statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
         
        int iduser=SessionManager.getId();
        String url = Statics.BASE_URL+"/product/add?name="+name.toString()+"&price="+price.toString()+"&quantity="+quantity.toString()+"&iduser="+iduser;        
        
        req.setUrl(url);
       
        //Control saisi
        if(name.equals(" ") && price.equals(" ") && quantity.equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
      
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            System.out.println("data ===>"+responseData);
            Dialog.show("Success",responseData,"OK",null);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
     }
     
     public ArrayList<Product>affichagePRorducts() {
        ArrayList<Product> result = new ArrayList<>();
        int iduser=SessionManager.getId();
        String url = Statics.BASE_URL+"/product/list?iduser="+iduser;        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                JSONParser jsonp ;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapProducts = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
                    
                    List<Map<String,Object>> listOfMaps =  (List<Map<String,Object>>) mapProducts.get("root");
                    
                    for(Map<String, Object> obj : listOfMaps) {
                       Product p=new Product();
                        //Session 
                       float id = Float.parseFloat(obj.get("id").toString());
                       p.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                       p.setName(obj.get("name").toString());
                       p.setPrice(obj.get("prix").toString());
                       p.setQuantity(obj.get("quantity").toString());
                       //p.setQuantity(obj.get("isDesponible").toString());
                   //    p.setCreateur(obj.get("createur").get('email')toString());
                    //   p.setCreateur(obj.get("createur").get('email').toString());                      
                    
                       result.add(p);
                    }
                    
                }catch(Exception ex) {
                    
                    ex.printStackTrace();
                }
            
            }
        });
        
      NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

        return result;
    }
     
     public Product DetailProduct(int id ,Product product){
         
        String url = Statics.BASE_URL+"/product/list?idproduct="+id;        
        req.setUrl(url);
        String str  = new String(req.getResponseData());
        req.addResponseListener(((evt) -> {
        
            JSONParser jsonp = new JSONParser();
            try {
                
                Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                    product.setName(obj.get("name").toString());
                    product.setPrice(obj.get("price").toString());
                    product.setQuantity(obj.get("quantity").toString());
                  //  product.setCreateur(obj.get("createur").toString());  
                
            }catch(IOException ex) {
                System.out.println("error related to sql :( "+ex.getMessage());
            }
            
            
            System.out.println("data === "+str);
            
            
            
        }));
        
            NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha

            return product;
    }
     
       //Delete 
    public boolean deleteProduct(int id ) {
        String url = Statics.BASE_URL +"/product/Delete?idproduct="+id;
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                    
                    req.removeResponseCodeListener(this);
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        return  resultOk;
    }
    
      //Update 
    public boolean modifierReclamation(Product product) {
        String url = Statics.BASE_URL +"/product/edit?id="+product.getId()+"&name="+product.getName()+"&PRICE="+product.getPrice()+"&quantity="+product.getQuantity();
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOk = req.getResponseCode() == 200 ;  // Code response Http 200 ok
                req.removeResponseListener(this);
            }
        });
        
    NetworkManager.getInstance().addToQueueAndWait(req);//execution ta3 request sinon yet3ada chy dima nal9awha
    return resultOk;
        
    }
}
