import TestsHW.ArrayChanges;
import org.junit.Test;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class TestArrayCut {
    @Parameterized.Parameters
    public static Collection<Object[]> data(){
        return Arrays.asList(new Object[][] {
                {new int[]{1, 2, 4, 4, 2, 3, 4, 1, 7}, new int[]{1, 7}},
                {new int[]{3, 5, 6, 7, 7, 8, 9, 0, 4}, new int[]{}},
                {new int[]{1, 2, 2, 2, 4, 3, 1, 1, 7}, new int[]{3, 1, 1, 7}},
        });
    }

    private ArrayChanges arr = new ArrayChanges();
    private int[] inArray;
    private int[] outArray;

    public TestArrayCut(int[] arr1, int[] arr2){
        this.inArray = arr1;
        this.outArray = arr2;
    }

    @Test
    public void testArrCut(){
        Assert.assertArrayEquals(outArray, arr.arrayCut(inArray));
    }

    //Тест 3 раза выполнится, т.к. не можно исключить из параметрического запуска конкретный тест, или можно но я хз как
    //А непараметрические лень писать :)
    @Test(expected = RuntimeException.class)
    public void testArrayCutException(){
        arr.arrayCut(new int[]{1, 2, 8, 9, 2, 3, 1, 1, 7});
    }
}
