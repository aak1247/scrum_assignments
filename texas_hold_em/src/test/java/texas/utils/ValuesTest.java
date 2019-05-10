package texas.utils;

import org.junit.Assert;
import org.junit.Test;

public class ValuesTest {
    @Test
    public void compareTest1() {
        var values1 = new int[]{3,6,9};
        var values2 = new int[] {2,7,8};
        Assert.assertEquals(Values.compare(values1, values2), true);
    }

    @Test
    public void compareTest2() {
        var values1 = new int[]{3,6,8};
        var values2 = new int[] {2,7,8};
        Assert.assertEquals(Values.compare(values1, values2), false);
    }
}
