package src;

import java.awt.Color;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {
    String filePath;
    int generated = 0;
    String content;

    WriteFile(){
        if (Main.fileName==null){
            filePath = "testing/Solved_generated" + generated + ".txt";
            generated++;
        } else {
            filePath = "testing/Solved_" + Main.fileName;
        }
        content = Main.maxReward + "\n";
        if (!AnswerFrame.notFound){
            content += AnswerFrame.optimalSequence + "\n";
            int i=0;
            while (i < Main.buffer && !(Main.answerRoute[i].col == -1 && Main.answerRoute[i].row == -1)){
                content += (Main.answerRoute[i].col+1) + "," + (Main.answerRoute[i].row+1) + "\n";
                i++;
            }
        }
        content += "\n";
        content += Main.time + " ms";
        try{
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(content);
            bufferedWriter.close();
            AnswerFrame.messageLabel.setText("<html>Solution succesfully saved as<br>" + filePath + "</html>");
            AnswerFrame.messageLabel.setForeground(Frame.Blue);
        } catch(IOException e) {
            AnswerFrame.messageLabel.setText("An error occured while saving Solution");
            AnswerFrame.messageLabel.setForeground(Color.RED);
        }
    }
}
