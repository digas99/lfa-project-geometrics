import java.util.List;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import org.antlr.v4.runtime.misc.Pair;
import java.lang.Math;
import java.util.Map;
import java.util.HashMap;


import structures.*;
public class Test1 extends JPanel implements ActionListener,KeyListener{
    static final int Width_Board = 1000;
    static final int Height_Board = 1000;
    List<Figure> figures = new ArrayList<>();
    double varExpr88 = 30;
    double varExpr87 = varExpr88;
    private Timer timer = new Timer((int) varExpr87, this);
    Map<String,Pair<Double, Double>> positions = new HashMap<>();
    Map<String,structures.Angle> angles = new HashMap<>();
    boolean firstPaint = true;
    Shape boardTopBounds = new Line2D.Double(-500,-500,500,-500);
    Shape boardRightBounds = new Line2D.Double(500,-500,500,500);
    Shape boardBottomBounds = new Line2D.Double(500,500,-500,500);
    Shape boardLeftBounds = new Line2D.Double(-500,500,-500,-500);
    structures.Circle circ8Figure;
    structures.Circle circ7Figure;
    double angleValue;
    structures.Circle circ3Figure;
    double switches;
    structures.Circle circFigure;
    double velocity;
    structures.Circle circ4Figure;
    structures.Rectangle recFigure;
    structures.Circle circ5Figure;
    double hitTop;
    structures.Circle circ6Figure;
    structures.Circle circ2Figure;



    public void paint(Graphics g){
        super.paintComponent(g);

        ArrayList<java.awt.Rectangle> rectBoundsList = new ArrayList<>();

        g.drawString("P - play animation",10,10);
        g.drawString("S - stop animation",8,25);

        Graphics2D recGraphics = (Graphics2D) g.create();
        if (firstPaint) {
            recFigure = new structures.Rectangle("rec");
            double varExpr1 = 75;
            recFigure.setWidth(varExpr1);
            double varExpr2 = 75;
            recFigure.setHeight(varExpr2);
            double varExpr3 = 2;
            recFigure.setThickness(varExpr3);
            double varExpr4 = 15;
            Angle varExpr5 = new Angle((int) varExpr4);
            if(firstPaint)angles.put("rec", varExpr5);
            recFigure.setAngle(varExpr5);

            recFigure.setDisplay(true);

            recFigure.setFilled(true);
            double varExpr6 = 2;
            recFigure.setDepth(varExpr6);
            double varExpr7 = 21;

            double varExpr8 = 179;

            double varExpr9 = 91;
            structures.Color varExpr10 = new structures.Color(new RGB((int)varExpr7,(int)varExpr8,(int)varExpr9));
            recFigure.setColor(varExpr10);
            double varExpr11 = 0;

            double varExpr12 = 10;
            structures.Point varPointExpr1 = new structures.Point(varExpr11,varExpr12);
            if(firstPaint) positions.put("rec", new Pair<Double, Double>(varExpr11,varExpr12));
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
        recGraphics.rotate(angles.get("rec").rad());
        recGraphics.translate(-rec.getCenterX(),-rec.getCenterY());

        Graphics2D circGraphics = (Graphics2D) g.create();
        if (firstPaint) {
            circFigure = new structures.Circle("circ");
            double varExpr13 = 75;
            circFigure.setDiameter(varExpr13);
            double varExpr14 = 10;
            circFigure.setThickness(varExpr14);

            circFigure.setDisplay(true);

            circFigure.setFilled(true);
            double varExpr15 = 2;
            circFigure.setDepth(varExpr15);
            double varExpr16 = 0;

            double varExpr17 = 0;

            double varExpr18 = 0;
            structures.Color varExpr19 = new structures.Color(new RGB((int)varExpr16,(int)varExpr17,(int)varExpr18));
            circFigure.setColor(varExpr19);
            double varExpr20 = 0;

            double varExpr21 = 0;
            structures.Point varPointExpr2 = new structures.Point(varExpr20,varExpr21);
            if(firstPaint) positions.put("circ", new Pair<Double, Double>(varExpr20,varExpr21));
            circFigure.setCenter(varPointExpr2);
        }

        circGraphics.translate(getWidth()/2,getHeight()/2);
        Shape circ = new Ellipse2D.Double(positions.get("circ").a - circFigure.diameter()/2,positions.get("circ").b - circFigure.diameter()/2,
                                           circFigure.diameter(),circFigure.diameter());
        java.awt.Rectangle circBounds = circ.getBounds();
        rectBoundsList.add(circBounds);

        circGraphics.setColor(new java.awt.Color(circFigure.color().rgb().p0(), circFigure.color().rgb().p1(), circFigure.color().rgb().p2()));
        circGraphics.setStroke(new BasicStroke((int)circFigure.thickness()));

        Graphics2D circ2Graphics = (Graphics2D) g.create();
        if (firstPaint) {
            circ2Figure = new structures.Circle("circ2");
            double varExpr22 = 75;
            circ2Figure.setDiameter(varExpr22);
            double varExpr23 = 10;
            circ2Figure.setThickness(varExpr23);

            circ2Figure.setDisplay(true);

            circ2Figure.setFilled(true);
            double varExpr24 = 2;
            circ2Figure.setDepth(varExpr24);
            double varExpr25 = 0;

            double varExpr26 = 0;

            double varExpr27 = 0;
            structures.Color varExpr28 = new structures.Color(new RGB((int)varExpr25,(int)varExpr26,(int)varExpr27));
            circ2Figure.setColor(varExpr28);
            double varExpr30 = 250;
            double varExpr29 = -varExpr30;

            double varExpr31 = 0;
            structures.Point varPointExpr3 = new structures.Point(varExpr29,varExpr31);
            if(firstPaint) positions.put("circ2", new Pair<Double, Double>(varExpr29,varExpr31));
            circ2Figure.setCenter(varPointExpr3);
        }

        circ2Graphics.translate(getWidth()/2,getHeight()/2);
        Shape circ2 = new Ellipse2D.Double(positions.get("circ2").a - circ2Figure.diameter()/2,positions.get("circ2").b - circ2Figure.diameter()/2,
                                           circ2Figure.diameter(),circ2Figure.diameter());
        java.awt.Rectangle circ2Bounds = circ2.getBounds();
        rectBoundsList.add(circ2Bounds);

        circ2Graphics.setColor(new java.awt.Color(circ2Figure.color().rgb().p0(), circ2Figure.color().rgb().p1(), circ2Figure.color().rgb().p2()));
        circ2Graphics.setStroke(new BasicStroke((int)circ2Figure.thickness()));

        Graphics2D circ3Graphics = (Graphics2D) g.create();
        if (firstPaint) {
            circ3Figure = new structures.Circle("circ3");
            double varExpr32 = 75;
            circ3Figure.setDiameter(varExpr32);
            double varExpr33 = 10;
            circ3Figure.setThickness(varExpr33);

            circ3Figure.setDisplay(true);

            circ3Figure.setFilled(true);
            double varExpr34 = 2;
            circ3Figure.setDepth(varExpr34);
            double varExpr35 = 0;

            double varExpr36 = 0;

            double varExpr37 = 0;
            structures.Color varExpr38 = new structures.Color(new RGB((int)varExpr35,(int)varExpr36,(int)varExpr37));
            circ3Figure.setColor(varExpr38);
            double varExpr39 = 250;

            double varExpr40 = 0;
            structures.Point varPointExpr4 = new structures.Point(varExpr39,varExpr40);
            if(firstPaint) positions.put("circ3", new Pair<Double, Double>(varExpr39,varExpr40));
            circ3Figure.setCenter(varPointExpr4);
        }

        circ3Graphics.translate(getWidth()/2,getHeight()/2);
        Shape circ3 = new Ellipse2D.Double(positions.get("circ3").a - circ3Figure.diameter()/2,positions.get("circ3").b - circ3Figure.diameter()/2,
                                           circ3Figure.diameter(),circ3Figure.diameter());
        java.awt.Rectangle circ3Bounds = circ3.getBounds();
        rectBoundsList.add(circ3Bounds);

        circ3Graphics.setColor(new java.awt.Color(circ3Figure.color().rgb().p0(), circ3Figure.color().rgb().p1(), circ3Figure.color().rgb().p2()));
        circ3Graphics.setStroke(new BasicStroke((int)circ3Figure.thickness()));

        Graphics2D circ4Graphics = (Graphics2D) g.create();
        if (firstPaint) {
            circ4Figure = new structures.Circle("circ4");
            double varExpr41 = 75;
            circ4Figure.setDiameter(varExpr41);
            double varExpr42 = 10;
            circ4Figure.setThickness(varExpr42);

            circ4Figure.setDisplay(true);

            circ4Figure.setFilled(true);
            double varExpr43 = 2;
            circ4Figure.setDepth(varExpr43);
            double varExpr44 = 0;

            double varExpr45 = 0;

            double varExpr46 = 0;
            structures.Color varExpr47 = new structures.Color(new RGB((int)varExpr44,(int)varExpr45,(int)varExpr46));
            circ4Figure.setColor(varExpr47);
            double varExpr48 = 0;

            double varExpr49 = 0;
            structures.Point varPointExpr5 = new structures.Point(varExpr48,varExpr49);
            if(firstPaint) positions.put("circ4", new Pair<Double, Double>(varExpr48,varExpr49));
            circ4Figure.setCenter(varPointExpr5);
        }

        circ4Graphics.translate(getWidth()/2,getHeight()/2);
        Shape circ4 = new Ellipse2D.Double(positions.get("circ4").a - circ4Figure.diameter()/2,positions.get("circ4").b - circ4Figure.diameter()/2,
                                           circ4Figure.diameter(),circ4Figure.diameter());
        java.awt.Rectangle circ4Bounds = circ4.getBounds();
        rectBoundsList.add(circ4Bounds);

        circ4Graphics.setColor(new java.awt.Color(circ4Figure.color().rgb().p0(), circ4Figure.color().rgb().p1(), circ4Figure.color().rgb().p2()));
        circ4Graphics.setStroke(new BasicStroke((int)circ4Figure.thickness()));

        Graphics2D circ5Graphics = (Graphics2D) g.create();
        if (firstPaint) {
            circ5Figure = new structures.Circle("circ5");
            double varExpr50 = 75;
            circ5Figure.setDiameter(varExpr50);
            double varExpr51 = 10;
            circ5Figure.setThickness(varExpr51);

            circ5Figure.setDisplay(true);

            circ5Figure.setFilled(true);
            double varExpr52 = 2;
            circ5Figure.setDepth(varExpr52);
            double varExpr53 = 0;

            double varExpr54 = 0;

            double varExpr55 = 0;
            structures.Color varExpr56 = new structures.Color(new RGB((int)varExpr53,(int)varExpr54,(int)varExpr55));
            circ5Figure.setColor(varExpr56);
            double varExpr57 = 0;

            double varExpr59 = 250;
            double varExpr58 = -varExpr59;
            structures.Point varPointExpr6 = new structures.Point(varExpr57,varExpr58);
            if(firstPaint) positions.put("circ5", new Pair<Double, Double>(varExpr57,varExpr58));
            circ5Figure.setCenter(varPointExpr6);
        }

        circ5Graphics.translate(getWidth()/2,getHeight()/2);
        Shape circ5 = new Ellipse2D.Double(positions.get("circ5").a - circ5Figure.diameter()/2,positions.get("circ5").b - circ5Figure.diameter()/2,
                                           circ5Figure.diameter(),circ5Figure.diameter());
        java.awt.Rectangle circ5Bounds = circ5.getBounds();
        rectBoundsList.add(circ5Bounds);

        circ5Graphics.setColor(new java.awt.Color(circ5Figure.color().rgb().p0(), circ5Figure.color().rgb().p1(), circ5Figure.color().rgb().p2()));
        circ5Graphics.setStroke(new BasicStroke((int)circ5Figure.thickness()));

        Graphics2D circ6Graphics = (Graphics2D) g.create();
        if (firstPaint) {
            circ6Figure = new structures.Circle("circ6");
            double varExpr60 = 75;
            circ6Figure.setDiameter(varExpr60);
            double varExpr61 = 10;
            circ6Figure.setThickness(varExpr61);

            circ6Figure.setDisplay(true);

            circ6Figure.setFilled(true);
            double varExpr62 = 2;
            circ6Figure.setDepth(varExpr62);
            double varExpr63 = 0;

            double varExpr64 = 0;

            double varExpr65 = 0;
            structures.Color varExpr66 = new structures.Color(new RGB((int)varExpr63,(int)varExpr64,(int)varExpr65));
            circ6Figure.setColor(varExpr66);
            double varExpr67 = 0;

            double varExpr68 = 250;
            structures.Point varPointExpr7 = new structures.Point(varExpr67,varExpr68);
            if(firstPaint) positions.put("circ6", new Pair<Double, Double>(varExpr67,varExpr68));
            circ6Figure.setCenter(varPointExpr7);
        }

        circ6Graphics.translate(getWidth()/2,getHeight()/2);
        Shape circ6 = new Ellipse2D.Double(positions.get("circ6").a - circ6Figure.diameter()/2,positions.get("circ6").b - circ6Figure.diameter()/2,
                                           circ6Figure.diameter(),circ6Figure.diameter());
        java.awt.Rectangle circ6Bounds = circ6.getBounds();
        rectBoundsList.add(circ6Bounds);

        circ6Graphics.setColor(new java.awt.Color(circ6Figure.color().rgb().p0(), circ6Figure.color().rgb().p1(), circ6Figure.color().rgb().p2()));
        circ6Graphics.setStroke(new BasicStroke((int)circ6Figure.thickness()));

        Graphics2D circ7Graphics = (Graphics2D) g.create();
        if (firstPaint) {
            circ7Figure = new structures.Circle("circ7");
            double varExpr69 = 75;
            circ7Figure.setDiameter(varExpr69);
            double varExpr70 = 10;
            circ7Figure.setThickness(varExpr70);

            circ7Figure.setDisplay(true);

            circ7Figure.setFilled(true);
            double varExpr71 = 2;
            circ7Figure.setDepth(varExpr71);
            double varExpr72 = 0;

            double varExpr73 = 0;

            double varExpr74 = 0;
            structures.Color varExpr75 = new structures.Color(new RGB((int)varExpr72,(int)varExpr73,(int)varExpr74));
            circ7Figure.setColor(varExpr75);
            double varExpr76 = 0;

            double varExpr77 = 0;
            structures.Point varPointExpr8 = new structures.Point(varExpr76,varExpr77);
            if(firstPaint) positions.put("circ7", new Pair<Double, Double>(varExpr76,varExpr77));
            circ7Figure.setCenter(varPointExpr8);
        }

        circ7Graphics.translate(getWidth()/2,getHeight()/2);
        Shape circ7 = new Ellipse2D.Double(positions.get("circ7").a - circ7Figure.diameter()/2,positions.get("circ7").b - circ7Figure.diameter()/2,
                                           circ7Figure.diameter(),circ7Figure.diameter());
        java.awt.Rectangle circ7Bounds = circ7.getBounds();
        rectBoundsList.add(circ7Bounds);

        circ7Graphics.setColor(new java.awt.Color(circ7Figure.color().rgb().p0(), circ7Figure.color().rgb().p1(), circ7Figure.color().rgb().p2()));
        circ7Graphics.setStroke(new BasicStroke((int)circ7Figure.thickness()));

        Graphics2D circ8Graphics = (Graphics2D) g.create();
        if (firstPaint) {
            circ8Figure = new structures.Circle("circ8");
            double varExpr78 = 75;
            circ8Figure.setDiameter(varExpr78);
            double varExpr79 = 10;
            circ8Figure.setThickness(varExpr79);

            circ8Figure.setDisplay(true);

            circ8Figure.setFilled(true);
            double varExpr80 = 2;
            circ8Figure.setDepth(varExpr80);
            double varExpr81 = 0;

            double varExpr82 = 0;

            double varExpr83 = 0;
            structures.Color varExpr84 = new structures.Color(new RGB((int)varExpr81,(int)varExpr82,(int)varExpr83));
            circ8Figure.setColor(varExpr84);
            double varExpr85 = 0;

            double varExpr86 = 0;
            structures.Point varPointExpr9 = new structures.Point(varExpr85,varExpr86);
            if(firstPaint) positions.put("circ8", new Pair<Double, Double>(varExpr85,varExpr86));
            circ8Figure.setCenter(varPointExpr9);
        }

        circ8Graphics.translate(getWidth()/2,getHeight()/2);
        Shape circ8 = new Ellipse2D.Double(positions.get("circ8").a - circ8Figure.diameter()/2,positions.get("circ8").b - circ8Figure.diameter()/2,
                                           circ8Figure.diameter(),circ8Figure.diameter());
        java.awt.Rectangle circ8Bounds = circ8.getBounds();
        rectBoundsList.add(circ8Bounds);

        circ8Graphics.setColor(new java.awt.Color(circ8Figure.color().rgb().p0(), circ8Figure.color().rgb().p1(), circ8Figure.color().rgb().p2()));
        circ8Graphics.setStroke(new BasicStroke((int)circ8Figure.thickness()));

        if(recFigure.filled())     
            recGraphics.fill(rec);
        else
            recGraphics.draw(rec);
        if(circFigure.filled())     
            circGraphics.fill(circ);
        else
            circGraphics.draw(circ);
        if(circ2Figure.filled())     
            circ2Graphics.fill(circ2);
        else
            circ2Graphics.draw(circ2);
        if(circ3Figure.filled())     
            circ3Graphics.fill(circ3);
        else
            circ3Graphics.draw(circ3);
        if(circ4Figure.filled())     
            circ4Graphics.fill(circ4);
        else
            circ4Graphics.draw(circ4);
        if(circ5Figure.filled())     
            circ5Graphics.fill(circ5);
        else
            circ5Graphics.draw(circ5);
        if(circ6Figure.filled())     
            circ6Graphics.fill(circ6);
        else
            circ6Graphics.draw(circ6);
        if(circ7Figure.filled())     
            circ7Graphics.fill(circ7);
        else
            circ7Graphics.draw(circ7);
        if(circ8Figure.filled())     
            circ8Graphics.fill(circ8);
        else
            circ8Graphics.draw(circ8);

        double varExpr89 = 0;
         if (firstPaint) hitTop = varExpr89;

        double varExpr90 = 20;
         if (firstPaint) velocity = varExpr90;

        double varExpr91 = 0;
         if (firstPaint) switches = varExpr91;

        double varExpr92 = 0;
         if (firstPaint) angleValue = varExpr92;

        boolean varBoolExpr1 = boardBottomBounds.intersects(circBounds);
        if(varBoolExpr1){
            double varExpr93 = 0;
            hitTop = varExpr93;double varExpr95 = switches;

            double varExpr96 = 1;
            double varExpr94 = varExpr95+varExpr96;
            switches = varExpr94;double varExpr98 = angleValue;

            double varExpr99 = 15;
            double varExpr97 = varExpr98+varExpr99;
            angleValue = varExpr97;
        }

        double varExpr101 = hitTop;
        double varExpr100 = varExpr101;

        double varExpr103 = 0;
        double varExpr102 = varExpr103;
        boolean varBoolExpr2 = varExpr100==varExpr102;
        if(varBoolExpr2){
            double varExpr104 = circFigure.center().x();

            double varExpr106 = circFigure.center().y();

            double varExpr107 = velocity;
            double varExpr105 = varExpr106-varExpr107;
            structures.Point varPointExpr10 = new structures.Point(varExpr104,varExpr105);
            positions.put("circ", new Pair<Double, Double>(varExpr104,varExpr105));
            circFigure.setCenter(varPointExpr10);double varExpr108 = circ2Figure.center().x();

            double varExpr110 = circ2Figure.center().y();

            double varExpr111 = velocity;
            double varExpr109 = varExpr110-varExpr111;
            structures.Point varPointExpr11 = new structures.Point(varExpr108,varExpr109);
            positions.put("circ2", new Pair<Double, Double>(varExpr108,varExpr109));
            circ2Figure.setCenter(varPointExpr11);double varExpr112 = circ3Figure.center().x();

            double varExpr114 = circ3Figure.center().y();

            double varExpr115 = velocity;
            double varExpr113 = varExpr114-varExpr115;
            structures.Point varPointExpr12 = new structures.Point(varExpr112,varExpr113);
            positions.put("circ3", new Pair<Double, Double>(varExpr112,varExpr113));
            circ3Figure.setCenter(varPointExpr12);double varExpr117 = circ4Figure.center().x();

            double varExpr118 = velocity;
            double varExpr116 = varExpr117-varExpr118;

            double varExpr119 = circ4Figure.center().y();
            structures.Point varPointExpr13 = new structures.Point(varExpr116,varExpr119);
            positions.put("circ4", new Pair<Double, Double>(varExpr116,varExpr119));
            circ4Figure.setCenter(varPointExpr13);double varExpr121 = circ5Figure.center().x();

            double varExpr122 = velocity;
            double varExpr120 = varExpr121-varExpr122;

            double varExpr123 = circ5Figure.center().y();
            structures.Point varPointExpr14 = new structures.Point(varExpr120,varExpr123);
            positions.put("circ5", new Pair<Double, Double>(varExpr120,varExpr123));
            circ5Figure.setCenter(varPointExpr14);double varExpr125 = circ6Figure.center().x();

            double varExpr126 = velocity;
            double varExpr124 = varExpr125-varExpr126;

            double varExpr127 = circ6Figure.center().y();
            structures.Point varPointExpr15 = new structures.Point(varExpr124,varExpr127);
            positions.put("circ6", new Pair<Double, Double>(varExpr124,varExpr127));
            circ6Figure.setCenter(varPointExpr15);double varExpr129 = circ7Figure.center().x();

            double varExpr130 = velocity;
            double varExpr128 = varExpr129-varExpr130;

            double varExpr132 = circ7Figure.center().y();

            double varExpr133 = velocity;
            double varExpr131 = varExpr132-varExpr133;
            structures.Point varPointExpr16 = new structures.Point(varExpr128,varExpr131);
            positions.put("circ7", new Pair<Double, Double>(varExpr128,varExpr131));
            circ7Figure.setCenter(varPointExpr16);double varExpr135 = circ8Figure.center().x();

            double varExpr136 = velocity;
            double varExpr134 = varExpr135+varExpr136;

            double varExpr138 = circ8Figure.center().y();

            double varExpr139 = velocity;
            double varExpr137 = varExpr138+varExpr139;
            structures.Point varPointExpr17 = new structures.Point(varExpr134,varExpr137);
            positions.put("circ8", new Pair<Double, Double>(varExpr134,varExpr137));
            circ8Figure.setCenter(varPointExpr17);double varExpr141 = switches;
            double varExpr140 = varExpr141;

            double varExpr143 = 1;
            double varExpr142 = varExpr143;
            boolean varBoolExpr3 = varExpr140>varExpr142;
            if(varBoolExpr3){
                double varExpr144 = 255;

                double varExpr145 = 0;

                double varExpr146 = 0;
                structures.Color varExpr147 = new structures.Color(new RGB((int)varExpr144,(int)varExpr145,(int)varExpr146));
                circFigure.setColor(varExpr147);double varExpr148 = 255;

                double varExpr149 = 191;

                double varExpr150 = 0;
                structures.Color varExpr151 = new structures.Color(new RGB((int)varExpr148,(int)varExpr149,(int)varExpr150));
                circ2Figure.setColor(varExpr151);double varExpr152 = 187;

                double varExpr153 = 255;

                double varExpr154 = 0;
                structures.Color varExpr155 = new structures.Color(new RGB((int)varExpr152,(int)varExpr153,(int)varExpr154));
                circ3Figure.setColor(varExpr155);double varExpr156 = 0;

                double varExpr157 = 255;

                double varExpr158 = 38;
                structures.Color varExpr159 = new structures.Color(new RGB((int)varExpr156,(int)varExpr157,(int)varExpr158));
                circ4Figure.setColor(varExpr159);double varExpr160 = 0;

                double varExpr161 = 255;

                double varExpr162 = 208;
                structures.Color varExpr163 = new structures.Color(new RGB((int)varExpr160,(int)varExpr161,(int)varExpr162));
                circ5Figure.setColor(varExpr163);double varExpr164 = 0;

                double varExpr165 = 98;

                double varExpr166 = 255;
                structures.Color varExpr167 = new structures.Color(new RGB((int)varExpr164,(int)varExpr165,(int)varExpr166));
                circ6Figure.setColor(varExpr167);double varExpr168 = 153;

                double varExpr169 = 0;

                double varExpr170 = 255;
                structures.Color varExpr171 = new structures.Color(new RGB((int)varExpr168,(int)varExpr169,(int)varExpr170));
                circ7Figure.setColor(varExpr171);double varExpr172 = 255;

                double varExpr173 = 0;

                double varExpr174 = 187;
                structures.Color varExpr175 = new structures.Color(new RGB((int)varExpr172,(int)varExpr173,(int)varExpr174));
                circ8Figure.setColor(varExpr175);double varExpr176 = angleValue;
                Angle varExpr177 = new Angle((int) varExpr176);
                angles.put("rec", varExpr177);
                recFigure.setAngle(varExpr177);
            }
        }

        boolean varBoolExpr4 = boardTopBounds.intersects(circBounds);
        if(varBoolExpr4){
            double varExpr178 = 1;
            hitTop = varExpr178;double varExpr180 = switches;

            double varExpr181 = 1;
            double varExpr179 = varExpr180+varExpr181;
            switches = varExpr179;double varExpr183 = angleValue;

            double varExpr184 = 15;
            double varExpr182 = varExpr183+varExpr184;
            angleValue = varExpr182;
        }

        double varExpr186 = hitTop;
        double varExpr185 = varExpr186;

        double varExpr188 = 1;
        double varExpr187 = varExpr188;
        boolean varBoolExpr5 = varExpr185==varExpr187;
        if(varBoolExpr5){
            double varExpr189 = circFigure.center().x();

            double varExpr191 = circFigure.center().y();

            double varExpr192 = velocity;
            double varExpr190 = varExpr191+varExpr192;
            structures.Point varPointExpr18 = new structures.Point(varExpr189,varExpr190);
            positions.put("circ", new Pair<Double, Double>(varExpr189,varExpr190));
            circFigure.setCenter(varPointExpr18);double varExpr193 = circ2Figure.center().x();

            double varExpr195 = circ2Figure.center().y();

            double varExpr196 = velocity;
            double varExpr194 = varExpr195+varExpr196;
            structures.Point varPointExpr19 = new structures.Point(varExpr193,varExpr194);
            positions.put("circ2", new Pair<Double, Double>(varExpr193,varExpr194));
            circ2Figure.setCenter(varPointExpr19);double varExpr197 = circ3Figure.center().x();

            double varExpr199 = circ3Figure.center().y();

            double varExpr200 = velocity;
            double varExpr198 = varExpr199+varExpr200;
            structures.Point varPointExpr20 = new structures.Point(varExpr197,varExpr198);
            positions.put("circ3", new Pair<Double, Double>(varExpr197,varExpr198));
            circ3Figure.setCenter(varPointExpr20);double varExpr202 = circ4Figure.center().x();

            double varExpr203 = velocity;
            double varExpr201 = varExpr202+varExpr203;

            double varExpr204 = circ4Figure.center().y();
            structures.Point varPointExpr21 = new structures.Point(varExpr201,varExpr204);
            positions.put("circ4", new Pair<Double, Double>(varExpr201,varExpr204));
            circ4Figure.setCenter(varPointExpr21);double varExpr206 = circ5Figure.center().x();

            double varExpr207 = velocity;
            double varExpr205 = varExpr206+varExpr207;

            double varExpr208 = circ5Figure.center().y();
            structures.Point varPointExpr22 = new structures.Point(varExpr205,varExpr208);
            positions.put("circ5", new Pair<Double, Double>(varExpr205,varExpr208));
            circ5Figure.setCenter(varPointExpr22);double varExpr210 = circ6Figure.center().x();

            double varExpr211 = velocity;
            double varExpr209 = varExpr210+varExpr211;

            double varExpr212 = circ6Figure.center().y();
            structures.Point varPointExpr23 = new structures.Point(varExpr209,varExpr212);
            positions.put("circ6", new Pair<Double, Double>(varExpr209,varExpr212));
            circ6Figure.setCenter(varPointExpr23);double varExpr214 = circ7Figure.center().x();

            double varExpr215 = velocity;
            double varExpr213 = varExpr214+varExpr215;

            double varExpr217 = circ7Figure.center().y();

            double varExpr218 = velocity;
            double varExpr216 = varExpr217+varExpr218;
            structures.Point varPointExpr24 = new structures.Point(varExpr213,varExpr216);
            positions.put("circ7", new Pair<Double, Double>(varExpr213,varExpr216));
            circ7Figure.setCenter(varPointExpr24);double varExpr220 = circ8Figure.center().x();

            double varExpr221 = velocity;
            double varExpr219 = varExpr220-varExpr221;

            double varExpr223 = circ8Figure.center().y();

            double varExpr224 = velocity;
            double varExpr222 = varExpr223-varExpr224;
            structures.Point varPointExpr25 = new structures.Point(varExpr219,varExpr222);
            positions.put("circ8", new Pair<Double, Double>(varExpr219,varExpr222));
            circ8Figure.setCenter(varPointExpr25);double varExpr226 = switches;
            double varExpr225 = varExpr226;

            double varExpr228 = 1;
            double varExpr227 = varExpr228;
            boolean varBoolExpr6 = varExpr225>varExpr227;
            if(varBoolExpr6){
                double varExpr229 = 255;

                double varExpr230 = 0;

                double varExpr231 = 187;
                structures.Color varExpr232 = new structures.Color(new RGB((int)varExpr229,(int)varExpr230,(int)varExpr231));
                circFigure.setColor(varExpr232);double varExpr233 = 153;

                double varExpr234 = 0;

                double varExpr235 = 255;
                structures.Color varExpr236 = new structures.Color(new RGB((int)varExpr233,(int)varExpr234,(int)varExpr235));
                circ2Figure.setColor(varExpr236);double varExpr237 = 0;

                double varExpr238 = 98;

                double varExpr239 = 255;
                structures.Color varExpr240 = new structures.Color(new RGB((int)varExpr237,(int)varExpr238,(int)varExpr239));
                circ3Figure.setColor(varExpr240);double varExpr241 = 0;

                double varExpr242 = 255;

                double varExpr243 = 208;
                structures.Color varExpr244 = new structures.Color(new RGB((int)varExpr241,(int)varExpr242,(int)varExpr243));
                circ4Figure.setColor(varExpr244);double varExpr245 = 0;

                double varExpr246 = 255;

                double varExpr247 = 38;
                structures.Color varExpr248 = new structures.Color(new RGB((int)varExpr245,(int)varExpr246,(int)varExpr247));
                circ5Figure.setColor(varExpr248);double varExpr249 = 187;

                double varExpr250 = 255;

                double varExpr251 = 0;
                structures.Color varExpr252 = new structures.Color(new RGB((int)varExpr249,(int)varExpr250,(int)varExpr251));
                circ6Figure.setColor(varExpr252);double varExpr253 = 255;

                double varExpr254 = 191;

                double varExpr255 = 0;
                structures.Color varExpr256 = new structures.Color(new RGB((int)varExpr253,(int)varExpr254,(int)varExpr255));
                circ7Figure.setColor(varExpr256);double varExpr257 = 255;

                double varExpr258 = 0;

                double varExpr259 = 0;
                structures.Color varExpr260 = new structures.Color(new RGB((int)varExpr257,(int)varExpr258,(int)varExpr259));
                circ8Figure.setColor(varExpr260);double varExpr261 = angleValue;
                Angle varExpr262 = new Angle((int) varExpr261);
                angles.put("rec", varExpr262);
                recFigure.setAngle(varExpr262);
            }
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
