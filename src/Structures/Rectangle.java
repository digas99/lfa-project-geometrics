package Structures;

public class Rectangle extends Figure {
    
	private int width;
	private int height;
	private int angle;

	public Rectangle(Color color, Color borderColor, double border, Point center, boolean filled, double thickness, boolean collide, boolean visibility, int width, int height, int angle) {
		super(color, borderColor, border, center, filled, thickness, collide, visibility);
		this.width = width;
		this.height = height;
		this.angle = angle;
	}

	public int width() {
		return this.width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int height() {
		return this.height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

    public int angle() {
		return this.angle;
	}

    public void setAngle(int angle){
        this.angle = angle;
    }

}
