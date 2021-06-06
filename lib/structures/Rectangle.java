package structures;

import java.util.Arrays;
import java.util.stream.Collectors;

public class Rectangle extends Figure {
    
	private double width;
	private double height;
	private double angle;

	public Rectangle(String id, Color color, Color borderColor, double border, Point center, boolean filled, double thickness, boolean collide, boolean visibility, boolean container, double width, double height, double angle) {
		super(id, color, borderColor, border, center, filled, thickness, collide, visibility, container);
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

    public double angle() {
		return this.angle;
	}

    public void setAngle(double angle){
        this.angle = angle;
    }

	public String printFigure() {
		return String.format("%s \"%s\"\n", this.getClass().getSimpleName(), this.id())
			  +"     -------------------\n"
			  +"     |                 |\n"
			  +"     |                 |"+this.height+"\n"
			  +"     |                 |\n"
			  +"     -------------------\n"
			  +"            "+this.width+"\n"
			  +String.format("Center: %14s\nAngle: %10.2f\n", this.center().print(), this.angle)
			  +String.format("Color: %13s\nBorder: %9.2f %s\nThickness: %6.2f\nContainer: %7s\nFilled: %10s\nCollides: %7s\nVisible: %8s\nContains:    [%d] %s\n", this.color().rgb() != null ? "      "+this.color() : this.color(), this.border(), this.borderColor(), this.thickness(), this.container(), this.filled(), this.collide(), this.visibility(), this.figures().size(), "{"+String.join(", ", this.figures().stream().map(figure -> figure.id()).collect(Collectors.toList()))+"}")
			  ;
	}

	@Override public String toString() {
        return String.format("%s [%s] \nColor: %s; \nBorder: %.2f %s; \nCenter: %s; \nThickness: %.2f; \nWidth: %.2f; \nHeight: %.2f; \nAngle: %.2f; \n%s; \n%s; \n%s; \n%s; \nContains [%d] Figures\n", this.getClass().getName(), this.id(), this.color(), this.border(), this.borderColor(), this.center().print(), this.thickness(), this.width, this.height, this.angle, this.container() ? "Container" : "!Container", this.filled() ? "Filled" : "!Filled", this.collide() ? "Collides" : "!Collides", this.visibility() ? "Visible" : "!Visible", this.figures().size());
	}
}
