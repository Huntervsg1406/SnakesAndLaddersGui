/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._assessment2new;

/**
 *
 * @author hunte
 */
public class Player {
    // assigning number and colour to individual player
    private int number;
   
    private int position; 

    public Player(int number) {
       
        this.number = number;
        this.position = 0; // Start at position 0 (before the board)
        
    }

    
    // get set methods to retrieve players colours and position.

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "player" + number;
    }
    
    public int getNumber(){
        return number;
    }
    
      
}
