package src;

public class AnswerFrame {

    AnswerFrame(){
        
    }
    
    public static void debugAnswer(){
        int i=0;
        while (i < Main.buffer && !(Main.answerRoute[i].col == -1 && Main.answerRoute[i].row == -1)){
            System.out.print(Main.answerRoute[i].col + "-" + Main.answerRoute[i].row + " ");
            i++;
        }
        System.out.println();
        i=0;
        while (i < Main.buffer && !(Main.answerRoute[i].col == -1 && Main.answerRoute[i].row == -1)){
            System.out.print(Main.matrix.tab[Main.answerRoute[i].row][Main.answerRoute[i].col] + " ");
            i++;
        }
        System.out.println();
        System.out.println(Main.maxReward);
    }
}
