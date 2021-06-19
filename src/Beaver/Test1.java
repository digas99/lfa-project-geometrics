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

import structures.*;
public class Test1 extends JPanel implements ActionListener,KeyListener{
    static final int Width_Board = 1000;
    static final int Height_Board = 1000;
    List<Figure> figures = new ArrayList<>();

    public void paint(Graphics g){
        super.paintComponent(g);

        g.drawString("P - play animation",10,10);
        g.drawString("S - stop animation",8,25);

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
        double varExpr22 = 0;

        double varExpr23 = 10;
        Point varPointExpr1 = new structures.Point(varExpr22,varExpr23);
        recFigure.setCenter(varPointExpr1);

        Rectangle2D rec = new Rectangle2D.Double(positions.get("rec").a,positions.get("rec").b,
                                                   recFigure.width(),recFigure.height());
        java.awt.Rectangle recBounds = rec.getBounds(); 
        recGraphics.setColor(new Color(recFigure.color().rgb().toString()));
        recGraphics.setStroke(new BasicStroke(recFigure.thickness()));
        recGraphics.translate(rec.getCenterX(),rec.getCenterY());
        recGraphics.rotate(angles.get("rec").degree());
        recGraphics.translate(-rec.getCenterX(),-rec.getCenterY());

        Graphics2D circGraphics = (Graphics2D) g.create();
        structures.Circle circFigure = new structures.Circle("circ");
        double varExpr24 = 500;
        circFigure.setDiameter(varExpr24);
        double varExpr25 = 2;
        circFigure.setThickness(varExpr25);

        circFigure.setDisplay(true);

        circFigure.setFilled(false);
        double varExpr26 = 2;
        circFigure.setDepth(varExpr26);
        double varExpr27 = 12;

        double varExpr28 = 30;

        double varExpr29 = 20;
        Color varExpr30 = new Color(varExpr27,varExpr28,varExpr29);
        circFigure.setColor(varExpr30);
        double varExpr31 = 0;

        double varExpr32 = 10;
        Point varPointExpr2 = new structures.Point(varExpr31,varExpr32);
        circFigure.setCenter(varPointExpr2);

        Shape circ = new Ellipse2D.Double(positions.get("circ").a,positions.get("circ").b,
                                           circFigure.diameter(),circFigure.diameter());
        java.awt.Rectangle circBounds = circ.getBounds(); 
        circGraphics.setColor(new Color(circFigure.color().rgb().toString()));
        circGraphics.setStroke(new BasicStroke(circFigure.thickness()));
        circGraphics.translate(circ.getCenterX(),circ.getCenterY());
        circGraphics.rotate(angles.get("circ").degree());
        circGraphics.translate(-circ.getCenterX(),-circ.getCenterY());

        Graphics2D linGraphics = (Graphics2D) g.create();
        structures.Line linFigure = new structures.Line("lin");
        double varExpr33 = 2;
        linFigure.setThickness(varExpr33);

        linFigure.setDisplay(true);
        double varExpr34 = 250;

        double varExpr35 = 124;

        double varExpr36 = 98;
        Color varExpr37 = new Color(varExpr34,varExpr35,varExpr36);
        linFigure.setColor(varExpr37);
        Color varExpr38 = new Color("ffffff");
        linFigure.setColor(varExpr38);

        Shape lin = new Line2D.Double(lines.get("lin").a.x(),lines.get("lin").a.y(),
                                      lines.get("lin").b.x(),lines.get("lin".b.y()));
        java.awt.Rectangle linBounds = lin.getBounds(); 
        linGraphics.setColor(new Color(linFigure.color().rgb().toString()));
        linGraphics.setStroke(new BasicStroke(linFigure.thickness()));
        linGraphics.translate(lin.getCenterX(),lin.getCenterY());
        linGraphics.rotate(angles.get("lin").degree());
        linGraphics.translate(-lin.getCenterX(),-lin.getCenterY());

        Graphics2D trinGraphics = (Graphics2D) g.create();
        structures.Triangle trinFigure = new structures.Triangle("trin");
        double varExpr39 = 2;
        trinFigure.setThickness(varExpr39);

        trinFigure.setDisplay(true);
        double varExpr40 = 250;

        double varExpr41 = 124;

        double varExpr42 = 98;
        Color varExpr43 = new Color(varExpr40,varExpr41,varExpr42);
        trinFigure.setColor(varExpr43);
        Color varExpr44 = new Color("ffffff");
        trinFigure.setColor(varExpr44);

        Polygon trin =  new Polygon();
        trin.addPoint((int)triangles.get("trin").a.x(),(int)triangles.get("trin").a.y());
        trin.addPoint((int)triangles.get("trin").b.x(),(int)triangles.get("trin").b.y());
        trin.addPoint((int)triangles.get("trin").c.x(),(int)triangles.get("trin").c.y());
        java.awt.Rectangle trinBounds = trin.getBounds(); 
        trinGraphics.setColor(new Color(trinFigure.color().rgb().toString()));
        trinGraphics.setStroke(new BasicStroke(trinFigure.thickness()));
        trinGraphics.translate(trin.getCenterX(),trin.getCenterY());
        trinGraphics.rotate(angles.get("trin").degree());
        trinGraphics.translate(-trin.getCenterX(),-trin.getCenterY());

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

    Map<String,Pair<Double, Double>> positions = Map.ofEntires(entry("rec", new Pair(varExpr22, varExpr23)),entry("circ", new Pair(varExpr31, varExpr32)));
}
