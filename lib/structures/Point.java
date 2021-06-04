package structures;

public class Point {
    private double x;
	private double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public double x() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double y() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
