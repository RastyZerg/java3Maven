package TestsHW;

import java.util.Arrays;

// Написать метод, которому в качестве аргумента передается не пустой одномерный
//        целочисленный массив, метод должен вернуть новый массив, который получен путем
//вытаскивания элементов из исходного массива, идущих после последней четверки. Входной
//массив должен содержать хотя бы одну четверку, в противном случае в методе необходимо
//выбросить RuntimeException.
//Написать набор тестов для этого метода (варианта 3-4 входных данных)
//вх: [ 1 2 4 4 2 3 4 1 7 ] -> вых: [ 1 7 ]
//         Написать метод, который проверяет что массив состоит только из чисел 1 и 4. Если в массиве
//нет хоть одной 4 или 1, то метод вернет false;
//Написать набор тестов для этого метода (варианта 3-4 входных данных)

public class ArrayChanges {

    public int[] arrayCut(int[] arr){
        int index = -1;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] == 4){
                index = i;
            }
        }
        if (index != -1){
            return Arrays.copyOfRange(arr, index + 1, arr.length);
        }
        else {
            throw new RuntimeException();
        }


    }

    public boolean arrayCheck(int[] arr){
        boolean containsOne = false;
        boolean containsFour = false;
        for (int i = 0; i < arr.length; i++){
            if (arr[i] != 1 && arr[i] != 4){
                return false;
            }
            else {
                if (arr[i] == 1 && !containsOne){
                    containsOne = true;
                }
                if (arr[i] == 4 && !containsFour){
                    containsFour = true;
                }
            }
        }
        return (containsOne && containsFour);
    }
}
