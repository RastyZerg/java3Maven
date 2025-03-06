package MultiThreadingRaceHW;

import java.util.ArrayList;
import java.util.Arrays;

public class Race {
    private boolean isWinnerDetermined = false;
    private ArrayList<Stage> stages;
    public ArrayList<Stage> getStages() { return stages; }
    public Race(Stage... stages) {
        this.stages = new ArrayList<>(Arrays.asList(stages));
    }
    public synchronized void getWinner(Car car){
        if (!isWinnerDetermined){
            System.out.println(car.getName() + " ВЫИГРАЛ ГОНКУ!!!");
            isWinnerDetermined = true;
        }
    }
}
