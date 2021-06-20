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
import org.antlr.v4.runtime.misc.Triple;
import java.lang.Math;
import java.util.Map;
import java.util.HashMap;
import static java.util.Map.entry;

import structures.*;
public class Test1 extends JPanel implements ActionListener,KeyListener{
    static final int Width_Board = 1000;
    static final int Height_Board = 1000;
    List<Figure> figures = new ArrayList<>();
    double varExpr34 = 30;
    double varExpr33 = varExpr34;
    private Timer timer = new Timer((int) varExpr33, this);
    Map<String,Pair<Double, Double>> positions = new HashMap<>();
    Map<String,structures.Angle> angles = new HashMap<>();
    boolean firstPaint = true;
    structures.Rectangle recFigure;
    structures.Circle circFigure;


    public void paint(Graphics g){
        super.paintComponent(g);

        ArrayList<java.awt.Rectangle> rectBoundsList = new ArrayList<>();

        g.drawString("P - play animation",10,10);
        g.drawString("S - stop animation",8,25);

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
        if (firstPaint) {
            recFigure = new structures.Rectangle("rec");
            double varExpr11 = 40;
            recFigure.setWidth(varExpr11);
            double varExpr12 = 50;
            recFigure.setHeight(varExpr12);
            double varExpr13 = 2;
            recFigure.setThickness(varExpr13);
            double varExpr14 = 3;
            Angle varExpr15 = new Angle((int) varExpr14);
            if(firstPaint) angles.put("rec", varExpr15);
            recFigure.setAngle(varExpr15);

            recFigure.setDisplay(true);

            recFigure.setFilled(false);
            double varExpr16 = 2;
            recFigure.setDepth(varExpr16);
            structures.Color varExpr17 = new structures.Color("ffffff");
            recFigure.setColor(varExpr17);
            double varExpr18 = 21;

            double varExpr19 = 179;

            double varExpr20 = 91;
            structures.Color varExpr21 = new structures.Color(new RGB((int)varExpr18,(int)varExpr19,(int)varExpr20));
            recFigure.setColor(varExpr21);
            double varExpr22 = 0;

            double varExpr23 = 10;
            structures.Point varPointExpr1 = new structures.Point(varExpr22,varExpr23);
            if(firstPaint) positions.put("rec", new Pair<Double, Double>(varExpr22,varExpr23));
            recFigure.setCenter(varPointExpr1);
        }

        recGraphics.translate(getWidth()/2,getHeight()/2);
        Rectangle2D rec = new Rectangle2D.Double(positions.get("rec").a - recFigure.width()/2,positions.get("rec").b - recFigure.height()/2,
                                                   recFigure.width(),recFigure.height());
        java.awt.Rectangle recBounds = rec.getBounds();
        rectBoundsList.add(recBounds);

        recGraphics.setColor(new java.awt.Color(recFigure.color().rgb().p0(), recFigure.color().rgb().p1(), recFigure.color().rgb().p2()));
        recGraphics.setStroke(new BasicStroke((int)recFigure.thickness()));
        recGraphics.translate(rec.getCenterX(),rec.getCenterY());
        recGraphics.rotate(angles.get("rec").degree());
        recGraphics.translate(-rec.getCenterX(),-rec.getCenterY());

        Graphics2D circGraphics = (Graphics2D) g.create();
        if (firstPaint) {
            circFigure = new structures.Circle("circ");
            double varExpr24 = 50;
            circFigure.setDiameter(varExpr24);
            double varExpr25 = 10;
            circFigure.setThickness(varExpr25);

            circFigure.setDisplay(true);

            circFigure.setFilled(false);
            double varExpr26 = 2;
            circFigure.setDepth(varExpr26);
            double varExpr27 = 209;

            double varExpr28 = 19;

            double varExpr29 = 85;
            structures.Color varExpr30 = new structures.Color(new RGB((int)varExpr27,(int)varExpr28,(int)varExpr29));
            circFigure.setColor(varExpr30);
            double varExpr31 = 0;

            double varExpr32 = 10;
            structures.Point varPointExpr2 = new structures.Point(varExpr31,varExpr32);
            if(firstPaint) positions.put("circ", new Pair<Double, Double>(varExpr31,varExpr32));
            circFigure.setCenter(varPointExpr2);
        }

        circGraphics.translate(getWidth()/2,getHeight()/2);
        Shape circ = new Ellipse2D.Double(positions.get("circ").a - circFigure.diameter()/2,positions.get("circ").b - circFigure.diameter()/2,
                                           circFigure.diameter(),circFigure.diameter());
        java.awt.Rectangle circBounds = circ.getBounds();
        rectBoundsList.add(circBounds);

        circGraphics.setColor(new java.awt.Color(circFigure.color().rgb().p0(), circFigure.color().rgb().p1(), circFigure.color().rgb().p2()));
        circGraphics.setStroke(new BasicStroke((int)circFigure.thickness()));
        circGraphics.translate(circBounds.getCenterX(),circBounds.getCenterY());
        circGraphics.translate(-circBounds.getCenterX(),-circBounds.getCenterY());


        recGraphics.draw(rec);

        circGraphics.draw(circ);
        double varExpr35 = 0;

        double varExpr37 = circFigure.center().y();

        double varExpr38 = 2;
        double varExpr36 = varExpr37-varExpr38;
        structures.Point varPointExpr3 = new structures.Point(varExpr35,varExpr36);
        positions.put("circ", new Pair<Double, Double>(varExpr35,varExpr36));
        circFigure.setCenter(varPointExpr3);
        boolean varBoolExpr5 = recBounds.intersects(circBounds);
        boolean varBoolExpr4 = !varBoolExpr5;
        if(varBoolExpr4){
            double varExpr39 = 0;

            double varExpr40 = 0;

            double varExpr41 = 0;
            structures.Color varExpr42 = new structures.Color(new RGB((int)varExpr39,(int)varExpr40,(int)varExpr41));
            circFigure.setColor(varExpr42);
        }

        boolean varBoolExpr6 = circBounds.intersects(boardTopBounds);
        if(varBoolExpr6){
            System.out.println("collided with top of board");
        }
        timer.start();
        if (firstPaint) firstPaint = false;

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        if(key == KeyEvent.VK_S){
            timer.stop();
        }
        if(key == KeyEvent.VK_P){
            timer.start(); 
        }  
    }

    @Override
    public void keyReleased(KeyEvent e){
    }

    @Override
    public void keyTyped(KeyEvent e){
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
        frame1.addKeyListener(n);
        frame1.add(n);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 

}
