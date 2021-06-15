import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.lang.Math;
import java.lang.Thread;
import java.util.ArrayList;

public class GeneralizedCode extends JPanel implements ActionListener {
    static final int Width_Board = 1000;
    static final int Height_Board = 1000;  
    Timer timer = new Timer(1000,this);
    Point2D pointForVegetaBody = new Point2D.Double(500,500);
    double x = pointForVegetaBody.getX();
    double y = pointForVegetaBody.getY();
    double speed = 1;
    ArrayList<Rectangle> recList = new ArrayList<Rectangle>();
    ArrayList<Shape> recListShape = new ArrayList<Shape>();

    //Default color of Java is Black
    public void paint(Graphics g){

        super.paintComponent(g);
        

//-------------------------------------------------------- BodyRectangle ----------------------------------------------------
        Graphics2D gBody = (Graphics2D)g.create();
         
        Rectangle2D vegetaBody = new Rectangle2D.Double(x,y,200,200); // x, y, width, height
        Rectangle recVegetaBody = vegetaBody.getBounds();
        recList.add(recVegetaBody);

        y -= speed;

//-------------------------------------------------------- HeadTriangle ----------------------------------------------------
        Graphics2D gHead = (Graphics2D)g.create();

        Point2D pointTopLeftRect = new Point2D.Double(vegetaBody.getX(),vegetaBody.getY());
        Point2D pointTopRightRect = new Point2D.Double(pointTopLeftRect.getX()+vegetaBody.getWidth(),
                                                      pointTopLeftRect.getY());
        Polygon shipCenterHead =  new Polygon();
        shipCenterHead.addPoint((int)pointTopLeftRect.getX(),(int)pointTopLeftRect.getY());
        // Bottom right
        shipCenterHead.addPoint((int)pointTopRightRect.getX(),(int)pointTopRightRect.getY());

        shipCenterHead.addPoint(((int)pointTopLeftRect.getX()+(int)pointTopRightRect.getX())/2, 
                                (int)pointTopLeftRect.getY()-50);

        //Can't be 2D 
        Rectangle recShipCenterHead = new Rectangle(shipCenterHead.getBounds());
        recList.add(recShipCenterHead);

        
//-------------------------------------------------------- ArmsLines ----------------------------------------------------

        Graphics2D gLeftArm = (Graphics2D)g.create();

        Point2D startingPointLeftArm = new Point2D.Double(vegetaBody.getX()+vegetaBody.getWidth(),
                                                          vegetaBody.getY()+vegetaBody.getHeight()/2);

        Point2D endingPointLeftArm = new Point2D.Double(startingPointLeftArm.getX()+50,vegetaBody.getY()+
                                                        vegetaBody.getHeight()/2+50);

        Shape lineShipLeftArm = new Line2D.Double(startingPointLeftArm.getX(),startingPointLeftArm.getY(),
                                                  endingPointLeftArm.getX(),endingPointLeftArm.getY()); // x1, y1, x2, y2

        Rectangle recShipLeftArm = new Rectangle(lineShipLeftArm.getBounds());      
        recList.add(recShipLeftArm);
        

        Graphics2D gRightArm = (Graphics2D)g.create();

        Point2D startingPointRightArm = new Point2D.Double(startingPointLeftArm.getX() - vegetaBody.getWidth(),
                                                           startingPointLeftArm.getY());

        Point2D endingPointRightArm = new Point2D.Double(endingPointLeftArm.getX() - vegetaBody.getWidth() - 100,endingPointLeftArm.getY());

        /*Shape lineShipRightArm = new Line2D.Double(startingPointLeftArm.getX() - vegetaBody.getWidth(),startingPointLeftArm.getY(),
                                                   endingPointLeftArm.getX() - vegetaBody.getWidth() - 100, endingPointLeftArm.getY());*/
        
        Shape lineShipRightArm = new Line2D.Double(startingPointRightArm.getX(),startingPointRightArm.getY(),
                                                   endingPointRightArm.getX(), endingPointRightArm.getY());
                                                  
        Rectangle recShipRightArm = new Rectangle(lineShipRightArm.getBounds());
        recList.add(recShipRightArm);
        

//-------------------------------------------------------- LegsCircles ----------------------------------------------------
        
        Graphics2D gLeftLeg = (Graphics2D)g.create();
        Point2D startingPointLeftLeg = new Point2D.Double(vegetaBody.getX() + vegetaBody.getWidth()/1.5, 
                                                          vegetaBody.getY() + vegetaBody.getHeight());

        Shape circleVegetaLeftLeg = new Ellipse2D.Double(startingPointLeftLeg.getX(),startingPointLeftLeg.getY(), 50, 50); // x ,y,width,height

        Rectangle recVegetaLeftLeg = new Rectangle(circleVegetaLeftLeg.getBounds());
        recList.add(recVegetaLeftLeg);
        
        Graphics2D gRightLeg = (Graphics2D)g.create();
        Shape circleVegetaRightLeg = new Ellipse2D.Double(startingPointLeftLeg.getX()-vegetaBody.getWidth()/2,startingPointLeftLeg.getY(), 50, 50);

        Rectangle recVegetaRightLeg = new Rectangle(circleVegetaRightLeg.getBounds());
        recList.add(recVegetaRightLeg);

//-------------------------------------------------------- GoalLine ----------------------------------------------------
        Graphics2D gGoal = (Graphics2D)g.create();

        Shape lineGoal = new Line2D.Double(0,50,1000,50); 

        gGoal.setStroke(new BasicStroke(20));

        for(int i=0; i < recList.size(); i++){
            if(lineGoal.intersects(recList.get(i)));{
               y += speed;
               System.out.println("i: " + i);
               System.out.println("Size: " + recList.size());
            }
        }

        /*for(int i=0; i < recList.size(); i++){
            gBody.draw(recList.get(i));
        }*/



        gBody.draw(vegetaBody);
        gHead.draw(shipCenterHead);
        gLeftArm.draw(lineShipLeftArm);
        gRightArm.draw(lineShipRightArm);
        gLeftLeg.draw(circleVegetaLeftLeg);
        gRightLeg.draw(circleVegetaRightLeg);
        gGoal.draw(lineGoal);



        gBody.dispose();
        gHead.dispose();
        gLeftArm.dispose();
        gRightArm.dispose();
        gLeftLeg.dispose();
        gRightLeg.dispose();
        gGoal.dispose();
               

        timer.start();
        
    }
        


    @Override
    public void actionPerformed(ActionEvent e) {

        repaint();
    }

    public static void main(String[] args) {
        GeneralizedCode n = new GeneralizedCode();
        JFrame frame1 = new JFrame();
        frame1.setTitle("Geometrics");
        frame1.setSize(new Dimension(Width_Board,Height_Board));
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.add(n);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);   
    }    
} 


