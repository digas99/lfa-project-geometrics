import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.geom.Point2D;
import java.awt.geom.Path2D;

import java.lang.Math;

import structures.*;
public class Test1 extends JPanel implements ActionListener{
    static final int Width_Board = 1000;
    static final int Height_Board = 1000;

    public void paint(Graphics g){
        super.paintComponent(g);
        System.out.println("this is a test");
        boolean varBoolExpr3 = true;

        boolean varBoolExpr4 = false;
        boolean varBoolExpr2 = varBoolExpr3||varBoolExpr4;

        boolean varBoolExpr5 = true;
        boolean varBoolExpr1 = varBoolExpr2&&varBoolExpr5;
        if(varBoolExpr1){
            structures.Point varExpr1 = n.center();
            structures.Point n3 = varExpr1;double varExpr2 = n.center().x();
            double n3x = varExpr2;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        repaint();
    }

    public static void main(String[] args) throws Exception{
        Test1 n = new Test1();
        JFrame frame1 = new JFrame();
        frame1.setTitle("Test");
        frame1.setSize(new Dimension(Width_Board,Height_Board));
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.add(n);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
}
