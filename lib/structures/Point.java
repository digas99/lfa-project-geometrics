package structures;

public class Point {
    private double x;
	private double y;

	public Point(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Point(Point point) {
		this.x = point.x();
		this.y = point.y();
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

	public static Point sum(Point p0, Point p1) {
		return new Point(p0.x()+p1.x(), p0.y()+p1.y());
	}

	public static Point sub(Point p0, Point p1) {
		return new Point(p0.x()-p1.x(), p0.y()-p1.y());
	}

	public static double distance(Point p0, Point p1) {
		return Math.sqrt(Math.pow(p0.x()-p1.x(),2) + Math.pow(p0.y()-p1.y(), 2));
	}

	public double distance(Point p) {
		return Math.sqrt(Math.pow(this.x-p.x(),2) + Math.pow(this.y-p.y(), 2));
	}

	public Point oppositePoint(double length, double angle) {
		return new Point(this.x+length*Math.cos(angle), this.y+length*Math.sin(angle));
	}

	public static Point parsePoint(String p) {
		p = p.replaceAll(" ", "");
		String[] split = p.split(",");
		return new Point(Double.parseDouble(split[0]), Double.parseDouble(split[1]));
	}

	public String print() {
		return String.format("(%.2f, %.2f)", this.x, this.y);
	}

	@Override public String toString() {
		return this.x+","+this.y;
	}
}
