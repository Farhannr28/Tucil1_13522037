package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Panel;
import java.awt.Rectangle;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagConstraints;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AnswerFrame extends JFrame implements ActionListener{

    public static boolean notFound = Main.answerRoute[0].col == -1 &&  Main.answerRoute[0].row == -1;
    static JPanel rewardPanel, answerPanel, routePanel, routeBorder, timePanel, saveButtonPanel;
    static JLabel rewardLabel, maxLabel, answerLabel, timeLabel, messageLabel;
    static JButton saveButton;
    public static String optimalSequence;

    AnswerFrame(){
        JFrame answerFrame = new JFrame();
        answerFrame.setTitle("Answer");
        answerFrame.setSize(1200, 900);
        answerFrame.setLocationRelativeTo(Main.frame);
        answerFrame.setLayout(null);
        answerFrame.setResizable(false);
        answerFrame.getContentPane().setBackground(Frame.backgroundColor);
        // answerFrame.setLayout(new GridLayout(2,2));
        answerFrame.setVisible(true);

        rewardPanel = new JPanel();
        rewardPanel.setBackground(Frame.Green);
        rewardPanel.setForeground(Frame.backgroundColor);
        rewardPanel.setBounds(new Rectangle(0, 0, 250, 100));
        answerFrame.add(rewardPanel);

        answerPanel = new JPanel();
        answerPanel.setBackground(Frame.backgroundColor );
        answerPanel.setForeground(Frame.Green);
        answerPanel.setBounds(new Rectangle(250, 0, 950, 100));
        answerFrame.add(answerPanel);

        routeBorder = new JPanel();
        routeBorder.setBackground(Frame.backgroundColor);
        routeBorder.setBounds(new Rectangle(50, 110, 740, 740));
        routeBorder.setBorder(BorderFactory.createLineBorder(Frame.Green, 5));
        answerFrame.add(routeBorder);

        timePanel = new JPanel();
        timePanel.setBackground(Frame.backgroundColor);
        timePanel.setBounds(new Rectangle(810, 110, 350, 350));
        answerFrame.add(timePanel);

        saveButtonPanel = new JPanel();
        saveButtonPanel.setBackground(Frame.backgroundColor);
        saveButtonPanel.setBounds(new Rectangle(810, 480, 350, 350));
        // saveButtonPanel.setLayout(new GridBagLayout());
        answerFrame.add(saveButtonPanel);
    }

    public void drawHeader(){
        maxLabel = new JLabel("Maximum Reward");
        maxLabel.setForeground(Frame.backgroundColor);
        maxLabel.setHorizontalAlignment(JLabel.CENTER);
        maxLabel.setVerticalAlignment(JLabel.TOP);
        maxLabel.setFont(Frame.fontSequence);
        rewardPanel.add(maxLabel);

        rewardLabel = new JLabel("0");
        if (!notFound){
            rewardLabel.setText(String.valueOf(Main.maxReward));
        }
        rewardLabel.setForeground(Frame.backgroundColor);
        rewardLabel.setHorizontalAlignment(JLabel.CENTER);
        rewardLabel.setVerticalAlignment(JLabel.CENTER);
        rewardLabel.setFont(Frame.fontCell);
        rewardPanel.add(rewardLabel);
        rewardPanel.revalidate();
        rewardPanel.repaint();

        answerLabel = new JLabel("No Solution Found");
        if (!notFound){
            int i=0;
            optimalSequence = "";
            while (i < Main.buffer && !(Main.answerRoute[i].col == -1 && Main.answerRoute[i].row == -1)){
                optimalSequence +=  Main.matrix.tab[Main.answerRoute[i].row][Main.answerRoute[i].col] + " ";
                i++;
            }
            answerLabel.setText("<html>Optimal Sequence: " + optimalSequence + "</html>");
        }
        answerLabel.setForeground(Frame.Green);
        answerLabel.setHorizontalAlignment(JLabel.CENTER);
        answerLabel.setVerticalAlignment(JLabel.CENTER);
        if (Main.buffer > 10){
            answerLabel.setFont(Frame.smallFont);
        } else {
            answerLabel.setFont(Frame.mediumFont);
        }
        answerLabel.setPreferredSize(new Dimension(950, 100));
        answerPanel.add(answerLabel);
        answerPanel.revalidate();
        answerPanel.repaint();
    }

    public void drawRoute(){
        // 20 Token per Row sized 36
        if (notFound){
            JLabel noRoute = new JLabel("No Solution Found");
            noRoute.setForeground(Frame.Green);
            noRoute.setFont(Frame.fontTitle);
            noRoute.setVerticalAlignment(JLabel.CENTER);
            noRoute.setHorizontalAlignment(JLabel.CENTER);
            routeBorder.add(noRoute);
        } else {
            routePanel = new JPanel();
            routePanel.setBackground(Frame.backgroundColor);
            if (Main.matrix.height <= 20 && Main.matrix.width <= 20){
                int row = Main.matrix.height;
                int col = Main.matrix.width;
                int cellSize = Math.min(Math.max(720/row, 36), Math.max(720/col, 36));
                routePanel.setSize(new Dimension(Math.max(740, 30*col), Math.max(740, 30*row)));
                //routePanel.setLayout(new GridBagLayout());
                GridBagConstraints c = new GridBagConstraints();
                Font fontRoute = Frame.fontCell.deriveFont((float) cellSize/ (float) 2.5);
                JLabel[][]  labelArr = new JLabel[row][col];
                for (int i=0; i<row; i++){
                    for (int j=0; j<col; j++){
                        JLabel label = new JLabel(Main.matrix.tab[i][j]);
                        label.setForeground(Frame.Green);
                        label.setPreferredSize(new Dimension(cellSize, cellSize));
                        label.setBorder(null);
                        label.setVerticalAlignment(JLabel.CENTER);
                        label.setHorizontalAlignment(JLabel.CENTER);
                        label.setFont(fontRoute);
                        // label.setOpaque(false);
                        c.gridx = j;
                        c.gridy = i;
                        c.gridwidth = 1;
                        c.gridheight = 1;
                        routePanel.add(label, c);
                        labelArr[i][j] = label;
                    }
                }
                int len = 0;
                while (len < Main.buffer && !(Main.answerRoute[len].col == -1 && Main.answerRoute[len].row == -1)){
                    len++;
                }
                JLabel source,dest;
                for (int i=0; i<len-1; i++){
                    source = labelArr[Main.answerRoute[i].row][Main.answerRoute[i].col];
                    dest = labelArr[Main.answerRoute[i+1].row][Main.answerRoute[i+1].col];
                    source.setBorder(BorderFactory.createLineBorder(Frame.Blue, 4));
                    dest.setBorder(BorderFactory.createLineBorder(Frame.Blue, 4));
                }
                routePanel.revalidate();
                routePanel.repaint();
                RouteLines routeLines = new RouteLines(labelArr, len);
                routePanel.revalidate();
                routePanel.repaint();
                routeLines.revalidate();
                routeLines.repaint();
                routePanel.add(routeLines);
                routePanel.revalidate();
                routePanel.repaint();
                routeBorder.add(routePanel);
                routeBorder.revalidate();
                routeBorder.repaint();
            } else {
                JLabel tooBig = new JLabel("Route is too big to be drawn, Save file to see the full route");
                tooBig.setForeground(Frame.Green);
                tooBig.setFont(Frame.fontTitle);
                tooBig.setVerticalAlignment(JLabel.CENTER);
                tooBig.setHorizontalAlignment(JLabel.CENTER);
                routeBorder.add(tooBig);
            }
        }
    }

    public void drawTimeButton(){
        timeLabel = new JLabel();
        String text = "<html>Time Taken:<br> " + Main.time + " ms</html>";
        timeLabel.setText(text);
        timeLabel.setForeground(Frame.Green);
        timeLabel.setHorizontalAlignment(JLabel.CENTER);
        timeLabel.setVerticalAlignment(JLabel.CENTER);
        timeLabel.setFont(Frame.mediumFont);
        timeLabel.setPreferredSize(new Dimension(300, 300));
        timePanel.add(timeLabel);
        saveButton = new JButton("Save");
        saveButton.setBackground(Frame.backgroundColor);
        saveButton.setBorder(BorderFactory.createLineBorder(Frame.Green, 5));
        saveButton.setPreferredSize(new Dimension(300, 150));
        saveButton.setForeground(Frame.Green);
        saveButton.setFont(Frame.buttonFont);
        saveButton.setFocusable(false);
        saveButton.setEnabled(true);
        saveButton.setBorder(BorderFactory.createLineBorder(Frame.Green, 5));
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                WriteFile writeFile = new WriteFile();
            }
        });
        saveButtonPanel.add(saveButton);
        messageLabel = new JLabel();
        messageLabel.setHorizontalAlignment(JLabel.CENTER);
        messageLabel.setVerticalAlignment(JLabel.CENTER);
        messageLabel.setFont(Frame.smallFont);
        messageLabel.setPreferredSize(new Dimension(200, 200));
        saveButtonPanel.add(messageLabel);
        answerPanel.revalidate();
        answerPanel.repaint();
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

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}
