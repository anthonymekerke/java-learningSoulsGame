package helpers;

import java.util.Random;

public class Dice {
    private int face;
    private Random random;

    public Dice(int faces){
        face = faces;
        random = new Random();
    }

    public int roll(){
        return random.nextInt(face);
    }

    public int getFace() {
        return face;
    }

    public static void main(String[] args){
        Dice d = new Dice(50);
        int min = d.roll();
        int max = d.roll();
        int current;

        for(int i = 0; i < 500; i++){
            current = d.roll();
            if(current < min ){ min = current;}
            else if(current > max) {max = current;}
        }

        System.out.println("Min: " + min);
        System.out.println("Max:" + max);
    }
}
