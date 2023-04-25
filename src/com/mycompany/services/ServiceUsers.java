/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;


import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.myapp.SessionManager;
import com.mycompany.utils.Statics;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author Lenovo
 */
public class ServiceUsers {
    
      //singleton 
    public static ServiceUsers instance = null ;
    
    public static boolean resultOk = true;
    String json;

    //initilisation connection request 
    private ConnectionRequest req;
    
    
    public static ServiceUsers getInstance() {
        if(instance == null )
            instance = new ServiceUsers();
        return instance ;
    }
    
    
    
    public ServiceUsers() {
        req = new ConnectionRequest();
        
    }
    
        //Signup
    public void signup(TextField firstname,TextField lastname,TextField password,TextField email,Picker date_naissance, ComboBox<String> roles) {
        
        String url = Statics.BASE_URL+"/user/signup?firstname="+firstname.getText().toString()+"&lastname="+lastname.getText().toString()+"&email="+email.getText().toString()+
                "&password="+password.getText().toString()+"&date_naissance="+date_naissance+"&roles="+roles.getSelectedItem().toString();
        
        req.setUrl(url);
       
        //Control saisi
        if(firstname.getText().equals(" ") &&lastname.getText().equals(" ") && password.getText().equals(" ") && email.getText().equals(" ")) {
            
            Dialog.show("Erreur","Veuillez remplir les champs","OK",null);
            
        }
        System.out.println("com.mycompany.services.ServiceUsers.signup()"+firstname.getText());
        System.out.println("com.mycompany.services.ServiceUsers.signup()"+lastname.getText());
        System.out.println("com.mycompany.services.ServiceUsers.signup()"+email.getText());
        System.out.println("com.mycompany.services.ServiceUsers.signup()"+password.getText());
        System.out.println("com.mycompany.services.ServiceUsers.signup()"+date_naissance.getText());
        System.out.println("com.mycompany.services.ServiceUsers.signup()"+roles.getSelectedItem().toString());
        
        //hethi wa9t tsir execution ta3 url 
        req.addResponseListener((e)-> {
         
            //njib data ly7atithom fi form 
            byte[]data = (byte[]) e.getMetaData();//lazm awl 7aja n7athrhom ke meta data ya3ni na5o id ta3 kol textField 
            String responseData = new String(data);//ba3dika na5o content 
            
            System.out.println("data ===>"+responseData);
        }
        );
        
        
        //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
        
            
        
    }
    
        //SignIn
    
    public void signin(TextField email,TextField password, Resources rs ) {
        
        
        String url = Statics.BASE_URL+"/user/signin?email="+email.getText().toString()+"&password="+password.getText().toString();
        req = new ConnectionRequest(url, false); //false ya3ni url mazlt matba3thtich lel server
        req.setUrl(url);
        
        req.addResponseListener((e) ->{
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData()) + "";
            
            
            try {
            
            if(json.equals("failed")) {
                Dialog.show("Echec d'authentification","Email ou mot de passe éronné","OK",null);
            }
            else {
                System.out.println("data =="+json);
                
                Map<String,Object> user = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
             
                //Session 
                float id = Float.parseFloat(user.get("id").toString());
                SessionManager.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                SessionManager.setPassowrd(user.get("password").toString());
                SessionManager.setFirstname(user.get("firstname").toString());
                SessionManager.setLastname(user.get("lastname").toString());
                SessionManager.setEmail(user.get("email").toString());
                
                //photo 
                
                //if(user.get("photo") != null)
                  //  SessionManager.setPhoto(user.get("photo").toString());
                
                
                //if(user.size() >0 ) // l9a user
                   // new ListReclamationForm(rs).show();//yemchi lel list reclamation
                    //new AjoutReclamationForm(rs).show();
                    
                }
            
            }catch(Exception ex) {
                ex.printStackTrace();
            }
            
            
            
        });
    
         //ba3d execution ta3 requete ely heya url nestanaw response ta3 server.
        NetworkManager.getInstance().addToQueueAndWait(req);
    }
    
}
