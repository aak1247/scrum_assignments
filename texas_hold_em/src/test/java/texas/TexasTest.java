package texas;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TexasTest {
    private static Texas texas;
    static {
        texas = new TexasImpl();
    }

    @Test
    public void Round1(){
        String black = "2H 3D 5S 9C KD";
        String white = "2C 3H 4S 8C AH";
        assertTrue(texas.isWhiteWin(black, white));
        assertEquals(texas.battle(black, white), "White wins - high card: Ace");
    }

    @Test
    public void Round2(){
        String black = "2H 4S 4C 2D 4H";
        String white = "2S 8S AS QS 3S";
        assertTrue(texas.isBlackWin(black, white));
        assertEquals(texas.battle(black, white), "Black wins - full house");
    }

    @Test
    public void Round3(){
        String black = "2H 3D 5S 9C KD";
        String white = "2C 3H 4S 8C KH";
        assertTrue(texas.isBlackWin(black, white));
        assertEquals(texas.battle(black, white), "Black wins - high card: 9");
    }

    @Test
    public void Round4(){
        String black = "2H 3D 5S 9C KD";
        String white = "2D 3H 5C 9S KH";
        assertTrue(texas.isTie(black, white));
        assertEquals(texas.battle(black, white), "Tie");
    }
}
