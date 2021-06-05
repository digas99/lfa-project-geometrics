package structures;

public class Circle extends Figure {
    
	private Point startingPoint;
	private Point endingPoint;
	private double diameter;

	public Circle(String id, Color color, Color borderColor, double border, Point center, boolean filled, double thickness, boolean collide,boolean visibility, boolean container, double diameter) {
		super(id, color, borderColor, border, center, filled, thickness, collide, visibility, container);
		this.diameter = diameter;
	}
	
	public Circle(String id, Color color, Color borderColor, double border, Point center, boolean filled, double thickness, boolean collide, boolean visibility, boolean container, Point startingPoint, Point endingPoint) {
		super(id, color, borderColor, border, center, filled, thickness, collide, visibility, container);
		this.startingPoint = startingPoint;
		this.endingPoint = endingPoint;
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
}
