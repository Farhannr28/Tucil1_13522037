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
    static int minimumLength;

    public static void solve(){
        // Initiate
        size = Main.buffer;
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

        // Fill
        // pointer odd, go vertical, same col
        boolean addPrevious;
        while (pointer < size){
            // System.out.println(pointer + " " + route[pointer].col + "-" + route[pointer].row);
            addPrevious = false;
            while ((pointer%2==0 && route[pointer].col>end.col) || (pointer%2==1 && route[pointer].row>end.row)){
                // System.out.println("addPrevious true: " + " " + route[pointer].col + "-" + route[pointer].row);
                visitedSet.remove(route[pointer]);
                pointer--;
                addPrevious = true;
            }
            if (addPrevious){
                while(!visitedSet.add(route[pointer])){
                    temp = new Coordinate(route[pointer].col, route[pointer].row);
                    route[pointer] = temp;
                    if (pointer%2==0){
                        route[pointer].col++;
                    } else {
                        route[pointer].row++;
                    }
                    //System.out.println(route[pointer].col + "-" + route[pointer].row);
                }
                // System.out.println("add: " + " " + route[pointer].col + "-" + route[pointer].row);
                if ((pointer%2==0 && route[pointer].col>end.col) || (pointer%2==1 && route[pointer].row>end.row)){
                    continue;
                }
                currentBuffer[pointer] = Main.matrix.tab[route[pointer].row][route[pointer].col];
                patternMatch();
            } else {
                pointer++;
                if (pointer == size){
                    break;
                }
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
                route[pointer] = temp;
                if ((pointer%2==0 && temp.col>end.col) || (pointer%2==1 && temp.row>end.row)){
                    // System.out.println("continue");
                    continue;
                }
                currentBuffer[pointer] = Main.matrix.tab[temp.row][temp.col];
                patternMatch();
            }
        }
        pointer--;

        // Start
       //  System.out.println("start");
        while (pointer >= 0){
            while ((pointer%2==0 && route[pointer].col>end.col) || (pointer%2==1 && route[pointer].row>end.row)){
                visitedSet.remove(route[pointer]);
                pointer--;
            }
            if (pointer<0){
                break;
            }
            visitedSet.remove(route[pointer]);
            if (pointer%2==0){
                route[pointer].col++;
            } else {
                route[pointer].row++;
            }
            temp = new Coordinate(route[pointer].col, route[pointer].row);
            route[pointer] = temp;
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
                temp = new Coordinate(route[pointer].col, route[pointer].row);
                route[pointer] = temp;
                while(!visitedSet.add(route[pointer])){
                    if (pointer%2==0){
                        route[pointer].col++;
                    } else {
                        route[pointer].row++;
                    }
                    // System.out.println(route[pointer].col + "-" + route[pointer].row);
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
    }

    public static void patternMatch(){
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
                        break;
                    }
                }
            }
        }
        if (sum > Main.maxReward){
            Main.maxReward = sum;
            int i=0;
            minimumLength = pointer;
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
        } else if (sum == Main.maxReward){
            if (pointer < minimumLength){
                Main.maxReward = sum;
                int i=0;
                minimumLength = pointer;
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
            }
        }
    }
}
