package structures;

public class Angle {
	
	private int degree;
	private double rad;

	public Angle(int degree) {
		this.degree = degree;
		this.rad = Angle.rad(degree);
	}

	public Angle(double rad) {
		this.rad = rad;
		this.degree = Angle.degree(rad);
	}

	public int degree() {
		return this.degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public double rad() {
		return this.rad;
	}

	public void setRad(double rad) {
		this.rad = rad;
	}

	public static double rad(int degree) {
		return degree*(Math.PI/180);
	}

	public static int degree(double rad) {
		return (int) (rad*(180/Math.PI));
	}

	@Override public String toString() {
		return String.format("%dº (%.2f rad)", this.degree, this.rad);
	}
}
