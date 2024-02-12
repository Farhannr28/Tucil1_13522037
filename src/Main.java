package src;

import java.util.Random;
public class Main{
    static Frame frame;
    static Solver solver;
    public static int buffer;
    public static Matrix matrix;
    public static Sequence[] sequenceList;
    public static int maxReward;
    public static Coordinate answerRoute[];
    public static Random rng;
    public static double time;
    public static String fileName;

    public static void main(String args[]){
        long currentTime = System.currentTimeMillis();
        rng = new Random(currentTime);
        solver = new Solver();
        frame = new Frame();  
    }
}