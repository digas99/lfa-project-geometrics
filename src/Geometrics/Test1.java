import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.geom.Point2D;
import java.awt.geom.Path2D;

import java.Math.*;

import structures.*;
public class Test1 extends JPanel implements ActionListener{
    public static void main(String[] args) throws Exception{
        System.out.println("this is a test");
        boolean varBoolExpr3 = true;

        boolean varBoolExpr4 = false;
        boolean varBoolExpr2 = varBoolExpr3||varBoolExpr4;

        boolean varBoolExpr5 = true;
        boolean varBoolExpr1 = varBoolExpr2&&varBoolExpr5;
        if(varBoolExpr1){
        }
    }
}