package Generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericsHW {

    private static <T> void arraySwitchElements(T[] array, int elNum1, int elNum2){
        T temp;
        if (elNum1 <= array.length && elNum2 <= array.length){
            temp = array[elNum1 - 1];
            array[elNum1 - 1] = array[elNum2 - 1];
            array[elNum2 - 1] = temp;
        }
    }

    private static <T> List<T> arrayToList(T[] array){
        return new ArrayList<T>(Arrays.asList(array));
    }

    public static void main(String[] args) {
        Integer[] arr1 = {1, 2, 3, 4};
        String[] arr2 = {"aaa", "bbb", "ccc", "ddd"};
        arraySwitchElements(arr1, 2, 4);
        arraySwitchElements(arr2, 2, 4);
        System.out.println(Arrays.toString(arr1));
        System.out.println(Arrays.toString(arr2));
        System.out.println(arrayToList(arr1));
    }
}