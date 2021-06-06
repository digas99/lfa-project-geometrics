import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;

public class AllInOne extends JPanel implements ActionListener {


    final int Width_Board = 1000;
    final int Length_Board = 1000;  
    private Timer timer = new Timer(30,this); //(ms,ActionListener)
    private int xRedSquare  = 0;
    private int xPurpleBlackSquare = 500;
    private int xGreenCircle = 300;
    private int xRedCircle = 600;
    private int velRedSquare = 1;
    private int velPurpleBlackSquare = 1;
    private int velGreenCircle  = 1;
    private int velRedCircle = 1;
    private double angle = 0.0;


    ///////////////--------------------------------Two rectangles interaction--------------------------------------------------------------------------

    //Need a PaintMethod
    public void paint(Graphics g){

        // If this is not here the image will not dissapear and appear after it, so it will "leave a trace". 
        // Might be useful. Run without it if you don't understand. 
        super.paintComponent(g);

        // cast to Graphics2D because Graphics is old, gives more options
        // this way you can use both Graphics and Graphics2D
        // Graphics2D is a child class of Graphics.
        // Not sure if g.create() is needed but it's good pratice.
        // containerRedSquare might not be the best name for it, it's not really a container
        // you draw/fill figures directly on the board, but here you assign all their properties
        // i.e. what they are and how they behave. You could MAYBE do this program on a single
        // container but I think this way is safer and easier to understand.
        Graphics2D containerRedSquare = (Graphics2D)g.create(); 

        // You have to create the figures inside paint method because paint
        // will be called every 30ms(in this case) because of Timer(line13)
        // paint method is called on the ActionListener.
        // Rectangle is a special case, you can/it's useful to create it like this 
        // For other shapes it's best to use Shape.
        // Where you put the variable is important because how you move figures is 
        // by updating the var. If you want to move it diagonally you have 
        // to have two vars.
        Rectangle rect = new Rectangle(100,xRedSquare,100,100);//x,y,width,height

        // This is the color of whataver is "attached" to containerRedSquare, in this case rect.
        // if you attach something else to containerRedSquare it will be this color
        // but, of course, you can update the color inside the container.
        containerRedSquare.setPaint(Color.RED);
     
        // This is so that the rectangle rotates around it's center.
        // If you just put containerRedSquare.rotate(angle) it will rotate
        // around the center point of the board.
        // translate changes the origin point of container to origin point of rect.
        containerRedSquare.translate((int)rect.getCenterX(),(int)rect.getCenterY());
        containerRedSquare.rotate(angle);
        // ~3 hours to realize you need this
        containerRedSquare.translate((int)-rect.getCenterX(),(int)-rect.getCenterY());
        // You could call draw(containerRedSquare.draw(rect)) to only draw the perimeter of the rectangle.
        containerRedSquare.fill(rect);

        // I have to create a Shape for rect to use intersects() (below)
        // You could do Shape square = new Rectangle2D.Double(100,x,100, 100); at the beginning
        // but then you can't use getCenterX() or getCenterY() for the translate, meaning,
        // you have to think about what you are going to do with the figure because
        // it has an impact on what methods are available to you.
        Shape rectS = rect;
        
        // I want to add something that doesn't share properties with containerRedSquare(which is a rect
        // with the properties translate,rotate,fill and color added to it).
        // If I added any other shape to containerRedSquare it would rotate and share all other properties
        // So I'll create another Graphics. 
        Graphics2D containerPurpleBlackSquare = (Graphics2D)g.create();

        Rectangle rect2 = new Rectangle(100,xPurpleBlackSquare,100,100);
        //Now with RGB
        containerPurpleBlackSquare.setColor(new Color(123,123,198));

        containerPurpleBlackSquare.translate((int)rect2.getCenterX(),(int)rect2.getCenterY());
        containerPurpleBlackSquare.rotate(angle);
        //~3 hours to find out I needed this line
        containerPurpleBlackSquare.translate((int)-rect2.getCenterX(),(int)-rect2.getCenterY());
        containerPurpleBlackSquare.fill(rect2);

        // You can also do the logic here instead of on
        // the ActionListener, but only do it when you have to.  
        // It's useful in some situations.
        // On intersection stop,rotate around it's center and
        // change color of both rects.
        if(rectS.intersects(rect2)){
            velRedSquare = 0;
            velPurpleBlackSquare = 0;
            angle += 0.1;
            containerPurpleBlackSquare.setColor(new Color(55,55,55));
            containerRedSquare.setColor(new Color(55,55,55));
            // After changing color (and most other things) you have to fill rect again,
            // remember paint method is where changes happen.
            containerPurpleBlackSquare.fill(rect2);
            containerRedSquare.fill(rectS);
            
        }
        // Only use dispose when you are sure you no longer need to update the
        // container. For example, above(in if statment), I made changes to containerRedSquare
        // but I'm in containerPurpleBlackSquare, this is only possible because I hand't 
        // disposed of containerRedSquare. dispose() closes container and you can
        // no longer update/use it. It's good pratice to use dispose, it's safer to close,
        // but only when you are sure.
        containerRedSquare.dispose();
        containerPurpleBlackSquare.dispose();

///////////////-------------- ------------------Two circles interaction--------------------------------------------------------------------------

        Graphics2D containerGreenCircle = (Graphics2D)g.create();

        // Using shape directly
        Shape circle = new Ellipse2D.Float(xGreenCircle, 100.0f, 100.0f, 100.0f);

        containerGreenCircle.setPaint(Color.GREEN);
        containerGreenCircle.fill(circle);
        containerGreenCircle.dispose();

        Graphics2D containerRedCircle = (Graphics2D)g.create();

        Shape circle2 = new Ellipse2D.Float(xRedCircle, 100.0f, 100.0f, 100.0f);

        containerRedCircle.setPaint(Color.RED);
        containerRedCircle.fill(circle2);

        // intersects() only accepts rectangles. so I'll create
        // rectangle that encloses the circle using getBounds().
        // If you don't draw/fill the rect, it's invisible
        // If you want to see it add containerRedCircle.draw(rect3);
        Rectangle rect3 = new Rectangle(circle.getBounds());
        
        
        // Circles move away from each other after intersecting
        // velGreenCircle =0 && velRedCircle=0 to make it more smooth
        if(circle2.intersects(rect3)){
            velGreenCircle  = 0;
            velRedCircle = 0;
            velGreenCircle  -= 1;
            velRedCircle -= 1;
        }

        containerRedCircle.dispose();

        timer.start();
    } 

    // This calls the paint method. If you can, put the logic here,
    // but sometimes it's not possible.
    @Override
    public void actionPerformed(ActionEvent e) {

        // This makes the redSquare(rect) go down, 
        // because xRedSquare is in y coordinate on line52
        xRedSquare += velRedSquare;
 
        xPurpleBlackSquare -= velPurpleBlackSquare;

        xGreenCircle += velGreenCircle ;
        xRedCircle -= velRedCircle;

        // This is so that board has collision.
        // Java is stupid so the -100 is because size of board includes borders of board.
        // With this logic figures go the opposite way.
        if(xRedSquare >= Length_Board-100){
            velRedSquare = velRedSquare * -1;
            velPurpleBlackSquare = velPurpleBlackSquare * -1;
            velGreenCircle  = velGreenCircle  * -1;
            velRedCircle = velRedCircle * -1;

        }else if( xPurpleBlackSquare >= Width_Board-100){
            velRedSquare = velRedSquare * -1;
            velPurpleBlackSquare = velPurpleBlackSquare * -1;
            velGreenCircle  = velGreenCircle  * -1;
            velRedCircle = velRedCircle * -1;
        
        }else if(xGreenCircle >= Width_Board-100){
            velRedSquare = velRedSquare * -1;
            velPurpleBlackSquare = velPurpleBlackSquare * -1;
            velGreenCircle  = velGreenCircle  * -1;
            velRedCircle = velRedCircle * -1;
        
        }else if(xRedCircle >= Length_Board-100){
            velRedSquare = velRedSquare * -1;
            velPurpleBlackSquare = velPurpleBlackSquare * -1;
            velGreenCircle  = velGreenCircle  * -1;
            velRedCircle = velRedCircle * -1;
        }

        // This will call the paint function. Paint function can't/shouldn't be refer to directly, in any case.
        // Without this things don't move!!
        repaint();
        
    }

    public static void main(String[] args) {
        AllInOne n = new AllInOne();
        JFrame frame1 = new JFrame();
        frame1.setTitle("Geometrics");
        frame1.setSize(1000,1000);
        frame1.setVisible(true);
        // centers the board to the center of screen
        frame1.setLocationRelativeTo(null);
        // program stops running after closing it on the X at the top right
        // otherwise you have to close it on the terminal
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame1.add(n);
    }
} 

