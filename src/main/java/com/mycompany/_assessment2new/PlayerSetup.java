/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._assessment2new;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author hunte
 */
public class PlayerSetup {
    private List<Player> players;
    
    public PlayerSetup() {
        this.players = new ArrayList<>();
    }
    // method that asks how many players are going to play and shows the available colours for the player
    public void addPlayer(int number) {
        players.add(new Player(number));
    }
   
    public List<Player> getPlayers() {
        return players;
    }
    
    public String getCertainPlayer(int num) {
        return players.get(num).toString();
    }
    
    
    public int getCertainPlayerNumber(int num) {
        return players.get(num).getNumber();
    }
}
