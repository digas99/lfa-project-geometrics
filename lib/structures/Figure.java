package structures;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Figure {
    private Color color;
    private Point center;
    private boolean filled;
    private double thickness;
    private boolean display;
    private String id;
    private boolean container;
    private double depth;
    private List<Figure> figures = new ArrayList<>();

    public Figure(String id) {
        this.id = id;
    }

    public Figure(String id, Color color, Point center, boolean filled, double thickness, double depth, boolean display, boolean container) {
        this.id = id;
        this.color = color;
        this.center = center;
        this.filled = filled;
        this.thickness = thickness;
        this.display = display;
        this.container = container;
        this.depth = depth;
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

    public void setDepth(double depth) {
        this.depth = depth;
    }

    public double depth() {
        return this.depth;
    }

    public void setDisplay(boolean display) {
        this.display = display;
    }

    public boolean display(){
        return this.display;
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

    public void removeFigure(String figureId) {
        int index = -1;
        for (int i=0; i<this.figures.size(); i++) {
            if (this.figures.get(i).id().equals(figureId))
                index = i;
        }
        this.figures.remove(index);
    }

    public List<Figure> figures(){
        return this.figures;
    }

    public Figure[] figuresArray(){
		Figure[] figuresArray = new Figure[this.figures.size()];
		this.figures.toArray(figuresArray);
		return figuresArray;
    }

    public int numberFigures() {
        return this.figures.size();
    }

	public String printFigure() {
		return this.toString();
	}

    public void printFamilyTree(int level) {
        for (int i=0; i<level; i++) {
            System.out.print(" ");
        }
        System.out.printf("-"+(level == 0 ? "[%s]" : "%s")+"\n", this.id);
        level++;
        for (Figure child : this.figures) {
            child.printFamilyTree(level);
        }
    }

    public void printFamily() {
        System.out.printf("%s\n", this.printFigure());
        for (Figure child : this.figures) {
            child.printFamily();
        }
    }

    @Override public String toString() {
        return String.format("[%s] \ncolor: %s; \ncenter: %s; \nthickness: %.2f; \ndepth: %.1f; \n%s; \n%s; \n%s; \nhas %d figures", this.id, this.color, this.center.print(), this.thickness, this.depth, this.container ? "container" : "!container", this.filled ? "filled" : "!filled", this.display ? "visible" : "!visible", this.figures.size());
    }
}
