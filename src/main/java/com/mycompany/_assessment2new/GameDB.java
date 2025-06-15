/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany._assessment2new;


import java.io.File;
import java.sql.*;
import java.time.Duration;

/**
 *
 * @author hunte
 */
public final class GameDB {
    
    private final Connection dbConnection; //Java database connection object
    
    
    
    
    
    
    static{
        try{
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");   //ai used to help bugfix as databaseUrl string wasnt working
        }
        catch(ClassNotFoundException error){
            throw new RuntimeException("Failed to load derby driver ", error);
        }
    }
    
    
    public GameDB() throws SQLException{
        String projectPath = System.getProperty("user.dir"); //ai used to help bugfix as databaseUrl string wasnt working
        String databaseURL = "jdbc:derby:" + projectPath + "/database/snakesAndLaddersDB;create=true";
        
        try{
            dbConnection = DriverManager.getConnection(databaseURL);
        createDatabaseTables();
        }
        catch(SQLException error){
            System.err.println("Database connection failure " + error.getMessage());
            throw error;
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    void createDatabaseTables() throws SQLException{ //create sql database
        String sqlCode = "CREATE TABLE savedGame(id INT PRIMARY KEY DEFAULT 1, player1Position INT, player2Position INT, currentPlayer INT, CONSTRAINT singleRow CHECK (id = 1))";
        try{
            Statement statement = dbConnection.createStatement();
            statement.execute(sqlCode);
            statement.execute("INSERT INTO savedGame (id) VALUES (1)");
            statement.close();
            
        }
        catch(SQLException error){
            System.out.println(error.getMessage());
        }
    }
    
    public void saveGame(int player1Pos, int player2Pos, int current_Player){
        String sqlCode = "UPDATE savedGame SET player1Position = " + player1Pos + ", player2Position = " + player2Pos + ", currentPlayer = " + current_Player + " WHERE id = 1";
        System.out.println("Saving game...");
        
        try{
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate(sqlCode);
            statement.close();
            System.out.println(".");             //debug code
            Thread.sleep(300);
            System.out.println(".");
            Thread.sleep(300);
            System.out.println(".");
            Thread.sleep(300);
            System.out.println("Saved game.");
        }
        catch(InterruptedException | SQLException error){
            System.out.println(error.getMessage());
            
        }
    }
    
    public int[] loadSavedGame(){
        
        String sqlCode = "SELECT player1Position, player2Position, currentPlayer FROM savedGame WHERE id = 1";
        
        try{
            Statement statement = dbConnection.createStatement();
            ResultSet results = statement.executeQuery(sqlCode);
            if(results.next()){
                int[] posAndCurrentPlayer = {results.getInt("Player1Position"),results.getInt("player2Position"),results.getInt("currentPlayer")};
                
                //todo integrate into main game code
                results.close();
                statement.close();
                return posAndCurrentPlayer;
            }  
        }
        catch(SQLException error){
            System.out.println(error.getMessage());
            
        }
        return null;
    }
    
    public void clearSavedGame(){
        try{
            String sqlCode = "UPDATE savedGame SET player1Position = 0,player2Position = 0,currentPlayer = 0 WHERE id =1";
            Statement statement = dbConnection.createStatement();
            statement.executeUpdate(sqlCode);
            statement.close();
        }
        catch(SQLException error){
            System.out.println("Clear game error "+error.getMessage());
            
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void cleanDB(){ //ai helped for debug
        try{
            DriverManager.getConnection("jdbc:derby:;shutdown=true");
        }
        catch(SQLException error){
            
        }
        
        File dbFolder = new File("database/snakesAndLaddersDB");
        if(dbFolder.exists()){
            deleteFolder(dbFolder);
        }
    }
    
    public void deleteFolder(File folder){
        File[] files = folder.listFiles();
        if(files != null){
            for (File file : files) {
                if(file.isDirectory()){
                    deleteFolder(file);
                }
                else{
                    file.delete();
                }
            }
        }
        folder.delete();
    }
    
    
    public void GetDbContents(){
        String sqlCode = "SELECT player1Position, player2Position, currentPlayer FROM savedGame WHERE id = 1";
        
        try{
            Statement statement = dbConnection.createStatement();
            ResultSet results = statement.executeQuery(sqlCode);
            if(results.next()){
                int[] posAndCurrentPlayer = {results.getInt("Player1Position"),results.getInt("player2Position"),results.getInt("currentPlayer")};
                
                
                results.close();
                statement.close();
                
                System.out.println("Database contents\n");
                System.out.println("player 1 db info: " + posAndCurrentPlayer[0]+"\n");
                System.out.println("player 2 db info: " + posAndCurrentPlayer[1]+"\n");
                System.out.println("currentplayer db info: " + posAndCurrentPlayer[2]);
            }  
        }
        catch(SQLException error){
            System.out.println(error.getMessage());
            
        }
    }
    
    
    
    
}
