/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany._assessment2new;

import java.io.File;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author mrgoo
 */
public class GameDBTest {
    
   private GameDB db;
    
    @Before
    public void setUp() throws Exception { //AI helped with this code
        db = new GameDB();
        db.clearSavedGame();
    }
    
   
 
    /**
     * Test of saveGame method, of class GameDB.
     */
    @Test
    public void testSaveGame() { //AI helped with this code
        try {
            db.saveGame(25, 50, 1);
        } catch (Exception e) {
            fail("Exception thrown: "+ e.getMessage());
        }
    }

    /**
     * Test of loadSavedGame method, of class GameDB.
     */
    @Test
    public void testLoadSavedGame() {
        db.saveGame(10, 20, 0);
        
        int[] loaded = db.loadSavedGame();
        assertNotNull("Load result should not be null", loaded);
        assertEquals("Player 1 position should be 10", 10, loaded[0]);
        assertEquals("Player 2 position should be 20", 20, loaded[1]);
        assertEquals("Current player should be 0", 0, loaded[2]);
    }

    /**
     * Test of clearSavedGame method, of class GameDB.
     */
    @Test
    public void testClearSavedGame() {
        db.saveGame(90, 80, 1);
        db.clearSavedGame();

        int[] loaded = db.loadSavedGame();
        assertNotNull("Load result should not be null after clear", loaded);
        assertEquals("Player 1 position should be reset to 0", 0, loaded[0]);
        assertEquals("Player 2 position should be reset to 0", 0, loaded[1]);
        assertEquals("Current player should be reset to 0", 0, loaded[2]);
    }

    
    
}
