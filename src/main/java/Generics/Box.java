package Generics;

import java.util.ArrayList;
import java.util.List;

public class Box <T extends Fruit>{

    List<T> fruits = new ArrayList<T>();

    public Box(List<T> fruits){
        this.fruits = fruits;
    }

    public float getWeight(){
        return fruits.size() * fruits.getFirst().getWeight();
    }

    public void add(T fruit){
        fruits.add(fruit);
    }

    public boolean compare(Box box){
        return Math.abs(this.getWeight() - box.getWeight()) < 0.0000001f;
    }

    public void join(Box<? super T> box){
            box.fruits.addAll(this.fruits);
            this.fruits.clear();
        }

}
