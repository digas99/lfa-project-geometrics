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
public class TestEx1 extends JPanel implements ActionListener,KeyListener{
    static final int Width_Board = 1000;
    static final int Height_Board = 1000;
    List<Figure> figures = new ArrayList<>();
    double varExpr68 = 30;
    double varExpr67 = varExpr68;
    private Timer timer = new Timer((int) varExpr67, this);
    Map<String,Pair<Double, Double>> positions = new HashMap<>();
    Map<String,structures.Angle> angles = new HashMap<>();
    boolean firstPaint = true;
    Shape boardTopBounds = new Line2D.Double(-500,-500,500,-500);
    Shape boardRightBounds = new Line2D.Double(500,-500,500,500);
    Shape boardBottomBounds = new Line2D.Double(500,500,-500,500);
    Shape boardLeftBounds = new Line2D.Double(-500,500,-500,-500);
    structures.Circle circTopFigure;
    double a;
    double b;
    structures.Circle circTopRightFigure;
    structures.Rectangle recLeftFigure;
    structures.Rectangle recRightFigure;
    structures.Circle circBottomLeftFigure;
    double trigger;
    structures.Circle circBottomFigure;
    double trigger2;



    public void paint(Graphics g){
        super.paintComponent(g);

        ArrayList<java.awt.Rectangle> rectBoundsList = new ArrayList<>();

        g.drawString("P - play animation",10,10);
        g.drawString("S - stop animation",8,25);

        double varExpr1 = 10;
         if (firstPaint) a = varExpr1;

        double varExpr3 = 5;

        double varExpr5 = a;

        double varExpr6 = 2;
        double varExpr4 = varExpr5/varExpr6;
        double varExpr2 = varExpr3+varExpr4;
         if (firstPaint) b = varExpr2;
        Graphics2D recRightGraphics = (Graphics2D) g.create();
        if (firstPaint) {
            recRightFigure = new structures.Rectangle("recRight");
            double varExpr7 = 100;
            recRightFigure.setWidth(varExpr7);
            double varExpr8 = 100;
            recRightFigure.setHeight(varExpr8);
            double varExpr9 = 5;
            recRightFigure.setThickness(varExpr9);
            double varExpr10 = 0;
            Angle varExpr11 = new Angle((int) varExpr10);
            if(firstPaint)angles.put("recRight", varExpr11);
            recRightFigure.setAngle(varExpr11);

            recRightFigure.setFilled(false);
            double varExpr12 = 21;

            double varExpr13 = 179;

            double varExpr14 = 91;
            structures.Color varExpr15 = new structures.Color(new RGB((int)varExpr12,(int)varExpr13,(int)varExpr14));
            recRightFigure.setColor(varExpr15);
            double varExpr16 = 400;

            double varExpr17 = 300;
            structures.Point varPointExpr1 = new structures.Point(varExpr16,varExpr17);
            if(firstPaint) positions.put("recRight", new Pair<Double, Double>(varExpr16,varExpr17));
            recRightFigure.setCenter(varPointExpr1);
        }

        recRightGraphics.translate(getWidth()/2,getHeight()/2);
        Rectangle2D recRight = new Rectangle2D.Double(positions.get("recRight").a - recRightFigure.width()/2,positions.get("recRight").b - recRightFigure.height()/2,
                                                   recRightFigure.width(),recRightFigure.height());
        java.awt.Rectangle recRightBounds = recRight.getBounds();
        rectBoundsList.add(recRightBounds);

        recRightGraphics.setColor(new java.awt.Color(recRightFigure.color().rgb().p0(), recRightFigure.color().rgb().p1(), recRightFigure.color().rgb().p2()));
        recRightGraphics.setStroke(new BasicStroke((int)recRightFigure.thickness()));
        recRightGraphics.translate(recRight.getCenterX(),recRight.getCenterY());
        recRightGraphics.rotate(angles.get("recRight").rad());
        recRightGraphics.translate(-recRight.getCenterX(),-recRight.getCenterY());

        Graphics2D recLeftGraphics = (Graphics2D) g.create();
        if (firstPaint) {
            recLeftFigure = new structures.Rectangle("recLeft");
            double varExpr18 = 100;
            recLeftFigure.setWidth(varExpr18);
            double varExpr19 = 100;
            recLeftFigure.setHeight(varExpr19);
            double varExpr20 = 5;
            recLeftFigure.setThickness(varExpr20);
            double varExpr21 = 0;
            Angle varExpr22 = new Angle((int) varExpr21);
            if(firstPaint)angles.put("recLeft", varExpr22);
            recLeftFigure.setAngle(varExpr22);

            recLeftFigure.setFilled(false);
            double varExpr23 = 0;

            double varExpr24 = 0;

            double varExpr25 = 0;
            structures.Color varExpr26 = new structures.Color(new RGB((int)varExpr23,(int)varExpr24,(int)varExpr25));
            recLeftFigure.setColor(varExpr26);
            double varExpr28 = 400;
            double varExpr27 = -varExpr28;

            double varExpr29 = 300;
            structures.Point varPointExpr2 = new structures.Point(varExpr27,varExpr29);
            if(firstPaint) positions.put("recLeft", new Pair<Double, Double>(varExpr27,varExpr29));
            recLeftFigure.setCenter(varPointExpr2);
        }

        recLeftGraphics.translate(getWidth()/2,getHeight()/2);
        Rectangle2D recLeft = new Rectangle2D.Double(positions.get("recLeft").a - recLeftFigure.width()/2,positions.get("recLeft").b - recLeftFigure.height()/2,
                                                   recLeftFigure.width(),recLeftFigure.height());
        java.awt.Rectangle recLeftBounds = recLeft.getBounds();
        rectBoundsList.add(recLeftBounds);

        recLeftGraphics.setColor(new java.awt.Color(recLeftFigure.color().rgb().p0(), recLeftFigure.color().rgb().p1(), recLeftFigure.color().rgb().p2()));
        recLeftGraphics.setStroke(new BasicStroke((int)recLeftFigure.thickness()));
        recLeftGraphics.translate(recLeft.getCenterX(),recLeft.getCenterY());
        recLeftGraphics.rotate(angles.get("recLeft").rad());
        recLeftGraphics.translate(-recLeft.getCenterX(),-recLeft.getCenterY());

        Graphics2D circTopGraphics = (Graphics2D) g.create();
        if (firstPaint) {
            circTopFigure = new structures.Circle("circTop");
            double varExpr30 = 50;
            circTopFigure.setDiameter(varExpr30);
            double varExpr31 = 5;
            circTopFigure.setThickness(varExpr31);

            circTopFigure.setFilled(false);
            double varExpr32 = 209;

            double varExpr33 = 19;

            double varExpr34 = 85;
            structures.Color varExpr35 = new structures.Color(new RGB((int)varExpr32,(int)varExpr33,(int)varExpr34));
            circTopFigure.setColor(varExpr35);
            double varExpr36 = 0;

            double varExpr37 = 200;
            structures.Point varPointExpr3 = new structures.Point(varExpr36,varExpr37);
            if(firstPaint) positions.put("circTop", new Pair<Double, Double>(varExpr36,varExpr37));
            circTopFigure.setCenter(varPointExpr3);
        }

        circTopGraphics.translate(getWidth()/2,getHeight()/2);
        Shape circTop = new Ellipse2D.Double(positions.get("circTop").a - circTopFigure.diameter()/2,positions.get("circTop").b - circTopFigure.diameter()/2,
                                           circTopFigure.diameter(),circTopFigure.diameter());
        java.awt.Rectangle circTopBounds = circTop.getBounds();
        rectBoundsList.add(circTopBounds);

        circTopGraphics.setColor(new java.awt.Color(circTopFigure.color().rgb().p0(), circTopFigure.color().rgb().p1(), circTopFigure.color().rgb().p2()));
        circTopGraphics.setStroke(new BasicStroke((int)circTopFigure.thickness()));
        circTopGraphics.translate(circTopBounds.getCenterX(),circTopBounds.getCenterY());
        circTopGraphics.translate(-circTopBounds.getCenterX(),-circTopBounds.getCenterY());

        Graphics2D circBottomGraphics = (Graphics2D) g.create();
        if (firstPaint) {
            circBottomFigure = new structures.Circle("circBottom");
            double varExpr38 = 50;
            circBottomFigure.setDiameter(varExpr38);
            double varExpr39 = 5;
            circBottomFigure.setThickness(varExpr39);

            circBottomFigure.setFilled(false);
            double varExpr40 = 0;

            double varExpr41 = 70;

            double varExpr42 = 235;
            structures.Color varExpr43 = new structures.Color(new RGB((int)varExpr40,(int)varExpr41,(int)varExpr42));
            circBottomFigure.setColor(varExpr43);
            double varExpr44 = 0;

            double varExpr46 = 200;
            double varExpr45 = -varExpr46;
            structures.Point varPointExpr4 = new structures.Point(varExpr44,varExpr45);
            if(firstPaint) positions.put("circBottom", new Pair<Double, Double>(varExpr44,varExpr45));
            circBottomFigure.setCenter(varPointExpr4);
        }

        circBottomGraphics.translate(getWidth()/2,getHeight()/2);
        Shape circBottom = new Ellipse2D.Double(positions.get("circBottom").a - circBottomFigure.diameter()/2,positions.get("circBottom").b - circBottomFigure.diameter()/2,
                                           circBottomFigure.diameter(),circBottomFigure.diameter());
        java.awt.Rectangle circBottomBounds = circBottom.getBounds();
        rectBoundsList.add(circBottomBounds);

        circBottomGraphics.setColor(new java.awt.Color(circBottomFigure.color().rgb().p0(), circBottomFigure.color().rgb().p1(), circBottomFigure.color().rgb().p2()));
        circBottomGraphics.setStroke(new BasicStroke((int)circBottomFigure.thickness()));
        circBottomGraphics.translate(circBottomBounds.getCenterX(),circBottomBounds.getCenterY());
        circBottomGraphics.translate(-circBottomBounds.getCenterX(),-circBottomBounds.getCenterY());

        Graphics2D circTopRightGraphics = (Graphics2D) g.create();
        if (firstPaint) {
            circTopRightFigure = new structures.Circle("circTopRight");
            double varExpr47 = 100;
            circTopRightFigure.setDiameter(varExpr47);
            double varExpr48 = 10;
            circTopRightFigure.setThickness(varExpr48);

            circTopRightFigure.setFilled(false);
            double varExpr49 = 113;

            double varExpr50 = 125;

            double varExpr51 = 155;
            structures.Color varExpr52 = new structures.Color(new RGB((int)varExpr49,(int)varExpr50,(int)varExpr51));
            circTopRightFigure.setColor(varExpr52);
            double varExpr53 = 450;

            double varExpr54 = 450;
            structures.Point varPointExpr5 = new structures.Point(varExpr53,varExpr54);
            if(firstPaint) positions.put("circTopRight", new Pair<Double, Double>(varExpr53,varExpr54));
            circTopRightFigure.setCenter(varPointExpr5);
        }

        circTopRightGraphics.translate(getWidth()/2,getHeight()/2);
        Shape circTopRight = new Ellipse2D.Double(positions.get("circTopRight").a - circTopRightFigure.diameter()/2,positions.get("circTopRight").b - circTopRightFigure.diameter()/2,
                                           circTopRightFigure.diameter(),circTopRightFigure.diameter());
        java.awt.Rectangle circTopRightBounds = circTopRight.getBounds();
        rectBoundsList.add(circTopRightBounds);

        circTopRightGraphics.setColor(new java.awt.Color(circTopRightFigure.color().rgb().p0(), circTopRightFigure.color().rgb().p1(), circTopRightFigure.color().rgb().p2()));
        circTopRightGraphics.setStroke(new BasicStroke((int)circTopRightFigure.thickness()));
        circTopRightGraphics.translate(circTopRightBounds.getCenterX(),circTopRightBounds.getCenterY());
        circTopRightGraphics.translate(-circTopRightBounds.getCenterX(),-circTopRightBounds.getCenterY());

        Graphics2D circBottomLeftGraphics = (Graphics2D) g.create();
        if (firstPaint) {
            circBottomLeftFigure = new structures.Circle("circBottomLeft");
            double varExpr55 = 100;
            circBottomLeftFigure.setDiameter(varExpr55);
            double varExpr56 = 10;
            circBottomLeftFigure.setThickness(varExpr56);

            circBottomLeftFigure.setFilled(false);
            double varExpr57 = 113;

            double varExpr58 = 125;

            double varExpr59 = 155;
            structures.Color varExpr60 = new structures.Color(new RGB((int)varExpr57,(int)varExpr58,(int)varExpr59));
            circBottomLeftFigure.setColor(varExpr60);
            double varExpr62 = 450;
            double varExpr61 = -varExpr62;

            double varExpr64 = 450;
            double varExpr63 = -varExpr64;
            structures.Point varPointExpr6 = new structures.Point(varExpr61,varExpr63);
            if(firstPaint) positions.put("circBottomLeft", new Pair<Double, Double>(varExpr61,varExpr63));
            circBottomLeftFigure.setCenter(varPointExpr6);
        }

        circBottomLeftGraphics.translate(getWidth()/2,getHeight()/2);
        Shape circBottomLeft = new Ellipse2D.Double(positions.get("circBottomLeft").a - circBottomLeftFigure.diameter()/2,positions.get("circBottomLeft").b - circBottomLeftFigure.diameter()/2,
                                           circBottomLeftFigure.diameter(),circBottomLeftFigure.diameter());
        java.awt.Rectangle circBottomLeftBounds = circBottomLeft.getBounds();
        rectBoundsList.add(circBottomLeftBounds);

        circBottomLeftGraphics.setColor(new java.awt.Color(circBottomLeftFigure.color().rgb().p0(), circBottomLeftFigure.color().rgb().p1(), circBottomLeftFigure.color().rgb().p2()));
        circBottomLeftGraphics.setStroke(new BasicStroke((int)circBottomLeftFigure.thickness()));
        circBottomLeftGraphics.translate(circBottomLeftBounds.getCenterX(),circBottomLeftBounds.getCenterY());
        circBottomLeftGraphics.translate(-circBottomLeftBounds.getCenterX(),-circBottomLeftBounds.getCenterY());


        recLeftGraphics.draw(recLeft);

        recRightGraphics.draw(recRight);

        double varExpr65 = 0;
         if (firstPaint) trigger = varExpr65;

        double varExpr66 = 0;
         if (firstPaint) trigger2 = varExpr66;
        double varExpr70 = recLeftFigure.center().x();

        double varExpr71 = 1;
        double varExpr69 = varExpr70+varExpr71;

        double varExpr72 = 0;
        structures.Point varPointExpr7 = new structures.Point(varExpr69,varExpr72);
        positions.put("recLeft", new Pair<Double, Double>(varExpr69,varExpr72));
        recLeftFigure.setCenter(varPointExpr7);
        double varExpr74 = recRightFigure.center().x();

        double varExpr75 = 1;
        double varExpr73 = varExpr74-varExpr75;

        double varExpr76 = 0;
        structures.Point varPointExpr8 = new structures.Point(varExpr73,varExpr76);
        positions.put("recRight", new Pair<Double, Double>(varExpr73,varExpr76));
        recRightFigure.setCenter(varPointExpr8);

        boolean varBoolExpr1 = recLeftBounds.intersects(recRightBounds);
        if(varBoolExpr1){
            double varExpr78 = recLeftFigure.center().x();

            double varExpr79 = 2;
            double varExpr77 = varExpr78-varExpr79;

            double varExpr80 = 0;
            structures.Point varPointExpr9 = new structures.Point(varExpr77,varExpr80);
            positions.put("recLeft", new Pair<Double, Double>(varExpr77,varExpr80));
            recLeftFigure.setCenter(varPointExpr9);double varExpr82 = recRightFigure.center().x();

            double varExpr83 = 2;
            double varExpr81 = varExpr82+varExpr83;

            double varExpr84 = 0;
            structures.Point varPointExpr10 = new structures.Point(varExpr81,varExpr84);
            positions.put("recRight", new Pair<Double, Double>(varExpr81,varExpr84));
            recRightFigure.setCenter(varPointExpr10);double varExpr85 = 216;

            double varExpr86 = 22;

            double varExpr87 = 22;
            structures.Color varExpr88 = new structures.Color(new RGB((int)varExpr85,(int)varExpr86,(int)varExpr87));
            recLeftFigure.setColor(varExpr88);double varExpr89 = 216;

            double varExpr90 = 22;

            double varExpr91 = 22;
            structures.Color varExpr92 = new structures.Color(new RGB((int)varExpr89,(int)varExpr90,(int)varExpr91));
            recRightFigure.setColor(varExpr92);
        }

        boolean varBoolExpr2 = boardLeftBounds.intersects(recLeftBounds);
        if(varBoolExpr2){
            double varExpr93 = 0;

            double varExpr94 = 0;
            structures.Point varPointExpr11 = new structures.Point(varExpr93,varExpr94);
            if(firstPaint) positions.put("recLeft", new Pair<Double, Double>(varExpr93,varExpr94));
            recLeftFigure.setCenter(varPointExpr11);double varExpr95 = 0;

            double varExpr96 = 0;
            structures.Point varPointExpr12 = new structures.Point(varExpr95,varExpr96);
            if(firstPaint) positions.put("recRight", new Pair<Double, Double>(varExpr95,varExpr96));
            recRightFigure.setCenter(varPointExpr12);double varExpr97 = 0;

            double varExpr98 = 0;

            double varExpr99 = 0;
            structures.Color varExpr100 = new structures.Color(new RGB((int)varExpr97,(int)varExpr98,(int)varExpr99));
            recLeftFigure.setColor(varExpr100);double varExpr101 = 0;

            double varExpr102 = 0;

            double varExpr103 = 0;
            structures.Color varExpr104 = new structures.Color(new RGB((int)varExpr101,(int)varExpr102,(int)varExpr103));
            recRightFigure.setColor(varExpr104);recLeftFigure.setFilled(true);recLeftFigure.setFilled(true);
            circTopRightGraphics.draw(circTopRight);
            circBottomLeftGraphics.draw(circBottomLeft);double varExpr105 = 1;
            trigger = varExpr105;
        }

        double varExpr107 = trigger;
        double varExpr106 = varExpr107;

        double varExpr109 = 1;
        double varExpr108 = varExpr109;
        boolean varBoolExpr3 = varExpr106==varExpr108;
        if(varBoolExpr3){

            circTopGraphics.draw(circTop);
            circBottomGraphics.draw(circBottom);double varExpr110 = 0;

            double varExpr112 = circTopFigure.center().y();

            double varExpr113 = 2;
            double varExpr111 = varExpr112-varExpr113;
            structures.Point varPointExpr13 = new structures.Point(varExpr110,varExpr111);
            positions.put("circTop", new Pair<Double, Double>(varExpr110,varExpr111));
            circTopFigure.setCenter(varPointExpr13);double varExpr114 = 0;

            double varExpr116 = circBottomFigure.center().y();

            double varExpr117 = 2;
            double varExpr115 = varExpr116+varExpr117;
            structures.Point varPointExpr14 = new structures.Point(varExpr114,varExpr115);
            positions.put("circBottom", new Pair<Double, Double>(varExpr114,varExpr115));
            circBottomFigure.setCenter(varPointExpr14);boolean varBoolExpr4 = circTopBounds.intersects(circBottomBounds);
            if(varBoolExpr4){
                double varExpr118 = 0;

                double varExpr120 = circTopFigure.center().y();

                double varExpr121 = 2;
                double varExpr119 = varExpr120+varExpr121;
                structures.Point varPointExpr15 = new structures.Point(varExpr118,varExpr119);
                positions.put("circTop", new Pair<Double, Double>(varExpr118,varExpr119));
                circTopFigure.setCenter(varPointExpr15);double varExpr122 = 0;

                double varExpr124 = circBottomFigure.center().y();

                double varExpr125 = 2;
                double varExpr123 = varExpr124-varExpr125;
                structures.Point varPointExpr16 = new structures.Point(varExpr122,varExpr123);
                positions.put("circBottom", new Pair<Double, Double>(varExpr122,varExpr123));
                circBottomFigure.setCenter(varPointExpr16);
            }
        }

        boolean varBoolExpr5 = boardTopBounds.intersects(circTopBounds);
        if(varBoolExpr5){
            double varExpr126 = 0;

            double varExpr128 = circTopFigure.center().y();

            double varExpr129 = 2;
            double varExpr127 = varExpr128-varExpr129;
            structures.Point varPointExpr17 = new structures.Point(varExpr126,varExpr127);
            positions.put("circTop", new Pair<Double, Double>(varExpr126,varExpr127));
            circTopFigure.setCenter(varPointExpr17);circTopFigure.setFilled(true);double varExpr130 = 1;
            trigger2 = varExpr130;
        }

        boolean varBoolExpr6 = boardBottomBounds.intersects(circBottomBounds);
        if(varBoolExpr6){
            double varExpr131 = 0;

            double varExpr133 = circBottomFigure.center().y();

            double varExpr134 = 2;
            double varExpr132 = varExpr133+varExpr134;
            structures.Point varPointExpr18 = new structures.Point(varExpr131,varExpr132);
            positions.put("circBottom", new Pair<Double, Double>(varExpr131,varExpr132));
            circBottomFigure.setCenter(varPointExpr18);circTopFigure.setFilled(true);double varExpr135 = 1;
            trigger2 = varExpr135;
        }

        double varExpr137 = trigger2;
        double varExpr136 = varExpr137;

        double varExpr139 = 1;
        double varExpr138 = varExpr139;
        boolean varBoolExpr7 = varExpr136==varExpr138;
        if(varBoolExpr7){
            double varExpr141 = circTopRightFigure.center().x();

            double varExpr142 = 1;
            double varExpr140 = varExpr141-varExpr142;

            double varExpr144 = circTopRightFigure.center().y();

            double varExpr145 = 1;
            double varExpr143 = varExpr144-varExpr145;
            structures.Point varPointExpr19 = new structures.Point(varExpr140,varExpr143);
            positions.put("circTopRight", new Pair<Double, Double>(varExpr140,varExpr143));
            circTopRightFigure.setCenter(varPointExpr19);double varExpr147 = circBottomFigure.center().x();

            double varExpr148 = 1;
            double varExpr146 = varExpr147+varExpr148;

            double varExpr150 = circBottomFigure.center().y();

            double varExpr151 = 1;
            double varExpr149 = varExpr150+varExpr151;
            structures.Point varPointExpr20 = new structures.Point(varExpr146,varExpr149);
            positions.put("circBottom", new Pair<Double, Double>(varExpr146,varExpr149));
            circBottomFigure.setCenter(varPointExpr20);
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
        TestEx1 n = new TestEx1();
        JFrame frame1 = new JFrame();
        frame1.setTitle("Geometrics");
        frame1.setSize(new Dimension(Width_Board,Height_Board));
        frame1.addKeyListener(n);
        frame1.add(n);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    } 

}
