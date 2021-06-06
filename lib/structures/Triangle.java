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
		return this.p2;
	}

	public void setP2(Point p2) {
		this.p2 = p2;
	}

	@Override public String toString() {
        return String.format("%s [%s] \ncolor: %s; \nborder: %.2f %s; \ncenter: %s; \nthickness: %.2f; \np0: %s; \np1: %s; \np2: %s; \n%s; \n%s; \n%s; \n%s; \nhas %d figures", this.getClass().getName(), this.id(), this.color(), this.border(), this.borderColor(), this.center().print(), this.thickness(), this.p0.print(), this.p1.print(), this.p2.print(), this.container() ? "container" : "!container", this.filled() ? "filled" : "!filled", this.collide() ? "collides" : "!collides", this.visibility() ? "visible" : "!visible", this.figures().size());
	}
}
