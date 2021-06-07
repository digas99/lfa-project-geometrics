package structures;

import java.util.stream.Collectors;

public class Line extends Figure {

    private Point startingPoint;
    private Point endingPoint;
    private Angle angle;
	private double length;
    
    public Line(String id, Color color, Color borderColor, double border, Point center, boolean filled, double thickness, boolean collide, boolean visibility, boolean container, Point startingPoint, Point endingPoint) {
		super(id, color, borderColor, border, center, filled, thickness, collide, visibility, container);
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
		this.setCenter(new Point((startingPoint.x()+endingPoint.x())/2, (startingPoint.y()+endingPoint.y())/2));
		this.length = Point.distance(startingPoint, endingPoint);
	}

    public Line(String id, Color color, Color borderColor, double border, Point center, boolean filled, double thickness, boolean collide, boolean visibility, boolean container, Angle angle, double length) {
		super(id, color, borderColor, border, center, filled, thickness, collide, visibility, container);
		this.length = length;
        this.angle = angle;
		this.startingPoint = this.center().oppositePoint(length/2, angle.rad());
		this.endingPoint = this.center().oppositePoint(length/2, Angle.rad(180+angle.degree()));
	}
    public Point startingPoint() {
		return this.startingPoint;
	}

	public void setStartinPoint(Point startingPoint) {
		this.startingPoint = startingPoint;
	}

	public Point endingPoint() {
		return this.endingPoint;
	}

	public void setEndingPoint(Point endingPoint) {
		this.endingPoint = endingPoint;
	}

    public Angle angle() {
		return this.angle;
	}

    public void setAngle(Angle angle){
        this.angle = angle;
    }

	public double length() {
		return this.length;
	}

	public void setLength(double length) {
		this.length = length;
	}

	public String printFigure() {
		return String.format("%s \"%s\"\n", this.getClass().getSimpleName(), this.id())
			  +"\n"
			  +"-------------\n"
			  +"\n"
			  +String.format("StartingPoint: %5s\nEndingPoint: %5s\nLength: %10.2f\nCenter: %14s\nAngle: %9s\n",this.startingPoint.print(), this.endingPoint.print(), this.length, this.center().print(), this.angle)
			  +String.format("Color: %13s\nBorder: %9.2f %s\nThickness: %6.2f\nContainer: %7s\nFilled: %10s\nCollides: %7s\nVisible: %8s\nContains:    [%d] %s\n", this.color().rgb() != null ? "      "+this.color() : this.color(), this.border(), this.borderColor(), this.thickness(), this.container(), this.filled(), this.collide(), this.visibility(), this.figures().size(), "{"+String.join(", ", this.figures().stream().map(figure -> figure.id()).collect(Collectors.toList()))+"}")
			  ;
	}
	@Override public String toString() {
        return String.format("%s [%s] \ncolor: %s; \nborder: %.2f %s; \ncenter: %s; \nthickness: %.2f; \nsartingPoint: %s; \nendingPoint: %s; \nangle: %.2f; \n%s; \n%s; \n%s; \n%s; \nhas %d figures", this.getClass().getName(), this.id(), this.color(), this.border(), this.borderColor(), this.center().print(), this.thickness(), this.startingPoint.print(), this.endingPoint.print(), this.angle, this.container() ? "container" : "!container", this.filled() ? "filled" : "!filled", this.collide() ? "collides" : "!collides", this.visibility() ? "visible" : "!visible", this.figures().size());
	}
}

