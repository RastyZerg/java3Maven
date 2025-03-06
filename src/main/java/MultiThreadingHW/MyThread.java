package MultiThreadingHW;

import java.io.FileWriter;
import java.io.IOException;

public class MyThread implements Runnable{
    private String line;

    public MyThread (String line){
        this.line = line;
    }

    @Override
    public void run() {
        try (FileWriter out = new FileWriter("6.txt", true)){
            for (int i = 0; i < 10; i++){
                //System.out.println(line);
                out.write(line + '\n');
                Thread.sleep(20);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}
