package com.mycompany.myapp;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import com.codename1.io.Preferences;
/**
 *
 * @author Lenovo
 */
public class SessionManager {
    
        public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id ; 
    private static String firstname ; 
    private static String lastname ; 
    private static String email; 
    private static String role;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        SessionManager.pref = pref;
    }

    public static int getId() {
        return pref.get("id",id);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("id",id);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getFirstname() {
        return pref.get("firstname",firstname);
    }

    public static void setFirstname(String firstname) {
         pref.set("firstname",firstname);
    }
    
    public static String getLastname() {
        return pref.get("lastname",lastname);
    }

    public static void setLastname(String lastname) {
         pref.set("lastname",lastname);
    }

    public static String getEmail() {
        return pref.get("email",email);
    }

    public static void setEmail(String email) {
         pref.set("email",email);
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String role) {
        SessionManager.role = role;
    }

  

   
    
    
}
