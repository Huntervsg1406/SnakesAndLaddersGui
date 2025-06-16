/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._assessment2new;

import java.awt.*;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.*;

/**
 *
 * @author hunte
 */
public class GameController {
    
    private Board board;
    private PlayerSetup playersetup;
    private int currentPlayerIndex = 0;
    private GameDB gameDB;
    
   
    
    
    public GameController(){
        board = new Board();
        playersetup = new PlayerSetup();
        playersetup.addPlayer(1);
        playersetup.addPlayer(2);
        try{
            //new GameDB().cleanDB();       //debug
            gameDB = new GameDB();
        }
        catch(SQLException error){
            System.out.println(error.getMessage());
        }
        gameDB.GetDbContents();
    }
    
    
    
    
    
    public void saveGame(){     //load game button function
        try{
            gameDB.saveGame(playersetup.getPlayers().get(0).getPosition(), playersetup.getPlayers().get(1).getPosition(), currentPlayerIndex);
        }
        catch(Exception error){
            throw new RuntimeException("Failed to save ", error);
            
        }
        
    }
    
    public boolean loadGame(){     // load game button function
        try{
            int[] posAndCurrentPlayer = gameDB.loadSavedGame();
        if(posAndCurrentPlayer != null){
            playersetup.getPlayers().get(0).setPosition(posAndCurrentPlayer[0]);
            playersetup.getPlayers().get(1).setPosition(posAndCurrentPlayer[1]);
            currentPlayerIndex = posAndCurrentPlayer[2];
            return true;
        }
        return false;
        }
        
        catch(Exception error){
            throw new RuntimeException("Failed to load ", error);
            
        }
        
    }
    
    public void newGame(){
        try{
            playersetup.getPlayers().get(0).setPosition(0);
            playersetup.getPlayers().get(1).setPosition(0);
            currentPlayerIndex = 0;
            gameDB.clearSavedGame();
        }
        catch(Exception error){
            throw new RuntimeException("Failed start new game");
        }
    }
    
    
    
    public String rollDiceFunction(){
        
        if (isGameFinished()){
            return "Game is over start a new one";
        }
        
        Player currentPlayer = playersetup.getPlayers().get(currentPlayerIndex);
        int roll = (int) (Math.random() * 6) + 1;;
        int newPosition = board.movePlayer(currentPlayer, roll);

        //resultLabel.setText("Player " + currentPlayer.getNumber() + " rolled a " + roll +
        //                    " and moved to tile " + newPosition);
        String result = board.playerVerbose;

        if (newPosition >= 100) {
            currentPlayerIndex = -1;
            result += "Player " + currentPlayer.getNumber() + " wins!";  //AI generated line
            
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % playersetup.getPlayers().size();
        }
        return result;
    }
    
    public boolean isGameFinished(){
        return currentPlayerIndex == -1;
    }
    
    public String getCurrentPlayerName(){
        if(isGameFinished()){
            return "Game is over.";
        }
        return playersetup.getPlayers().get(currentPlayerIndex).toString();
    }
    
    public String startGameMessage(){
        return "Game has started, click roll to start.";
    }
    
    
    
    
    
}
