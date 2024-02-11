package src;

import java.util.SortedSet;
import java.util.TreeSet;

public class Solver {
    static Coordinate[] route;
    static String[] currentBuffer;
    static SortedSet<Coordinate> visitedSet;
    static int pointer;
    static int caseCount;
    static int size;

    Solver(int sz){
        size = sz;
    }

    public static void solve(){
        // Initiate
        route = new Coordinate[size];
        Main.maxReward = 0;
        currentBuffer = new String[size];
        visitedSet = new TreeSet<>();
        pointer = 0;
        caseCount = 0;
        Coordinate end = new Coordinate(Main.matrix.width-1, Main.matrix.height-1);
        Coordinate temp = new Coordinate(0, 0);
        
        route[0] = temp;
        visitedSet.add(temp);
        currentBuffer[0] = Main.matrix.tab[0][0];
        patternMatch();
        pointer++;

        // Fill
        // pointer odd, go vertical, same col
        while (pointer < size){
            if (pointer%2==0){
                temp = new Coordinate(0, route[pointer-1].row);
            } else {
                temp = new Coordinate(route[pointer-1].col, 0);
            }
            while (!visitedSet.add(temp)){
                if (pointer%2==0){
                    temp.col++;
                } else {
                    temp.row++;
                }
            }
            currentBuffer[pointer] = Main.matrix.tab[temp.row][temp.col];
            route[pointer] = temp;
            patternMatch();
            pointer++;
        }
        pointer--;

        // Start
        while (pointer >= 0){
            while ((pointer%2==0 && route[pointer].col>end.col) || (pointer%2==1 && route[pointer].row>end.row)){
                visitedSet.remove(route[pointer]);
                pointer--;
            }
            if (pointer < 0){
                break;
            }
            visitedSet.remove(route[pointer]);
            if (pointer%2==0){
                route[pointer].col++;
            } else {
                route[pointer].row++;
            }
            while(!visitedSet.add(route[pointer])){
                if (pointer%2==0){
                    route[pointer].col++;
                } else {
                    route[pointer].row++;
                }
            }
            if ((pointer%2==0 && route[pointer].col>end.col) || (pointer%2==1 && route[pointer].row>end.row)){
                continue;
            }
            currentBuffer[pointer] = Main.matrix.tab[route[pointer].row][route[pointer].col];
            patternMatch();
            pointer++;
            while (pointer < size){
                if (pointer%2==0){
                    route[pointer].col = 0;
                    route[pointer].row = route[pointer-1].row; 
                } else {
                    route[pointer].row = 0;
                    route[pointer].col = route[pointer-1].col; 
                }
                while(!visitedSet.add(route[pointer])){
                    if (pointer%2==0){
                        route[pointer].col++;
                    } else {
                        route[pointer].row++;
                    }
                }
                if ((pointer%2==0 && route[pointer].col>end.col) || (pointer%2==1 && route[pointer].row>end.row)){
                    continue;
                }
                currentBuffer[pointer] = Main.matrix.tab[route[pointer].row][route[pointer].col];
                patternMatch();
                pointer++;
            }
            pointer--;

            /*
            for (int i=0; i<size; i++){
                System.out.print(route[i].col + "-" + route[i].row + " ");
            }
            System.out.println();
            */
        }
        System.out.println(caseCount);
    }

    public static void patternMatch(){
        /*
        for (int i=0; i<=pointer; i++){
            System.out.print(currentBuffer[i] + " ");
        }
        System.out.println();

        for (int i=0; i<=pointer; i++){
            System.out.print(route[i].col + "-" + route[i].row + " ");
        }
        System.out.println();
        */
        caseCount++;
        int sum = 0;
        int j;
        for (Sequence sq : Main.sequenceList){
            for (int i=0; i<=pointer; i++){
                if (currentBuffer[i].charAt(0) == sq.strArr[0].charAt(0) && currentBuffer[i].charAt(1) == sq.strArr[0].charAt(1)){
                    j=1;
                    while (j<sq.len && i+j<=pointer && currentBuffer[i+j].charAt(0) == sq.strArr[j].charAt(0) && currentBuffer[i+j].charAt(1) == sq.strArr[j].charAt(1)){
                        j++;
                    }
                    if (j == sq.len){
                        sum += sq.rew;
                        /*
                        System.out.println(j + " " + sq.len);
                        System.out.println(currentBuffer[i+j-1] + " = " + sq.strArr[j-1]);
                        System.out.println(currentBuffer[i+j-2] + " = " + sq.strArr[j-2]);
                        System.out.println(currentBuffer[i+j-3] + " = " + sq.strArr[j-3]);
                        */
                        break;
                    }
                }
            }
        }
        if (sum > Main.maxReward){
            Main.maxReward = sum;
            int i=0;
            while (i <= pointer){
                Main.answerRoute[i].row = route[i].row;
                Main.answerRoute[i].col = route[i].col;
                i++;
            }
    
            while (i < Main.buffer){
                Main.answerRoute[i].row = -1;
                Main.answerRoute[i].col = -1;
                i++;
            }
            
            /*
            int k=0;
            while (k < Main.buffer && !(Main.answerRoute[k].col == -1 && Main.answerRoute[k].row == -1)){
                System.out.print(Main.matrix.tab[Main.answerRoute[k].row][Main.answerRoute[k].col] + " ");
                k++;
            }
            System.out.println();
            System.out.println(sum);
            */
        }
    }

    /*
    public static void main(String[] args){
        currentBuffer = new String[]{"7A", "55", "55", "55", "BD", "1C", "E9", "55", "7A", "55", "1C", "E9"};
        String[] sqList = new String[]{"BD", "E9", "1C"};
        for (int i=0; i<=11; i++){
            if (currentBuffer[i].equals(sqList[0])){
                System.out.println(currentBuffer[i] + sqList[0]);
                int j=1;
                while (j<3 && i+j<=11 && currentBuffer[i+j].equals(sqList[j])){
                    System.out.println(currentBuffer[i+j] + sqList[j]);
                    j++;
                }
                if (j == 3){
                    System.out.println("exist");
                    System.out.println(j + " " + 3);
                    System.out.println(currentBuffer[i+j-1] + " = " + sqList[j-1]);
                    System.out.println(currentBuffer[i+j-2] + " = " + sqList[j-2]);
                    System.out.println(currentBuffer[i+j-3] + " = " + sqList[j-3]);
                    break;
                }
            }
        }
        System.out.println("nope");
    }
    */
}
