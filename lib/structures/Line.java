package structures;

import java.util.stream.Collectors;

public class Line extends Figure {

    private Point startingPoint;
    private Point endingPoint;
    private Angle angle;
    private double length;
    
    public Line(String id) {
		super(id);
	}

    public Line(String id, Color color, Point center, boolean filled, double thickness, double depth, boolean display, boolean container, Point startingPoint, Point endingPoint) {
		super(id, color, center, filled, thickness, depth, display, container);
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
		this.setCenter(new Point((startingPoint.x()+endingPoint.x())/2, (startingPoint.y()+endingPoint.y())/2));
		this.length = Point.distance(startingPoint, endingPoint);
	}

    public Line(String id, Color color, Point center, boolean filled, double thickness, double depth, boolean display, boolean container, Angle angle, double length) {
		super(id, color, center, filled, thickness, depth, display, container);
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
			  +String.format("Color: %13s\nThickness: %6.2f\nContainer: %7s\nFilled: %10s\nVisible: %8s\nContains:    [%d] %s\n", this.color().rgb() != null ? "      "+this.color() : this.color(), this.thickness(), this.container(), this.filled(), this.display(), this.figures().size(), "{"+String.join(", ", this.figures().stream().map(figure -> figure.id()).collect(Collectors.toList()))+"}")
			  ;
	}

	@Override public String toString() {
        return String.format("%s [%s] Color: %s; Border: %.2f %s; Center: %s; Thickness: %.2f; StartingPoint: %s; EndingPoint: %s; Angle: %s; Length: %.2f; %s; %s; %s; Contains [%d] Figures", this.getClass().getSimpleName(), this.id(), this.color(), this.center().print(), this.thickness(), this.startingPoint, this.endingPoint, this.angle, this.length, this.container() ? "Container" : "!Container", this.filled() ? "Filled" : "!Filled", this.display() ? "Visible" : "!Visible", this.figures().size());
	}
}

