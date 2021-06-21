package structures;

import java.util.stream.Collectors;

public class Triangle extends Figure{
    private Point p0;
    private Point p1;
    private Point p2;

    public Triangle(String id) {
		super(id);
	}

    public Triangle(String id, Color color, Point center, boolean filled, double thickness, double depth, boolean display, boolean container, Point p0, Point p1, Point p2) {
		super(id, color, center, filled, thickness, depth, display, container); 
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

	public String printFigure() {
		return String.format("%s \"%s\"\n", this.getClass().getSimpleName(), this.id())
			  +"    /\\\n"
			  +"   /  \\\n"
			  +"  /    \\\n"
			  +" /      \\\n"
			  +"----------\n"
			  +String.format("P0: %10s\nP1: %10s\nP2: %10s\nCenter: %14s\n",this.p0.print(), this.p1.print(), this.p2.print(), this.center().print())
			  +String.format("Color: %13s\nThickness: %6.2f\nContainer: %7s\nFilled: %10s\nVisible: %8s\nContains:    [%d] %s\n", this.color().rgb() != null ? "      "+this.color() : this.color(), this.thickness(), this.container(), this.filled(), this.display(), this.figures().size(), "{"+String.join(", ", this.figures().stream().map(figure -> figure.id()).collect(Collectors.toList()))+"}")
			  ;
	}

	@Override public String toString() {
        return String.format("%s [%s] Color: %s; Center: %s; Thickness: %.2f; P0: %s; P1: %s; P2: %s; %s; %s; %s; Contains [%d] Figures", this.getClass().getSimpleName(), this.id(), this.color(), this.center().print(), this.thickness(), this.p0, this.p1, this.p2, this.container() ? "Container" : "!Container", this.filled() ? "Filled" : "!Filled", this.display() ? "Visible" : "!Visible", this.figures().size());
	}
}
