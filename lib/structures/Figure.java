package structures;

public abstract class Figure {
    private Color color;
    private Color borderColor;
    private double border;
    private Point center;
    private boolean filled;
    private double thickness;
    private boolean collide;
    private boolean visibility;

    public Figure(Color color, Color borderColor, double border, Point center, boolean filled, double thickness, boolean collide, boolean visibility) {
        this.color = color;
        this.borderColor = borderColor;
        this.center = center;
        this.border = border;
        this.filled = filled;
        this.thickness = thickness;
        this.collide = collide;
        this.visibility = visibility;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Color color() {
        return this.color;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color borderColor() {
        return this.borderColor;
    }

    public void setBorder(double border) {
        this.border = border;
    }

    public double border() {
        return this.border;
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public Point center() {
        return this.center;
    }

    public void setFilled(boolean filled) {
        this.filled = filled;
    }

    public boolean filled() {
        return this.filled;
    }

    public void setThickness(double thickness) {
        this.thickness = thickness;
    }

    public double thickness() {
        return this.thickness;
    }

    public void setCollide(boolean collide) {
        this.collide = collide;
    }

    public boolean collide(){
        return this.collide;
    }

    public void setVisibility(boolean visibility) {
        this.visibility = visibility;
    }

    public boolean visibility(){
        return this.visibility;
    }
}
