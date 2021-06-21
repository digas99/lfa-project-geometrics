package structures;

import java.util.stream.Collectors;

public class Rectangle extends Figure {
    
	private double width;
	private double height;
	private Angle angle;

    public Rectangle(String id) {
		super(id);
	}

	public Rectangle(String id, Color color, Point center, boolean filled, double thickness, double depth, boolean display, boolean container, double width, double height, Angle angle) {
		super(id, color, center, filled, thickness, depth, display, container);
		this.width = width;
		this.height = height;
		this.angle = angle;
	}

	public double width() {
		return this.width;
	}

	public void setWidth(double width) {
		this.width = width;
	}

	public double height() {
		return this.height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

    public Angle angle() {
		return this.angle;
	}

    public void setAngle(Angle angle){
        this.angle = angle;
    }

	public String printFigure() {
		return String.format("%s \"%s\"\n", this.getClass().getSimpleName(), this.id())
			  +"-------------------\n"
			  +"|                 |\n"
			  +"|                 |\n"
			  +"|                 |\n"
			  +"-------------------\n"
			  +String.format("Width: %10.2f\nHeight: %10.2f\nCenter: %14s\nAngle: %9s\n",this.width, this.height, this.center().print(), this.angle)
			  +String.format("Color: %13s\nThickness: %6.2f\nContainer: %7s\nFilled: %10s\nVisible: %8s\nContains:    [%d] %s\n", this.color().rgb() != null ? "      "+this.color() : this.color(), this.thickness(), this.container(), this.filled(), this.display(), this.figures().size(), "{"+String.join(", ", this.figures().stream().map(figure -> figure.id()).collect(Collectors.toList()))+"}")
			  ;
	}

	@Override public String toString() {
        return String.format("%s [%s] Color: %s; Border: %.2f %s; Center: %s; Thickness: %.2f; Width: %.2f; Height: %.2f; Angle: %s; %s; %s; %s; Contains [%d] Figures", this.getClass().getSimpleName(), this.id(), this.color(), this.center().print(), this.thickness(), this.width, this.height, this.angle, this.container() ? "Container" : "!Container", this.filled() ? "Filled" : "!Filled", this.display() ? "Visible" : "!Visible", this.figures().size());
	}
}
