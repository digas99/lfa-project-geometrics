package structures;

public class Triangle extends Figure{
    private Point p0;
    private Point p1;
    private Point p2;

    public Triangle(String id, Color color, Color borderColor, double border, Point center, boolean filled, double thickness, boolean collide, boolean visibility, boolean container, Point p0, Point p1, Point p2) {
		super(id, color, borderColor, border, center, filled, thickness, collide, visibility, container); 
		this.p0 = p0;
		this.p1 = p1;
		this.p2 = p2;
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

    public Point p2() {
		return this.p1;
	}

	public void setP2(Point p1) {
		this.p1 = p1;
	}
}
