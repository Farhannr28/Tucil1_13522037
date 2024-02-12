package src;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Dimension;

public class RouteLines extends JComponent{
    JPanel[][] cellArr;
    int len;

    RouteLines(JPanel[][] arr, int length, int sz){
        setPreferredSize(new Dimension(sz/2, sz/2));
        this.cellArr = arr;
        this.len = length;
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("hi");
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Frame.Blue);
        g2d.setStroke(new BasicStroke(4));
        JPanel source,dest;
        for (int i=0; i<len-1; i++){
            source = cellArr[Main.answerRoute[i].row][Main.answerRoute[i].col];
            dest = cellArr[Main.answerRoute[i+1].row][Main.answerRoute[i+1].col];
            source.setBorder(BorderFactory.createLineBorder(Frame.Blue, 4));
            dest.setBorder(BorderFactory.createLineBorder(Frame.Blue, 4));
            /*
            // System.out.println(Main.answerRoute[i].row + " " + Main.answerRoute[i].col);
            //System.out.println(source.getX() + " " + dest.getX());
            if (source.getX() == dest.getX()) {
                g2d.drawLine(source.getX() + source.getWidth() / 2, source.getY(), dest.getX() + source.getWidth() / 2, dest.getY());
            } else {
                g2d.drawLine(source.getX(), source.getY() + source.getHeight() / 2, dest.getX(), dest.getY() + dest.getHeight() / 2);
            }
            */
        }
    }
}
