package structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Figure {
    private Color color;
    private Color borderColor;
    private double border;
    private Point center;
    private boolean filled;
    private double thickness;
    private boolean collide;
    private boolean visibility;
    private String id;
    private boolean container;
    private List<Figure> figures = new ArrayList<>();

    public Figure(String id, Color color, Color borderColor, double border, Point center, boolean filled, double thickness, boolean collide, boolean visibility, boolean container) {
        this.id = id;
        this.color = color;
        this.borderColor = borderColor;
        this.center = center;
        this.border = border;
        this.filled = filled;
        this.thickness = thickness;
        this.collide = collide;
        this.visibility = visibility;
        this.container = container;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String id() {
        return this.id;
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

    public void setContainer(boolean container) {
        this.container = container;
    }

    public boolean container(){
        return this.container;
    }

    public void setFigures(Figure... figures) {
        this.figures = Arrays.asList(figures);
    }

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
    }

    public void addFigure(Figure f) {
        this.figures.add(f);
    }

    public List<Figure> figures(){
        return this.figures;
    }

    public Figure[] figuresArray(){
		Figure[] figuresArray = new Figure[this.figures.size()];
		this.figures.toArray(figuresArray);
		return figuresArray;
    }

	public String printFigure() {
		return this.toString();
	}

    @Override public String toString() {
        return String.format("[%s] \ncolor: %s; \nborder: %.2f %s; \ncenter: %s; \nthickness: %.2f; \n%s; \n%s; \n%s; \n%s; \nhas %d figures", this.id, this.color, this.border, this.borderColor, this.center.print(), this.thickness, this.container ? "container" : "!container", this.filled ? "filled" : "!filled", this.collide ? "collides" : "!collides", this.visibility ? "visible" : "!visible", this.figures.size());
    }
}
