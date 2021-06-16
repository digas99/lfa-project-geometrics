import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.geom.Point2D;
import java.awt.geom.Path2D;

import java.lang.Math;

import structures.*;
public class TesteST extends JPanel implements ActionListener{
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
            double varExpr3 = 1;

            double varExpr4 = 3;
            double varExpr2 = varExpr3+varExpr4;

            double varExpr5 = 3;
            double varExpr1 = varExpr2/varExpr5;
            double n = varExpr1;double varExpr8 = 5;

            double varExpr9 = 1;
            double varExpr7 = Math.pow(varExpr8, varExpr9);

            double varExpr10 = 3;
            double varExpr6 = Math.pow(varExpr7, varExpr10);
            double n2 = varExpr6;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }

    public static void main(String[] args) throws Exception{
        TesteST n = new TesteST();
        JFrame frame1 = new JFrame();
        frame1.setTitle("Geometrics");
        frame1.setSize(new Dimension(Width_Board,Height_Board));
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.add(n);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
    }
}
