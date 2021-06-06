package structures;

import java.util.stream.Collectors;

public class Circle extends Figure {
    
	private Point startingPoint;
	private Point endingPoint;
	private double diameter;

	public Circle(String id, Color color, Color borderColor, double border, Point center, boolean filled, double thickness, boolean collide,boolean visibility, boolean container, double diameter) {
		super(id, color, borderColor, border, center, filled, thickness, collide, visibility, container);
		this.diameter = diameter;
		this.startingPoint = new Point(center.x() - diameter/2, 0);
		this.endingPoint = new Point(center.x() + diameter/2, 0);
	}
	
	public Circle(String id, Color color, Color borderColor, double border, Point center, boolean filled, double thickness, boolean collide, boolean visibility, boolean container, Point startingPoint, Point endingPoint) {
		super(id, color, borderColor, border, center, filled, thickness, collide, visibility, container);
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
			  +"         **\n"
			  +"      **    **\n"
			  +"     *        *\n"
			  +"     *        *\n"
			  +"      **    **\n"
			  +"         **\n"
			  +this.startingPoint.print()+"   "+this.endingPoint.print()+"\n"
			  +String.format("Center: %14s\nDiameter: %9.2f\n", this.center().print(), this.diameter)
			  +String.format("Color: %13s\nBorder: %9.2f %s\nThickness: %6.2f\nContainer: %7s\nFilled: %10s\nCollides: %7s\nVisible: %8s\nContains:    [%d] %s\n", this.color().rgb() != null ? "      "+this.color() : this.color(), this.border(), this.borderColor(), this.thickness(), this.container(), this.filled(), this.collide(), this.visibility(), this.figures().size(), "{"+String.join(", ", this.figures().stream().map(figure -> figure.id()).collect(Collectors.toList()))+"}")
			  ;
	}

	@Override public String toString() {
        return String.format("%s [%s] \ncolor: %s; \nborder: %.2f %s; \ncenter: %s; \nthickness: %.2f; \nsartingPoint: %s; \nendingPoint: %s; \ndiameter: %.2f; \n%s; \n%s; \n%s; \n%s; \nhas %d figures", this.getClass().getName(), this.id(), this.color(), this.border(), this.borderColor(), this.center().print(), this.thickness(), this.startingPoint.print(), this.endingPoint.print(), this.diameter, this.container() ? "container" : "!container", this.filled() ? "filled" : "!filled", this.collide() ? "collides" : "!collides", this.visibility() ? "visible" : "!visible", this.figures().size());
	}
}
