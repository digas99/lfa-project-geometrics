package structures;

import java.util.Arrays;
import java.util.List;

public class SuperFigure {

	private List<Figure> figures;
	private String id;

	public SuperFigure(String id, List<Figure> figures) {
		this.figures = figures;
		this.id = id;
	}

	public SuperFigure(String id, Figure... figures) {
		this.figures = Arrays.asList(figures);
		this.id = id;
	}

	public List<Figure> figures() {
		return this.figures;
	}

	public Figure[] figuresArray() {
		Figure[] figuresArray = new Figure[this.figures.size()];
		this.figures.toArray(figuresArray);
		return figuresArray;
	}

	public String id() {
		return this.id;
	}

	public void setFigures(List<Figure> figures) {
		this.figures = figures;
	}

	public void setFigures(Figure... figures) {
		this.figures = Arrays.asList(figures);
	}

	public void setId(String id) {
		this.id = id;
	}
}
