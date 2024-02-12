package src;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.filechooser.*;
import java.nio.file.*;

public class Frame extends JFrame implements ActionListener{
    static Color backgroundColor = new Color(13,15,17);
    static Color Green = new Color(208,237,87);
    static Color DisabledGreen = new Color(87, 87, 87);
    static Font fontTitle = new Font(Font.MONOSPACED, Font.BOLD,  45);
    static Font fontCell = new Font(Font.MONOSPACED, Font.BOLD,  50);
    static Font fontSequence = new Font(Font.MONOSPACED, Font.BOLD,  24);
    static Font buttonFont = new Font(Font.MONOSPACED, Font.PLAIN,  24);
    static Font smallFont = new Font(Font.MONOSPACED, Font.PLAIN,  18);

    static JLabel titleLabel, fileNameLabel;
    static JPanel bufferPanel, matrixBorder, sequenceBorder, sequenceListPanel, controlPanel, matrixPanel, filePanel;
    static JButton solveButton, randomButton, inputButton;
    static JScrollPane scrollMatrix, scrollSequence;

    Frame(){
        bufferPanel = new JPanel();
        bufferPanel.setBackground(backgroundColor);
        bufferPanel.setForeground(Green);
        bufferPanel.setBounds(65, 13, 200, 60);
        bufferPanel.setBorder(BorderFactory.createLineBorder(Green, 5));

        titleLabel = new JLabel();
        titleLabel.setText("Breach Protocol Solver");
        titleLabel.setForeground(Green);
        titleLabel.setFont(fontTitle);
        titleLabel.setBounds(550, 0, 700, 80);
        titleLabel.setVisible(true);

        matrixBorder = new JPanel();
        matrixBorder.setBounds(55, 85, 755, 755);
        matrixBorder.setBackground(backgroundColor);
        matrixBorder.setBorder(BorderFactory.createLineBorder(Green, 5));

        matrixPanel = new JPanel();
        matrixPanel.setBackground(backgroundColor);
        matrixPanel.setBorder(null);
        matrixPanel.setSize(750,750);

        sequenceBorder = new JPanel();
        sequenceBorder.setBounds(860, 205, 675, 635);
        sequenceBorder.setBackground(backgroundColor);
        sequenceBorder.setBorder(BorderFactory.createLineBorder(Green, 5));

        sequenceListPanel = new JPanel();
        sequenceListPanel.setBackground(backgroundColor);
        sequenceListPanel.setBounds(860, 205, 670, 630);
        sequenceListPanel.setBorder(null);

        controlPanel = new JPanel();
        controlPanel.setBounds(860, 85, 670, 100);
        controlPanel.setBackground(backgroundColor);
        controlPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();

        filePanel = new JPanel();
        filePanel.setBackground(backgroundColor);
        filePanel.setBorder(BorderFactory.createLineBorder(Green, 5));
        filePanel.setPreferredSize(new Dimension(300, 100));
        c.fill = GridBagConstraints.VERTICAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.gridheight = 1;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.WEST;
        filePanel.setLayout(new GridLayout(2, 1));
        controlPanel.add(filePanel, c);

        fileNameLabel = new JLabel("no file choosen");
        fileNameLabel.setBackground(backgroundColor);
        fileNameLabel.setHorizontalAlignment(JLabel.CENTER);
        fileNameLabel.setVerticalAlignment(JLabel.CENTER);
        fileNameLabel.setForeground(Green);
        fileNameLabel.setFont(smallFont);
        filePanel.add(fileNameLabel);

        inputButton = new JButton("Input File");
        inputButton.setBackground(Green);
        inputButton.setBorder(null);
        inputButton.setPreferredSize(new Dimension(300, 50));
        inputButton.setForeground(backgroundColor);
        inputButton.setFont(buttonFont);
        inputButton.setFocusable(false);
        inputButton.addActionListener(this);
        filePanel.add(inputButton);

        solveButton = new JButton("Solve");
        solveButton.setBackground(backgroundColor);
        solveButton.setBorder(BorderFactory.createLineBorder(Green, 5));
        solveButton.setPreferredSize(new Dimension(150, 100));
        solveButton.setForeground(Green);
        solveButton.setFont(buttonFont);
        solveButton.setFocusable(false);
        solveButton.setEnabled(false);
        solveButton.setBorder(BorderFactory.createLineBorder(DisabledGreen, 5));
        solveButton.addActionListener(this);
        c.gridx = 2;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridy = 0;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.CENTER;
        controlPanel.add(solveButton, c);

        randomButton = new JButton("Randomize");
        randomButton.setBackground(backgroundColor);
        randomButton.setBorder(BorderFactory.createLineBorder(Green, 5));
        randomButton.setPreferredSize(new Dimension(150, 100));
        randomButton.setForeground(Green);
        randomButton.setFont(buttonFont);
        randomButton.setFocusable(false);
        randomButton.addActionListener(this);
        c.gridx = 3;
        c.gridwidth = 1;
        c.gridheight = 1;
        c.gridy = 0;
        c.weightx = 1.0;
        c.anchor = GridBagConstraints.EAST;
        controlPanel.add(randomButton, c);

        this.setTitle("Cyberpunk 2077");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1600, 900);
        this.setLayout(null);
        this.setResizable(false);
        this.getContentPane().setBackground(backgroundColor);

        this.add(bufferPanel);
        this.add(titleLabel);
        this.add(controlPanel);
        this.add(matrixBorder);
        this.add(sequenceBorder);

        this.setVisible(true);
    }

    public void drawBuffer(){
        JLabel bufferLabel = new JLabel();
        bufferLabel.setForeground(Green);
        bufferLabel.setText("Buffer: " + Main.buffer);
        bufferLabel.setFont(fontSequence);
        bufferLabel.setHorizontalAlignment(JLabel.CENTER);
        bufferLabel.setVerticalAlignment(JLabel.CENTER);
        bufferPanel.add(bufferLabel);
    }

    public void drawMatrix(){
        matrixBorder.setBorder(null);
        // 24 Token per Row
        int row = Main.matrix.height;
        int col = Main.matrix.width;
        int cellSize = Math.min(Math.max(720/row, 30), Math.max(720/col, 30));
        matrixPanel.setSize(new Dimension(Math.max(720, 30*col), Math.max(720, 30*row)));
        // System.out.println(scrollMatrix.getSize());
        matrixPanel.setLayout(new GridLayout(row, col, 0, 0));
        // System.out.println(matrixPanel.getLayout());
        fontCell = fontCell.deriveFont((float) cellSize/ (float) 2.5);
        for (int i=0; i<row; i++){
            for (int j=0; j<col; j++){
                JLabel label = new JLabel(Main.matrix.tab[i][j]);
                label.setForeground(Green);
                label.setPreferredSize(new Dimension(cellSize, cellSize));
                //label.setBorder(BorderFactory.createLineBorder(Green, 5));
                label.setVerticalAlignment(JLabel.CENTER);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setFont(fontCell);
                matrixPanel.add(label);
                // System.out.println(label.getSize());
            }
        }

        matrixPanel.revalidate();
        matrixPanel.repaint();
        // System.out.println("panel: " + matrixPanel.getSize());

        scrollMatrix = new JScrollPane(matrixPanel);
        // System.out.println(scrollMatrix.getComponents());
        scrollMatrix.setSize(new Dimension(750, 750));
        // System.out.println("Scroll: " + scrollMatrix.getSize());
        scrollMatrix.setBorder(BorderFactory.createLineBorder(Green, 5));
        scrollMatrix.setBackground(backgroundColor);
        matrixBorder.add(scrollMatrix);

        scrollMatrix.revalidate();
        scrollMatrix.repaint();
        this.add(matrixBorder);
        matrixBorder.revalidate();
        matrixBorder.repaint();
        // System.out.println("Border: " + matrixBorder.getSize());
    }

    public void drawSequenceList(){
        sequenceBorder.setBorder(null);
        // 8 Token per Row
        int totalToken = 0;
        int numSequence = Main.sequenceList.length;
        for (int i=0; i<numSequence; i++){
            totalToken += (Main.sequenceList[i].len / 8 + 1);
        }
        sequenceListPanel.setSize(new Dimension(600, Math.max(totalToken * 60, 600)));
        //sequenceListPanel.setSize(new Dimension(600, totalToken * 60));
        //System.out.println("ListPanel: " + sequenceListPanel.getSize());
        sequenceListPanel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        int k=0;
        for (int i=0; i<numSequence; i++){
            Sequence currentSequence = Main.sequenceList[i];
            JPanel sequencePanel = new JPanel();
            // sequencePanel.setSize(new Dimension(600, Math.max((currentSequence.len / 8 + 1) * 60, 60)));
            // System.out.println("Panel: " + sequencePanel.getSize());
            sequencePanel.setBackground(backgroundColor);
            sequencePanel.setBorder(BorderFactory.createLineBorder(Green, 2));
            sequencePanel.setLayout(new GridLayout(currentSequence.len/8 + 1, 8, 0, 0));
            int j=0;
            JLabel label;
            while (j<Math.min(currentSequence.len, 8)){
                label = new JLabel(currentSequence.strArr[j]);
                label.setForeground(Green);
                label.setSize(new Dimension(60, 60));
                //System.out.println("label: " + label.getSize());
                label.setVerticalAlignment(JLabel.CENTER);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setFont(fontSequence);
                sequencePanel.add(label);
                j++;
            }
            while (j < currentSequence.len){
                label = new JLabel(currentSequence.strArr[j]);
                label.setForeground(Green);
                label.setBackground(backgroundColor);
                label.setSize(new Dimension(60, 60));
                label.setVerticalAlignment(JLabel.CENTER);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setFont(fontSequence);
                sequencePanel.add(label);
                j++;
            }
            while (j % 8 != 7){
                label = new JLabel();
                label.setBackground(backgroundColor);
                label.setSize(new Dimension(60, 60));
                label.setVerticalAlignment(JLabel.CENTER);
                label.setHorizontalAlignment(JLabel.CENTER);
                label.setFont(fontSequence);
                sequencePanel.add(label);
                j++;
            }
            label = new JLabel(Integer.toString(currentSequence.rew));
            label.setForeground(Color.white);
            label.setSize(new Dimension(60, 60));
            label.setVerticalAlignment(JLabel.CENTER);
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setFont(fontSequence);
            sequencePanel.add(label);
            j++;
            sequencePanel.revalidate();
            sequencePanel.repaint();
            c.gridx = 0;
            c.gridy = k;
            c.gridheight = currentSequence.len / 8 + 1;
            c.weightx = 1.0;
            c.fill = GridBagConstraints.HORIZONTAL;
            k += currentSequence.len / 8 + 1;
            sequenceListPanel.add(sequencePanel, c);
        }
        if (k < 10){
            JPanel dullPanel = new JPanel();
            dullPanel.setBackground(backgroundColor);
            c.gridy = k;
            k = 11 - k;
            dullPanel.setPreferredSize(new Dimension(600, k * 60 + 30));
            c.gridx = 0;
            c.gridheight = k;
            c.weightx = 1.0;
            c.fill = GridBagConstraints.HORIZONTAL;
            sequenceListPanel.add(dullPanel, c);
        }
        sequenceListPanel.revalidate();
        sequenceListPanel.repaint();

        // sequenceListPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        scrollSequence = new JScrollPane(sequenceListPanel);
        scrollSequence.setSize(new Dimension(670, 630));
        scrollSequence.setBorder(BorderFactory.createLineBorder(Green, 5));
        scrollSequence.setBackground(backgroundColor);
        sequenceBorder.add(scrollSequence, BorderLayout.NORTH);

        scrollSequence.revalidate();
        scrollSequence.repaint();
        this.add(sequenceBorder);
        sequenceBorder.revalidate();
        sequenceBorder.repaint();
    }

    public static void clear(){
        Main.matrix = null;
        Main.sequenceList = null;
        Main.answerRoute = null;
        bufferPanel.removeAll();
        matrixPanel.removeAll();
        matrixBorder.removeAll();
        sequenceListPanel.removeAll();
        sequenceBorder.removeAll();
        matrixBorder.setBorder(BorderFactory.createLineBorder(Green, 5));
        sequenceBorder.setBorder(BorderFactory.createLineBorder(Green, 5));
        matrixBorder.revalidate();
        matrixBorder.repaint();
        sequenceBorder.revalidate();
        sequenceBorder.repaint();
    }

    public void toggleSolveButton(){
        if (solveButton.isEnabled()){
            solveButton.setEnabled(false);
            solveButton.setBorder(BorderFactory.createLineBorder(DisabledGreen, 5));
        } else {
            solveButton.setEnabled(true);
            solveButton.setBorder(BorderFactory.createLineBorder(Green, 5));
        }
    }

    public void actionPerformed(ActionEvent e){
        String com = e.getActionCommand();
        if (com == "Input File"){
            JFileChooser fileChooser = new JFileChooser(FileSystemView.getFileSystemView());
            int openDialog = fileChooser.showOpenDialog(null);
            String fileName = null;
            if (openDialog == JFileChooser.APPROVE_OPTION){
                fileName = fileChooser.getSelectedFile().getName();
                Path absolutePath = fileChooser.getSelectedFile().toPath();
                Path currentDirectory = Paths.get(System.getProperty("user.dir"));
                Path relativePath = currentDirectory.relativize(absolutePath);
                Frame.clear();
                FileReader.readFile(relativePath.toString());
                Main.frame.drawBuffer();
                Main.frame.drawMatrix();
                Main.frame.drawSequenceList();
                fileNameLabel.setText(fileName);
                toggleSolveButton();
            } else {
                fileName = null;
                fileNameLabel.setText("no file choosen");
            }
        } else if (com == "Solve"){
            Main.answerRoute = new Coordinate[Main.buffer];
            for (int i=0; i<Main.buffer; i++){
                Main.answerRoute[i] = new Coordinate(-1, -1);
            }
            long startTime = System.nanoTime();
            Solver.solve();
            long endTime = System.nanoTime();
            long duration = (endTime - startTime);
            double milliseconds = duration / 1_000_000.0;
            System.out.println("Time taken: " + milliseconds + " milliseconds");
            AnswerFrame answerFrame = new AnswerFrame();
        } else {
            RandomizeFrame randomizeFrame = new RandomizeFrame();
        }
    }
}