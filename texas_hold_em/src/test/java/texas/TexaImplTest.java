package texas;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class TexaImplTest {
    TexasImpl texas = new TexasImpl();

    @Test
    public void isPairTest(){
        var input = new String[] {
          "2H", "TS", "2D", "AC", "9C"
        };
        assertTrue(texas.isPair(input));
    }

    @Test
    public void isThreeOfAKindTest(){
        var input = new String[] {
                "2H", "TH", "2D", "AH", "6D"
        };
        assertTrue(texas.isThreeOfAKind(input));
    }

    @Test
    public void isStraightTest(){
        var input = new String[] {
                "2H", "3H", "4D", "5C", "6S"
        };
        assertTrue(texas.isStraight(input));
    }

    @Test
    public void isFlushTest(){
        var input = new String[] {
                "2H", "3H", "4H", "5H", "AH"
        };
        assertTrue(texas.isFlush(input));
    }

    @Test
    public void isFullHouseTest(){
        var input = new String[] {
                "2H", "2D", "2C", "6S", "6H"
        };
        assertTrue(texas.isFullHouse(input));
    }

    @Test
    public void isFourOfAKindTest(){
        var input = new String[] {
                "2H", "2D", "2C", "2S", "AH"
        };
        assertTrue(texas.isFourOfAKind(input));
    }

    @Test
    public void isStraightFlush(){
        var input = new String[] {
                "2H", "3H", "4H", "5H", "AH"
        };
        assertTrue(texas.isStraightFlush(input));
    }
}
