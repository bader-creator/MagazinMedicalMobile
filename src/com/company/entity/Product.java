/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.company.entity;

import com.codename1.io.Preferences;

/**
 *
 * @author Lenovo
 */
public class Product {
    
    
    private static int id ; 
    private static String name ; 
    private static String price ; 
    private static String quantity; 
    private static String createur;
    private static String createdAt;

    public Product() {
    }
    
    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        Product.id = id;
    }

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        Product.name = name;
    }

    public static String getPrice() {
        return price;
    }

    public static void setPrice(String price) {
        Product.price = price;
    }

    public static String getQuantity() {
        return quantity;
    }

    public static void setQuantity(String quantity) {
        Product.quantity = quantity;
    }

    public static String getCreateur() {
        return createur;
    }

    public static void setCreateur(String createur) {
        Product.createur = createur;
    }

    public static String getCreatedAt() {
        return createdAt;
    }

    public static void setCreatedAt(String createdAt) {
        Product.createdAt = createdAt;
    }
}
