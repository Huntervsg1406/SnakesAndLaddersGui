/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany._assessment2new;

import javax.swing.SwingUtilities;

/**
 *
 * @author hunte
 */
public class App {
    
    

    public static void main(String[] args) {
        //Test commit added by John
        
        SwingUtilities.invokeLater(() -> {
           try{
               new GUI(); 
           }
           catch(Exception error){
               System.err.println("Failed to start " + error.getMessage());
               error.printStackTrace();
           }
           
        });
        
    }
}
