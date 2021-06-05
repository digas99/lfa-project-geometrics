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

	public static Point stringToPoint(String p) {
		p = p.replaceAll(" ", "");
		String[] split = p.split(",");
		return new Point(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
	}

	@Override public String toString() {
		return this.x+","+this.y;
	}
}
