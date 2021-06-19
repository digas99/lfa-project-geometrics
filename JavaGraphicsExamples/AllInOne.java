import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.ArrayList;



public class AllInOne extends JPanel implements ActionListener,KeyListener{


    private static final int Width_Board = 1000;
    private static final int Height_Board = 1000;
    private Timer timer = new Timer((int)30.5, this); // (ms,ActionListener)
    private int xRedSquare = 0;
    private int xPurpleBlackSquare = 500;
    private int xGreenCircle = 300;
    private int xRedCircle = 600;
    private int velRedSquare = 1;
    private int velPurpleBlackSquare = 1;
    private int velGreenCircle = 1;
    private int velRedCircle = 1;
    private double angle = 0.0;
    //These can be initiated inside paint
    private int depthGreenCircle = 0;
    private int depthRedCircle = 0;
    String play = new String("P - play animation");
    String stop = new String("S - stop animation");

    /////////////// --------------------------------Two rectangles------------------

    // Need a PaintMethod
    public void paint(Graphics g) {
        

        // If this is not here the image will not dissapear and appear after it, so it
        // will "leave a trace".
        // Might be useful. Run without it if you don't understand.
        super.paintComponent(g);

        ArrayList<java.awt.Rectangle> rectList = new ArrayList<>();
        
        g.drawString(play,10,10);
        g.drawString(stop,8,25);


        // cast to Graphics2D because Graphics is old, gives more options
        // this way you can use both Graphics and Graphics2D
        // Graphics2D is a child class of Graphics.
        // Not sure if g.create() is needed but it's good pratice.
        // containerRedSquare might not be the best name for it, it's not really a
        // container
        // you draw/fill figures directly on the board, but here you assign all their
        // properties
        // i.e. what they are and how they behave. You could MAYBE do this program on a
        // single
        // container but I think this way is safer and easier to understand.
        Graphics2D containerRedSquare = (Graphics2D) g.create();

        // You have to create the figures inside paint method because paint
        // will be called every 30ms(in this case) because of Timer(line13)
        // paint method is called on the ActionListener.
        // Rectangle is a special case, you can/it's useful to create it like this
        // For other shapes it's best to use Shape.
        // Where you put the variable is important because how you move figures is
        // by updating the var. If you want to move it diagonally you have
        // to have two vars.
        Rectangle2D rect = new Rectangle2D.Double(100, xRedSquare, 100, 100);// x,y,width,height

        // This is the color of whataver is "attached" to containerRedSquare, in this
        // case rect.
        // if you attach something else to containerRedSquare it will be this color
        // but, of course, you can update the color inside the container.
        containerRedSquare.setColor(Color.RED);

        // This is so that the rectangle rotates around it's center.
        // If you just put containerRedSquare.rotate(angle) it will rotate
        // around the center point of the board.
        // translate changes the origin point of container to origin point of rect.
        containerRedSquare.translate(rect.getCenterX(), rect.getCenterY());
        containerRedSquare.rotate(angle); 
        // ~3 hours to realize you need this
        containerRedSquare.translate(-rect.getCenterX(),-rect.getCenterY());
        // You could call draw(containerRedSquare.draw(rect)) to only draw the perimeter
        // of the rectangle.
        containerRedSquare.fill(rect);

        // I have to create a Shape for rect to use intersects() (below)
        // You could do Shape square = new Rectangle2D.Double(100,x,100, 100); at the
        // beginning
        // but then you can't use getCenterX() or getCenterY() for the translate,
        // meaning,
        // you have to think about what you are going to do with the figure because
        // it has an impact on what methods are available to you.
        Shape rectS = rect;

        // I want to add something that doesn't share properties with
        // containerRedSquare(which is a rect
        // with the properties translate,rotate,fill and color added to it).
        // If I added any other shape to containerRedSquare it would rotate and share
        // all other properties
        // So I'll create another Graphics.
        Graphics2D containerPurpleBlackSquare = (Graphics2D) g.create();

        Rectangle rect2 = new Rectangle(100, xPurpleBlackSquare, 100, 100);
        // Now with RGB
        containerPurpleBlackSquare.setColor(new Color(123, 123, 198));

        containerPurpleBlackSquare.translate((int) rect2.getCenterX(), (int) rect2.getCenterY());
        containerPurpleBlackSquare.rotate(angle);
        containerPurpleBlackSquare.translate((int) -rect2.getCenterX(), (int) -rect2.getCenterY());
        containerPurpleBlackSquare.fill(rect2);

        // You can also do the logic here instead of on
        // the ActionListener, but only do it when you have to.
        // It's useful in some situations.
        // On intersection stop,rotate around it's center and
        // change color of both rects.
        if (rectS.intersects(rect2)) {
            velRedSquare = 0;
            velPurpleBlackSquare = 0;
            angle += Math.PI/4;
            containerPurpleBlackSquare.setColor(new Color(55, 55, 55));
            containerRedSquare.setColor(new Color(55, 55, 55));
            // After changing color (and most other things) you have to fill rect again,
            // remember paint method is where changes happen.
            containerPurpleBlackSquare.fill(rect2);
            containerRedSquare.fill(rectS);

        }
        // Only use dispose when you are sure you no longer need to update the
        // container. For example, above(in if statment), I made changes to
        // containerRedSquare
        // but I'm in containerPurpleBlackSquare, this is only possible because I hand't
        // disposed of containerRedSquare. dispose() closes container and you can
        // no longer update/use it. It's good pratice to use dispose, it's safer to
        // close,
        // but only when you are sure.
        //containerRedSquare.dispose();
        //containerPurpleBlackSquare.dispose();

        /////////////// -------------- ------------------Two circles -------------------------

        Graphics2D containerRedCircle = (Graphics2D) g.create();

        Shape circle2 = new Ellipse2D.Double(xRedCircle, 100.0, 100.0, 100.0);
        rectList.add(circle2.getBounds());

        containerRedCircle.setPaint(Color.RED);
        //This is to erase shape, the color that is above fill or draw
        //will be the color of the shape, it doesn't really erase it
        //containerRedCircle.setPaint(containerRedCircle.getBackground());
        containerRedCircle.fill(circle2);
        depthRedCircle = 1;

        Graphics2D containerGreenCircle = (Graphics2D) g.create();

        // Using shape directly
        Shape circle = new Ellipse2D.Float(xGreenCircle, 100.0f, 100.0f, 100.0f);
        rectList.add(circle.getBounds());

        for(int i =0; i<rectList.size(); i++){
            containerGreenCircle.draw(rectList.get(i));
        }
        containerGreenCircle.setPaint(Color.GREEN);
        containerGreenCircle.fill(circle);
        depthGreenCircle = 2;
        //containerGreenCircle.dispose();

        // intersects() only accepts rectangles. so I'll create
        // rectangle that encloses the circle using getBounds().
        // If you don't draw/fill the rect, it's invisible
        // If you want to see it add containerRedCircle.draw(rect3);
        //Rectangle rect3 = new Rectangle(circle.getBounds());
        
        Graphics2D testingChangingOrigin = (Graphics2D)g.create();

        Rectangle2D recOrigin = new Rectangle2D.Double(500,500,200,200);
        testingChangingOrigin.draw(recOrigin);

        //This translate doesn't need cast to int because it's 2D
        testingChangingOrigin.translate(recOrigin.getCenterX(),recOrigin.getCenterY());
        Rectangle2D recInsideRec = new Rectangle2D.Double(0,0,50,50);
        testingChangingOrigin.drawString("String center",0,0);
        testingChangingOrigin.draw(recInsideRec);
        testingChangingOrigin.translate((int) -rect.getCenterX(), (int) -rect.getCenterY());
        
        


        /////////////// -------------- ------------------Two circles interaction -------------------------        

        // Circles move away from each other after intersecting
        // velGreenCircle =0 && velRedCircle=0 to make it more smooth
        //if (circle2.intersects(circle.getBounds2D())) {
        for(int i=0; i<rectList.size(); i++){
            for(int j=i+1; j<rectList.size(); j++){
                if(rectList.get(i).intersects(rectList.get(j))){
                    velGreenCircle = 0;
                    velRedCircle = 0;
                    velGreenCircle -= 1;
                    velRedCircle -= 1;
                }    
            }
        }
       
            //This is for hangling depth
            /*if(depthRedCircle > depthGreenCircle){
                containerRedCircle.fill(circle2);
            }
            else{
                containerGreenCircle.fill(circle); 
            }*/

        containerRedCircle.dispose();
        containerRedSquare.dispose();
        containerPurpleBlackSquare.dispose();
        containerGreenCircle.dispose();
        testingChangingOrigin.dispose();
        
        timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        // This makes the redSquare(rect) go down,
        // because xRedSquare is in y coordinate
        xRedSquare += velRedSquare;

        xPurpleBlackSquare -= velPurpleBlackSquare;

        xGreenCircle += velGreenCircle;
        xRedCircle -= velRedCircle;

        if (xRedSquare >= getWidth() - getWidth() * 0.10 ) {
            velRedSquare = velRedSquare * -1;
            velPurpleBlackSquare = velPurpleBlackSquare * -1;
            velGreenCircle = velGreenCircle * -1;
            velRedCircle = velRedCircle * -1;

        } else if (xPurpleBlackSquare >= getWidth() - getWidth() * 0.10) {
            velRedSquare = velRedSquare * -1;
            velPurpleBlackSquare = velPurpleBlackSquare * -1;
            velGreenCircle = velGreenCircle * -1;
            velRedCircle = velRedCircle * -1;

        } else if (xGreenCircle >= getWidth() - getWidth() * 0.10) {
            velRedSquare = velRedSquare * -1;
            velPurpleBlackSquare = velPurpleBlackSquare * -1;
            velGreenCircle = velGreenCircle * -1;
            velRedCircle = velRedCircle * -1;

        } else if (xRedCircle >= getWidth() - getWidth() * 0.10) {
            velRedSquare = velRedSquare * -1;
            velPurpleBlackSquare = velPurpleBlackSquare * -1;
            velGreenCircle = velGreenCircle * -1;
            velRedCircle = velRedCircle * -1;
        }

        repaint();
    }

    //For interaction
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


    public static void main(String[] args) {
        AllInOne n = new AllInOne();
        JFrame frame1 = new JFrame();
        frame1.setTitle("Geometrics");
        frame1.setSize(Width_Board, Height_Board);
        frame1.setVisible(true);
        // centers the board to the center of screen
        frame1.setLocationRelativeTo(null);
        frame1.add(n);
        frame1.addKeyListener(n);
         // program stops running after closing it on the X at the top right
        // otherwise you have to close it on the terminal
        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
    }
}


