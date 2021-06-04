package structures;

public class Rectangle extends Figure {
    
	private double width;
	private double height;
	private double angle;

	public Rectangle(String id, Color color, Color borderColor, double border, Point center, boolean filled, double thickness, boolean collide, boolean visibility, double width, double height, double angle) {
		super(id, color, borderColor, border, center, filled, thickness, collide, visibility);
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

}
