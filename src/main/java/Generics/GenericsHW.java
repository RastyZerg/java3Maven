package Generics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GenericsHW {

    //Написать метод, который меняет два элемента массива местами. (массив может быть любого
    //ссылочного типа);
    private static <T> void arraySwitchElements(T[] array, int elNum1, int elNum2){
        T temp;
        if (elNum1 <= array.length && elNum2 <= array.length){
            temp = array[elNum1 - 1];
            array[elNum1 - 1] = array[elNum2 - 1];
            array[elNum2 - 1] = temp;
        }
    }
    //Написать метод, который преобразует массив в ArrayList;
    private static <T> List<T> arrayToList(T[] array){
        return new ArrayList<T>(Arrays.asList(array));
    }


//    Есть классы Fruit -> Apple, Orange; (больше фруктов не надо)
//    Класс Box в который можно складывать фрукты, коробки условно сортируются по типу фрукта,
//    поэтому в одну коробку нельзя сложить и яблоки, и апельсины;
//    Для хранения фруктов внутри коробки можете использовать ArrayList;
//    Сделать метод getWeight() который высчитывает вес коробки, зная кол-во фруктов и вес
//    одного фрукта(вес яблока - 1.0f, апельсина - 1.5f, не важно в каких это единицах);
//    Внутри класса коробка сделать метод compare, который позволяет сравнить текущую коробку
//    с той, которую подадут в compare в качестве параметра, true - если их веса равны, false в
//    противной случае(коробки с яблоками мы можем сравнивать с коробками с апельсинами);
//    Написать метод, который позволяет пересыпать фрукты из текущей коробки в другую коробку
//            (помним про сортировку фруктов, нельзя яблоки высыпать в коробку с апельсинами),
//    соответственно в текущей коробке фруктов не остается, а в другую перекидываются объекты,
//    которые были в этой коробке;
//    Ну и не забываем про метод добавления фрукта в коробку;
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