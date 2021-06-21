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
public class TestEx1 extends JPanel implements ActionListener,KeyListener{
    static final int Width_Board = 1000;
    static final int Height_Board = 1000;
    List<Figure> figures = new ArrayList<>();
    double varExpr72 = 30;
    double varExpr71 = varExpr72;
    private Timer timer = new Timer((int) varExpr71, this);
    Map<String,Pair<Double, Double>> positions = new HashMap<>();
    Map<String,structures.Angle> angles = new HashMap<>();
    boolean firstPaint = true;
    Shape boardTopBounds = new Line2D.Double(-500,-500,500,-500);
    Shape boardRightBounds = new Line2D.Double(500,-500,500,500);
    Shape boardBottomBounds = new Line2D.Double(500,500,-500,500);
    Shape boardLeftBounds = new Line2D.Double(-500,500,-500,-500);
    structures.Circle circTopFigure;
    double a;
    double stopRec;
    double b;
    double twoWorldsCollide;
    double recSpeed;
    structures.Circle circBottomLeftFigure;
    double trigger;
    double trigger3;
    structures.Circle circBottomFigure;
    double trigger2;
    double moveCircles;
    structures.Circle circTopRightFigure;
    structures.Rectangle recLeftFigure;
    structures.Rectangle recRightFigure;
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

        Graphics2D circBottomGraphics = (Graphics2D) g.create();
        if (firstPaint) {
            circBottomFigure = new structures.Circle("circBottom");
            double varExpr30 = 50;
            circBottomFigure.setDiameter(varExpr30);
            double varExpr31 = 5;
            circBottomFigure.setThickness(varExpr31);

            circBottomFigure.setFilled(false);
            double varExpr32 = 209;

            double varExpr33 = 19;

            double varExpr34 = 85;
            structures.Color varExpr35 = new structures.Color(new RGB((int)varExpr32,(int)varExpr33,(int)varExpr34));
            circBottomFigure.setColor(varExpr35);
            double varExpr36 = 0;

            double varExpr37 = 200;
            structures.Point varPointExpr3 = new structures.Point(varExpr36,varExpr37);
            if(firstPaint) positions.put("circBottom", new Pair<Double, Double>(varExpr36,varExpr37));
            circBottomFigure.setCenter(varPointExpr3);
        }

        circBottomGraphics.translate(getWidth()/2,getHeight()/2);
        Shape circBottom = new Ellipse2D.Double(positions.get("circBottom").a - circBottomFigure.diameter()/2,positions.get("circBottom").b - circBottomFigure.diameter()/2,
                                           circBottomFigure.diameter(),circBottomFigure.diameter());
        java.awt.Rectangle circBottomBounds = circBottom.getBounds();
        rectBoundsList.add(circBottomBounds);

        circBottomGraphics.setColor(new java.awt.Color(circBottomFigure.color().rgb().p0(), circBottomFigure.color().rgb().p1(), circBottomFigure.color().rgb().p2()));
        circBottomGraphics.setStroke(new BasicStroke((int)circBottomFigure.thickness()));

        Graphics2D circTopGraphics = (Graphics2D) g.create();
        if (firstPaint) {
            circTopFigure = new structures.Circle("circTop");
            double varExpr38 = 50;
            circTopFigure.setDiameter(varExpr38);
            double varExpr39 = 5;
            circTopFigure.setThickness(varExpr39);

            circTopFigure.setFilled(false);
            double varExpr40 = 0;

            double varExpr41 = 70;

            double varExpr42 = 235;
            structures.Color varExpr43 = new structures.Color(new RGB((int)varExpr40,(int)varExpr41,(int)varExpr42));
            circTopFigure.setColor(varExpr43);
            double varExpr44 = 0;

            double varExpr46 = 200;
            double varExpr45 = -varExpr46;
            structures.Point varPointExpr4 = new structures.Point(varExpr44,varExpr45);
            if(firstPaint) positions.put("circTop", new Pair<Double, Double>(varExpr44,varExpr45));
            circTopFigure.setCenter(varPointExpr4);
        }

        circTopGraphics.translate(getWidth()/2,getHeight()/2);
        Shape circTop = new Ellipse2D.Double(positions.get("circTop").a - circTopFigure.diameter()/2,positions.get("circTop").b - circTopFigure.diameter()/2,
                                           circTopFigure.diameter(),circTopFigure.diameter());
        java.awt.Rectangle circTopBounds = circTop.getBounds();
        rectBoundsList.add(circTopBounds);

        circTopGraphics.setColor(new java.awt.Color(circTopFigure.color().rgb().p0(), circTopFigure.color().rgb().p1(), circTopFigure.color().rgb().p2()));
        circTopGraphics.setStroke(new BasicStroke((int)circTopFigure.thickness()));

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

        if(recLeftFigure.filled())     
            recLeftGraphics.fill(recLeft);
        else
            recLeftGraphics.draw(recLeft);
        if(recRightFigure.filled())     
            recRightGraphics.fill(recRight);
        else
            recRightGraphics.draw(recRight);

        double varExpr65 = 0;
         if (firstPaint) trigger = varExpr65;

        double varExpr66 = 0;
         if (firstPaint) trigger2 = varExpr66;

        double varExpr67 = 0;
         if (firstPaint) leftAndRightCollided = varExpr67;

        double varExpr68 = 5;
         if (firstPaint) recSpeed = varExpr68;

        double varExpr69 = 0;
         if (firstPaint) twoWorldsCollide = varExpr69;

        double varExpr70 = 0;
         if (firstPaint) stopRec = varExpr70;

        double varExpr74 = leftAndRightCollided;
        double varExpr73 = varExpr74;

        double varExpr76 = 0;
        double varExpr75 = varExpr76;
        boolean varBoolExpr2 = varExpr73==varExpr75;
        double varExpr78 = stopRec;
        double varExpr77 = varExpr78;

        double varExpr80 = 0;
        double varExpr79 = varExpr80;
        boolean varBoolExpr4 = varExpr77==varExpr79;
        boolean varBoolExpr3 = varBoolExpr4;
        boolean varBoolExpr1 = varBoolExpr2&&varBoolExpr3;
        if(varBoolExpr1){
            double varExpr82 = recLeftFigure.center().x();

            double varExpr83 = recSpeed;
            double varExpr81 = varExpr82+varExpr83;

            double varExpr84 = 0;
            structures.Point varPointExpr7 = new structures.Point(varExpr81,varExpr84);
            positions.put("recLeft", new Pair<Double, Double>(varExpr81,varExpr84));
            recLeftFigure.setCenter(varPointExpr7);double varExpr86 = recRightFigure.center().x();

            double varExpr87 = recSpeed;
            double varExpr85 = varExpr86-varExpr87;

            double varExpr88 = 0;
            structures.Point varPointExpr8 = new structures.Point(varExpr85,varExpr88);
            positions.put("recRight", new Pair<Double, Double>(varExpr85,varExpr88));
            recRightFigure.setCenter(varPointExpr8);
        }

        boolean varBoolExpr5 = recLeftBounds.intersects(recRightBounds);
        if(varBoolExpr5){
            double varExpr89 = 1;
            leftAndRightCollided = varExpr89;
        }

        double varExpr91 = leftAndRightCollided;
        double varExpr90 = varExpr91;

        double varExpr93 = 1;
        double varExpr92 = varExpr93;
        boolean varBoolExpr7 = varExpr90==varExpr92;
        double varExpr95 = stopRec;
        double varExpr94 = varExpr95;

        double varExpr97 = 0;
        double varExpr96 = varExpr97;
        boolean varBoolExpr9 = varExpr94==varExpr96;
        boolean varBoolExpr8 = varBoolExpr9;
        boolean varBoolExpr6 = varBoolExpr7&&varBoolExpr8;
        if(varBoolExpr6){
            double varExpr99 = recLeftFigure.center().x();

            double varExpr100 = recSpeed;
            double varExpr98 = varExpr99-varExpr100;

            double varExpr101 = 0;
            structures.Point varPointExpr9 = new structures.Point(varExpr98,varExpr101);
            positions.put("recLeft", new Pair<Double, Double>(varExpr98,varExpr101));
            recLeftFigure.setCenter(varPointExpr9);double varExpr103 = recRightFigure.center().x();

            double varExpr104 = recSpeed;
            double varExpr102 = varExpr103+varExpr104;

            double varExpr105 = 0;
            structures.Point varPointExpr10 = new structures.Point(varExpr102,varExpr105);
            positions.put("recRight", new Pair<Double, Double>(varExpr102,varExpr105));
            recRightFigure.setCenter(varPointExpr10);double varExpr106 = 216;

            double varExpr107 = 22;

            double varExpr108 = 22;
            structures.Color varExpr109 = new structures.Color(new RGB((int)varExpr106,(int)varExpr107,(int)varExpr108));
            recLeftFigure.setColor(varExpr109);double varExpr110 = 216;

            double varExpr111 = 22;

            double varExpr112 = 22;
            structures.Color varExpr113 = new structures.Color(new RGB((int)varExpr110,(int)varExpr111,(int)varExpr112));
            recRightFigure.setColor(varExpr113);
        }

        boolean varBoolExpr11 = boardLeftBounds.intersects(recLeftBounds);

        boolean varBoolExpr12 = boardRightBounds.intersects(recRightBounds);
        boolean varBoolExpr10 = varBoolExpr11||varBoolExpr12;
        if(varBoolExpr10){
            double varExpr114 = 1;
            twoWorldsCollide = varExpr114;double varExpr115 = 1;
            stopRec = varExpr115;
        }

        double varExpr117 = twoWorldsCollide;
        double varExpr116 = varExpr117;

        double varExpr119 = 1;
        double varExpr118 = varExpr119;
        boolean varBoolExpr13 = varExpr116==varExpr118;
        if(varBoolExpr13){
            double varExpr120 = 0;

            double varExpr121 = 0;

            double varExpr122 = 0;
            structures.Color varExpr123 = new structures.Color(new RGB((int)varExpr120,(int)varExpr121,(int)varExpr122));
            recLeftFigure.setColor(varExpr123);double varExpr124 = 0;

            double varExpr125 = 0;

            double varExpr126 = 0;
            structures.Color varExpr127 = new structures.Color(new RGB((int)varExpr124,(int)varExpr125,(int)varExpr126));
            recRightFigure.setColor(varExpr127);double varExpr128 = 45;
            Angle varExpr129 = new Angle((int) varExpr128);
            angles.put("recLeft", varExpr129);
            recLeftFigure.setAngle(varExpr129);double varExpr131 = 45;
            double varExpr130 = -varExpr131;
            Angle varExpr132 = new Angle((int) varExpr130);
            angles.put("recRight", varExpr132);
            recRightFigure.setAngle(varExpr132);recLeftFigure.setFilled(true);recRightFigure.setFilled(true);if(circTopRightFigure.filled())     
                circTopRightGraphics.fill(circTopRight);
            else
                circTopRightGraphics.draw(circTopRight);if(circBottomLeftFigure.filled())     
                circBottomLeftGraphics.fill(circBottomLeft);
            else
                circBottomLeftGraphics.draw(circBottomLeft);double varExpr133 = 1;
            trigger = varExpr133;
        }

        double varExpr134 = 0;
         if (firstPaint) moveCircles = varExpr134;

        double varExpr136 = trigger;
        double varExpr135 = varExpr136;

        double varExpr138 = 1;
        double varExpr137 = varExpr138;
        boolean varBoolExpr14 = varExpr135==varExpr137;
        if(varBoolExpr14){
            if(circTopFigure.filled())     
                circTopGraphics.fill(circTop);
            else
                circTopGraphics.draw(circTop);if(circBottomFigure.filled())     
                circBottomGraphics.fill(circBottom);
            else
                circBottomGraphics.draw(circBottom);double varExpr140 = moveCircles;
            double varExpr139 = varExpr140;

            double varExpr142 = 0;
            double varExpr141 = varExpr142;
            boolean varBoolExpr15 = varExpr139==varExpr141;
            if(varBoolExpr15){
                double varExpr143 = 0;

                double varExpr145 = circTopFigure.center().y();

                double varExpr146 = 2;
                double varExpr144 = varExpr145+varExpr146;
                structures.Point varPointExpr11 = new structures.Point(varExpr143,varExpr144);
                positions.put("circTop", new Pair<Double, Double>(varExpr143,varExpr144));
                circTopFigure.setCenter(varPointExpr11);double varExpr147 = 0;

                double varExpr149 = circBottomFigure.center().y();

                double varExpr150 = 2;
                double varExpr148 = varExpr149-varExpr150;
                structures.Point varPointExpr12 = new structures.Point(varExpr147,varExpr148);
                positions.put("circBottom", new Pair<Double, Double>(varExpr147,varExpr148));
                circBottomFigure.setCenter(varPointExpr12);
            }boolean varBoolExpr16 = circTopBounds.intersects(circBottomBounds);
            if(varBoolExpr16){
                double varExpr151 = 1;
                moveCircles = varExpr151;
            }double varExpr153 = moveCircles;
            double varExpr152 = varExpr153;

            double varExpr155 = 1;
            double varExpr154 = varExpr155;
            boolean varBoolExpr17 = varExpr152==varExpr154;
            if(varBoolExpr17){
                double varExpr156 = 0;

                double varExpr158 = circTopFigure.center().y();

                double varExpr159 = 5;
                double varExpr157 = varExpr158-varExpr159;
                structures.Point varPointExpr13 = new structures.Point(varExpr156,varExpr157);
                positions.put("circTop", new Pair<Double, Double>(varExpr156,varExpr157));
                circTopFigure.setCenter(varPointExpr13);double varExpr160 = 0;

                double varExpr162 = circBottomFigure.center().y();

                double varExpr163 = 5;
                double varExpr161 = varExpr162+varExpr163;
                structures.Point varPointExpr14 = new structures.Point(varExpr160,varExpr161);
                positions.put("circBottom", new Pair<Double, Double>(varExpr160,varExpr161));
                circBottomFigure.setCenter(varPointExpr14);
            }
        }

        boolean varBoolExpr18 = boardTopBounds.intersects(circTopBounds);
        if(varBoolExpr18){
            double varExpr164 = 2;
            moveCircles = varExpr164;circTopFigure.setFilled(true);circBottomFigure.setFilled(true);double varExpr165 = 1;
            trigger2 = varExpr165;double varExpr166 = 200;
            circBottomFigure.setDiameter(varExpr166);double varExpr167 = 200;
            circTopFigure.setDiameter(varExpr167);
        }

        double varExpr168 = 0;
         if (firstPaint) trigger3 = varExpr168;

        double varExpr170 = trigger2;
        double varExpr169 = varExpr170;

        double varExpr172 = 1;
        double varExpr171 = varExpr172;
        boolean varBoolExpr20 = varExpr169==varExpr171;
        double varExpr174 = trigger3;
        double varExpr173 = varExpr174;

        double varExpr176 = 0;
        double varExpr175 = varExpr176;
        boolean varBoolExpr22 = varExpr173==varExpr175;
        boolean varBoolExpr21 = varBoolExpr22;
        boolean varBoolExpr19 = varBoolExpr20&&varBoolExpr21;
        if(varBoolExpr19){
            double varExpr178 = circTopRightFigure.center().x();

            double varExpr179 = 5;
            double varExpr177 = varExpr178-varExpr179;

            double varExpr181 = circTopRightFigure.center().y();

            double varExpr182 = 5;
            double varExpr180 = varExpr181-varExpr182;
            structures.Point varPointExpr15 = new structures.Point(varExpr177,varExpr180);
            positions.put("circTopRight", new Pair<Double, Double>(varExpr177,varExpr180));
            circTopRightFigure.setCenter(varPointExpr15);double varExpr184 = circBottomLeftFigure.center().x();

            double varExpr185 = 5;
            double varExpr183 = varExpr184+varExpr185;

            double varExpr187 = circBottomLeftFigure.center().y();

            double varExpr188 = 5;
            double varExpr186 = varExpr187+varExpr188;
            structures.Point varPointExpr16 = new structures.Point(varExpr183,varExpr186);
            positions.put("circBottomLeft", new Pair<Double, Double>(varExpr183,varExpr186));
            circBottomLeftFigure.setCenter(varPointExpr16);
        }
        double varExpr190 = circTopRightFigure.center().x();
        double varExpr189 = varExpr190;

        double varExpr192 = circBottomLeftFigure.center().x();
        double varExpr191 = varExpr192;
        boolean varBoolExpr25 = varExpr189==varExpr191;
        boolean varBoolExpr24 = varBoolExpr25;
        double varExpr194 = circTopRightFigure.center().y();
        double varExpr193 = varExpr194;

        double varExpr196 = circBottomLeftFigure.center().y();
        double varExpr195 = varExpr196;
        boolean varBoolExpr27 = varExpr193==varExpr195;
        boolean varBoolExpr26 = varBoolExpr27;
        boolean varBoolExpr23 = varBoolExpr24&&varBoolExpr26;
        if(varBoolExpr23){
            double varExpr197 = 1;
            trigger3 = varExpr197;
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
