/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package com.mycompany._assessment2new;

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
public class BoardTest {
    
    private Board board;
    private Player player;
    
    
    @Before
    public void setUp() {
        board = new Board();
        player = new Player(1);
    }
    
    @Test
    public void testLadder() {
        player.setPosition(2);
        int newSpot = board.movePlayer(player, 1);
        assertEquals(16, newSpot);
    }
    
    @Test
    public void testSnake() {
        player.setPosition(98);
        int newSpot = board.movePlayer(player, 1);
        assertEquals(77, newSpot);
    }
    
    @Test
    public void testNormalTile() {
        player.setPosition(10);
        int newSpot = board.movePlayer(player, 1);
        assertEquals(11, newSpot);
    }
    
    @Test
    public void testOvershot() {
        player.setPosition(98);
        int newSpot = board.movePlayer(player, 5);
        assertEquals(97, newSpot);
    }
    
    @Test
    public void testWin() {
        player.setPosition(97);
        int newSpot = board.movePlayer(player, 3);
        assertEquals(100, newSpot);
    }
    
}
