package src;

import java.awt.Container;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Line2D;
import java.awt.geom.Path2D;
import java.awt.geom.Point2D;
import java.awt.Dimension;
import java.awt.BasicStroke;

public class RouteLines extends JPanel{
    JLabel[][] labelArr;
    int len;

    RouteLines(JLabel[][] arr, int length){
        this.labelArr = arr;
        this.len = length;
    }

    @Override
    protected void paintComponent(Graphics g) {
        System.out.println("hi");
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(Frame.Blue);
        g2d.setStroke(new BasicStroke(6));
        JLabel source,dest;
        for (int i=0; i<len-1; i++){
            source = labelArr[Main.answerRoute[i].row][Main.answerRoute[i].col];
            dest = labelArr[Main.answerRoute[i+1].row][Main.answerRoute[i+1].col];
            System.out.println(Main.answerRoute[i].row + " " + Main.answerRoute[i].col);
            System.out.println(source.getX() + " " + dest.getX());
            if (source.getX() == dest.getX()) {
                g2d.drawLine(source.getX() + source.getWidth() / 2, source.getY(), dest.getX() + source.getWidth() / 2, dest.getY());
            } else {
                g2d.drawLine(source.getX(), source.getY() + source.getHeight() / 2, dest.getX(), dest.getY() + dest.getHeight() / 2);
            }
        }
    }
}
