import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.geom.Point2D;
import java.lang.Math;

public class ShipLine extends JPanel implements ActionListener {


    final static int Width_Board = 1000;
    final static int Height_Board = 1000;  
    Timer timer = new Timer(5,this);
    int velShipBody = 1;
    double angle = 0.0;
    double xShipBody = 500;
    double HeightTriangle = 50;
    double maxRandAngle = 360;
    double maxRandColor = 255;
    int r=0;
    int gr=0;
    int b=0;

    public void paint(Graphics g){

        super.paintComponent(g);        

        r=(int)(Math.random() * maxRandColor);
        gr=(int)(Math.random() * maxRandColor);
        b=(int)(Math.random() * maxRandColor);

        Graphics2D containerShip = (Graphics2D)g.create(); 
        
        Rectangle2D ShipBody = new Rectangle2D.Double(450,xShipBody,200,200);
        
        Point2D pointTopLeftRect = new Point2D.Double(ShipBody.getX(),ShipBody.getY());
        Point2D pointTopRightRect = new Point2D.Double(pointTopLeftRect.getX()+ShipBody.getWidth(),pointTopLeftRect.getY());

        Polygon triangle =  new Polygon();

        triangle.addPoint((int)pointTopLeftRect.getX(),(int)pointTopLeftRect.getY());
        // Bottom right(in this case) point of triangle
        triangle.addPoint((int)pointTopRightRect.getX(),(int)pointTopRightRect.getY());

        triangle.addPoint(((int)pointTopLeftRect.getX()+(int)pointTopRightRect.getX())/2, 
                         (int)pointTopLeftRect.getY()-(int)HeightTriangle);

        
        containerShip.setColor(new Color(r,gr,b));
        //This is thickness
        containerShip.setStroke(new BasicStroke(10));
   
        Rectangle RectTriangle = new Rectangle(triangle.getBounds());

        Rectangle2D sumRect = new Rectangle2D.Double(RectTriangle.getX(),RectTriangle.getY(),
                                                     ShipBody.getWidth(),ShipBody.getHeight()+RectTriangle.getHeight());


        containerShip.translate((int)sumRect.getCenterX(),(int)sumRect.getCenterY());
        containerShip.rotate(angle);
        containerShip.translate((int)-sumRect.getCenterX(),(int)-sumRect.getCenterY());

        containerShip.fill(ShipBody);
        containerShip.draw(triangle);
    

        
//////////////////--------------------------Line Container -----------------------------/////////////////
        
        Graphics2D containerLine = (Graphics2D)g.create();
        
        containerLine.setColor(new Color(r,gr,b));
        containerLine.setStroke(new BasicStroke(20));
        Shape line = new Line2D.Double(0,50,1000,50);
        containerLine.draw(line);

        if(line.intersects(sumRect)){
            velShipBody -= 1;
            angle = Math.toRadians((Math.random() * maxRandAngle)); 
            }

        containerShip.dispose();
        containerLine.dispose();

        timer.start();
        
    }
        
    @Override
    public void actionPerformed(ActionEvent e) {
 
        
        xShipBody -= velShipBody;

        if( xShipBody == Width_Board-200){ 
            velShipBody = velShipBody * -1;
            angle = Math.toRadians((Math.random() * maxRandAngle));
        }

        repaint();
    }

    public static void main(String[] args) {
        ShipLine n = new ShipLine();
        JFrame frame1 = new JFrame();
        frame1.setTitle("Geometrics");
        frame1.setSize(new Dimension(Width_Board,Height_Board));
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.add(n);
        frame1.setLocationRelativeTo(null);
        frame1.setVisible(true);   
    }    
} 


