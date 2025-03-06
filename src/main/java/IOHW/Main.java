package IOHW;

import com.sun.jdi.connect.spi.Connection;

import java.io.*;
import java.util.*;

public class Main {
private static final Scanner scanner = new Scanner(System.in);
private static final int pageSize = 1800;
    public static void main(String[] args) {
        //task1();
        //task2();
        //task3();
    }

    private static void task3() {
        long page = scanner.nextLong();
        byte[] arr = new byte[pageSize];
        try (RandomAccessFile raf = new RandomAccessFile("1.txt", "r")) {
            if ((page * pageSize) > raf.length()){
                System.out.println("Страница не существует");
            } else {
                raf.seek((pageSize * (page - 1)));
                raf.read(arr);
                for (byte b: arr){
                    System.out.print((char)b);
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void task2() {
        try {
            FileInputStream in2 = new FileInputStream("2.txt");
            FileInputStream in3 = new FileInputStream("3.txt");
            FileInputStream in4 = new FileInputStream("4.txt");
            FileInputStream in5 = new FileInputStream("5.txt");
            FileOutputStream out = new FileOutputStream("1.txt", true);
            ArrayList<InputStream> ai = new ArrayList<>();
            ai.add(in2);
            ai.add(in3);
            ai.add(in4);
            ai.add(in5);
            SequenceInputStream in = new SequenceInputStream(Collections.enumeration(ai));
            int x;
            while((x = in.read()) != -1){
                out.write(x);
            }
            in.close();
            out.flush();
            out.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void task1() {
        try (FileInputStream in = new FileInputStream("HomeWork.txt")) {
            byte[] arr = new byte[20];
            int x;
            while ((x = in.read(arr)) != -1){
                for (byte b: arr) {
                    System.out.print((char)b);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
