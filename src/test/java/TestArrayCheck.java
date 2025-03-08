import TestsHW.ArrayChanges;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestArrayCheck {
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][] {
                {new int[]{1, 4, 4, 1, 1}, true},
                {new int[]{1, 1, 1, 1, 1}, false},
                {new int[]{4, 4, 4, 4, 4}, false},
                {new int[]{1, 4, 5, 1, 1}, false},
        });
    }
    private int[] inArray;
    private boolean resultCheck;
    private ArrayChanges arr = new ArrayChanges();

    public TestArrayCheck(int[] inArray, boolean resultCheck){
        this.inArray = inArray;
        this.resultCheck = resultCheck;
    }

    @Test
    public void testArrCheck() {
        Assert.assertEquals(arr.arrayCheck(inArray), resultCheck);
    }

}
