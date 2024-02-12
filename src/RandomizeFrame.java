package src;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RandomizeFrame extends JFrame implements ActionListener {
    JTextField bufferField;
    JTextField matrixField;
    JTextField numSeqField;
    JTextField maxSeqField;
    JTextField tokenInputField;

    RandomizeFrame(){
        JFrame randomFrame = new JFrame();
        randomFrame.setTitle("Generate Random Input");
        randomFrame.setSize(1200, 500);
        randomFrame.setLocationRelativeTo(Main.frame);
        randomFrame.setLayout(null);
        randomFrame.setResizable(false);
        randomFrame.getContentPane().setBackground(Frame.backgroundColor);
        randomFrame.setLayout(new GridLayout(2,2));
        randomFrame.setVisible(true);

        JPanel bufferMatrixPanel = new JPanel();
        bufferMatrixPanel.setBackground(Frame.backgroundColor);
        bufferMatrixPanel.setPreferredSize(new Dimension(600, 250));
        bufferMatrixPanel.setLayout(new GridLayout(4,1, 0, 30));
        JLabel bufferLabel = new JLabel("Buffer");
        bufferLabel.setForeground(Frame.Green);
        bufferLabel.setFont(Frame.fontSequence);
        bufferLabel.setHorizontalAlignment(JLabel.CENTER);
        bufferMatrixPanel.add(bufferLabel);
        bufferField = new JTextField(40);
        bufferField.setText("Insert buffer size");
        bufferField.setFont(Frame.smallFont);
        bufferField.setForeground(Color.BLACK);
        bufferField.setHorizontalAlignment(JTextField.CENTER);
        bufferField.setBorder(BorderFactory.createLineBorder(Frame.Green, 3));
        bufferMatrixPanel.add(bufferField);
        JLabel matrixLabel = new JLabel("Matrix");
        matrixLabel.setForeground(Frame.Green);
        matrixLabel.setFont(Frame.fontSequence);
        matrixLabel.setHorizontalAlignment(JLabel.CENTER);
        bufferMatrixPanel.add(matrixLabel);
        matrixField = new JTextField(50);
        matrixField.setText("Insert matrix size formatted \"row colums\" Ex: 3 5");
        matrixField.setFont(Frame.smallFont);
        matrixField.setForeground(Color.BLACK);
        matrixField.setHorizontalAlignment(JTextField.CENTER);
        matrixField.setBorder(BorderFactory.createLineBorder(Frame.Green, 3));
        bufferMatrixPanel.add(matrixField);
        randomFrame.add(bufferMatrixPanel);

        JPanel sequenceInputPanel = new JPanel();
        sequenceInputPanel.setBackground(Frame.backgroundColor);
        sequenceInputPanel.setPreferredSize(new Dimension(600, 250));
        sequenceInputPanel.setLayout(new GridLayout(4,1, 0, 30));
        JLabel sequenceInputLabel = new JLabel("Sequence");
        sequenceInputLabel.setForeground(Frame.Green);
        sequenceInputLabel.setFont(Frame.fontSequence);
        sequenceInputPanel.add(sequenceInputLabel);
        sequenceInputLabel.setHorizontalAlignment(JLabel.CENTER);
        numSeqField = new JTextField(50);
        numSeqField.setText("Insert number of sequence");
        numSeqField.setFont(Frame.smallFont);
        numSeqField.setForeground(Color.BLACK);
        numSeqField.setHorizontalAlignment(JTextField.CENTER);
        numSeqField.setBorder(BorderFactory.createLineBorder(Frame.Green, 3));
        sequenceInputPanel.add(numSeqField);
        JLabel emptyLabel1 = new JLabel("");
        emptyLabel1.setForeground(Frame.backgroundColor);
        sequenceInputPanel.add(emptyLabel1);
        maxSeqField = new JTextField(50);
        maxSeqField.setText("Insert the maximum length of sequence");
        maxSeqField.setFont(Frame.smallFont);
        maxSeqField.setForeground(Color.BLACK);
        maxSeqField.setHorizontalAlignment(JTextField.CENTER);
        maxSeqField.setBorder(BorderFactory.createLineBorder(Frame.Green, 3));
        sequenceInputPanel.add(maxSeqField);
        randomFrame.add(sequenceInputPanel);

        JPanel tokenInputPanel = new JPanel();
        tokenInputPanel.setBackground(Frame.backgroundColor);
        tokenInputPanel.setPreferredSize(new Dimension(600, 250));
        JLabel tokenInputLabel = new JLabel("Tokens");
        tokenInputLabel.setForeground(Frame.Green);
        tokenInputLabel.setFont(Frame.fontSequence);
        tokenInputPanel.add(tokenInputLabel);
        tokenInputField = new JTextField();
        tokenInputField.setText("Insert unique tokens with space (Ex: BD 1C 7A 55 E9)");
        tokenInputField.setFont(Frame.smallFont);
        tokenInputField.setForeground(Color.BLACK);
        tokenInputField.setHorizontalAlignment(JTextField.CENTER);
        tokenInputField.setBorder(BorderFactory.createLineBorder(Frame.Green, 3));
        tokenInputField.setPreferredSize(new Dimension(500, 170));
        tokenInputPanel.add(tokenInputField);
        randomFrame.add(tokenInputPanel);

        JPanel generatePanel = new JPanel();
        generatePanel.setPreferredSize(new Dimension(600, 250));
        generatePanel.setBackground(Frame.backgroundColor);
        JButton generateButton = new JButton("Generate");
        generateButton.setBackground(Frame.backgroundColor);
        generateButton.setBorder(BorderFactory.createLineBorder(Frame.Green, 5));
        generateButton.setPreferredSize(new Dimension(150, 100));
        generateButton.setForeground(Frame.Green);
        generateButton.setFont(Frame.buttonFont);
        generateButton.setFocusable(false);
        generateButton.setEnabled(true);
        generateButton.setBorder(BorderFactory.createLineBorder(Frame.Green, 5));
        generateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                Boolean filled1 = !((bufferField.getText().equals("Insert buffer size")) || bufferField.getText().isEmpty());
                Boolean filled2 = !((matrixField.getText().equals("Insert matrix size formatted \"row colums\" Ex: 3 5")) || matrixField.getText().isEmpty());
                Boolean filled3 = !((numSeqField.getText().equals("Insert number of sequence")) || numSeqField.getText().isEmpty());
                Boolean filled4 = !((maxSeqField.getText().equals("Insert the maximum length of sequence")) || maxSeqField.getText().isEmpty());
                Boolean filled5 = !((tokenInputField.getText().equals("Insert unique tokens with space (Ex: BD 1C 7A 55 E9)")) || tokenInputField.getText().isEmpty());
                if (filled1){
                    bufferField.setBorder(BorderFactory.createLineBorder(Frame.Green, 3));
                    if (filled2){
                        matrixField.setBorder(BorderFactory.createLineBorder(Frame.Green, 3));
                        if (filled3){
                            numSeqField.setBorder(BorderFactory.createLineBorder(Frame.Green, 3));
                            if (filled4){
                                maxSeqField.setBorder(BorderFactory.createLineBorder(Frame.Green, 3));
                                if (filled5){
                                    tokenInputField.setBorder(BorderFactory.createLineBorder(Frame.Green, 3));
                                    generateRandom();
                                    Main.frame.toggleSolveButton();
                                } else {
                                    tokenInputField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                                }
                            } else {
                                maxSeqField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                            }
                        } else {
                            numSeqField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                        }
                    } else {
                        matrixField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                    }
                } else {
                    bufferField.setBorder(BorderFactory.createLineBorder(Color.RED, 3));
                }
            }
        });
        generatePanel.setLayout(new GridBagLayout());
        generatePanel.add(generateButton, new GridBagConstraints());
        randomFrame.add(generatePanel);
    }

    public void generateRandom(){
        Frame.clear();
        String[] tokens = tokenInputField.getText().split(" ");
        Main.buffer = Integer.parseInt(bufferField.getText());
        String[] temp = matrixField.getText().split(" ");
        int w = Integer.parseInt(temp[0]);
        int h = Integer.parseInt(temp[1]);
        String[][] tempMat = new String[h][w];
        int len = tokens.length;
        for (int i=0; i<h; i++){
            for (int j=0; j<w; j++){
                tempMat[i][j] = tokens[Main.rng.nextInt(len)];
            }
        }
        Main.matrix = new Matrix(h, w, tempMat);
        int numSequence = Integer.parseInt(numSeqField.getText());
        int maxLength = Integer.parseInt(maxSeqField.getText());
        Main.sequenceList = new Sequence[numSequence];
        int reward;
        int currLen;
        String[] seq = new String[maxLength];
        for (int i=0; i<numSequence; i++){
            currLen = Main.rng.nextInt(maxLength-1)+2;
            for (int j=0; j<currLen; j++){
                seq[j] = tokens[Main.rng.nextInt(len)];
            }
            reward = Main.rng.nextInt(120) - 20;
            Main.sequenceList[i] = new Sequence(currLen, seq, reward);
        }
        Main.frame.drawBuffer();
        Main.frame.drawMatrix();
        Main.frame.drawSequenceList();
    }

    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'actionPerformed'");
    }
}