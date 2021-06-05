//////////////////////////---THIS IS NOT FINISHED--------------------------------
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.awt.geom.Point2D;
import java.awt.geom.Path2D;


public class ShipLine extends JPanel implements ActionListener {


    final int Width_Board = 1000;
    final int Length_Board = 1000;  
    Timer timer = new Timer(30,this);
    int velShipbody = 1;
    int velTriangle = 1;
    double angle = 0.0;
    Point point = new Point(100,500);
    int xShipbody = (int)point.getY();

    public void paint(Graphics g){


        super.paintComponent(g);

        int xShipBody = (int)point.getX();
        Graphics2D containerShip = (Graphics2D)g.create(); 

  
        Rectangle2D Shipbody = new Rectangle((int)point.getX(),xShipBody,200,200);
        

        Polygon triangle =  new Polygon();
        triangle.addPoint((int)point.getX(),(int)point.getY());
        triangle.addPoint((int)point.getX(),(int)point.getY()+200);
        triangle.addPoint((int)point.getX()-50,(int)point.getY()+100);

        containerShip.setColor(Color.BLACK);
        containerShip.draw(Shipbody);
        containerShip.setColor(Color.MAGENTA);
        containerShip.setStroke(new BasicStroke(10));
        containerShip.draw(triangle);
        containerShip.dispose();
        

        timer.start();
    } 

    
    @Override
    public void actionPerformed(ActionEvent e) {

        xShipbody -= velShipbody ;


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


