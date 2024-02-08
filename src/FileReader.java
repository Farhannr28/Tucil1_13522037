package src;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FileReader {
    private static Scanner sc;
    public static void readFile(String path){
        try {
            sc = new Scanner(new File(path));
            Main.buffer = Integer.parseInt(sc.nextLine());
        String[] temp = sc.nextLine().split(" ");
        int w = Integer.parseInt(temp[0]);
        int h = Integer.parseInt(temp[1]);
        String[][] tempMat = new String[h][w];
        for (int i=0; i<h; i++){
            temp = sc.nextLine().split(" ");
            for (int j=0; j<w; j++){
                tempMat[i][j] = temp[j];
            }
        }
        Main.matrix = new Matrix(h, w, tempMat);
        int numSequence = Integer.parseInt(sc.nextLine());
        Main.sequenceList = new Sequence[numSequence];
        int reward;
        for (int i=0; i<numSequence; i++){
            temp = sc.nextLine().split(" ");
            reward = Integer.parseInt(sc.nextLine());
            Main.sequenceList[i] = new Sequence(temp.length, temp, reward);
        }
        sc.close();
        // System.out.println("Done Reading");
        } catch (FileNotFoundException e){
            System.out.println("File with path \'" + path + "\' is not found");
        }
    }
}
