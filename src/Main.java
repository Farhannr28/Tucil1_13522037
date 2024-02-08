package src;

public class Main{
    static Frame frame;
    public static int buffer;
    public static Matrix matrix;
    public static Sequence[] sequenceList;

    public static void main(String args[]){
        frame = new Frame();
        FileReader.readFile("testcase/tc2.txt");


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
        } 
        */
    }
}