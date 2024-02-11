package src;

public class Main{
    static Frame frame;
    static Solver solver;
    public static int buffer;
    public static Matrix matrix;
    public static Sequence[] sequenceList;
    public static int maxReward;
    public static Coordinate answerRoute[];

    public static void main(String args[]){
        frame = new Frame();
        FileReader.readFile("testcase/tc1.txt");

        frame.drawMatrix();
        frame.drawSequenceList();


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
        

        long startTime = System.nanoTime();
        answerRoute = new Coordinate[buffer];
        for (int i=0; i<buffer; i++){
            answerRoute[i] = new Coordinate(-1, -1);
        }
        solver = new Solver(buffer);
        Solver.solve();
        
        int i=0;
        while (i < Main.buffer && !(answerRoute[i].col == -1 && answerRoute[i].row == -1)){
            System.out.print(answerRoute[i].col + "-" + answerRoute[i].row + " ");
            i++;
        }
        System.out.println();
        i=0;
        while (i < Main.buffer && !(answerRoute[i].col == -1 && answerRoute[i].row == -1)){
            System.out.print(matrix.tab[answerRoute[i].row][answerRoute[i].col] + " ");
            i++;
        }
        System.out.println("");
        System.out.println(maxReward);
        
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        double milliseconds = duration / 1_000_000.0;
        System.out.println("Time taken: " + milliseconds + " milliseconds");
    }
}