package AnotationsHW;

public class TestClass1{

    public static void test1(){
        System.out.println("Test1");
    }

    @Test(priority = 1)
    public static void test2(){
        System.out.println("Test2");
    }

    @BeforeSuite
    public static void test3(){
        System.out.println("Test3");
    }

    public static void test4(){
        System.out.println("Test4");
    }

    @Test(priority = 4)
    public static void test5(){
        System.out.println("Test5");
    }
}
