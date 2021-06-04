package structures;

public class Circle extends Figure {
    
	private double startingPoint;
	private double endingPoint;
	private double diameter;

	public Circle(Color color, Color borderColor, double border, Point center, boolean filled, double thickness, boolean collide,boolean visibility,double diameter) {
		super(color, borderColor, border, center, filled, thickness, collide, visibility);
		this.diameter = diameter;
	}
	
	public Circle(Color color, Color borderColor, double border, Point center, boolean filled, double thickness, boolean collide, double startingPoint,boolean visibility,double endingPoint) {
		super(color, borderColor, border, center, filled, thickness, collide,visibility);
		this.startingPoint = startingPoint;
		this.endingPoint = endingPoint;
	}

    public double startingPoint() {
		return this.startingPoint;
	}

	public void setStartingPoint(double startingPoint) {
		this.startingPoint = startingPoint;
	}

    public double endingPoint() {
		return this.endingPoint;
	}

	public void setEndingPoint(double endingPoint) {
		this.endingPoint = endingPoint;
	}

    public double diameter() {
		return this.diameter;
	}

    public void setDiameter(double diameter){
        this.diameter = diameter;
    }
}
