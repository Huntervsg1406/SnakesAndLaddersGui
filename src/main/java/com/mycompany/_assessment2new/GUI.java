/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._assessment2new;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author hunte
 */

public class GUI extends JFrame {

    private JButton rollButton;
    private JLabel resultLabel;
    private Board board;
    private PlayerSetup playersetup;
    private int currentPlayerIndex = 0;
    private JButton saveButton;
    private JButton loadButton;
    private JButton newGameButton;
    private GameDB gameDB;
    
    
    
    public GUI() {
        
        
        //mising commits
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
            JOptionPane.showMessageDialog(rootPane, error.getMessage());
            
        }
        
        gameDB.GetDbContents(); // console debug
                
        setTitle("Snakes and Ladders");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);  // center window
        setResizable(false);
        
        
        
        
        // Initialize components
        
        rollButton = new JButton("Roll Dice!");
        resultLabel = new JLabel("Click the button to roll!");
        saveButton = new JButton("Save Game");
        loadButton = new JButton("Load Game");
        newGameButton = new JButton("New Game");
        

        // Add action
        
        saveButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                saveGame();
                showControls();
            }
        });
        
        loadButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                loadGame();
                showControls();
            }
        });
        
        newGameButton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                newGame();
                showControls();
            }
        });
        
        
        rollButton.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        rollDiceFunction();
        }
        });
        
        
        
        
        JPanel panel = new JPanel();
        
        //panel.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10, Color.lightGray));
        
        //panel.add(loadButton.);
        //panel.add(saveButton);
        //panel.add(newGameButton);
        //panel.add(rollButton);
        //panel.add(resultLabel);
        
        JPanel buttonPanel = new JPanel();
        buttonPanel.setSize(600, 40);
        buttonPanel.add(loadButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(newGameButton);
        
        
        buttonPanel.add(rollButton);
        
        panel.add(buttonPanel);
        //resultLabel.setSize(150, 80);
        //resultLabel.setBounds(200, 120, 120, 120);
        panel.add(resultLabel);
        
        add(panel);
        rollButton.setVisible(false);
        resultLabel.setVisible(false);
        
        setVisible(true);
    }
    
    
    
    private void showControls(){
        rollButton.setVisible(true);
        resultLabel.setVisible(true);
    }
    
    
    
    
    
    private void saveGame(){     //load game button function
        gameDB.saveGame(playersetup.getPlayers().get(0).getPosition(), playersetup.getPlayers().get(1).getPosition(), currentPlayerIndex);
        JOptionPane.showMessageDialog(rootPane, "Game saved.");
    }
    
    private void loadGame(){     // load game button function
        int[] posAndCurrentPlayer = gameDB.loadSavedGame();
        if(posAndCurrentPlayer != null){
            playersetup.getPlayers().get(0).setPosition(posAndCurrentPlayer[0]);
            playersetup.getPlayers().get(1).setPosition(posAndCurrentPlayer[1]);
            currentPlayerIndex = posAndCurrentPlayer[2];
            JOptionPane.showMessageDialog(rootPane, "Game loaded.");
        }
        else{
            JOptionPane.showMessageDialog(rootPane, "No game found.");
        }
    }
    
    private void newGame(){       //new game button function
        int newGameConfirm = 0;
        newGameConfirm = JOptionPane.showConfirmDialog(rootPane, "New game? progress will be lost if not saved.","Are you sure?", JOptionPane.YES_NO_OPTION);
        if(newGameConfirm == JOptionPane.YES_OPTION){
            playersetup.getPlayers().get(0).setPosition(0);
            playersetup.getPlayers().get(1).setPosition(0);
            currentPlayerIndex = 0;
            gameDB.clearSavedGame();
            resultLabel.setText("Game has been reset, " + playersetup.getPlayers().get(currentPlayerIndex) + " is first. Click roll to start.");
        }
    }
    
    private int rollDice() {
        return (int) (Math.random() * 6) + 1;
    }
    
    private void rollDiceFunction(){
        
        Player currentPlayer = playersetup.getPlayers().get(currentPlayerIndex);
        int roll = rollDice();
        int newPosition = board.movePlayer(currentPlayer, roll);

        //resultLabel.setText("Player " + currentPlayer.getNumber() + " rolled a " + roll +
        //                    " and moved to tile " + newPosition);
        resultLabel.setText(board.playerVerbose);

        if (newPosition >= 100) {
            JOptionPane.showMessageDialog(null, "Player " + currentPlayer.getNumber() + " wins!");  //AI generated line
            rollButton.setEnabled(false);
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % playersetup.getPlayers().size();
        }
    }
    
    
    
    
}


