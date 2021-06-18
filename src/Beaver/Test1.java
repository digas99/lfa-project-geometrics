import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.geom.Point2D;
import java.awt.geom.Path2D;
import org.antlr.v4.runtime.misc.Pair;
import java.lang.Math;

import structures.*;
public class Test1 extends JPanel implements ActionListener{
    static final int Width_Board = 1000;
    static final int Height_Board = 1000;
    List<Figure> figures = new ArrayList<>();
    public void paint(Graphics g){
        super.paintComponent(g);
        String[] BeaverMainMainArgs = {"../../doc/Beaver/working/flags.bvr"};
        BeaverMain.main(BeaverMainMainArgs);
        structures.Figure flag1 = BeaverMain.getContainer("japanese");
        figures.add(flag1);

        structures.Figure sphere = BeaverMain.getContainer("armilarSphere");
        figures.add(sphere);

        System.out.println("this is a test");

        double varExpr1 = 10;
        double a = varExpr1;

        double varExpr3 = 5;

        double varExpr5 = a;

        double varExpr6 = 2;
        double varExpr4 = varExpr5/varExpr6;
        double varExpr2 = varExpr3+varExpr4;
        double b = varExpr2;

        double varExpr8 = a;
        double varExpr7 = varExpr8;

        double varExpr10 = b;
        double varExpr9 = varExpr10;
        boolean varBoolExpr2 = varExpr7==varExpr9;

        boolean varBoolExpr3 = true;
        boolean varBoolExpr1 = varBoolExpr2&&varBoolExpr3;
        if(varBoolExpr1){
            String string = "this is a string";
        }
        Graphics2D recGraphics = (Graphics2D) g.create();
        structures.Rectangle recFigure = new structures.Rectangle("rec");
        double varExpr11 = 400;
        recFigure.setWidth(varExpr11);
        double varExpr12 = 500;
        recFigure.setHeight(varExpr12);
        double varExpr13 = 2;
        recFigure.setThickness(varExpr13);
        double varExpr14 = 3;
        Angle varExpr15 = new Angle((int) varExpr14);
        recFigure.setAngle(varExpr15);

        recFigure.setDisplay(true);

        recFigure.setFilled(false);
        double varExpr16 = 2;
        recFigure.setDepth(varExpr16);
        Color varExpr17 = new Color("ffffff");
        recFigure.setColor(varExpr17);
        double varExpr18 = 12;

        double varExpr19 = 30;

        double varExpr20 = 20;
        Color varExpr21 = new Color(varExpr18,varExpr19,varExpr20);
        recFigure.setColor(varExpr21);
        Rectangle2D rec = new Rectangle2D.Double(positions.get("rec").a,positions.get("rec").b,recFigure.width(),recFigure.height());
        java.awt.Rectangle recBounds = rec.getBounds(); 
        rec.setColor(new Color(recFigure.color().rgb().toString()));
        rec.setStroke(new BasicStroke(recFigure.thickness()));
        recGraphics.translate((int)rec.getCenterX(),(int) rec.getCenterY());
        recGraphics.rotate(angles.get("rec").degree());
        recGraphics.translate((int) -rec.getCenterX(), (int) -rec.getCenterY());
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
