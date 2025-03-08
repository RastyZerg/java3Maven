package AnotationsHW;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.*;

public class MainClass {

//    Создать класс, который может выполнять «тесты», в качестве тестов выступают классы с наборами
//    методов с аннотациями @Test. Для этого у него должен быть статический метод start(), которому в
//    качестве параметра передается или объект типа Class, или имя класса. Из «класса-теста» вначале
//    должен быть запущен метод с аннотацией @BeforeSuite если такой имеется, далее запущены методы
//    с аннотациями @Test, а по завершению всех тестов – метод с аннотацией @AfterSuite. К каждому
//    тесту необходимо также добавить приоритеты (int числа от 1 до 10), в соответствии с которыми будет
//    выбираться порядок их выполнения, если приоритет одинаковый то порядок не имеет значения.
//    Методы с аннотациями @BeforeSuite и @AfterSuite должны присутствовать в единственном
//    экземпляре, иначе необходимо бросить RuntimeException при запуске «тестирования».

    private static void start(Class c){

        boolean BeforeAnnotation = false;
        boolean AfterAnnotation = false;
        Method[] methods = c.getDeclaredMethods();

        //Тут код дублируется, можно лучше, например выделить в метод, но вертеть названия анотаций в классы и обратно слишком муторно
        //А еще наверное лучше сначала проверки все сделать а потом тесты начинать гонять, а то может часть отработать и потом только эксепшн в конце
        //Но в задании не уточнено
        for (Method m : methods){
            if (m.isAnnotationPresent(BeforeSuite.class)){
                if (!BeforeAnnotation){
                    try {
                        m.invoke(null);
                        BeforeAnnotation = true;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
                else throw new RuntimeException("Аннотаций @BeforeSuite больше одной" );

            }
        }

        if (!BeforeAnnotation) {
            throw new RuntimeException("Отсутствует аннотация @BeforeSuite" );
        }


        for (int i = 1; i < 11; i++){
            for (Method m : methods){
                if (m.isAnnotationPresent(Test.class) && (m.getAnnotation(Test.class).priority() > 10 || m.getAnnotation(Test.class).priority() < 1)){
                    throw new RuntimeException("Некорректный приоритет теста");
                }
                if (m.isAnnotationPresent(Test.class) && m.getAnnotation(Test.class).priority() == i){
                    try {
                        m.invoke(null);
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }

        for (Method m : methods){
            if (m.isAnnotationPresent(AfterSuite.class)){
                if (!AfterAnnotation){
                    try {
                        m.invoke(null);
                        AfterAnnotation = true;
                    } catch (IllegalAccessException e) {
                        throw new RuntimeException(e);
                    } catch (InvocationTargetException e) {
                        throw new RuntimeException(e);
                    }
                }
                else throw new RuntimeException("Аннотаций @AfterSuite больше одной" );

            }
        }
        if (!AfterAnnotation) {
            throw new RuntimeException("Отсутствует аннотация @AfterSuite" );
        }

    }




    public static void main(String[] args) {

        start(TestClass1.class);
    }
}
