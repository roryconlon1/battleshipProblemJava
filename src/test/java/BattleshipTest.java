import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BattleshipTest {

    String[] ships = {"0:0,0:2", "2:2,2:3", "4:4,6:4", "6:1,9:1"};
    String[] guesses1 = {"1:1", "2:2", "1:4", "2:3", "8:9", "4:4", "5:4", "6:4"};
    String[] guesses2 = {"1:1", "1:2", "1:4", "2:3", "8:9", "4:4", "5:4", "6:4"};

    @Before
    public void setUp() {
        Battleship.gameBoard = new int[10][10];
    }

    @Test
    public void checkVerticalShipSetUp(){
        Battleship.setUpGame(ships);
        assertEquals(1, Battleship.gameBoard[0][0]);
        assertEquals(1, Battleship.gameBoard[0][1]);
        assertEquals(1, Battleship.gameBoard[0][2]);
        assertEquals(2, Battleship.gameBoard[2][2]);
        assertEquals(2, Battleship.gameBoard[2][3]);
    }

    @Test
    public void checkHorizontalShipSetUp(){
        Battleship.setUpGame(ships);
        assertEquals(3, Battleship.gameBoard[4][4]);
        assertEquals(3, Battleship.gameBoard[5][4]);
        assertEquals(3, Battleship.gameBoard[6][4]);
        assertEquals(4, Battleship.gameBoard[9][1]);
        assertEquals(4, Battleship.gameBoard[8][1]);
        assertEquals(4, Battleship.gameBoard[7][1]);
        assertEquals(4, Battleship.gameBoard[6][1]);
    }

    @Test
    public void checkWater(){
        Battleship.setUpGame(ships);
        assertEquals(0, Battleship.gameBoard[1][1]);
        assertEquals(0, Battleship.gameBoard[3][7]);
        assertEquals(0, Battleship.gameBoard[9][9]);
    }

    @Test
    public void canGetHit(){
        Battleship.setUpGame(ships);
        assertTrue(Battleship.hitOrMiss("6:4"));
        assertTrue(Battleship.hitOrMiss("9:1"));
        assertTrue(Battleship.hitOrMiss("0:0"));
    }

    @Test
    public void canReturnNotHit(){
        Battleship.setUpGame(ships);
        assertFalse(Battleship.hitOrMiss("3:7"));
        assertFalse(Battleship.hitOrMiss("1:1"));
        assertFalse(Battleship.hitOrMiss("9:9"));
    }

    @Test
    public void canBeSunk(){
        Battleship.setUpGame(ships);
        assertFalse(Battleship.isSunk(2));
        assertTrue(Battleship.isSunk(5));
    }

    @Test
    public void fullGame(){
        Battleship.setUpGame(ships);
        assertEquals(2, Battleship.play(ships, guesses1));
        assertEquals(1, Battleship.play(ships, guesses2));
    }
}
