/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._assessment2new;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *
 * @author hunte
 */
public class Board {
    List<Integer> board;
    String playerVerbose;
    
    
    //not sure how to do this
    public Board(){
        // assigning the size of the board
        board = new ArrayList<>(101);
        
        for(int i = 0; i <= 100; i++) {
            board.add(i);
        }
        //Snakes
        board.set(99, 77);
        board.set(95, 85);
        board.set(93, 69);
        board.set(87, 24);
        board.set(67, 30);
        board.set(42, 17);
        board.set(16, 7);
        
        //ladders
        board.set(3, 16);
        board.set(18, 37);
        board.set(25, 54);
        board.set(33, 51);
        board.set(45, 77);
        board.set(64, 82);
        board.set(88, 96);
    }
    
    
    // this moves the position of the player
    
    public int movePlayer(Player player, int diceRoll){
        int newPosition = player.getPosition() + diceRoll;
        
        if(newPosition > 100){
            newPosition = 100 - (newPosition -100);
        }
        
        //check for da snake or ladder and output final position
        int finalPosition = board.get(newPosition);
        
        if(finalPosition > newPosition){
            System.out.println(player+ " Rolled a " +  diceRoll + " and landed on " + (newPosition+diceRoll)+"\nClimbing a ladder to " + finalPosition+"\n");
            playerVerbose = player+ " Rolled a " +  diceRoll + " and landed on " + (newPosition+diceRoll)+", Climbing a ladder to " + finalPosition+"\n";
        }
        else if(finalPosition < newPosition){
            System.out.println(player+ " Rolled a " +  diceRoll + " and landed on " + (newPosition+diceRoll)+"\nFalling down a snake to " + finalPosition+"\n");
            playerVerbose = player+ " Rolled a " +  diceRoll + " and landed on " + (newPosition+diceRoll)+", Falling down a snake to " + finalPosition+"\n";
        }
        else {
            System.out.println(player+ " Rolled a " +  diceRoll + " and landed on " + finalPosition+"\n");
            playerVerbose = player+ " Rolled a " +  diceRoll + " and landed on " + finalPosition+"\n";
        }
        
        player.setPosition(finalPosition);
        return finalPosition;
        
    }
    
    
   
    
}
