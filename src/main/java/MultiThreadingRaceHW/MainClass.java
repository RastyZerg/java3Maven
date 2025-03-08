package MultiThreadingRaceHW;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.*;

//Все участники должны стартовать одновременно, несмотря на то что на подготовку у
//каждого их них уходит разное время
//В туннель не может заехать одновременно больше половины участников(условность)
//Попробуйте все это синхронизировать
//Только после того как все завершат гонку нужно выдать объявление об окончании
//Можете корректировать классы (в т.ч. конструктор машин) и добавлять объекты
//классов из пакета util.concurrent
//Как только первая из машин пересекает финишную черту, ее необходимо объявить
//победителем (победитель должен быть только один)

public class MainClass {
    public static final int CARS_COUNT = 4;
    public static CyclicBarrier cb = new CyclicBarrier(CARS_COUNT); //Барьер для одновременного старта
    public static ExecutorService service = Executors.newFixedThreadPool(CARS_COUNT);
    public static CountDownLatch cdl = new CountDownLatch(CARS_COUNT); //Счетчик готовых машинок чтоб раньше времени не поехали :)
    public static void main(String[] args) {
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Подготовка!!!");
        Race race = new Race(new Road(60), new Tunnel(CARS_COUNT / 2), new Road(40));
        Car[] cars = new Car[CARS_COUNT];
        for (int i = 0; i < cars.length; i++) {
            cars[i] = new Car(race, 20 + (int) (Math.random() * 10), cb, cdl);
        }
        for (int i = 0; i < cars.length; i++) {
            service.execute(cars[i]);
            //new Thread(cars[i]).start();
        }
        try {
            cdl.await();
            System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка началась!!!");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        service.shutdown();
        try {
            service.awaitTermination(5, TimeUnit.HOURS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.out.println("ВАЖНОЕ ОБЪЯВЛЕНИЕ >>> Гонка закончилась!!!");
    }
}

