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

    public static void main(String args[]){
        long currentTime = System.currentTimeMillis();
        rng = new Random(currentTime);
        solver = new Solver();
        frame = new Frame();

        /*
        System.out.println(buffer);
        for (int i=0; i<matrix.height; i++){
            for (int j=0; j<matrix.width; j++){
                System.out.print(matrix.tab[i][j] + " ");
            }
            System.out.println("");
        }
        for (int i=0; i<sequenceList.length; i++){
            for (int j=0; j<sequenceList[i].len; j++){
                System.out.print(sequenceList[i].strArr[j] + " ");
            }
            System.out.println("\n" + sequenceList[i].rew);
            System.out.println("\n" + sequenceList[i].len);
        } 
        */      
    }
}