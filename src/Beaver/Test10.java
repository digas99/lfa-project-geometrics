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
public class Test10 extends JPanel implements ActionListener,KeyListener{
    static final int Width_Board = 1000;
    static final int Height_Board = 1000;
    List<Figure> figures = new ArrayList<>();
    double varExpr70 = 30;
    double varExpr69 = varExpr70;
    private Timer timer = new Timer((int) varExpr69, this);
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
    double recSpeed;
    structures.Circle circTopRightFigure;
    structures.Rectangle recLeftFigure;
    structures.Rectangle recRightFigure;
    structures.Circle circBottomLeftFigure;
    double trigger;
    structures.Circle circBottomFigure;
    double trigger2;
    double leftAndRightCollided;



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

        double varExpr67 = 0;
         if (firstPaint) leftAndRightCollided = varExpr67;

        double varExpr68 = 5;
         if (firstPaint) recSpeed = varExpr68;

        double varExpr72 = leftAndRightCollided;
        double varExpr71 = varExpr72;

        double varExpr74 = 0;
        double varExpr73 = varExpr74;
        boolean varBoolExpr1 = varExpr71==varExpr73;
        if(varBoolExpr1){
            double varExpr76 = recLeftFigure.center().x();

            double varExpr77 = recSpeed;
            double varExpr75 = varExpr76+varExpr77;

            double varExpr78 = 0;
            structures.Point varPointExpr7 = new structures.Point(varExpr75,varExpr78);
            positions.put("recLeft", new Pair<Double, Double>(varExpr75,varExpr78));
            recLeftFigure.setCenter(varPointExpr7);double varExpr80 = recRightFigure.center().x();

            double varExpr81 = recSpeed;
            double varExpr79 = varExpr80-varExpr81;

            double varExpr82 = 0;
            structures.Point varPointExpr8 = new structures.Point(varExpr79,varExpr82);
            positions.put("recRight", new Pair<Double, Double>(varExpr79,varExpr82));
            recRightFigure.setCenter(varPointExpr8);
        }

        boolean varBoolExpr2 = recLeftBounds.intersects(recRightBounds);
        if(varBoolExpr2){
            double varExpr83 = 1;
            leftAndRightCollided = varExpr83;
        }

        double varExpr85 = leftAndRightCollided;
        double varExpr84 = varExpr85;

        double varExpr87 = 1;
        double varExpr86 = varExpr87;
        boolean varBoolExpr3 = varExpr84==varExpr86;
        if(varBoolExpr3){
            double varExpr89 = recLeftFigure.center().x();

            double varExpr90 = recSpeed;
            double varExpr88 = varExpr89-varExpr90;

            double varExpr91 = 0;
            structures.Point varPointExpr9 = new structures.Point(varExpr88,varExpr91);
            positions.put("recLeft", new Pair<Double, Double>(varExpr88,varExpr91));
            recLeftFigure.setCenter(varPointExpr9);double varExpr93 = recRightFigure.center().x();

            double varExpr94 = recSpeed;
            double varExpr92 = varExpr93+varExpr94;

            double varExpr95 = 0;
            structures.Point varPointExpr10 = new structures.Point(varExpr92,varExpr95);
            positions.put("recRight", new Pair<Double, Double>(varExpr92,varExpr95));
            recRightFigure.setCenter(varPointExpr10);double varExpr96 = 216;

            double varExpr97 = 22;

            double varExpr98 = 22;
            structures.Color varExpr99 = new structures.Color(new RGB((int)varExpr96,(int)varExpr97,(int)varExpr98));
            recLeftFigure.setColor(varExpr99);double varExpr100 = 216;

            double varExpr101 = 22;

            double varExpr102 = 22;
            structures.Color varExpr103 = new structures.Color(new RGB((int)varExpr100,(int)varExpr101,(int)varExpr102));
            recRightFigure.setColor(varExpr103);
        }

        boolean varBoolExpr4 = boardLeftBounds.intersects(recLeftBounds);
        if(varBoolExpr4){
            double varExpr104 = 0;

            double varExpr105 = 0;
            structures.Point varPointExpr11 = new structures.Point(varExpr104,varExpr105);
            if(firstPaint) positions.put("recLeft", new Pair<Double, Double>(varExpr104,varExpr105));
            recLeftFigure.setCenter(varPointExpr11);double varExpr106 = 0;

            double varExpr107 = 0;
            structures.Point varPointExpr12 = new structures.Point(varExpr106,varExpr107);
            if(firstPaint) positions.put("recRight", new Pair<Double, Double>(varExpr106,varExpr107));
            recRightFigure.setCenter(varPointExpr12);double varExpr108 = 0;

            double varExpr109 = 0;

            double varExpr110 = 0;
            structures.Color varExpr111 = new structures.Color(new RGB((int)varExpr108,(int)varExpr109,(int)varExpr110));
            recLeftFigure.setColor(varExpr111);double varExpr112 = 0;

            double varExpr113 = 0;

            double varExpr114 = 0;
            structures.Color varExpr115 = new structures.Color(new RGB((int)varExpr112,(int)varExpr113,(int)varExpr114));
            recRightFigure.setColor(varExpr115);double varExpr116 = 45;
            Angle varExpr117 = new Angle((int) varExpr116);
            angles.put("recLeft", varExpr117);
            recLeftFigure.setAngle(varExpr117);double varExpr119 = 45;
            double varExpr118 = -varExpr119;
            Angle varExpr120 = new Angle((int) varExpr118);
            angles.put("recRight", varExpr120);
            recRightFigure.setAngle(varExpr120);recLeftFigure.setFilled(true);recRightFigure.setFilled(true);
            circTopRightGraphics.draw(circTopRight);
            circBottomLeftGraphics.draw(circBottomLeft);double varExpr121 = 1;
            trigger = varExpr121;
        }

        double varExpr123 = trigger;
        double varExpr122 = varExpr123;

        double varExpr125 = 1;
        double varExpr124 = varExpr125;
        boolean varBoolExpr5 = varExpr122==varExpr124;
        if(varBoolExpr5){

            circTopGraphics.draw(circTop);
            circBottomGraphics.draw(circBottom);double varExpr126 = 0;

            double varExpr128 = circTopFigure.center().y();

            double varExpr129 = 2;
            double varExpr127 = varExpr128-varExpr129;
            structures.Point varPointExpr13 = new structures.Point(varExpr126,varExpr127);
            positions.put("circTop", new Pair<Double, Double>(varExpr126,varExpr127));
            circTopFigure.setCenter(varPointExpr13);double varExpr130 = 0;

            double varExpr132 = circBottomFigure.center().y();

            double varExpr133 = 2;
            double varExpr131 = varExpr132+varExpr133;
            structures.Point varPointExpr14 = new structures.Point(varExpr130,varExpr131);
            positions.put("circBottom", new Pair<Double, Double>(varExpr130,varExpr131));
            circBottomFigure.setCenter(varPointExpr14);boolean varBoolExpr6 = circTopBounds.intersects(circBottomBounds);
            if(varBoolExpr6){
                double varExpr134 = 0;

                double varExpr136 = circTopFigure.center().y();

                double varExpr137 = 2;
                double varExpr135 = varExpr136+varExpr137;
                structures.Point varPointExpr15 = new structures.Point(varExpr134,varExpr135);
                positions.put("circTop", new Pair<Double, Double>(varExpr134,varExpr135));
                circTopFigure.setCenter(varPointExpr15);double varExpr138 = 0;

                double varExpr140 = circBottomFigure.center().y();

                double varExpr141 = 2;
                double varExpr139 = varExpr140-varExpr141;
                structures.Point varPointExpr16 = new structures.Point(varExpr138,varExpr139);
                positions.put("circBottom", new Pair<Double, Double>(varExpr138,varExpr139));
                circBottomFigure.setCenter(varPointExpr16);
            }
        }

        boolean varBoolExpr7 = boardTopBounds.intersects(circTopBounds);
        if(varBoolExpr7){
            double varExpr142 = 0;

            double varExpr144 = circTopFigure.center().y();

            double varExpr145 = 2;
            double varExpr143 = varExpr144-varExpr145;
            structures.Point varPointExpr17 = new structures.Point(varExpr142,varExpr143);
            positions.put("circTop", new Pair<Double, Double>(varExpr142,varExpr143));
            circTopFigure.setCenter(varPointExpr17);circTopFigure.setFilled(true);double varExpr146 = 1;
            trigger2 = varExpr146;
        }

        boolean varBoolExpr8 = boardBottomBounds.intersects(circBottomBounds);
        if(varBoolExpr8){
            double varExpr147 = 0;

            double varExpr149 = circBottomFigure.center().y();

            double varExpr150 = 2;
            double varExpr148 = varExpr149+varExpr150;
            structures.Point varPointExpr18 = new structures.Point(varExpr147,varExpr148);
            positions.put("circBottom", new Pair<Double, Double>(varExpr147,varExpr148));
            circBottomFigure.setCenter(varPointExpr18);circTopFigure.setFilled(true);double varExpr151 = 1;
            trigger2 = varExpr151;
        }

        double varExpr153 = trigger2;
        double varExpr152 = varExpr153;

        double varExpr155 = 1;
        double varExpr154 = varExpr155;
        boolean varBoolExpr9 = varExpr152==varExpr154;
        if(varBoolExpr9){
            double varExpr157 = circTopRightFigure.center().x();

            double varExpr158 = 1;
            double varExpr156 = varExpr157-varExpr158;

            double varExpr160 = circTopRightFigure.center().y();

            double varExpr161 = 1;
            double varExpr159 = varExpr160-varExpr161;
            structures.Point varPointExpr19 = new structures.Point(varExpr156,varExpr159);
            positions.put("circTopRight", new Pair<Double, Double>(varExpr156,varExpr159));
            circTopRightFigure.setCenter(varPointExpr19);double varExpr163 = circBottomFigure.center().x();

            double varExpr164 = 1;
            double varExpr162 = varExpr163+varExpr164;

            double varExpr166 = circBottomFigure.center().y();

            double varExpr167 = 1;
            double varExpr165 = varExpr166+varExpr167;
            structures.Point varPointExpr20 = new structures.Point(varExpr162,varExpr165);
            positions.put("circBottom", new Pair<Double, Double>(varExpr162,varExpr165));
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
        Test10 n = new Test10();
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
