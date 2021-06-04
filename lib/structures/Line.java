package structures;

public class Line extends Figure {

    private Point p0;
    private Point p1;
    private double angle;
    
    public Line(Color color, Color borderColor, double border, Point center, boolean filled, double thickness, boolean collide, boolean visibility, Point p0, Point p1, double angle) {
		super(color, borderColor, border, center, filled, thickness, collide, visibility);
        this.p0 = p0;
        this.p1 = p1;
        this.angle = angle;
	}

    public Point p0() {
		return this.p0;
	}

	public void setP0(Point p0) {
		this.p0 = p0;
	}

	public Point p1() {
		return this.p1;
	}

	public void setP1(Point p1) {
		this.p1 = p1;
	}

    public double angle() {
		return this.angle;
	}

    public void setAngle(double angle){
        this.angle = angle;
    }
}

