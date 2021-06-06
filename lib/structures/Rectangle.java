package structures;

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

	@Override public String toString() {
        return String.format("%s [%s] \ncolor: %s; \nborder: %.2f %s; \ncenter: %s; \nthickness: %.2f; \nwidth: %.2f; \nheight: %.2f; \nangle: %.2f; \n%s; \n%s; \n%s; \n%s; \nhas %d figures", this.getClass().getName(), this.id(), this.color(), this.border(), this.borderColor(), this.center().print(), this.thickness(), this.width, this.height, this.angle, this.container() ? "container" : "!container", this.filled() ? "filled" : "!filled", this.collide() ? "collides" : "!collides", this.visibility() ? "visible" : "!visible", this.figures().size());
	}
}
