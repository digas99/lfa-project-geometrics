//////////////////////////---THIS IS NOT FINISHED--------------------------------
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.geom.Point2D;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class ShipLine extends JPanel implements ActionListener {


    final int Width_Board = 1000;
    final int Length_Board = 1000;  
    Timer timer = new Timer(30,this);
    int velShipBody = 1;
    int velTriangle = 1;
    double angle = 0.0;
    // Point pointTopLeftRect = new Point(100,500);
    // Point pointTopRightRect = new Point(pointTopLeftRect.getX()+ShipBody.getWidth(),pointTopLeftRect.getY()+ShipBody.getHeight());
    double xShipBody = 500;
    double HeightTriangle = 50;
    double ySumRect;
    double xSumRect;
    double maxHeightFigure;
    double maxWidthFIgure;
    double varMinX = Width_Board;
    double varMinY = Length_Board;
    double varMaxX = Width_Board;
    double varMaxY = Length_Board;
    ArrayList<Double> minX = new ArrayList<Double>();
    ArrayList<Double> minY = new ArrayList<Double>();
    ArrayList<Double> maxWidth = new ArrayList<Double>();
    ArrayList<Double> maxY = new ArrayList<Double>();

    public void paint(Graphics g){


        super.paintComponent(g);

        Graphics2D containerShip = (Graphics2D)g.create(); 
        
        Rectangle2D ShipBody = new Rectangle2D.Double(450,xShipBody,200,200);
        
        Point2D pointTopLeftRect = new Point2D.Double(ShipBody.getX(),ShipBody.getY());
        Point2D pointTopRightRect = new Point2D.Double(pointTopLeftRect.getX()+ShipBody.getWidth(),pointTopLeftRect.getY());

        Polygon triangle =  new Polygon();
        // This logic puts the triangle on top of rect(or any other figure)
        // it's made by me, so I'm not sure if it works for everything. 
        // Bottom left point of triangle
        triangle.addPoint((int)pointTopLeftRect.getX(),(int)pointTopLeftRect.getY());
        // Bottom right point of triangle
        triangle.addPoint((int)pointTopRightRect.getX(),(int)pointTopRightRect.getY());

        triangle.addPoint(((int)pointTopLeftRect.getX()+(int)pointTopRightRect.getX())/2, 
                         (int)pointTopLeftRect.getY()-(int)HeightTriangle);

        containerShip.setColor(Color.BLACK);
        containerShip.draw(ShipBody);
        containerShip.setColor(Color.MAGENTA);
        //This is thickness
        containerShip.setStroke(new BasicStroke(10));
        containerShip.draw(triangle);

        Rectangle RectTriangle = new Rectangle(triangle.getBounds());
        Shape hiddenRect = RectTriangle;

        Rectangle2D sumRect = new Rectangle2D.Double(RectTriangle.getX(),RectTriangle.getY(),
                                                     ShipBody.getWidth(),ShipBody.getHeight()+RectTriangle.getHeight());


        containerShip.translate((int)sumRect.getCenterX(),(int)sumRect.getCenterY());
        containerShip.rota(angle);
        containerShip.translate((int)-sumRect.getCenterX(),(int)-sumRect.getCenterY());
        
//////////////////--------------------------__Line Container -----------------------------/////////////////
        
        Graphics2D containerLine = (Graphics2D)g.create();
        
        containerLine.setColor(Color.RED);
        containerLine.setStroke(new BasicStroke(20));
        
        // Could use points, as above
        Shape line = new Line2D.Double(0,50,1000,50);
        containerLine.draw(line);

        /*
            minX.add(ShipBody.getX());
            minX.add(RectTriangle.getX());


        for(double num : minX){
            if(varMinX >= num) 
            varMinX = num;
        }
            varMinY.add(ShipBody.getY());
            varMinY.add(RectTriangle.getY());
        
        for(double num : minY){
                if(varMinY >= num) 
                varMinY = num;
        }

        maxWidth.add(ShipBody.getWidth());
        maxWidth.add(RectTriangle.getWidth());

        for(double num : maxWidth){
            if(varMaxX >= num) 
            varMaxX = num;
        }

        varMaxX = varMaxX + varMinX;

        maxY.add(ShipBody.getHeight());
        maxY.add(RectTriangle.getHeight());

       /* for(double num : maxY){
            if(varMaxY >= num) 
            varMaxY = num;
        }

        varMaxY = varMaxY + varMinY;*/ 

        

        
        //Eu quero o menor Y e o menor X da figura toda
        /*if(ShipBody.getY() >= RectTriangle.getY()){
            ySumRect = RectTriangle.getY();
        }else{
            ySumRect = ShipBody.getY();
        }
        if(ShipBody.getX() >= RectTriangle.getX()){
            xSumRect = RectTriangle.getX();
        }else{
            xSumRect = ShipBody.getX();
        }*/
        

       /* maxHeightFigure = RectTriangle.getHeight()+ ShipBody.getHeight()+ varMinY  ;
        maxWidthFigure = varMaxX + varMinX; */

        

        if(line.intersects(sumRect)){
        containerLine.setColor(Color.BLUE);
        velShipBody = 0;
        containerLine.draw(line);
        angle = 3;
        containerShip.setColor(Color.BLACK);
        containerShip.draw(ShipBody);
        containerShip.draw(triangle);
        }
         
        
        containerShip.dispose();
        containerLine.dispose();
        


        timer.start();
    } 

    
    @Override
    public void actionPerformed(ActionEvent e) {

        // This moves Rect and triangle at the same time because they share xShipBody
        xShipBody -= velShipBody ;
        


        repaint();
        
    }

    public static void main(String[] args) {
        ShipLine n = new ShipLine();
        JFrame frame1 = new JFrame();
        frame1.setTitle("Geometrics");
        //frame1.setBackground(Color.MAGENTA);
        frame1.setSize(1000,1000);
        frame1.setVisible(true);
        frame1.setLocationRelativeTo(null);
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.add(n);
    }
    
} 


