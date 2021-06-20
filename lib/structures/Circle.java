package structures;

import java.util.stream.Collectors;

public class Circle extends Figure {
    
	private Point startingPoint;
	private Point endingPoint;
	private double diameter;

	public Circle(String id) {
		super(id);
	}

	public Circle(String id, Color color, Point center, boolean filled, double thickness, double depth, boolean display, boolean container, double diameter) {
		super(id, color, center, filled, thickness, depth, display, container);
		this.diameter = diameter;
		this.startingPoint = new Point(center.x() - diameter/2, 0);
		this.endingPoint = new Point(center.x() + diameter/2, 0);
	}
	
	public Circle(String id, Color color, Point center, boolean filled, double thickness, double depth, boolean display, boolean container, Point startingPoint, Point endingPoint) {
		super(id, color, center, filled, thickness, depth, display, container);
		this.startingPoint = startingPoint;
		this.endingPoint = endingPoint;
		this.diameter = Point.distance(startingPoint, endingPoint);
	}

    public Point startingPoint() {
		return this.startingPoint;
	}

	public void setStartingPoint(Point startingPoint) {
		this.startingPoint = startingPoint;
	}

    public Point endingPoint() {
		return this.endingPoint;
	}

	public void setEndingPoint(Point endingPoint) {
		this.endingPoint = endingPoint;
	}

    public double diameter() {
		return this.diameter;
	}

    public void setDiameter(double diameter){
        this.diameter = diameter;
    }

	public String printFigure() {
		return String.format("%s \"%s\"\n", this.getClass().getSimpleName(), this.id())
			  +"    **\n"
			  +" **    **\n"
			  +"*        *\n"
			  +"*        *\n"
			  +" **    **\n"
			  +"    **\n"
			  +String.format("StartingPoint: %5s\nEndingPoint: %5s\nCenter: %14s\nDiameter: %9.2f\n",this.startingPoint.print(), this.endingPoint.print(), this.center().print(), this.diameter)
			  +String.format("Color: %13s\nThickness: %6.2f\nContainer: %7s\nFilled: %10s\nVisible: %8s\nContains:    [%d] %s\n", this.color().rgb() != null ? "      "+this.color() : this.color(), this.thickness(), this.container(), this.filled(), this.display(), this.figures().size(), "{"+String.join(", ", this.figures().stream().map(figure -> figure.id()).collect(Collectors.toList()))+"}")
			  ;
	}

	@Override public String toString() {
        return String.format("%s [%s] Color: %s; Center: %s; Thickness: %.2f; StartingPoint: %s; EndingPoint: %s; Diameter: %.2f; %s; %s; %s; Contains [%d] Figures", this.getClass().getSimpleName(), this.id(), this.color(), this.center().print(), this.thickness(), this.startingPoint, this.endingPoint, this.diameter, this.container() ? "Container" : "!Container", this.filled() ? "Filled" : "!Filled", this.display() ? "Visible" : "!Visible", this.figures().size());
	}
}
