package structures;

public class Line extends Figure {

    private Point startingPoint;
    private Point endingPoint;
    private double angle;
    
    public Line(String id, Color color, Color borderColor, double border, Point center, boolean filled, double thickness, boolean collide, boolean visibility, Point startingPoint, Point endingPoint, double angle) {
		super(id, color, borderColor, border, center, filled, thickness, collide, visibility);
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.angle = angle;
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

    public double angle() {
		return this.angle;
	}

    public void setAngle(double angle){
        this.angle = angle;
    }
}

